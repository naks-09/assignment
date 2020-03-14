package `in`.nakul.background.youtube

import `in`.nakul.background.entities.YoutubeResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class VideosFetcher
constructor() {

  companion object {
    private const val BASE_URL = "www.googleapis.com/youtube/v3"
    private const val REL_PATH = "search"
    private const val SEARCH_KEY = "cricket"
    private const val DATE_AFTER = "2020-03-14T00:00:00Z"
    private const val API_KEY = "AIzaSyBiVpZMvQXOE8QShPTl67So4U_xBmjQny8"
  }

  suspend fun makeCall(pageToken: String? = null) =
    HttpClient(OkHttp) { install(JsonFeature) { serializer = GsonSerializer() } }
      .get<YoutubeResponse>(scheme = "https", host = BASE_URL, path = REL_PATH) {
        parameter("part", "snippet")
        parameter("type", "video")
        parameter("order", "date")
        parameter("q", SEARCH_KEY)
        parameter("publishedAfter", DATE_AFTER)
        parameter("key", API_KEY)
        pageToken
          ?.let { parameter("pageToken", it) }
      }
}
