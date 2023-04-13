package com.momiji.api.gateway.outbound

import com.momiji.api.common.model.BaseResponse
import com.momiji.api.common.model.ChatAdminsResponse
import com.momiji.api.common.model.SendMessageResponse
import com.momiji.api.gateway.outbound.model.FrontendNamesResponse
import com.momiji.api.gateway.outbound.model.SendTextMessageRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam


interface GatewayMessageSenderController {

    @RequestMapping(
        "text-messages",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendText(@RequestBody request: SendTextMessageRequest): SendMessageResponse

    @RequestMapping(
        "actions/typing",
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendTypingAction(
        @RequestParam frontend: String,
        @RequestParam chatId: String
    ): BaseResponse

    @RequestMapping(
        "frontends",
        method = [RequestMethod.GET],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getFrontendNames(): FrontendNamesResponse

    @RequestMapping(
        "/chats/{chat-id}/admins",
        method = [RequestMethod.GET],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getChatAdmins(
        @PathVariable("chat-id") chatId: String,
        @RequestParam("frontend") frontend: String
    ): ChatAdminsResponse
}
