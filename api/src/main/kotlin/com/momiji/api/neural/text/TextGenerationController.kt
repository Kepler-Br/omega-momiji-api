package com.momiji.api.neural.text

import com.momiji.api.common.model.BasicResponse
import com.momiji.api.neural.text.model.HistoryRequest
import com.momiji.api.neural.text.model.HistoryResponse
import com.momiji.api.neural.text.model.RawRequest
import com.momiji.api.neural.text.model.RawResponse
import java.util.UUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody


interface TextGenerationController {

    @PutMapping("/history-prompts/{promptId}")
    fun requestGenerationFromHistory(
        @RequestBody content: HistoryRequest,
        @PathVariable promptId: UUID,
    ): BasicResponse

    @GetMapping("/history-prompts/{promptId}")
    fun getGeneratedFromHistory(
        @PathVariable promptId: UUID,
    ): HistoryResponse

    @PutMapping("/raw-prompts/{promptId}")
    fun requestGenerationFromRaw(
        @RequestBody content: RawRequest,
        @PathVariable promptId: UUID,
    ): BasicResponse

    @GetMapping("/raw-prompts/{promptId}")
    fun getGeneratedFromRaw(
        @PathVariable promptId: UUID,
    ): RawResponse
}
