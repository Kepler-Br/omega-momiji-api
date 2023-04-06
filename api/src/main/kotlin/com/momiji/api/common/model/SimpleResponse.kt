package com.momiji.api.common.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SimpleResponse(
    @JsonProperty("status")
    override val status: ResponseStatus,
    @JsonProperty("error_message")
    override val errorMessage: String? = null,
) : BaseResponse
