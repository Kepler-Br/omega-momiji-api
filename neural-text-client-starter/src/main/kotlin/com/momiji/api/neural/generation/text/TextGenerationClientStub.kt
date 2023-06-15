package com.momiji.api.neural.generation.text

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.generation.text.model.HistoryRequest
import com.momiji.api.neural.generation.text.model.HistoryResponse
import com.momiji.api.neural.generation.text.model.Message
import com.momiji.api.neural.generation.text.model.MessageType
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TextGenerationClientStub(
    private val objectMapper: ObjectMapper,
    private val requests: ConcurrentHashMap<UUID, HistoryRequest>,
) : TextGenerationClient {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun requestGenerationFromHistory(
        content: HistoryRequest,
    ): TaskScheduledResponse {
        val requestJsonText = objectMapper.writeValueAsString(content)

        logger.debug(
            "Requesting text generation:\n$requestJsonText"
        )

        val promptId = UUID.randomUUID()
        requests[promptId] = content

        return TaskScheduledResponse(
            status = ResponseStatus.OK,
            taskId = promptId
        )
    }

    override fun getGeneratedFromHistory(taskId: UUID, async: Boolean): HistoryResponse {
        if (!requests.containsKey(taskId)) {
            return HistoryResponse(
                status = ResponseStatus.NOT_FOUND,
                errorMessage = null,
                messages = null
            )
        }

        val request = requests.remove(taskId)!!

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
}
