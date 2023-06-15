package com.momiji.api.neural.caption.speech

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.neural.caption.speech.model.SpeechCaptionResponse
import com.momiji.api.neural.caption.speech.model.SpeechCaptioningRequest
import com.momiji.api.neural.common.model.TaskScheduledResponse
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SpeechCaptionClientStub(
    private val objectMapper: ObjectMapper,
    private val requests: ConcurrentHashMap<UUID, SpeechCaptioningRequest>,
) : SpeechCaptionClient {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun requestCaption(content: SpeechCaptioningRequest): TaskScheduledResponse {
        val requestJsonText = objectMapper.writeValueAsString(content)

        logger.debug(
            "Requesting speech captioning:\n$requestJsonText"
        )

        val promptId = UUID.randomUUID()
        requests[promptId] = content

        return TaskScheduledResponse(
            status = ResponseStatus.OK,
            taskId = promptId
        )
    }

    override fun getCaption(taskId: UUID, async: Boolean): SpeechCaptionResponse {
        if (!requests.containsKey(taskId)) {
            return SpeechCaptionResponse(
                status = ResponseStatus.NOT_FOUND,
                errorMessage = null,
                caption = null
            )
        }

        requests.remove(taskId)!!

        return SpeechCaptionResponse(
            status = ResponseStatus.OK,
            errorMessage = null,
            caption = "The quick brown fox jumps over the lazy dog"
        )
    }
}
