package `in`.nakul.service.entities

import `in`.nakul.background.entities.Video

data class GetPageResponse(
  val nextPageToken: Int,
  val videos: List<Video>
)