package com.momiji.api.gateway.outbound.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.momiji.api.common.model.BaseResponse
import com.momiji.api.common.model.ResponseStatus

data class FrontendNamesResponse(
    @JsonProperty("status")
    override val status: ResponseStatus,
    @JsonProperty("error_message")
    override val errorMessage: String? = null,
    @JsonProperty("names")
    val names: List<String>,
) : BaseResponse
