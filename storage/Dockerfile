FROM openjdk:8-jre
MAINTAINER yuanzhibin
# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/myservice/lib
# Add the service itself

ADD target/samples-storage-1.1.0.jar /usr/share/myservice/myservice.jar

ENTRYPOINT ["/usr/local/openjdk-8/bin/java", "-jar", "/usr/share/myservice/myservice.jar"]
