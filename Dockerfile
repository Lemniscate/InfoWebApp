FROM java:7
VOLUME /tmp
ADD target/info-webapp.jar info-webapp.jar
RUN bash -c 'touch /info-webapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/info-webapp.jar"]