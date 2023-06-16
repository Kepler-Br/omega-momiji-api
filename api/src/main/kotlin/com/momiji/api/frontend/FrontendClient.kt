package com.momiji.api.frontend

import com.momiji.api.common.model.SimpleResponse
import com.momiji.api.frontend.model.ChatAdminsFrontendResponse
import com.momiji.api.frontend.model.SendBinaryMessageFrontendRequest
import com.momiji.api.frontend.model.SendMessageFrontendRequest
import com.momiji.api.frontend.model.SendMessageFrontendResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


interface FrontendClient {

    @RequestMapping(
        "image-messages",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendImage(@RequestBody body: SendBinaryMessageFrontendRequest): SendMessageFrontendResponse

    @RequestMapping(
        "voice-messages",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendVoice(@RequestBody body: SendBinaryMessageFrontendRequest): SendMessageFrontendResponse


    @RequestMapping(
        "/text-messages",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendText(@RequestBody body: SendMessageFrontendRequest): SendMessageFrontendResponse

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
    fun getChatAdmins(@PathVariable("chat-id") chatId: String): ChatAdminsFrontendResponse
}
