package `in`.nakul

import java.sql.Connection
import java.sql.DriverManager

object DbConnection {

  private const val DB_URL = "jdbc:postgresql://localhost:5432/youtubedata?useUnicode=yes&characterEncoding=UTF-8"

  private val driver = Class.forName("org.postgresql.Driver")
  val conn = DriverManager.getConnection(DB_URL, "postgres", "")
}