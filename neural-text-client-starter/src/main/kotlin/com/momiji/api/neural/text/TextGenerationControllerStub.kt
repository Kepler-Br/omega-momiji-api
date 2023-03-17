package com.momiji.api.neural.text

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.neural.text.model.HistoryRequest
import com.momiji.api.neural.text.model.HistoryResponse
import com.momiji.api.neural.text.model.Message
import com.momiji.api.neural.text.model.MessageType
import com.momiji.api.neural.text.model.RawRequest
import com.momiji.api.neural.text.model.RawResponse
import java.util.UUID
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TextGenerationControllerStub(
    private val objectMapper: ObjectMapper,
) : TextGenerationController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun generateFromHistory(content: HistoryRequest, promptId: UUID): HistoryResponse {
        val requestJsonText = objectMapper.writeValueAsString(content)

        logger.debug(
            "Requesting text generation:\n$requestJsonText"
        )

        return HistoryResponse(
            messages = listOf(
                Message(
                    messageType = MessageType.TEXT,
                    content = "Stub",
                    author = "Stubber",
                    messageId = "Stub",
                )
            )
        )
    }

    override fun generateFromRaw(content: RawRequest, promptId: UUID): RawResponse {
        val requestJsonText = objectMapper.writeValueAsString(content)

        logger.debug(
            "Requesting raw text generation:\n$requestJsonText"
        )

        return RawResponse(
            text = "Stub"
        )
    }
}