package com.momiji.api.neural.text.api

import com.momiji.api.neural.text.api.model.HistoryRequest
import com.momiji.api.neural.text.api.model.HistoryResponse
import com.momiji.api.neural.text.api.model.RawRequest
import com.momiji.api.neural.text.api.model.RawResponse
import java.util.UUID
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody


interface TextGenerationController {

    @PutMapping("/history-prompts/{promptId}")
    fun generateFromHistory(
        @RequestBody content: HistoryRequest,
        @PathVariable promptId: UUID,
    ): HistoryResponse

    @PutMapping("/raw-prompts/{promptId}")
    fun generateFromRaw(
        @RequestBody content: RawRequest,
        @PathVariable promptId: UUID,
    ): RawResponse
}
