package com.momiji.api.neural.generation.image

import com.momiji.api.neural.common.AbstractClientService
import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.generation.image.model.ImageGenerationRequest
import com.momiji.api.neural.generation.image.model.ImageGenerationResponse
import java.util.Base64
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class ImageGenerationClientServiceImpl(
    private val client: ImageGenerationClient,
    timeout: Long,
    waitBeforeRequest: Long,
    runAsync: Boolean,
) : ImageGenerationClientService, AbstractClientService(timeout, waitBeforeRequest, runAsync) {

    override fun requestGenerationBlocking(
        prompt: String,
        negativePrompt: String?,
    ): ByteArray {
        val scheduled = requestGeneration(
            prompt = prompt,
            negativePrompt = negativePrompt,
        )

        return Base64.getDecoder()
            .decode(requestUntilTimeout(taskId = scheduled.taskId!!, ::getGenerated).data)
    }

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
