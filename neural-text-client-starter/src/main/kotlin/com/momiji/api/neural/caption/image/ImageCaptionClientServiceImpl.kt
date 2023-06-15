package com.momiji.api.neural.caption.image

import com.momiji.api.neural.caption.image.model.ImageCaptionResponse
import com.momiji.api.neural.caption.image.model.ImageCaptioningRequest
import com.momiji.api.neural.common.AbstractClientService
import com.momiji.api.neural.common.model.TaskScheduledResponse
import java.util.Base64
import java.util.UUID

class ImageCaptionClientServiceImpl(
    private val imageCaptionClient: ImageCaptionClient,
    timeout: Long,
    waitBeforeRequest: Long,
    runAsync: Boolean,
) : ImageCaptionClientService, AbstractClientService(timeout, waitBeforeRequest, runAsync) {
    override fun requestCaptionBlocking(data: ByteArray, condition: String?): String {
        val scheduled = requestCaption(
            data = data,
            condition = condition,
        )

        return requestUntilTimeout(scheduled.taskId!!, ::getCaption).caption!!
    }

    override fun requestCaption(data: ByteArray, condition: String?): TaskScheduledResponse {
        return imageCaptionClient.requestCaption(
            ImageCaptioningRequest(
                data = Base64.getEncoder().encodeToString(data),
                condition = condition,
            )
        )
    }

    override fun getCaption(taskId: UUID, async: Boolean): ImageCaptionResponse {
        return imageCaptionClient.getCaption(
            taskId = taskId,
            async = async,
        )
    }
}
