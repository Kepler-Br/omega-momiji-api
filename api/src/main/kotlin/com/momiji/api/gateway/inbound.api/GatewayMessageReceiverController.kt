package com.momiji.api.gateway.inbound.api

import com.momiji.api.gateway.inbound.api.model.ReceivedMessage
import org.springframework.web.bind.annotation.PutMapping


interface GatewayMessageReceiverController {

    @PutMapping("messages")
    fun receiveMessage(message: ReceivedMessage)
}
