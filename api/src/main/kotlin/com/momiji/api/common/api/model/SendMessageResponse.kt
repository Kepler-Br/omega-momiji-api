package com.momiji.api.common.api.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SendMessageResponse(
    @JsonProperty("error_message")
    val errorMessage: String?,
    @JsonProperty("message_id")
    val messageId: String?,
)
