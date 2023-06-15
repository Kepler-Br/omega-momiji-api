package com.momiji.api.neural.generation.image

import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.generation.image.model.ImageGenerationRequest
import com.momiji.api.neural.generation.image.model.ImageGenerationResponse
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class ImageGenerationClientServiceImpl(
    private val client: ImageGenerationClient,
) : ImageGenerationClientService {
    override fun requestGeneration(
        prompt: String,
        negativePrompt: String?,
    ): TaskScheduledResponse {
        return client.requestGeneration(
            ImageGenerationRequest(
                prompt = prompt,
                negativePrompt = negativePrompt,
            )
        )
    }

    override fun getGenerated(taskId: UUID, async: Boolean): ImageGenerationResponse {
        return client.getGenerated(
            taskId = taskId,
            async = async,
        )
    }
}
