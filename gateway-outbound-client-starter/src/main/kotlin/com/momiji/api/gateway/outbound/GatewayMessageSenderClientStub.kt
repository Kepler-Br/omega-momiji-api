package com.momiji.api.gateway.outbound

import com.momiji.api.frontend.model.ChatAdminsFrontendResponse
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.frontend.model.SendMessageFrontendResponse
import com.momiji.api.common.model.SimpleResponse
import com.momiji.api.gateway.outbound.model.FrontendNamesResponse
import com.momiji.api.gateway.outbound.model.SendBinaryMessageGatewayRequest
import com.momiji.api.gateway.outbound.model.SendTextMessageRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GatewayMessageSenderClientStub : GatewayMessageSenderClient {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun sendImage(request: SendBinaryMessageGatewayRequest): SendMessageFrontendResponse {
        logger.debug(
            "Sending image message to frontend \"${request.frontend}\", " +
                    "to chatId \"${request.chatId}\", " +
                    "with binary of \"${request.data.length}\" bytes, " +
                    "with reply to message id \"${request.replyToMessageId}\""
        )

        return SendMessageFrontendResponse(
            errorMessage = null,
            messageId = "messageId",
            status = ResponseStatus.OK,
        )
    }

    override fun sendVoice(request: SendBinaryMessageGatewayRequest): SendMessageFrontendResponse {
        logger.debug(
            "Sending voice message to frontend \"${request.frontend}\", " +
                    "to chatId \"${request.chatId}\", " +
                    "with binary of \"${request.data.length}\" bytes, " +
                    "with reply to message id \"${request.replyToMessageId}\""
        )

        return SendMessageFrontendResponse(
            errorMessage = null,
            messageId = "messageId",
            status = ResponseStatus.OK,
        )
    }

    override fun sendText(request: SendTextMessageRequest): SendMessageFrontendResponse {
        logger.debug(
            "Sending text message to frontend \"${request.frontend}\", " +
                    "to chatId \"${request.chatId}\", " +
                    "with text \"${request.text}\", " +
                    "with reply to message id \"${request.replyToMessageId}\""
        )

        return SendMessageFrontendResponse(
            errorMessage = null,
            messageId = "messageId",
            status = ResponseStatus.OK,
        )
    }

    override fun sendTypingAction(frontend: String, chatId: String): SimpleResponse {
        return SimpleResponse(
            status = ResponseStatus.OK,
        )
    }

    override fun getFrontendNames(): FrontendNamesResponse {
        logger.debug("Getting frontend names")

        return FrontendNamesResponse(
            names = listOf("MockFrontend"),
            status = ResponseStatus.OK,
        )
    }

    override fun getChatAdmins(chatId: String, frontend: String): ChatAdminsFrontendResponse {
        logger.debug("Getting chat admins for chat \"$chatId\" and frontend \"$frontend\"")

        return ChatAdminsFrontendResponse(
            status = ResponseStatus.OK,
            adminIds = emptyList()
        )
    }
}
