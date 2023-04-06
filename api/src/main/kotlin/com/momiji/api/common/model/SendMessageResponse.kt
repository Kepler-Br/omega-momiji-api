package com.momiji.api.common.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SendMessageResponse(
    @JsonProperty("status")
    override val status: ResponseStatus,
    @JsonProperty("error_message")
    override val errorMessage: String? = null,
    @JsonProperty("message_id")
    val messageId: String? = null,
) : BaseResponse
