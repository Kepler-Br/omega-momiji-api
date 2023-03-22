package com.momiji.api.frontend

import com.momiji.api.common.model.BasicResponse
import com.momiji.api.common.model.SendMessageRequest
import com.momiji.api.common.model.SendMessageResponse

interface FrontendContainer {
    fun getFrontend(frontend: String): FrontendController
    fun sendTextMessage(body: SendMessageRequest, frontend: String): SendMessageResponse
    fun sendTypingAction(chatId: String, frontend: String): BasicResponse
    fun getFrontendNames(): List<String>
}
