package com.momiji.api.neural.text.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GenerationParams(
    @JsonProperty("temperature")
    val temperature: Float? = null,
    @JsonProperty("max_new_tokens")
    val maxNewTokens: Int? = null,
    @JsonProperty("num_beams")
    val numBeams: Int? = null,
    @JsonProperty("repetition_penalty")
    val repetitionPenalty: Float? = null,
    @JsonProperty("no_repeat_ngram_size")
    val noRepeatNgramSize: Int? = null,
    @JsonProperty("early_stopping")
    val earlyStopping: Boolean? = null,
    @JsonProperty("seed")
    val seed: Long? = null,
    @JsonProperty("top_k")
    val topK: Int? = null,
    @JsonProperty("top_p")
    val topP: Float? = null,
    @JsonProperty("bad_words")
    val badWords: List<String> = emptyList(),
)
