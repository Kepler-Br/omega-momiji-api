package com.momiji.api.neural.caption.image

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.common.model.exception.ServiceResponseException
import com.momiji.api.neural.caption.image.model.ImageCaptionResponse
import com.momiji.api.neural.caption.image.model.ImageCaptioningRequest
import com.momiji.api.neural.common.model.TaskScheduledResponse
import feign.FeignException
import java.util.UUID
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ImageCaptionClientImpl(
    private val client: ImageCaptionClient,
    private val objectMapper: ObjectMapper,
) : ImageCaptionClient {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private fun <T> runCatching(valueType: Class<T>, runnable: () -> T): T {
        return try {
            runnable()
        } catch (ex: FeignException.FeignClientException) {
            logger.debug("FeignClientException caught! Trying to deserialize returning value")
            try {
                objectMapper.readValue(ex.responseBody().get().array(), valueType)
            } catch (ex2: Exception) {
                logger.error(
                    "Unable to deserialize returned value from service. Throwing original exception",
                    ex2
                )
                throw ex
            }
        }
    }

    override fun requestCaption(content: ImageCaptioningRequest): TaskScheduledResponse {
        val res = runCatching(TaskScheduledResponse::class.java) {
            client.requestCaption(content)
        }

        if (res.status != ResponseStatus.OK) {
            throw ServiceResponseException(res)
        }

        return res
    }

    override fun getCaption(taskId: UUID, async: Boolean): ImageCaptionResponse {
        val res = runCatching(ImageCaptionResponse::class.java) {
            client.getCaption(taskId, async)
        }

        if (res.status != ResponseStatus.OK && res.status != ResponseStatus.NOT_READY) {
            throw ServiceResponseException(res)
        }

        return res
    }
}
