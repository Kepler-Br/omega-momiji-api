package com.momiji.api.frontend

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.ChatAdminsResponse
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.common.model.SendMessageRequest
import com.momiji.api.common.model.SendMessageResponse
import com.momiji.api.common.model.SimpleResponse
import com.momiji.api.common.model.exception.ServiceResponseException
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FrontendClientWrapper(
    private val frontendClient: FrontendClient,
    private val objectMapper: ObjectMapper,
) : FrontendClient {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private fun <T> runCatching(valueType: Class<T>, runnable: () -> T): T {
        return try {
            runnable()
        } catch (ex: FeignException.FeignClientException) {
            logger.debug("FeignClientException caught! Trying to deserialize returning value")
            try {
                objectMapper.readValue(ex.responseBody().get().array(), valueType)
            } catch (ex2: Exception) {
                logger.error("Unable to deserialize returned value from service. Throwing original exception", ex2)
                throw ex
            }
        }
    }

    override fun sendTextMessage(body: SendMessageRequest): SendMessageResponse {
        logger.debug(
            "Sending text message with text \"${body.text}\", " +
                    "to chat id \"${body.chatId}\", " +
                    "reply to \"${body.replyTo}\""
        )

        val res = runCatching(SendMessageResponse::class.java) {
            frontendClient.sendTextMessage(body)
        }

        if (res.status != ResponseStatus.OK && res.status != ResponseStatus.NOT_READY) {
            throw ServiceResponseException(res)
        }

        return res
    }

    override fun sendTypingAction(chatId: String): SimpleResponse {
        logger.debug("Sending typing action to chat id \"$chatId\"")

        val res = runCatching(SimpleResponse::class.java) {
            frontendClient.sendTypingAction(chatId)
        }

        if (res.status != ResponseStatus.OK && res.status != ResponseStatus.NOT_READY) {
            throw ServiceResponseException(res)
        }

        return res
    }

    override fun getChatAdmins(chatId: String): ChatAdminsResponse {
        logger.debug("Getting chat admins of \"$chatId\"")

        val res = runCatching(ChatAdminsResponse::class.java) {
            frontendClient.getChatAdmins(chatId)
        }

        if (res.status != ResponseStatus.OK) {
            throw ServiceResponseException(res)
        }

        return res
    }
}
