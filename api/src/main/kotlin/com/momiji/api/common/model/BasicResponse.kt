package com.momiji.api.common.model

import com.fasterxml.jackson.annotation.JsonProperty

data class BasicResponse(
    @JsonProperty("status")
    val status: ResponseStatus,
    @JsonProperty("error_message")
    val errorMessage: String? = null,
)
