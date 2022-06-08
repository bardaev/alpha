# Тестовое задание

## Сборка проекта и запуск контейнера docker

1. ./gradlew bootJar
2. docker build -t alpha:0.0.1 .
3. docker run -d -p 8080:8080 -t alpha:0.0.1
4. Перейти по URL: [http://localhost:8080/api/getGif](http://localhost:8080/getGif)