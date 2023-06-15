package com.momiji.api.gateway.inbound

import com.momiji.api.common.model.SimpleResponse
import com.momiji.api.gateway.inbound.model.ReceivedMessage
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody


interface GatewayMessageReceiverClient {

    @PutMapping("messages")
    fun receiveMessage(@RequestBody message: ReceivedMessage): SimpleResponse
}
