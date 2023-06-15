package com.momiji.api.frontend

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Contract
import feign.Feign
import feign.codec.Decoder
import feign.codec.Encoder
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(
    prefix = "momiji.clients.frontends",
    name = ["enabled"],
    havingValue = "true"
)
@EnableConfigurationProperties(FrontendsConfigurationProperties::class)
class FrontendClientListConfiguration {

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.frontends",
        name = ["stub"],
        havingValue = "true"
    )
    fun frontendContainerWithStub(
        properties: FrontendsConfigurationProperties,
    ): FrontendContainer {
        val clients = properties.urls.mapValues {
            FrontendClientStub()
        }

        return FrontendContainerImpl(clients = clients)
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.frontends",
        name = ["stub"],
        havingValue = "false",
        matchIfMissing = true
    )
    fun frontendClientContainer(
        properties: FrontendsConfigurationProperties,
        contract: Contract,
        decoder: Decoder,
        encoder: Encoder,
        objectMapper: ObjectMapper,
    ): FrontendContainer {
        val clients = properties.urls.mapValues {
            val client = Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .target(FrontendClient::class.java, it.value)

            FrontendClientWrapper(
                frontendClient = client,
                objectMapper = objectMapper,
            )
        }

        return FrontendContainerImpl(clients = clients)
    }
}
