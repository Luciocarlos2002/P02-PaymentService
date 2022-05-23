FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-paymentService.jar
ENTRYPOINT ["java","-jar","/ms-paymentService.jar"]