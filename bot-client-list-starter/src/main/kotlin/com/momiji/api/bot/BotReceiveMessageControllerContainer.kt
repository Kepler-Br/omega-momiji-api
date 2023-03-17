package com.momiji.api.bot

import com.momiji.api.bot.model.NewMessageRequest

class BotReceiveMessageControllerContainer(
    private val botClients: List<BotReceiveMessageController>
) : BotReceiveMessageController {
    override fun newMessage(request: NewMessageRequest) {
        botClients.forEach {
            it.newMessage(request)
        }
    }
}
