package com.momiji.api.neural.text

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.text.model.HistoryRequest
import com.momiji.api.neural.text.model.HistoryResponse
import com.momiji.api.neural.text.model.RawRequest
import com.momiji.api.neural.text.model.RawResponse
import feign.FeignException
import java.util.UUID
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TextGenerationControllerWrapper(
    private val textGenerationController: TextGenerationController,
    private val objectMapper: ObjectMapper,
) : TextGenerationController {
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

    override fun requestGenerationFromHistory(content: HistoryRequest): TaskScheduledResponse {
        return runCatching(TaskScheduledResponse::class.java) {
            textGenerationController.requestGenerationFromHistory(content)
        }
    }

    override fun getGeneratedFromHistory(promptId: UUID, async: Boolean): HistoryResponse {
        return runCatching(HistoryResponse::class.java) {
            textGenerationController.getGeneratedFromHistory(promptId, async)
        }
    }

    override fun requestGenerationFromRaw(content: RawRequest): TaskScheduledResponse {
        return runCatching(TaskScheduledResponse::class.java) {
            textGenerationController.requestGenerationFromRaw(content)
        }
    }

    override fun getGeneratedFromRaw(promptId: UUID, async: Boolean): RawResponse {
        return runCatching(RawResponse::class.java) {
            textGenerationController.getGeneratedFromRaw(promptId, async)
        }
    }
}
