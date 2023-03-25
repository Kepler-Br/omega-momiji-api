package com.momiji.api.neural.text.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.momiji.api.common.model.ResponseStatus

data class HistoryResponse(
    @JsonProperty("status")
    val status: ResponseStatus,
    @JsonProperty("error_message")
    val errorMessage: String?,
    @JsonProperty("messages")
    val messages: List<Message>?,
)
