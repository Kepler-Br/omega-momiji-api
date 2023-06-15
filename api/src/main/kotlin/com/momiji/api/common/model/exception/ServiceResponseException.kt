package com.momiji.api.common.model.exception

import com.momiji.api.common.model.BaseResponse

class ServiceResponseException(val response: BaseResponse?, message: String?, cause: Throwable?) :
    RuntimeException(message, cause) {
    constructor(response: BaseResponse, message: String) : this(response, message, null)
    constructor(response: BaseResponse) : this(response, null, null)
    constructor(message: String) : this(null, message, null)
}
