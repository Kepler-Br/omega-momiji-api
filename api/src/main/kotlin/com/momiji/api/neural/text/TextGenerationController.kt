package com.momiji.api.neural.text

import com.momiji.api.neural.common.model.TaskScheduledResponse
import com.momiji.api.neural.text.model.HistoryRequest
import com.momiji.api.neural.text.model.HistoryResponse
import com.momiji.api.neural.text.model.RawRequest
import com.momiji.api.neural.text.model.RawResponse
import java.util.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader


interface TextGenerationController {

    @PostMapping("/history-prompts")
    fun requestGenerationFromHistory(
        @RequestBody content: HistoryRequest,
    ): TaskScheduledResponse

    @GetMapping("/history-prompts/{promptId}")
    fun getGeneratedFromHistory(
        @PathVariable promptId: UUID,
        @RequestHeader("Async") async: Boolean,
    ): HistoryResponse

    @PostMapping("/raw-prompts")
    fun requestGenerationFromRaw(
        @RequestBody content: RawRequest,
    ): TaskScheduledResponse

    @GetMapping("/raw-prompts/{promptId}")
    fun getGeneratedFromRaw(
        @PathVariable promptId: UUID,
        @RequestHeader("Async") async: Boolean,
    ): RawResponse
}
