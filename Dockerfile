FROM maven:3.8.5-openjdk-17

WORKDIR /game-match-app
COPY . .
RUN mvn -DskipTests clean install

CMD mvn spring-boot:run -D spring-boot.run.profiles=prod