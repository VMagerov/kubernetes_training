#! /usr/bin/bash

mvn clean package
docker build -t vladimirmagerov/post_service:1.0.0 ./
docker login
docker push vladimirmagerov/post_service:1.0.0
docker run -ti -e DB_URL='jdbc:postgresql://user_db:5432/posts' -e DB_username='admin' -e DB_PASSWORD='admin1234' -p 8090:8090 vladimirmagerov/post_service:1.0.0
