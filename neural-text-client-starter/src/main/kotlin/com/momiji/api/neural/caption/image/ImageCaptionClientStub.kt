package com.momiji.api.neural.caption.image

import com.fasterxml.jackson.databind.ObjectMapper
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.neural.caption.image.model.ImageCaptionResponse
import com.momiji.api.neural.caption.image.model.ImageCaptioningRequest
import com.momiji.api.neural.common.model.TaskScheduledResponse
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ImageCaptionClientStub(
    private val objectMapper: ObjectMapper,
    private val requests: ConcurrentHashMap<UUID, ImageCaptioningRequest>,
) : ImageCaptionClient {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun requestCaption(content: ImageCaptioningRequest): TaskScheduledResponse {
        val requestJsonText = objectMapper.writeValueAsString(content)

        logger.debug(
            "Requesting image captioning:\n$requestJsonText"
        )

        val promptId = UUID.randomUUID()
        requests[promptId] = content

        return TaskScheduledResponse(
            status = ResponseStatus.OK,
            taskId = promptId
        )
    }

    override fun getCaption(taskId: UUID, async: Boolean): ImageCaptionResponse {
        if (!requests.containsKey(taskId)) {
            return ImageCaptionResponse(
                status = ResponseStatus.NOT_FOUND,
                errorMessage = null,
                caption = null
            )
        }

        requests.remove(taskId)!!

        return ImageCaptionResponse(
            status = ResponseStatus.OK,
            errorMessage = null,
            caption = "A soccer player kicking a soccer ball"
        )
    }
}
