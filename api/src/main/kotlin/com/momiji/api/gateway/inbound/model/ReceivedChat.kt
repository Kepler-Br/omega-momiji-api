package com.momiji.api.gateway.inbound.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.momiji.api.gateway.inbound.model.enumerator.ChatType

data class ReceivedChat(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("type")
    var type: ChatType,
)
