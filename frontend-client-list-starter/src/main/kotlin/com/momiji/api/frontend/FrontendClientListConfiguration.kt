package com.momiji.api.frontend

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
            FrontendControllerStub()
        }

        return DefaultFrontendContainer(clients = clients)
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.frontends",
        name = ["stub"],
        havingValue = "false",
        matchIfMissing = true
    )
    fun frontendControllerContainer(
        properties: FrontendsConfigurationProperties,
        contract: Contract,
        decoder: Decoder,
        encoder: Encoder,
    ): FrontendContainer {
        val clients = properties.urls.mapValues {
            Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .target(FrontendController::class.java, it.value)
        }

        return DefaultFrontendContainer(clients = clients)
    }
}
