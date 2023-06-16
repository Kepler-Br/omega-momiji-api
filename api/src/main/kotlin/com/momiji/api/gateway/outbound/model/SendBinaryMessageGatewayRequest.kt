package com.momiji.api.gateway.outbound.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SendBinaryMessageGatewayRequest(
    @JsonProperty("frontend")
    val frontend: String,
    @JsonProperty("data")
    val data: String,
    // Should be a native chat id
    @JsonProperty("chat_id")
    val chatId: String,
    // Should be a native message id
    @JsonProperty("reply_to_message_id")
    val replyToMessageId: String? = null,
)
