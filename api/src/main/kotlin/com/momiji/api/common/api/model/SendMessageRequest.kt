package com.momiji.api.common.api.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SendMessageRequest(
    @JsonProperty("text")
    val text: String,
    @JsonProperty("reply_to")
    val replyTo: String? = null,
    @JsonProperty("chat_id")
    val chatId: String,
)
