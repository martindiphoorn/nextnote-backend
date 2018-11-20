FROM openjdk:8
LABEL author="Martin Diphoorn"

RUN apt-get update && apt-get install --no-install-recommends -y mariadb-client unzip && rm -rf /var/lib/apt/lists/*;

VOLUME /tmp

ADD target/*.jar nxt-note.jar
ADD docker-files/starter.sh /opt/starter.sh

RUN chmod +x /opt/starter.sh

HEALTHCHECK --interval=5s --timeout=2s --retries=12 \
  CMD curl --silent --fail http://localhost:8080/api/management/health || exit 1

ENTRYPOINT ["/opt/starter.sh","java","-jar","nxt-note.jar"]