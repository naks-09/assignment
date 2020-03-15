object Libs {
    private const val googleApi = "com.google.apis"
    private const val googleApiVersion = "v3-rev20200213-1.30.9"
    const val youtubeData = "$googleApi:google-api-services-youtube:$googleApiVersion"

    private const val ktorVersion = "1.2.4"
    private const val ktorGroup = "io.ktor"
    const val ktorClientOkhttp = "$ktorGroup:ktor-client-okhttp:$ktorVersion"
    const val ktorClientGson = "$ktorGroup:ktor-client-gson:$ktorVersion"

    const val ktorServerCore = "$ktorGroup:ktor-server-core:$ktorVersion"
    const val ktorNetty = "$ktorGroup:ktor-server-netty:$ktorVersion"
    const val ktorServerGson =  "$ktorGroup:ktor-gson:$ktorVersion"

    private const val postgresqlGroup = "org.postgresql"
    private const val postgresqlVersion = "42.2.11.jre7"
    const val postgresql = "$postgresqlGroup:postgresql:$postgresqlVersion"
}