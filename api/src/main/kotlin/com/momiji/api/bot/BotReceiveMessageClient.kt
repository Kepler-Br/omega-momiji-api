package com.momiji.api.bot

import com.momiji.api.bot.model.NewMessageRequest
import com.momiji.api.common.model.SimpleResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


interface BotReceiveMessageClient {

    @PostMapping("messages")
    fun newMessage(@RequestBody request: NewMessageRequest): SimpleResponse
}
