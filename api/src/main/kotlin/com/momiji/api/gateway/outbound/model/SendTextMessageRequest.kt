package com.momiji.api.gateway.outbound.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SendTextMessageRequest(
    @JsonProperty("frontend")
    val frontend: String,
    @JsonProperty("text")
    val text: String,
    // Should be a native chat id
    @JsonProperty("chat_id")
    val chatId: String,
    // Should be a native message id
    @JsonProperty("reply_to_message_id")
    val replyToMessageId: String? = null,
)
