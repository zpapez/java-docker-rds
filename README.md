# java-docker-rds
Connecting to Amazon DocumentDB with RDS CA from a dockerized Java.

## Build and Run

1. Configure your cluster host, user, password, database, and collection in [Main.java](./src/main/java/cz/zpapez/javadockerrds/Main.java)
2. Build a fat jar (see [build.gradle](./build.gradle))
  ```
  ./gradlew clean jar
  ```
3. Build docker image (see [Dockerfile](./Dockerfile))
  ```
  docker build --tag my-prj/service:0.0.0-local --build-arg trustStorePassword=somePassword .
  ```
  This bakes a trust store with RDS CA certificate inside the image using [import_rds_certs.sh](./import_rds_certs.sh)

4. Run the image
  ```
  docker run abcdef12345
  ```
5. This should output the number of entries in your collection.
