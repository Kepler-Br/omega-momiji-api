package com.momiji.api.frontend

import com.momiji.api.common.model.ChatAdminsResponse
import com.momiji.api.common.model.SendMessageRequest
import com.momiji.api.common.model.SendMessageResponse
import com.momiji.api.common.model.SimpleResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


interface FrontendController {

    @RequestMapping(
        "/text-messages",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendTextMessage(@RequestBody body: SendMessageRequest): SendMessageResponse

    @RequestMapping(
        "/chats/{chat-id}/actions/typing",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendTypingAction(@PathVariable("chat-id") chatId: String): SimpleResponse

    @RequestMapping(
        "/chats/{chat-id}/admins",
        method = [RequestMethod.GET],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getChatAdmins(@PathVariable("chat-id") chatId: String): ChatAdminsResponse
}
