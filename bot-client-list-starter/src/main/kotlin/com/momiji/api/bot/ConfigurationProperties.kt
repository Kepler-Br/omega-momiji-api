package com.momiji.api.bot

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "momiji.clients.bots")
data class ConfigurationProperties(
    var urls: List<String>
)
