package com.momiji.api.bot

import com.momiji.api.bot.model.NewMessageRequest
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.common.model.SimpleResponse
import feign.FeignException
import feign.RetryableException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BotReceiveMessageControllerContainer(
    private val botClients: List<BotReceiveMessageController>
) : BotReceiveMessageController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun newMessage(request: NewMessageRequest): SimpleResponse {
        botClients.forEach {
            try {
                it.newMessage(request)
            } catch (ex: RetryableException) {
                logger.error("Connection error to bot", ex)
            } catch (ex: FeignException) {
                logger.error("General exception", ex)
            }
        }

        return SimpleResponse(
            status = ResponseStatus.OK,
        )
    }
}
