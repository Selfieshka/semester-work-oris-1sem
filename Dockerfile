FROM tomcat
WORKDIR /usr/local/tomcat
COPY target/BusinessEfficiency-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
#CMD["catalina.sh","run"]
#LABEL authors="Kirill"
#ENTRYPOINT ["top", "-b"]
