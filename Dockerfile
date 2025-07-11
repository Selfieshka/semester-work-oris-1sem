FROM maven:3.9.9-eclipse-temurin-21-jammy as build

COPY src /home/app/src
COPY pom.xml home/app
RUN mvn -f /home/app/pom.xml clean package

FROM tomcat:10.1.33-jdk21-temurin-jammy

WORKDIR /usr/local/tomcat
COPY --from=build target/BusinessEfficiency-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
