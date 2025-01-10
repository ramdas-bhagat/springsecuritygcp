# springsecuritygcp
Spring security with GCP cloud run test.

## Steps to run in docker

1. docker build -t springsecuritygcp:0.0.1 .
2. docker run -d -p 7070:7070 --name springsecuritygcp springsecuritygcp:0.0.1

## For checkstyle

1. ./gradlew checkstyleMain
2. ./gradlew checkstyleTest

## For spotless

1. ./gradlew spotlessCheck
2. ./gradlew spotlessApply

