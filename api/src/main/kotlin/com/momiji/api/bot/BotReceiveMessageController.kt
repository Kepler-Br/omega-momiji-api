package com.momiji.api.bot

import com.momiji.api.bot.model.NewMessageRequest
import org.springframework.web.bind.annotation.PostMapping


interface BotReceiveMessageController {

    @PostMapping("messages")
    fun newMessage(request: NewMessageRequest)
}
