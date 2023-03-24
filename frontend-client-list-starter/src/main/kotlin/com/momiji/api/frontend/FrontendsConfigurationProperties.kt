package com.momiji.api.frontend

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("momiji.clients.frontends")
@ConstructorBinding
data class FrontendsConfigurationProperties(
    val urls: Map<String, String>,
)
