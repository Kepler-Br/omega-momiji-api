package com.momiji.api.neural.generation.text

import com.momiji.api.neural.common.AbstractClientService
import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.generation.text.model.GenerationParams
import com.momiji.api.neural.generation.text.model.HistoryRequest
import com.momiji.api.neural.generation.text.model.HistoryResponse
import com.momiji.api.neural.generation.text.model.Message
import com.momiji.api.neural.generation.text.model.MessageType
import java.util.UUID

class TextGenerationClientServiceImpl(
    private val client: TextGenerationClient,
    timeout: Long,
    waitBeforeRequest: Long,
    runAsync: Boolean,
) : TextGenerationClientService, AbstractClientService(timeout, waitBeforeRequest, runAsync) {
    override fun requestGenerationFromHistoryBlocking(
        generationParams: GenerationParams,
        messageType: MessageType,
        prompt: String?,
        promptAuthor: String?,
        replyToMessageId: Int?,
        history: List<Message>
    ): List<Message> {
        val scheduled = requestGenerationFromHistory(
            generationParams = generationParams,
            messageType = messageType,
            prompt = prompt,
            promptAuthor = promptAuthor,
            replyToMessageId = replyToMessageId,
            history = history,
        )

        return requestUntilTimeout(
            taskId = scheduled.taskId!!,
            ::getGeneratedFromHistory
        ).messages!!
    }

    override fun requestGenerationFromHistory(
        generationParams: GenerationParams,
        messageType: MessageType,
        prompt: String?,
        promptAuthor: String?,
        replyToMessageId: Int?,
        history: List<Message>,
    ): TaskScheduledResponse {
        return client.requestGenerationFromHistory(
            HistoryRequest(
                generationParams = generationParams,
                messageType = messageType,
                prompt = prompt,
                promptAuthor = promptAuthor,
                replyToMessageId = replyToMessageId,
                history = history,
            )
        )
    }

    override fun getGeneratedFromHistory(
        taskId: UUID,
        async: Boolean,
    ): HistoryResponse {
        return client.getGeneratedFromHistory(
            taskId = taskId, async = async
        )
    }
}
