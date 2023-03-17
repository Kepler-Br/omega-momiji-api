package com.momiji.api.gateway.inbound

import com.momiji.api.gateway.inbound.model.ReceivedMessage
import org.springframework.web.bind.annotation.PutMapping


interface GatewayMessageReceiverController {

    @PutMapping("messages")
    fun receiveMessage(message: ReceivedMessage)
}
