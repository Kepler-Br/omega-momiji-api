package com.momiji.api.neural.generation.text.model

import com.fasterxml.jackson.annotation.JsonProperty

data class HistoryRequest(
    @JsonProperty("generation_params")
    val generationParams: GenerationParams,
    @JsonProperty("message_type")
    val messageType: MessageType? = null,
    @JsonProperty("prompt")
    val prompt: String? = null,
    @JsonProperty("prompt_author")
    val promptAuthor: String? = null,
    @JsonProperty("reply_to_message_id")
    val replyToMessageId: Int? = null,
    @JsonProperty("history")
    val history: List<Message> = emptyList(),
)
