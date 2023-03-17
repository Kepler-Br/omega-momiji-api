package com.momiji.api.gateway.outbound

import feign.Contract
import feign.Feign
import feign.codec.Decoder
import feign.codec.Encoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(
    prefix = "momiji.clients.gateway",
    name = ["url"]
)
class GatewayOutboundClientConfiguration {

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.gateway",
        name = ["stub"],
        havingValue = "true"
    )
    fun gatewayMessageSenderControllerStub(): GatewayMessageSenderController {
        return GatewayMessageSenderControllerStub()
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.gateway",
        name = ["stub"],
        havingValue = "false",
        matchIfMissing = true
    )
    fun gatewayMessageSenderClient(
        @Value("\${momiji.clients.gateway.url}") url: String,
        contract: Contract,
        decoder: Decoder,
        encoder: Encoder,
    ): GatewayMessageSenderController {
        return Feign.builder()
            .encoder(encoder)
            .decoder(decoder)
            .contract(contract)
            .target(GatewayMessageSenderController::class.java, "$url/outbound")
    }
}
