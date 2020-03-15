package `in`.nakul

import `in`.nakul.server.main
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
  embeddedServer(Netty, port = 8080) {
    main()
  }.start(wait = true)
}
