package com.momiji.api.neural.caption.speech

import com.momiji.api.neural.caption.speech.model.SpeechCaptionResponse
import com.momiji.api.neural.common.model.TaskScheduledResponse
import java.util.UUID

interface SpeechCaptionClientService {
    fun requestCaptionBlocking(
        data: ByteArray,
    ): String

    fun requestCaption(
        data: ByteArray,
    ): TaskScheduledResponse

    fun getCaption(
        taskId: UUID,
        async: Boolean,
    ): SpeechCaptionResponse
}
