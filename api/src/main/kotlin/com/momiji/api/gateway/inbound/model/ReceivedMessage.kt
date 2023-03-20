package com.momiji.api.gateway.inbound.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.momiji.api.gateway.inbound.model.enumerator.MediaType
import com.momiji.api.gateway.inbound.model.enumerator.MessageType


data class ReceivedMessage(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("reply_to")
    val replyTo: String,
    @JsonProperty("author")
    val author: ReceivedUser,
    @JsonProperty("chat")
    val chat: ReceivedChat,
    @JsonProperty("frontend")
    val frontend: String,
    @JsonProperty("text")
    val text: String?,
    @JsonProperty("type")
    val type: MessageType,
    @JsonProperty("action_info")
    val actionInfo: ActionInfo?,
    @JsonProperty("media_type")
    val mediaType: MediaType?,
    @JsonProperty("media_bytes")
    val mediaBytes: String?,
)
