package com.momiji.api.frontend

import com.momiji.api.common.model.SendMessageRequest
import com.momiji.api.common.model.SendMessageResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


interface FrontendController {

    @RequestMapping(
        "/text-messages",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendTextMessage(body: SendMessageRequest): SendMessageResponse
}
