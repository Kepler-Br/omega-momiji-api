package com.momiji.api.gateway.inbound.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.momiji.api.gateway.inbound.api.model.enumerator.ActionType

data class ActionInfo(
    @JsonProperty("action_type")
    val actionType: ActionType,
    @JsonProperty("related_user")
    val relatedUser: ReceivedUser,
)
