package com.aimusic.pocketmusician

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class URLClass(
    @SerialName("regular")
    val regular: String
)

@Serializable
data class ResultClass(
    @SerialName("urls")
    val urls: URLClass
)

@Serializable
data class APIResponse(
    @SerialName("results")
    val results: List<ResultClass>
)