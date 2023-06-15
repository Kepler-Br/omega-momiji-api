package com.momiji.api.bot

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
    prefix = "momiji.clients.bots",
    name = ["urls[0]"]
)
@EnableConfigurationProperties(ConfigurationProperties::class)
class BotClientListConfiguration {

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.bots",
        name = ["stub"],
        havingValue = "true"
    )
    fun botReceiveMessageClientStub(): BotReceiveMessageClient {
        return BotReceiveMessageClientStub()
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.bots",
        name = ["stub"],
        havingValue = "false",
        matchIfMissing = true
    )
    fun botReceiveMessageClientContainer(
        config: ConfigurationProperties,
        contract: Contract,
        decoder: Decoder,
        encoder: Encoder,
    ): BotReceiveMessageClient {
        val clients = config.urls.map {
            Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .target(BotReceiveMessageClient::class.java, it)
        }

        return BotReceiveMessageClientContainer(clients)
    }
}
