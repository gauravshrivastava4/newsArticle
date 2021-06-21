# News Article API's

## Reference Documentation

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.news.article.ArticleApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Access application
Application be accessed using url http://localhost:8080/

## API Documentation
Documentation available on http://localhost:8080/api-docs.html

### /api/createArticle
curl -X 'POST' \
'http://localhost:8080/api/createArticle' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"title": "string",
"text": "string"
}'

### /api/updateArticle
curl -X 'PUT' \
'http://localhost:8080/api/updateArticle' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"id": 0,
"title": "string",
"text": "string"
}'

### /api/getNewsArticle/{id}
curl -X 'GET' \
'http://localhost:8080/api/getNewsArticle/1' \
-H 'accept: */*'

### /api/getAllNewsArticles/
curl -X 'GET' \
'http://localhost:8080/api/getAllNewsArticles' \
-H 'accept: */*'