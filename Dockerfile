FROM openjdk:8

ENV SBT_VERSION 0.13.15

RUN \
  apt-get update && \
  apt-get install -y apt-transport-https ca-certificates && \
  echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list && \
  curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add && \
  apt-get update && \
  apt-get install -y redis-server && \
  apt-get install -y sbt && \
  sbt sbtVersion

WORKDIR /livestore

COPY . /livestore
COPY start.sh /livestore
RUN ["chmod", "+x", "/livestore/start.sh"]
CMD /livestore/start.sh


