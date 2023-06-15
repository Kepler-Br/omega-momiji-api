package com.momiji.api.frontend

import com.momiji.api.common.model.BaseResponse
import com.momiji.api.common.model.ChatAdminsResponse
import com.momiji.api.common.model.SendMessageRequest
import com.momiji.api.common.model.SendMessageResponse
import com.momiji.api.frontend.exception.FrontendNotFoundException

class FrontendContainerImpl(
    private val clients: Map<String, FrontendClient>,
) : FrontendContainer {
    override fun getFrontend(frontend: String): FrontendClient {
        if (frontend !in clients) {
            throw FrontendNotFoundException(frontend)
        }

        return clients[frontend]!!
    }

    override fun sendTextMessage(body: SendMessageRequest, frontend: String): SendMessageResponse {
        return getFrontend(frontend).sendTextMessage(body = body)
    }

    override fun sendTypingAction(chatId: String, frontend: String): BaseResponse {
        return getFrontend(frontend).sendTypingAction(chatId = chatId)
    }

    override fun getChatAdmins(frontend: String, chatId: String): ChatAdminsResponse {
        return getFrontend(frontend).getChatAdmins(chatId)
    }

    override fun getFrontendNames(): List<String> {
        return clients.keys.toList()
    }
}
