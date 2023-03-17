package com.momiji.api.frontend

import com.momiji.api.common.model.SendMessageRequest
import com.momiji.api.common.model.SendMessageResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FrontendControllerStub : FrontendController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun sendTextMessage(body: SendMessageRequest): SendMessageResponse {
        logger.debug(
            "Sending text message with text \"${body.text}\", " +
                    "to chat id \"${body.chatId}\", " +
                    "reply to \"${body.replyTo}\""
        )

        return SendMessageResponse(
            errorMessage = null,
            messageId = "stub",
        )
    }
}
