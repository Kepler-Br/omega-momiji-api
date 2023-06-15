package com.momiji.api.neural.caption.image

import com.momiji.api.neural.caption.image.model.ImageCaptionResponse
import com.momiji.api.neural.caption.image.model.ImageCaptioningRequest
import com.momiji.api.neural.common.model.TaskScheduledResponse
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class ImageCaptionClientServiceImpl(
    private val imageCaptionClient: ImageCaptionClient,
) : ImageCaptionClientService {
    override fun requestCaption(data: String, condition: String?): TaskScheduledResponse {
        return imageCaptionClient.requestCaption(
            ImageCaptioningRequest(
                data = data,
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
