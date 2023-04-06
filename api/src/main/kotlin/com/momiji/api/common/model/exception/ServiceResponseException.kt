package com.momiji.api.common.model.exception

import com.momiji.api.common.model.BaseResponse

class ServiceResponseException(val response: BaseResponse) : RuntimeException()
