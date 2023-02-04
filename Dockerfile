FROM alpine:3.17

LABEL maintainer=”walter”

# Expose Web Port
EXPOSE 8080

# Set environment
ENV JAVA_HOME /opt/jdk
ENV PATH ${PATH}:${JAVA_HOME}/bin
ENV JAVA_PACKAGE server-jre

ENV TOMCAT_VERSION_MAJOR 10
ENV TOMCAT_VERSION_FULL  10.1.4
ENV CATALINA_HOME /opt/tomcat

# Download and install Java
RUN apk --update add openjdk17-jre &&\
    mkdir -p /opt/jdk &&\
    ln -s /usr/lib/jvm/java-17-openjdk/bin /opt/jdk

# Download and install Tomcat
RUN apk add --update curl &&\
  curl -LO https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_VERSION_MAJOR}/v${TOMCAT_VERSION_FULL}/bin/apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz &&\
  curl -LO https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_VERSION_MAJOR}/v${TOMCAT_VERSION_FULL}/bin/apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz.sha512 &&\
  sha512sum -c apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz.sha512 &&\
  gunzip -c apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz | tar -xf - -C /opt &&\
  rm -f apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz apache-tomcat-${TOMCAT_VERSION_FULL}.tar.gz.sha512 &&\
  ln -s /opt/apache-tomcat-${TOMCAT_VERSION_FULL} /opt/tomcat &&\
  rm -rf /opt/tomcat/webapps/examples /opt/tomcat/webapps/ROOT /opt/tomcat/webapps/docs &&\
  apk del curl &&\
  rm -rf /var/cache/apk/*
  ADD target/*.war /opt/tomcat/webapps/ROOT.war 

# Launch Tomcat on startup
CMD ${CATALINA_HOME}/bin/catalina.sh run