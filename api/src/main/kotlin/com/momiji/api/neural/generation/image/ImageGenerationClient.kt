package com.momiji.api.neural.generation.image

import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.generation.image.model.ImageGenerationRequest
import com.momiji.api.neural.generation.image.model.ImageGenerationResponse
import java.util.UUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

interface ImageGenerationClient {
    @PostMapping("/images")
    fun requestGeneration(
        @RequestBody content: ImageGenerationRequest,
    ): TaskScheduledResponse

    @GetMapping("/images/{taskId}")
    fun getGenerated(
        @PathVariable taskId: UUID,
        @RequestHeader("Async") async: Boolean,
    ): ImageGenerationResponse
}
