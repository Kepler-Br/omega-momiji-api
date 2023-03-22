package com.momiji.api.gateway.outbound

import com.momiji.api.common.model.BasicResponse
import com.momiji.api.common.model.SendMessageResponse
import com.momiji.api.gateway.outbound.model.FrontendNamesResponse
import com.momiji.api.gateway.outbound.model.SendTextMessageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam


interface GatewayMessageSenderController {

    @PostMapping("text-messages")
    fun sendText(@RequestBody request: SendTextMessageRequest): SendMessageResponse

    @PostMapping("actions/typing")
    fun sendTypingAction(
        @RequestParam frontend: String,
        @RequestParam chatId: String
    ): BasicResponse

    @GetMapping("frontends")
    fun getFrontendNames(): FrontendNamesResponse
}
