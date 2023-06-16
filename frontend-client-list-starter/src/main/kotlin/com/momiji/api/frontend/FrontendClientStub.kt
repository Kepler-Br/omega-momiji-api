package com.momiji.api.frontend

import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.common.model.SimpleResponse
import com.momiji.api.frontend.model.ChatAdminsFrontendResponse
import com.momiji.api.frontend.model.SendBinaryMessageFrontendRequest
import com.momiji.api.frontend.model.SendMessageFrontendRequest
import com.momiji.api.frontend.model.SendMessageFrontendResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FrontendClientStub : FrontendClient {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun sendImage(body: SendBinaryMessageFrontendRequest): SendMessageFrontendResponse {
        logger.debug(
            "Sending image message with base64 of \"${body.data}\" characters, " +
                    "to chat id \"${body.chatId}\", " +
                    "reply to \"${body.replyTo}\""
        )

        return SendMessageFrontendResponse(
            errorMessage = null,
            messageId = "stub",
            status = ResponseStatus.OK,
        )
    }

    override fun sendVoice(body: SendBinaryMessageFrontendRequest): SendMessageFrontendResponse {
        logger.debug(
            "Sending voice message with base64 of \"${body.data}\" characters, " +
                    "to chat id \"${body.chatId}\", " +
                    "reply to \"${body.replyTo}\""
        )

        return SendMessageFrontendResponse(
            errorMessage = null,
            messageId = "stub",
            status = ResponseStatus.OK,
        )
    }

    override fun sendText(body: SendMessageFrontendRequest): SendMessageFrontendResponse {
        logger.debug(
            "Sending text message with text \"${body.text}\", " +
                    "to chat id \"${body.chatId}\", " +
                    "reply to \"${body.replyTo}\""
        )

        return SendMessageFrontendResponse(
            errorMessage = null,
            messageId = "stub",
            status = ResponseStatus.OK,
        )
    }

    override fun sendTypingAction(chatId: String): SimpleResponse {
        logger.debug("Sending typing action to chat id \"$chatId\"")

        return SimpleResponse(
            status = ResponseStatus.OK,
        )
    }

    override fun getChatAdmins(chatId: String): ChatAdminsFrontendResponse {
        logger.debug("Getting chat admins of \"$chatId\"")

        return ChatAdminsFrontendResponse(
            status = ResponseStatus.OK,
            adminIds = emptyList()
        )
    }
}
