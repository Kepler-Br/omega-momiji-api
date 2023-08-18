package com.momiji.api.gateway.outbound

import com.momiji.api.gateway.outbound.model.SendBinaryMessageGatewayRequest
import com.momiji.api.gateway.outbound.model.SendTextMessageRequest
import java.util.Base64

class GatewayMessageSenderServiceImpl(
    private val client: GatewayMessageSenderClient,
) : GatewayMessageSenderService {

    /**
     * @return messageId that was sent
     */
    override fun sendVoice(
        frontend: String,
        data: ByteArray,
        chatId: String,
        replyToMessageId: String?
    ): String {
        return client.sendVoice(
            SendBinaryMessageGatewayRequest(
                frontend = frontend,
                data = Base64.getEncoder().encodeToString(data),
                chatId = chatId,
                replyToMessageId = replyToMessageId
            )
        ).messageId!!
    }

    /**
     * @return messageId that was sent
     */
    override fun sendImage(
        frontend: String,
        data: ByteArray,
        chatId: String,
        replyToMessageId: String?
    ): String {
        return client.sendImage(
            SendBinaryMessageGatewayRequest(
                frontend = frontend,
                data = Base64.getEncoder().encodeToString(data),
                chatId = chatId,
                replyToMessageId = replyToMessageId
            )
        ).messageId!!
    }

    /**
     * @return messageId that was sent
     */
    override fun sendText(
        frontend: String,
        text: String,
        chatId: String,
        replyToMessageId: String?
    ): String {
        return client.sendText(
            SendTextMessageRequest(
                frontend = frontend,
                text = text,
                chatId = chatId,
                replyToMessageId = replyToMessageId,
            )
        ).messageId!!
    }

    override fun sendTextWithTyping(
        frontend: String,
        text: String,
        chatId: String,
        replyToMessageId: String?,
    ): String {
        sendTypingAction(frontend=frontend, chatId=chatId)

        return sendText(
            frontend = frontend,
            text = text,
            chatId = chatId,
            replyToMessageId = replyToMessageId,
        )
    }

    override fun sendTypingAction(frontend: String, chatId: String) {
        client.sendTypingAction(frontend = frontend, chatId = chatId)
    }

    override fun getFrontendNames(): List<String> {
        return client.getFrontendNames().names!!
    }

    override fun getChatAdmins(chatId: String, frontend: String): List<String> {
        return client.getChatAdmins(chatId = chatId, frontend = frontend).adminIds!!
    }
}
