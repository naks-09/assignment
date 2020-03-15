package `in`.nakul

import java.sql.DriverManager

object DbConnection {

  private val DB_URL =
    "jdbc:postgresql://localhost:5432/${System.getenv("DB_NAME")}?useUnicode=yes&characterEncoding=UTF-8"

  private val driver = Class.forName("org.postgresql.Driver")
  val conn = DriverManager.getConnection(DB_URL, System.getenv("DB_USER_NAME"), System.getenv("DB_PASSWORD"))
}
