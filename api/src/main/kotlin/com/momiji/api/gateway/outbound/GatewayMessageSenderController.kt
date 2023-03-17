package com.momiji.api.gateway.outbound

import com.momiji.api.common.model.SendMessageResponse
import com.momiji.api.gateway.outbound.model.FrontendNamesResponse
import com.momiji.api.gateway.outbound.model.SendTextMessageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping


interface GatewayMessageSenderController {

    @PostMapping("text-messages")
    fun sendText(request: SendTextMessageRequest): SendMessageResponse

    @GetMapping("frontends")
    fun getFrontendNames(): FrontendNamesResponse
}
