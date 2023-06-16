package com.momiji.api.frontend.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.momiji.api.common.model.BaseResponse
import com.momiji.api.common.model.ResponseStatus

data class ChatAdminsFrontendResponse(
    @JsonProperty("status")
    override val status: ResponseStatus,
    @JsonProperty("error_message")
    override val errorMessage: String? = null,
    @JsonProperty("admin_ids")
    val adminIds: List<String>?,
) : BaseResponse
