package com.momiji.api.neural.caption.image

import com.momiji.api.neural.caption.image.model.ImageCaptionResponse
import com.momiji.api.neural.caption.image.model.ImageCaptioningRequest
import com.momiji.api.neural.common.model.TaskScheduledResponse
import java.util.UUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

interface ImageCaptionClient {

    @PostMapping("/image-captions")
    fun requestCaption(
        @RequestBody content: ImageCaptioningRequest,
    ): TaskScheduledResponse

    @GetMapping("/image-captions/{taskId}")
    fun getCaption(
        @PathVariable taskId: UUID,
        @RequestHeader("Async") async: Boolean,
    ): ImageCaptionResponse
}
