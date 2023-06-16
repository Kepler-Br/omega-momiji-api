package com.momiji.api.frontend

import com.momiji.api.frontend.model.SendBinaryMessageFrontendRequest
import com.momiji.api.frontend.model.SendMessageFrontendRequest
import java.util.Base64

interface FrontendContainer {
    fun getFrontend(frontend: String): FrontendClient

    fun withFrontend(frontend: String): WithFrontend

    fun getFrontendNames(): List<String>

    class WithFrontend(private val client: FrontendClient) {
        fun sendImage(data: ByteArray, chatId: String, replyTo: String?): String {
            return client.sendImage(
                SendBinaryMessageFrontendRequest(
                    data = Base64.getEncoder().encodeToString(data),
                    chatId = chatId,
                    replyTo = replyTo,
                )
            ).messageId!!
        }

        fun sendVoice(data: ByteArray, chatId: String, replyTo: String?): String {
            return client.sendVoice(
                SendBinaryMessageFrontendRequest(
                    data = Base64.getEncoder().encodeToString(data),
                    chatId = chatId,
                    replyTo = replyTo,
                )
            ).messageId!!
        }

        fun sendImage(data: String, chatId: String, replyTo: String?): String {
            return client.sendImage(
                SendBinaryMessageFrontendRequest(
                    data = data,
                    chatId = chatId,
                    replyTo = replyTo,
                )
            ).messageId!!
        }

        fun sendVoice(data: String, chatId: String, replyTo: String?): String {
            return client.sendVoice(
                SendBinaryMessageFrontendRequest(
                    data = data,
                    chatId = chatId,
                    replyTo = replyTo,
                )
            ).messageId!!
        }

        fun sendText(text: String, chatId: String, replyTo: String?): String {
            return client.sendText(
                SendMessageFrontendRequest(
                    text = text,
                    chatId = chatId,
                    replyTo = replyTo,
                )
            ).messageId!!
        }

        fun sendTypingAction(chatId: String) {
            client.sendTypingAction(chatId)
        }

        fun getChatAdmins(chatId: String): List<String> {
            return client.getChatAdmins(chatId).adminIds!!
        }
    }
}
