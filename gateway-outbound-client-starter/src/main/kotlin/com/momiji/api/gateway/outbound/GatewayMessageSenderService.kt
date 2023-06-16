package com.momiji.api.gateway.outbound

interface GatewayMessageSenderService {
    /**
     * @return messageId that was sent
     */
    fun sendVoice(
        frontend: String,
        data: ByteArray,
        chatId: String,
        replyToMessageId: String? = null,
    ): String

    /**
     * @return messageId that was sent
     */
    fun sendImage(
        frontend: String,
        data: ByteArray,
        chatId: String,
        replyToMessageId: String? = null,
    ): String

    /**
     * @return messageId that was sent
     */
    fun sendText(
        frontend: String,
        text: String,
        chatId: String,
        replyToMessageId: String? = null,
    ): String

    fun sendTypingAction(frontend: String, chatId: String)
    fun getFrontendNames(): List<String>
    fun getChatAdmins(chatId: String, frontend: String): List<String>
}
