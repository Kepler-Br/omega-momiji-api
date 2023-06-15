package com.momiji.api.neural.caption.speech

import com.momiji.api.neural.caption.speech.model.SpeechCaptionResponse
import com.momiji.api.neural.caption.speech.model.SpeechCaptioningRequest
import com.momiji.api.neural.common.model.TaskScheduledResponse
import java.util.UUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

interface SpeechCaptionClient {
    @PostMapping("/speech-captions")
    fun requestCaption(
        @RequestBody content: SpeechCaptioningRequest,
    ): TaskScheduledResponse

    @GetMapping("/speech-captions/{taskId}")
    fun getCaption(
        @PathVariable taskId: UUID,
        @RequestHeader("Async") async: Boolean,
    ): SpeechCaptionResponse
}
