FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY build/libs/latest/springsecuritygcp-*.jar /app/springsecuritygcp.jar
EXPOSE 7070

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-Xms2g", "-Xmx8g", "-jar", "/app/springsecuritygcp.jar"]
