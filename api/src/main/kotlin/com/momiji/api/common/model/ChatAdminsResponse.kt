package com.momiji.api.common.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatAdminsResponse(
    @JsonProperty("status")
    override val status: ResponseStatus,
    @JsonProperty("error_message")
    override val errorMessage: String? = null,
    @JsonProperty("admin_ids")
    val adminIds: Set<String>,
) : BaseResponse
