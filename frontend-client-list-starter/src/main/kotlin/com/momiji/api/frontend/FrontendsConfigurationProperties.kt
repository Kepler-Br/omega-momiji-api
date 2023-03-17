package com.momiji.api.frontend

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("momiji.clients.frontends")
data class FrontendsConfigurationProperties constructor(
    var urls: Map<String, String>,
)
