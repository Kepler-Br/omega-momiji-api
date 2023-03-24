package com.momiji.api.bot

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "momiji.clients.bots")
@ConstructorBinding
data class ConfigurationProperties(
    val urls: List<String>
)
