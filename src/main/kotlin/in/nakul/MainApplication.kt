package `in`.nakul

import `in`.nakul.background.data.VideosConsumer
import `in`.nakul.background.youtube.videosProducer
import kotlinx.coroutines.*
import java.sql.Connection
import java.sql.DriverManager

class MainApplication {
  private val conn: Connection

  companion object {
    private const val DB_URL = "jdbc:postgresql://localhost:5432/youtubedata?useUnicode=yes&characterEncoding=UTF-8"
  }

  init {
    Class.forName("org.postgresql.Driver")
    conn = DriverManager.getConnection(DB_URL, "postgres", "")
  }

  suspend fun invoke() = coroutineScope {
    videosProducer()
      .let { launch { VideosConsumer(conn, it).invoke() } }
  }
}

fun main() {
  runBlocking {
    MainApplication().invoke()
  }
}

