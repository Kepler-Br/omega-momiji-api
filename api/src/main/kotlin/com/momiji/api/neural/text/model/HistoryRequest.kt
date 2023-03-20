package com.momiji.api.neural.text.model

import com.fasterxml.jackson.annotation.JsonProperty

data class HistoryRequest(
    @JsonProperty("message_type")
    val messageType: MessageType,
    @JsonProperty("prompt")
    val prompt: String? = null,
    @JsonProperty("temperature")
    val temperature: Float? = null,
    @JsonProperty("max_new_tokens")
    val maxNewTokens: Int? = null,
    @JsonProperty("num_beams")
    val numBeams: Int? = null,
    @JsonProperty("repetition_penalty")
    val repetitionPenalty: Float? = null,
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
    @JsonProperty("history")
    val history: List<Message> = emptyList(),
)
