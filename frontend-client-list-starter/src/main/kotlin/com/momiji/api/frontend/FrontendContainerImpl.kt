package com.momiji.api.frontend

import com.momiji.api.frontend.exception.FrontendNotFoundException

class FrontendContainerImpl(
    private val clients: Map<String, FrontendClient>,
) : FrontendContainer {
    override fun withFrontend(frontend: String): FrontendContainer.WithFrontend {
        return FrontendContainer.WithFrontend(getFrontend(frontend))
    }

    override fun getFrontend(frontend: String): FrontendClient {
        if (frontend !in clients) {
            throw FrontendNotFoundException(frontend)
        }

        return clients[frontend]!!
    }

    override fun getFrontendNames(): List<String> {
        return clients.keys.toList()
    }
}
