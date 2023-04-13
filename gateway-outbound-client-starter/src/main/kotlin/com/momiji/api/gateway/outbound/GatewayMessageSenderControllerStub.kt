package com.momiji.api.gateway.outbound

import com.momiji.api.common.model.ChatAdminsResponse
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.common.model.SendMessageResponse
import com.momiji.api.common.model.SimpleResponse
import com.momiji.api.gateway.outbound.model.FrontendNamesResponse
import com.momiji.api.gateway.outbound.model.SendTextMessageRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GatewayMessageSenderControllerStub : GatewayMessageSenderController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun sendText(request: SendTextMessageRequest): SendMessageResponse {
        logger.debug(
            "Sending text message to frontend \"${request.frontend}\", " +
                    "to chatId \"${request.chatId}\", " +
                    "with text \"${request.text}\", " +
                    "with reply to message id \"${request.replyToMessageId}\""
        )

        return SendMessageResponse(
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

    override fun getChatAdmins(chatId: String, frontend: String): ChatAdminsResponse {
        logger.debug("Getting chat admins for chat \"$chatId\" and frontend \"$frontend\"")

        return ChatAdminsResponse(
            status = ResponseStatus.OK,
            adminIds = emptySet()
        )
    }
}
