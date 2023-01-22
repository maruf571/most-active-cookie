#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY cookie_log.csv /home/app
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


#
# Package stage
#
FROM adoptopenjdk/openjdk11:jre-11.0.13_8
COPY --from=build /home/app/target/cookiefinder-*.jar /opt/app/cookiefinder.jar

#COPY target/cookiefinder-*.jar /opt/app/cookiefinder.jar
ENTRYPOINT ["java", "-jar","/opt/app/cookiefinder.jar"]

