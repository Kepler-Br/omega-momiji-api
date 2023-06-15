package com.momiji.api.neural.generation.image

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.common.model.exception.ServiceResponseException
import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.generation.image.model.ImageGenerationRequest
import com.momiji.api.neural.generation.image.model.ImageGenerationResponse
import feign.FeignException
import java.util.UUID
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ImageGenerationClientImpl(
    private val client: ImageGenerationClient,
    private val objectMapper: ObjectMapper,
) : ImageGenerationClient {
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

    override fun requestGeneration(content: ImageGenerationRequest): TaskScheduledResponse {
        val res = runCatching(TaskScheduledResponse::class.java) {
            client.requestGeneration(content)
        }

        if (res.status != ResponseStatus.OK) {
            throw ServiceResponseException(res)
        }

        return res
    }

    override fun getGenerated(taskId: UUID, async: Boolean): ImageGenerationResponse {
        val res = runCatching(ImageGenerationResponse::class.java) {
            client.getGenerated(taskId, async)
        }

        if (res.status != ResponseStatus.OK && res.status != ResponseStatus.NOT_READY) {
            throw ServiceResponseException(res)
        }

        return res
    }
}
