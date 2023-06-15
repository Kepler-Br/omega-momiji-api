package com.momiji.api.neural.caption.speech

import com.momiji.api.neural.caption.speech.model.SpeechCaptionResponse
import com.momiji.api.neural.caption.speech.model.SpeechCaptioningRequest
import com.momiji.api.neural.common.model.TaskScheduledResponse
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class SpeechCaptionClientServiceImpl(
    private val client: SpeechCaptionClient,
) : SpeechCaptionClientService {
    override fun requestCaption(data: String): TaskScheduledResponse {
        return client.requestCaption(
            SpeechCaptioningRequest(
                data = data,
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
