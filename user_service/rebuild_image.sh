#! /usr/bin/bash

mvn clean package
docker build -t vladimirmagerov/user_service:1.0.0 ./
docker login
docker push vladimirmagerov/user_service:1.0.0
docker run -ti -e DB_URL='jdbc:postgresql://user_db:5432/users' -e DB_username='admin' -e DB_PASSWORD='admin1234' -p 8080:8080 vladimirmagerov/user_service:1.0.0
