package `in`.nakul.background

import `in`.nakul.DbConnection
import `in`.nakul.background.data.VideosConsumer
import `in`.nakul.background.youtube.videosProducer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class BackgroundJobRunner {
  suspend fun invoke() = coroutineScope {
    DbConnection.conn
      .prepareStatement("delete from videocontent")
      .executeUpdate()
    videosProducer()
      .let { launch { VideosConsumer(DbConnection.conn, it).invoke() } }
  }
}