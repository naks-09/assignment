package `in`.nakul.background.youtube

import `in`.nakul.background.entities.Video
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay

suspend fun CoroutineScope.videosProducer(): ReceiveChannel<Video> = produce(capacity = Channel.UNLIMITED) {
  var nextPageToken: String? = null
  val videosFetcher = VideosFetcher()
  while (true) {
    println("starting")
    videosFetcher.makeCall(nextPageToken)
      .also { nextPageToken = it.nextPageToken }
      .also { println("Producing youtube response ${it.nextPageToken}  \t  ${it.items}") }
      .also { it.items.sortedByDescending { it.snippet.publishedAt }.onEach { send(it.snippet) } }
      .nextPageToken
      ?: this.close()
    delay(1000)
  }
}
