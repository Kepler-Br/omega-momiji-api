package com.momiji.api.bot.api

import com.momiji.api.bot.api.model.NewMessageRequest
import org.springframework.web.bind.annotation.PostMapping


interface ReceiveMessageEventsController {

    @PostMapping("messages")
    fun newMessage(request: NewMessageRequest)
}
