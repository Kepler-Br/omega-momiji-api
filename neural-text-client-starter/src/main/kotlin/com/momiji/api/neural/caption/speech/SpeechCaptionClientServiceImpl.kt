package com.momiji.api.neural.caption.speech

import com.momiji.api.neural.caption.speech.model.SpeechCaptionResponse
import com.momiji.api.neural.caption.speech.model.SpeechCaptioningRequest
import com.momiji.api.neural.common.AbstractClientService
import com.momiji.api.neural.common.model.TaskScheduledResponse
import java.util.Base64
import java.util.UUID

class SpeechCaptionClientServiceImpl(
    private val client: SpeechCaptionClient,
    timeout: Long,
    waitBeforeRequest: Long,
    runAsync: Boolean,
) : SpeechCaptionClientService, AbstractClientService(timeout, waitBeforeRequest, runAsync) {
    override fun requestCaptionBlocking(data: ByteArray): String {
        val scheduled = requestCaption(
            data = data,
        )

        return requestUntilTimeout(scheduled.taskId!!, ::getCaption).caption!!
    }

    override fun requestCaption(data: ByteArray): TaskScheduledResponse {
        return client.requestCaption(
            SpeechCaptioningRequest(
                data = Base64.getEncoder().encodeToString(data),
            )
        )
    }

    override fun getCaption(taskId: UUID, async: Boolean): SpeechCaptionResponse {
        return client.getCaption(
            taskId = taskId,
            async = async,
        )
    }
}
