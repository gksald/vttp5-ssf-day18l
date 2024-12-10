FROM openjdk:23-jdk-oracle AS compiler

ARG COMPILE_DIR=/code_folder

WORKDIR ${COMPILE_DIR}

COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn

# RUN chmod a+x ./mvnw
RUN ./mvnw clean package -Dmaven.skip.tests=true

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

# ENTRYPOINT ./mvnw spring-boot:RUN
ENTRYPOINT ["java", "-jar", "target/vttp5-ssf-day18l-0.0.1-SNAPSHOT.jar"]

# stage 2
FROM openjdk:23-jdk-oracle

ARG DEPLOY_DIR=/app

WORKDIR ${DEPLOY_DIR}

COPY --from=compiler /code_folder/target/vttp5-ssf-day18l-0.0.1-SNAPSHOT.jar vttp5-day18l.jar

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 CMD curl -s -f http://localhost:3000/demo/health || exit 1

ENTRYPOINT ["java", "-jar", "vttp5-day18l.jar"]