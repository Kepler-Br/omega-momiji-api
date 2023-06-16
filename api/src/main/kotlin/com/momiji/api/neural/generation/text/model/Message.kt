package com.momiji.api.neural.generation.text.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Message(
    @JsonProperty("message_type")
    val messageType: MessageType,
    @JsonProperty("content")
    val content: String?,
    @JsonProperty("author")
    val author: String,
    @JsonProperty("message_id")
    val messageId: String,
    @JsonProperty("reply_to_message_id")
    val replyToMessageId: String? = null,
    @JsonProperty("emoji")
    val emoji: String? = null,
)
