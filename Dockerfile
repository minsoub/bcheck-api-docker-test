FROM centos:7
RUN yum -y update &&  yum -y install initscripts && yum -y install git && yum -y install java-11-openjdk && yum -y install wget && yum -y install telnet && yum -y install net-tools && yum clean all && yum clean metadata && rm -rf /var/cache/yum/*
VOLUME /tmp
ARG JAR_FILE
WORKDIR /
COPY ${JAR_FILE} bcheck-api-docker-test.jar
COPY entrypoint.sh entrypoint.sh
RUN chmod 755 /entrypoint.sh
RUN mkdir /logs
RUN touch /logs/bcheck-app.log
WORKDIR /
EXPOSE 7777/tcp
ENTRYPOINT ["sh", "entrypoint.sh"]