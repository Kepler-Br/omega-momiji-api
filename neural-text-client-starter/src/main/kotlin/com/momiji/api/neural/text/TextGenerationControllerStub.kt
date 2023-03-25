package com.momiji.api.neural.text

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.BasicResponse
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.neural.text.model.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class TextGenerationControllerStub(
    private val objectMapper: ObjectMapper,
    private val generateFromHistoryRequests: ConcurrentHashMap<UUID, HistoryRequest>,
) : TextGenerationController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun requestGenerationFromHistory(
        content: HistoryRequest,
        promptId: UUID
    ): BasicResponse {
        val requestJsonText = objectMapper.writeValueAsString(content)

        logger.debug(
            "Requesting text generation:\n$requestJsonText"
        )

        generateFromHistoryRequests[promptId] = content

        return BasicResponse(
            status = ResponseStatus.OK,
        )
    }

    override fun getGeneratedFromHistory(promptId: UUID): HistoryResponse {
        if (!generateFromHistoryRequests.containsKey(promptId)) {
            return HistoryResponse(
                status = ResponseStatus.NOT_FOUND,
                errorMessage = null,
                messages = emptyList()
            )
        }

        val request = generateFromHistoryRequests.remove(promptId)!!

        return HistoryResponse(
            status = ResponseStatus.OK,
            errorMessage = null,
            messages = listOf(
                Message(
                    messageType = MessageType.TEXT,
                    content = "Stub",
                    author = request.promptAuthor ?: "Stubber",
                    messageId = "1",
                )
            )
        )
    }

    override fun requestGenerationFromRaw(content: RawRequest, promptId: UUID): BasicResponse {
        val requestJsonText = objectMapper.writeValueAsString(content)

        logger.debug(
            "Requesting raw text generation:\n$requestJsonText"
        )

        return BasicResponse(
            status = ResponseStatus.OK,
        )
    }

    override fun getGeneratedFromRaw(promptId: UUID): RawResponse {
        return RawResponse(
            text = "Stub"
        )
    }
}
