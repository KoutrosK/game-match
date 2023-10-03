# Game Match Odds REST api

Implementation of a REST api using two entities Match and MatchOdd

## Run the application
PostgreSQL must be installed and configured before running the application
The properties that should be considered are following

* username
* password
* url

The properties must be set in the application-prod.yml accordingly

### Without Docker
To run the application without Docker, Java 17 & maven must be configured.
Start the application using the following command

`mvn spring-boot:run -D spring-boot.run.profiles=prod`

### With Docker
Docker must be installed and configured.
Using the following command `docker-compose up`, the DockerFile image of the application should be created and the dependencies(Java, Postgresql) should be configured as well.

## Endpoints

### Without Docker

- api: [http://localhost:8080/game-match/api](http://localhost:8080/game-match/api)
- swagger: [http://localhost:8080/game-match/api/swagger-ui/index.html#/](http://localhost:8080/game-match/api/swagger-ui/index.html#/)

### With Docker

- api: [http://localhost:8081/game-match/api](http://localhost:8081/game-match/api)
- swagger: [http://localhost:8081/game-match/api/swagger-ui/index.html#/](http://localhost:8081/game-match/api/swagger-ui/index.html#/)

### Postman collection
A collection (`./game_match_odds.json`) of requests is also provided.
Please make sure to update the URL accordingly based on whether the application is running on Docker or not.