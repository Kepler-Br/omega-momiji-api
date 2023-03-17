package com.momiji.api.bot

import com.momiji.api.bot.model.NewMessageRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BotReceiveMessageControllerStub : BotReceiveMessageController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun newMessage(request: NewMessageRequest) {
        logger.debug(
            "New message: Message ID: \"${request.messageId}\", " +
                    "Frontend: \"${request.frontend}\", " +
                    "Chat ID: \"${request.chatId}\", " +
                    "Is updated: ${request.isUpdated}"
        )
    }
}
