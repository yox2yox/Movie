package com.ingentity.movie.util

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class ContentItem(val image: String, val contentsText: List<String>)

@Serializable
data class ContentList(val contents: List<ContentItem>)

fun parseContent(jsonString: String): ContentList {
    return Json.decodeFromString(jsonString)
}
