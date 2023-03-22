package com.momiji.api.neural.text

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Contract
import feign.Feign
import feign.codec.Decoder
import feign.codec.Encoder
import java.util.concurrent.ConcurrentHashMap
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(
    prefix = "momiji.clients.neural.text",
    name = ["url"]
)
class NeuralTextClientConfiguration {

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.neural.text",
        name = ["stub"],
        havingValue = "true"
    )
    fun textGenerationClientStub(objectMapper: ObjectMapper): TextGenerationController {
        return TextGenerationControllerStub(objectMapper, ConcurrentHashMap())
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.neural.text",
        name = ["stub"],
        havingValue = "false",
        matchIfMissing = true
    )
    fun textGenerationClient(
        @Value("\${momiji.clients.neural.text.url}") url: String,
        contract: Contract,
        decoder: Decoder,
        encoder: Encoder,
    ): TextGenerationController {
        return Feign.builder()
            .encoder(encoder)
            .decoder(decoder)
            .contract(contract)
            .target(TextGenerationController::class.java, url)
    }
}
