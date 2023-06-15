package com.momiji.api.neural.common

import com.momiji.api.common.model.BaseResponse
import com.momiji.api.common.model.ResponseStatus
import com.momiji.api.common.model.exception.ServiceResponseException
import java.net.SocketTimeoutException
import java.util.UUID

abstract class AbstractClientService(
    private val timeout: Long,
    private val waitBeforeRequest: Long,
    private val runAsync: Boolean,
) {
    protected fun <T : BaseResponse> requestUntilTimeout(
        taskId: UUID,
        getGeneration: (UUID, Boolean) -> T
    ): T {
        var currentTimeElapsed = 0L

        do {
            currentTimeElapsed += waitBeforeRequest

            val tic = System.currentTimeMillis()

            val response = try {
                getGeneration(taskId, runAsync)
                // Probably not a correct exception
            } catch (ex: SocketTimeoutException) {
                currentTimeElapsed += System.currentTimeMillis() - tic
                continue
            }

            currentTimeElapsed += System.currentTimeMillis() - tic

            when (response.status) {
                ResponseStatus.OK -> return response
                ResponseStatus.NOT_READY,
                ResponseStatus.TOO_MANY_REQUESTS -> {
                    Thread.sleep(waitBeforeRequest)
                    continue
                }

                ResponseStatus.BAD_REQUEST,
                ResponseStatus.INTERNAL_SERVER_ERROR,
                ResponseStatus.NOT_FOUND -> {
                    throw ServiceResponseException(
                        response,
                        "Unexpected response status: ${response.status}"
                    )
                }
            }
        } while (currentTimeElapsed < timeout)

        throw ServiceResponseException("Timeout of $timeout has been reached")
    }
}
