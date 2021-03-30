# -------------------------------------------------------------
# - DOCKERFILE
# - AUTOR: Celula Digital
# - FECHA: 01-Enero-2021
# - DESCRIPCION: Dockerfile que prepara la la mediacion con el bus
# -------------------------------------------------------------

# escape=\ (backslash)
# Imagen base del Docker Registry para compilar nuestra servicio de kid de bienvenida
# Build Stage
FROM maven:3.6.3-ibmjava-8-alpine AS builder
WORKDIR /build/
COPY pom.xml .
COPY ./src ./src
RUN mvn clean package -Dmaven.test.skip=true

# Run Stage
FROM openjdk:8-jre-alpine

# Parametrizacion del nombre del archivo que genera spring boot
ARG JAR_FILE=events-service.jar
ARG BUILD_DATE
ARG BUILD_VERSION
ARG BUILD_REVISION

ENV APP_HOME="/app" \
	UPLOAD_DIR="/tmp/upload" \
	JAVA_OPTS="" \
	SSL_OSB="/app/ssl" \
	HTTP_PORT=8091

# Creando directorios de la aplicacion y de carga temporal de los archivos
RUN mkdir $APP_HOME && mkdir -p $UPLOAD_DIR && mkdir -p $SSL_OSB
	
# Informacion de la persona que mantiene la imagen
LABEL org.opencontainers.image.created=$BUILD_DATE \
	  org.opencontainers.image.authors="Manuel Bohoquez | Wilman Ortiz Navarro " \
	  org.opencontainers.image.url="https://github.com/wortiz1027/alfa-events/blob/master/Dockerfile" \
	  org.opencontainers.image.documentation="" \
	  org.opencontainers.image.source="https://github.com/wortiz1027/alfa-events/blob/master/Dockerfile" \
	  org.opencontainers.image.version=$BUILD_VERSION \
	  org.opencontainers.image.revision=$BUILD_REVISION \
	  org.opencontainers.image.vendor="Seguros Alfa | https://www.segurosalfa.com.co/" \
	  org.opencontainers.image.licenses="" \
	  org.opencontainers.image.title="Servicio que env+ia eventos al servicio mediador" \
	  org.opencontainers.image.description="El siguiente servicio tiene como finalidad preparar y la comunicacion que se tiene aws con el bus"

# Puerto de exposicion del servicio
EXPOSE $HTTP_PORT

# Copiando el compilado desde builder
COPY --from=builder /build/target/$JAR_FILE $APP_HOME/
COPY ./src/main/resources/ssl $SSL_OSB

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ${APP_HOME}/events-service.jar"]
