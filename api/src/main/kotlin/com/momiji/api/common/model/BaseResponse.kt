package com.momiji.api.common.model

interface BaseResponse {
    val status: ResponseStatus
    val errorMessage: String?
}
