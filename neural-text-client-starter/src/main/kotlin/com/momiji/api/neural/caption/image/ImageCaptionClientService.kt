package com.momiji.api.neural.caption.image

import com.momiji.api.neural.caption.image.model.ImageCaptionResponse
import com.momiji.api.neural.common.model.TaskScheduledResponse
import java.util.UUID

interface ImageCaptionClientService {
    fun requestCaptionBlocking(
        data: ByteArray,
        condition: String?,
    ): String

    fun requestCaption(
        data: ByteArray,
        condition: String?,
    ): TaskScheduledResponse

    fun getCaption(
        taskId: UUID,
        async: Boolean,
    ): ImageCaptionResponse
}
