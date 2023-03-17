package com.momiji.api.gateway.outbound.model

import com.fasterxml.jackson.annotation.JsonProperty

data class FrontendNamesResponse(
    @JsonProperty("names")
    val names: List<String>,
)
