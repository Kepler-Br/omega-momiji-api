package com.momiji.api.neural.text.model

import com.fasterxml.jackson.annotation.JsonProperty

data class RawRequest(
    @JsonProperty("prompt")
    val prompt: String,
    @JsonProperty("generation_params")
    val generationParams: GenerationParams,
)
