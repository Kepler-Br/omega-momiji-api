package com.momiji.api.neural.generation.image.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.momiji.api.common.model.BaseResponse
import com.momiji.api.common.model.ResponseStatus

data class ImageGenerationResponse(
    @JsonProperty("status")
    override val status: ResponseStatus,
    @JsonProperty("error_message")
    override val errorMessage: String?,
    val data: String?,
) : BaseResponse
