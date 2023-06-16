package com.momiji.api.neural.caption.image

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Contract
import feign.Feign
import feign.codec.Decoder
import feign.codec.Encoder
import java.util.concurrent.ConcurrentHashMap
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(
    prefix = "momiji.clients.neural.image-caption",
    name = ["url"]
)
class ImageCaptionClientConfiguration {

    @Bean
    @ConditionalOnBean(ImageCaptionClient::class)
    fun imageCaptionClientService(
        imageCaptionClient: ImageCaptionClient,
        @Value("\${momiji.clients.neural.image-caption.blocking.timeout-millis:300000}")
        timeout: Long,
        @Value("\${momiji.clients.neural.image-caption.blocking.wait-millis:1000}")
        waitBeforeRequest: Long,
        @Value("\${momiji.clients.neural.image-caption.blocking.async:false}")
        runAsync: Boolean,
    ): ImageCaptionClientService {
        return ImageCaptionClientServiceImpl(
            imageCaptionClient = imageCaptionClient,
            timeout = timeout,
            waitBeforeRequest = waitBeforeRequest,
            runAsync = runAsync,
        )
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.neural.image-caption",
        name = ["stub"],
        havingValue = "true"
    )
    fun imageCaptionClientStub(objectMapper: ObjectMapper): ImageCaptionClient {
        return ImageCaptionClientStub(objectMapper, ConcurrentHashMap())
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.neural.image-caption",
        name = ["stub"],
        havingValue = "false",
        matchIfMissing = true
    )
    fun imageCaptionClient(
        @Value("\${momiji.clients.neural.image-caption.url}") url: String,
        contract: Contract,
        decoder: Decoder,
        encoder: Encoder,
        objectMapper: ObjectMapper,
    ): ImageCaptionClient {
        val client = Feign.builder()
            .encoder(encoder)
            .decoder(decoder)
            .contract(contract)
            .target(ImageCaptionClient::class.java, url)

        return ImageCaptionClientImpl(
            client = client,
            objectMapper = objectMapper,
        )
    }
}
