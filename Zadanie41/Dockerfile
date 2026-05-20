FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM postgres:16

ENV POSTGRES_DB=shop
ENV POSTGRES_USER=shop_user
ENV POSTGRES_PASSWORD=shop_password

COPY --from=build /app/target/*.jar /opt/app/
COPY --from=build /app/target/*.sql /docker-entrypoint-initdb.d/

EXPOSE 5432