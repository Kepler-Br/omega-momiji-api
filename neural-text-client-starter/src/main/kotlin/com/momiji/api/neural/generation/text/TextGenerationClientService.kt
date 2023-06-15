package com.momiji.api.neural.generation.text

import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.generation.text.model.GenerationParams
import com.momiji.api.neural.generation.text.model.HistoryResponse
import com.momiji.api.neural.generation.text.model.Message
import com.momiji.api.neural.generation.text.model.MessageType
import java.util.UUID

interface TextGenerationClientService {
    fun requestGenerationFromHistory(
        generationParams: GenerationParams,
        messageType: MessageType,
        prompt: String? = null,
        promptAuthor: String? = null,
        replyToMessageId: Int? = null,
        history: List<Message> = emptyList(),
    ): TaskScheduledResponse

    fun getGeneratedFromHistory(
        taskId: UUID,
        async: Boolean,
    ): HistoryResponse
}
