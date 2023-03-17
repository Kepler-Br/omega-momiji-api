package com.momiji.api.neural.text.model

import com.fasterxml.jackson.annotation.JsonProperty

data class HistoryResponse(
    @JsonProperty("messages")
    val messages: List<Message>,
)
