package com.momiji.api.neural.generation.image.model

data class ImageGenerationRequest(
    val prompt: String,
    val negativePrompt: String?,
)
