package com.momiji.api.neural.generation.image

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
    prefix = "momiji.clients.neural.image-generation",
    name = ["url"]
)
class ImageGenerationClientConfiguration {

    @Bean
    fun imageGenerationClientService(
        client: ImageGenerationClient,
        @Value("\${momiji.clients.neural.image-generation.blocking.timeout-millis:300000}")
        timeout: Long,
        @Value("\${momiji.clients.neural.image-generation.blocking.wait-millis:1000}")
        waitBeforeRequest: Long,
        @Value("\${momiji.clients.neural.image-generation.blocking.async:false}")
        runAsync: Boolean,
    ): ImageGenerationClientService {
        return ImageGenerationClientServiceImpl(
            client = client,
            timeout = timeout,
            waitBeforeRequest = waitBeforeRequest,
            runAsync = runAsync,
        )
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.neural.image-generation",
        name = ["stub"],
        havingValue = "true"
    )
    fun imageGenerationClientStub(objectMapper: ObjectMapper): ImageGenerationClient {
        return ImageGenerationClientStub(objectMapper, ConcurrentHashMap())
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.neural.image-generation",
        name = ["stub"],
        havingValue = "false",
        matchIfMissing = true
    )
    fun imageGenerationClient(
        @Value("\${momiji.clients.neural.image-generation.url}") url: String,
        contract: Contract,
        decoder: Decoder,
        encoder: Encoder,
        objectMapper: ObjectMapper,
    ): ImageGenerationClient {
        val client = Feign.builder()
            .encoder(encoder)
            .decoder(decoder)
            .contract(contract)
            .target(ImageGenerationClient::class.java, url)

        return ImageGenerationClientImpl(
            client = client,
            objectMapper = objectMapper,
        )
    }
}
