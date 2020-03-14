package `in`.nakul.background.data

import `in`.nakul.background.entities.Video
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.sql.Connection

class VideosConsumer
constructor(
  private val connection: Connection,
  private val receiveChannel: ReceiveChannel<Video>
) {

  suspend fun invoke() = coroutineScope {
    val persistVideos = PersistVideos(connection)
    while (true) {
      try {
        receiveChannel.receive()
          .also { println("Consuming video: $it") }
          .let { persistVideos.invoke(it) }
      } catch (e: Exception) {
        break
      }
    }
  }
}
