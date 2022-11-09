FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /project 
COPY . ./ 
RUN ./gradlew build


FROM eclipse-temurin:17-jre-alpine AS prod
# COPY jar from build stage
COPY --from=build /project/build/libs/api-demo-0.0.1-SNAPSHOT.jar /app.jar
# Goal
ENTRYPOINT [ "java", "-jar", "/app.jar"]
