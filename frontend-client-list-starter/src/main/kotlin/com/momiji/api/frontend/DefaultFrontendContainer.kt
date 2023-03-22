package com.momiji.api.frontend

import com.momiji.api.common.model.BasicResponse
import com.momiji.api.common.model.SendMessageRequest
import com.momiji.api.common.model.SendMessageResponse
import com.momiji.api.frontend.exception.FrontendNotFoundException

class DefaultFrontendContainer(
    private val clients: Map<String, FrontendController>,
) : FrontendContainer {
    override fun getFrontend(frontend: String): FrontendController {
        if (frontend !in clients) {
            throw FrontendNotFoundException(frontend)
        }

        return clients[frontend]!!
    }

    override fun sendTextMessage(body: SendMessageRequest, frontend: String): SendMessageResponse {
        return getFrontend(frontend).sendTextMessage(body = body)
    }

    override fun sendTypingAction(chatId: String, frontend: String): BasicResponse {
        return getFrontend(frontend).sendTypingAction(chatId = chatId)
    }

    override fun getFrontendNames(): List<String> {
        return clients.keys.toList()
    }
}
