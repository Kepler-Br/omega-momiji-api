package com.momiji.api.gateway.inbound

import com.momiji.api.common.model.BaseResponse
import com.momiji.api.gateway.inbound.model.ReceivedMessage
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody


interface GatewayMessageReceiverController {

    @PutMapping("messages")
    fun receiveMessage(@RequestBody message: ReceivedMessage): BaseResponse
}
