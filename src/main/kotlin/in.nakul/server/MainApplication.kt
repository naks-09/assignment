package `in`.nakul.server

import `in`.nakul.background.BackgroundJobRunner
import `in`.nakul.service.entities.GetPageRequest
import `in`.nakul.service.ServeGetPageRequest
import com.google.gson.Gson
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.main() {

//  BackgroundJobRunner().invoke()
  install(ContentNegotiation) {
    gson { }
  }

  routing {
    get("getPage") {
      try {
        call.request.queryParameters["pageToken"]
          ?.let { it.toInt() } ?: 0
          .also { println("\n\n\n $it \n\n\n") }
          .let { GetPageRequest(it) }
          .let { ServeGetPageRequest(Gson()).invoke(it) }
          .let { call.respond(HttpStatusCode.OK, it) }
      } catch (e: Exception) {
        call.respond(HttpStatusCode.BadRequest, "invalid next page token")
      }
    }
  }
}
