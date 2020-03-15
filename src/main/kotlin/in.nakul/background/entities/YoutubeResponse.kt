package `in`.nakul.background.entities

data class YoutubeResponse(
    val nextPageToken: String?,
    val items: List<Snippet>
)