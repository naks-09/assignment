package `in`.nakul.service

import `in`.nakul.DbConnection
import `in`.nakul.background.entities.Video
import `in`.nakul.service.entities.GetPageRequest
import `in`.nakul.service.entities.GetPageResponse
import com.google.api.services.youtube.model.Thumbnail
import com.google.gson.Gson
import java.lang.Integer.max

class ServeGetPageRequest
constructor(
  private val gson: Gson
) {

  companion object {
    private const val PAGE_SIZE = 5
  }

  fun invoke(request: GetPageRequest): GetPageResponse =
    getFromDb(request.pageToken)

  private fun getFromDb(pageToken: Int): GetPageResponse {
    val rs = makeDbCall(pageToken)
    var videos = mutableListOf<Video>()
    var nextPageToken = pageToken
    while (rs.next()) {
      Video(
        title = rs.getString(2),
        description = rs.getString(3),
        thumbnails = gson.fromJson(rs.getString(4), Thumbnail::class.java),
        publishedAt = rs.getString(5)
      )
        .let { videos.add(it) }
      nextPageToken = max(nextPageToken, rs.getInt(1))
      println(rs.getInt(1))
    }
    println("\n\n\n")
    return GetPageResponse(nextPageToken, videos)
  }

  private fun makeDbCall(pageToken: Int) =
    DbConnection.conn
      .prepareStatement("SELECT id, title, description, thumbnails, publishedat FROM videocontent WHERE id > $pageToken LIMIT $PAGE_SIZE")
      .executeQuery()
}
