FROM openjdk:12-alpine

# Install needed packages
RUN apk update \
        && apk upgrade \
        && apk --no-cache add curl openssl perl

# add truststore needed for connection to DocumentDB cluster
COPY import_rds_certs.sh /certs/import_rds_certs.sh
ARG trustStorePassword
RUN /certs/import_rds_certs.sh $trustStorePassword

ENV TRUST_STORE_PASSWORD=$trustStorePassword

# copy app
COPY build/libs/java-docker-rds.jar /java-docker-rds.jar

CMD java -Djavax.net.ssl.trustStore=/certs/rds-truststore.jks -Djavax.net.ssl.trustStorePassword=$TRUST_STORE_PASSWORD -jar java-docker-rds.jar
