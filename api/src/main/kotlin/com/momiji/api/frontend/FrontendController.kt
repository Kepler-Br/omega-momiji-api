package com.momiji.api.frontend

import com.momiji.api.common.model.BasicResponse
import com.momiji.api.common.model.SendMessageRequest
import com.momiji.api.common.model.SendMessageResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam


interface FrontendController {

    @RequestMapping(
        "/text-messages",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendTextMessage(@RequestBody body: SendMessageRequest): SendMessageResponse

    @RequestMapping(
        "/actions/typing",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendTypingAction(@RequestParam("chat_id") chatId: String): BasicResponse
}
