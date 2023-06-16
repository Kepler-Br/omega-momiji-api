package com.momiji.api.frontend.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SendMessageFrontendRequest(
    @JsonProperty("text")
    val text: String,
    @JsonProperty("reply_to")
    val replyTo: String? = null,
    @JsonProperty("chat_id")
    val chatId: String,
)
