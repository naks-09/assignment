plugins {
  kotlin("jvm") version "1.3.61"
}

group = "in.nakul"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation(Libs.youtubeData)

  implementation(Libs.ktorClientOkhttp)
  implementation(Libs.ktorClientGson)

  implementation(Libs.postgresql)
}

tasks {
  compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
  compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
}