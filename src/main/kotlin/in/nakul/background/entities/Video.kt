package `in`.nakul.background.entities

import com.google.api.services.youtube.model.Thumbnail

data class Video(
    val title: String,
    val description: String,
    val publishedAt: String,
    val thumbnails: Thumbnail
)