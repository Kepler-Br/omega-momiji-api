package com.momiji.api.neural.text

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.text.model.HistoryRequest
import com.momiji.api.neural.text.model.HistoryResponse
import com.momiji.api.neural.text.model.Message
import com.momiji.api.neural.text.model.MessageType
import com.momiji.api.neural.text.model.RawRequest
import com.momiji.api.neural.text.model.RawResponse
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TextGenerationControllerStub(
    private val objectMapper: ObjectMapper,
    private val generateFromHistoryRequests: ConcurrentHashMap<UUID, HistoryRequest>,
) : TextGenerationController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun requestGenerationFromHistory(
        content: HistoryRequest,
    ): TaskScheduledResponse {
        val requestJsonText = objectMapper.writeValueAsString(content)

        logger.debug(
            "Requesting text generation:\n$requestJsonText"
        )

        val promptId = UUID.randomUUID()
        generateFromHistoryRequests[promptId] = content

        return TaskScheduledResponse(
            status = ResponseStatus.OK,
            taskId = promptId
        )
    }

    override fun getGeneratedFromHistory(promptId: UUID, async: Boolean): HistoryResponse {
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

    override fun requestGenerationFromRaw(
        content: RawRequest
    ): TaskScheduledResponse {
        val requestJsonText = objectMapper.writeValueAsString(content)

        logger.debug(
            "Requesting raw text generation:\n$requestJsonText"
        )
        val promptId = UUID.randomUUID()

        return TaskScheduledResponse(
            status = ResponseStatus.OK,
            taskId = promptId
        )
    }

    override fun getGeneratedFromRaw(promptId: UUID, async: Boolean): RawResponse {
        return RawResponse(
            text = "Stub"
        )
    }
}
