package com.momiji.api.neural.common.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.momiji.api.common.model.BaseResponse
import com.momiji.api.common.model.ResponseStatus
import java.util.UUID

data class TaskScheduledResponse(
    @JsonProperty("status")
    override val status: ResponseStatus,
    @JsonProperty("error_message")
    override val errorMessage: String? = null,
    @JsonProperty("task_id")
    val taskId: UUID? = null,
) : BaseResponse
