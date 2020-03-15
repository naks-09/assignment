**Setting up environment variables:**

Change/Add the following variables in the '.env' files
- export DB_USER_NAME=""
- export DB_PASSWORD=""
- export DB_NAME=""
- export YOUTUBE_API_KEY=""

**Setting up the database**

- Create table using the following command
    CREATE TABLE videoContent(
        id SERIAL PRIMARY KEY NOT NULL,
        title varchar(1000),
        description varchar(65535),
        thumbnails varchar(10000),
        publishedAt varchar(100)
   )
- Create the following index
    CREATE INDEX id_index ON videocontent(id)

**Running instructions**

- source .rnv
- ./gradlew clean build
- java -jar ./build/libs/assignment-1.0-SNAPSHOT.jar
