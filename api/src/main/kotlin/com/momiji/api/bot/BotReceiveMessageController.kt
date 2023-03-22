package com.momiji.api.bot

import com.momiji.api.bot.model.NewMessageRequest
import com.momiji.api.common.model.BasicResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


interface BotReceiveMessageController {

    @PostMapping("messages")
    fun newMessage(@RequestBody request: NewMessageRequest): BasicResponse
}
