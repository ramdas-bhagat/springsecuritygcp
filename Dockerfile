FROM openjdk:17
WORKDIR /app
COPY build/libs/latest/springsecuritygcp-*.jar /app/springsecuritygcp.jar
EXPOSE 7070

# Set the default profile as 'prod'
ENV SPRING_PROFILES_ACTIVE=prod

# Java command to run the application
ENTRYPOINT ["java", "-Xms2g", "-Xmx8g", "-jar", "springsecuritygcp.jar"]