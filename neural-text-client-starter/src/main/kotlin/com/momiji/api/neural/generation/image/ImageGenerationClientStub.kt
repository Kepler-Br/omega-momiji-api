package com.momiji.api.neural.generation.image

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.generation.image.model.ImageGenerationRequest
import com.momiji.api.neural.generation.image.model.ImageGenerationResponse
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ImageGenerationClientStub(
    private val objectMapper: ObjectMapper,
    private val requests: ConcurrentHashMap<UUID, ImageGenerationRequest>,
) : ImageGenerationClient {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun requestGeneration(
        content: ImageGenerationRequest,
    ): TaskScheduledResponse {
        val requestJsonText = objectMapper.writeValueAsString(content)

        logger.debug(
            "Requesting image generation:\n$requestJsonText"
        )

        val promptId = UUID.randomUUID()
        requests[promptId] = content

        return TaskScheduledResponse(
            status = ResponseStatus.OK,
            taskId = promptId
        )
    }

    override fun getGenerated(taskId: UUID, async: Boolean): ImageGenerationResponse {
        if (!requests.containsKey(taskId)) {
            return ImageGenerationResponse(
                status = ResponseStatus.NOT_FOUND,
                errorMessage = null,
                data = null,
            )
        }

        requests.remove(taskId)!!

        return ImageGenerationResponse(
            status = ResponseStatus.OK,
            errorMessage = null,
            data = "SGVsbG8gV29ybGQK"
        )
    }
}
