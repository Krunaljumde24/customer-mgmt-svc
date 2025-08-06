FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/customer-mgmt-svc-v1.00.jar customer-mgmt-svc-v1.00.jar
ENTRYPOINT ["java","-jar","customer-mgmt-svc-v1.00.jar"]