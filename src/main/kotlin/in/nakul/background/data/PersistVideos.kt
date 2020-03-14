package `in`.nakul.background.data

import `in`.nakul.background.entities.Video
import java.sql.Connection

class PersistVideos
constructor(
  private val connection: Connection
) {
  fun invoke(video: Video) {
    connection
      .prepareStatement("INSERT INTO videocontent (title, description, thumbnails, publishedat) values(?, ?, ?, ?)")
      .apply {
        setString(1, video.title)
        setString(2, video.description)
        setString(3, video.thumbnails.toString())
        setString(4, video.publishedAt)
      }
      .execute()
  }
}

/*
"CREATE TABLE videoContent(" +
"id SERIAL PRIMARY KEY NOT NULL," +
"title varchar(1000)," +
"description varchar(65535)," +
"thumbnails varchar(10000)," +
"publishedAt varchar(100)" +
")"

"CREATE INDEX date_index ON videocontent(publishedat)"

*/
