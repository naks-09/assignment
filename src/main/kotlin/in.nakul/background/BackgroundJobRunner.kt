package `in`.nakul.background

import `in`.nakul.DbConnection
import `in`.nakul.background.data.VideosConsumer
import `in`.nakul.background.youtube.videosProducer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class BackgroundJobRunner {
  suspend fun invoke() = coroutineScope {
    clearTable()
    resetIdAutoIncrement()
    videosProducer()
      .let { launch { VideosConsumer(DbConnection.conn, it).invoke() } }
  }

  private fun clearTable() =
    DbConnection.conn
      .prepareStatement("DELETE FROM videocontent")
      .executeUpdate()

  private fun resetIdAutoIncrement() =
    DbConnection.conn
      .prepareStatement("ALTER SEQUENCE videocontent_id_seq RESTART WITH 1")
      .executeUpdate()
}
