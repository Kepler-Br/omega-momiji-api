package com.momiji.api.neural.caption.speech

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
    prefix = "momiji.clients.neural.speech-caption",
    name = ["url"]
)
class SpeechCaptionClientConfiguration {

    @Bean
    @ConditionalOnBean(SpeechCaptionClient::class)
    fun speechCaptionClientServiceImpl(
        client: SpeechCaptionClient,
        @Value("\${momiji.clients.neural.speech-caption.blocking.timeout-millis:300000}")
        timeout: Long,
        @Value("\${momiji.clients.neural.speech-caption.blocking.wait-millis:1000}")
        waitBeforeRequest: Long,
        @Value("\${momiji.clients.neural.speech-caption.blocking.async:false}")
        runAsync: Boolean,
    ): SpeechCaptionClientServiceImpl {
        return SpeechCaptionClientServiceImpl(
            client = client,
            timeout = timeout,
            waitBeforeRequest = waitBeforeRequest,
            runAsync = runAsync,
        )
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.neural.speech-caption",
        name = ["stub"],
        havingValue = "true"
    )
    fun speechCaptionClientStub(objectMapper: ObjectMapper): SpeechCaptionClient {
        return SpeechCaptionClientStub(objectMapper, ConcurrentHashMap())
    }

    @Bean
    @ConditionalOnProperty(
        prefix = "momiji.clients.neural.speech-caption",
        name = ["stub"],
        havingValue = "false",
        matchIfMissing = true
    )
    fun speechCaptionClient(
        @Value("\${momiji.clients.neural.speech-caption.url}") url: String,
        contract: Contract,
        decoder: Decoder,
        encoder: Encoder,
        objectMapper: ObjectMapper,
    ): SpeechCaptionClient {
        val client = Feign.builder()
            .encoder(encoder)
            .decoder(decoder)
            .contract(contract)
            .target(SpeechCaptionClient::class.java, url)

        return SpeechCaptionClientImpl(
            client = client,
            objectMapper = objectMapper,
        )
    }
}
