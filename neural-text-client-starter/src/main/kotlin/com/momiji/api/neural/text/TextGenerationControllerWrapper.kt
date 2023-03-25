package com.momiji.api.neural.text

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.BasicResponse
import com.momiji.api.neural.text.model.HistoryRequest
import com.momiji.api.neural.text.model.HistoryResponse
import com.momiji.api.neural.text.model.RawRequest
import com.momiji.api.neural.text.model.RawResponse
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

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

    override fun requestGenerationFromHistory(content: HistoryRequest, promptId: UUID): BasicResponse {
        return runCatching(BasicResponse::class.java) {
            textGenerationController.requestGenerationFromHistory(content, promptId)
        }
    }

    override fun getGeneratedFromHistory(promptId: UUID): HistoryResponse {
        return runCatching(HistoryResponse::class.java) {
            textGenerationController.getGeneratedFromHistory(promptId)
        }
    }

    override fun requestGenerationFromRaw(content: RawRequest, promptId: UUID): BasicResponse {
        return runCatching(BasicResponse::class.java) {
            textGenerationController.requestGenerationFromRaw(content, promptId)
        }
    }

    override fun getGeneratedFromRaw(promptId: UUID): RawResponse {
        return runCatching(RawResponse::class.java) {
            textGenerationController.getGeneratedFromRaw(promptId)
        }
    }
}
