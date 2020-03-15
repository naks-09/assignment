plugins {
  application
  id("org.jetbrains.kotlin.jvm") version "1.3.61" apply false
  id("com.github.johnrengelman.shadow") version "5.0.0" apply true
}

apply(plugin = "com.github.johnrengelman.shadow")
apply(plugin = "org.jetbrains.kotlin.jvm")

application {
  mainClassName = "in.nakul.MainRunnerKt"
}

group = "in.nakul"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  compile(kotlin("stdlib"))
  implementation(kotlin("stdlib-jdk8"))
  implementation(Libs.youtubeData)

  implementation(Libs.ktorClientOkhttp)
  implementation(Libs.ktorClientGson)

  implementation(Libs.ktorServerCore)
  implementation(Libs.ktorNetty)
  implementation(Libs.ktorServerGson)

  implementation(Libs.postgresql)

  compile("ch.qos.logback:logback-classic:1.2.3")
}

tasks {
  withType<Jar> {
    manifest {
      attributes(mapOf("Main-Class" to application.mainClassName))
    }
    val version = "1.0-SNAPSHOT"

    archiveName = "${application.applicationName}-$version.jar"
  }

}
