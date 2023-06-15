package com.momiji.api.neural.generation.image

import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.generation.image.model.ImageGenerationResponse
import java.util.UUID

interface ImageGenerationClientService {
    fun requestGenerationBlocking(
        prompt: String,
        negativePrompt: String?,
    ): ByteArray

    fun requestGeneration(
        prompt: String,
        negativePrompt: String?,
    ): TaskScheduledResponse

    fun getGenerated(
        taskId: UUID,
        async: Boolean,
    ): ImageGenerationResponse
}
