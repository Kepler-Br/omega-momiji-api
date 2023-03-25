package com.momiji.api.frontend

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.BasicResponse
import com.momiji.api.common.model.SendMessageRequest
import com.momiji.api.common.model.SendMessageResponse
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FrontendControllerWrapper(
    private val frontendController: FrontendController,
    private val objectMapper: ObjectMapper,
) : FrontendController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private fun <T> runCatching(valueType: Class<T>, runnable: () -> T): T {
        return try {
            runnable()
        } catch (ex: FeignException.FeignClientException) {
            logger.debug("FeignClientException caught! Trying to deserialize returning value")
            try {
                objectMapper.readValue(ex.responseBody().get().array(), valueType)
            } catch (ex2: Exception) {
                logger.error("Unable to deserialize returned value from service. Throwing original exception", ex)
                throw ex
            }
        }
    }

    override fun sendTextMessage(body: SendMessageRequest): SendMessageResponse {
        return runCatching(SendMessageResponse::class.java) {
            frontendController.sendTextMessage(body)
        }
    }

    override fun sendTypingAction(chatId: String): BasicResponse {
        return runCatching(BasicResponse::class.java) {
            frontendController.sendTypingAction(chatId)
        }
    }
}
