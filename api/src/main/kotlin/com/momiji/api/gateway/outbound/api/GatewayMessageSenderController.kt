package com.momiji.api.gateway.outbound.api

import com.momiji.api.common.api.model.SendMessageResponse
import com.momiji.api.gateway.outbound.api.model.FrontendNamesResponse
import com.momiji.api.gateway.outbound.api.model.SendTextMessageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping


interface GatewayMessageSenderController {

    @PostMapping("text-messages")
    fun sendText(request: SendTextMessageRequest): SendMessageResponse

    @GetMapping("frontends")
    fun getFrontendNames(): FrontendNamesResponse
}
