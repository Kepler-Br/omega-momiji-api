package com.momiji.api.neural.text.model

import com.fasterxml.jackson.annotation.JsonProperty

data class RawResponse(
    @JsonProperty("text")
    val text: String,
)
