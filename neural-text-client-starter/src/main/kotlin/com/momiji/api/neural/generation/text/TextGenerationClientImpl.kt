package com.momiji.api.neural.generation.text

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.common.model.exception.ServiceResponseException
import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.generation.text.model.HistoryRequest
import com.momiji.api.neural.generation.text.model.HistoryResponse
import feign.FeignException
import java.util.UUID
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TextGenerationClientImpl(
    private val client: TextGenerationClient,
    private val objectMapper: ObjectMapper,
) : TextGenerationClient {
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

    override fun requestGenerationFromHistory(content: HistoryRequest): TaskScheduledResponse {
        val res = runCatching(TaskScheduledResponse::class.java) {
            client.requestGenerationFromHistory(content)
        }

        if (res.status != ResponseStatus.OK) {
            throw ServiceResponseException(res)
        }

        return res
    }

    override fun getGeneratedFromHistory(taskId: UUID, async: Boolean): HistoryResponse {
        val res = runCatching(HistoryResponse::class.java) {
            client.getGeneratedFromHistory(taskId, async)
        }

        if (res.status != ResponseStatus.OK && res.status != ResponseStatus.NOT_READY) {
            throw ServiceResponseException(res)
        }

        return res
    }
}
