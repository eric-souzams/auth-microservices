<h1 align="center">
  Auth Microservices
</h1>

<p align="center">
  <a href="#routes">Routes</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#documentation">Documentation</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#technologies">Technologies</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#building">Building</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#docker">Docker</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#contributing">Contributing</a>
</p>


## Routes
| Method | URL                                     | Description         |
|--------|-----------------------------------------|---------------------|
| GET    | http://localhost:8080/api/accounts/{id} | Get Account Details |
| GET    | http://localhost:8090/api/loans/{id}    | Get Loan Details    |
| GET    | http://localhost:9000/api/cards/{id}    | Get Cards Details   |

## Documentation
###

| URL                                                                          | Description                    |
|------------------------------------------------------------------------------|--------------------------------|


## Technologies
This project was developed using the following technologies:
- [Spring Boot](https://spring.io/)
- [Spring Data JPA]()
- [Spring Boot Actuator]()
- [Spring Cloud Config Server]()
- [Spring Cloud Config Client]()
- [Spring Cloud Netflix Eureka Server]()
- [Spring Cloud Netflix Eureka Client]()
- [Spring Cloud Circuit Breaker - Resilience4j]()
- [Spring Cloud Sleuth]()
- [Zipkin]()
- [H2 Database]()


## Building
You'll need [Java 11+](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html) and [Maven](https://maven.apache.org/download.cgi) installed on your computer in order to build this app.
```bash
$ git clone https://github.com/eric-souzams/auth-microservices.git
$ cd auth-microservices

$ cd {service-name}
$ mvn install

after install all applications
$ cd {service-name}
$ mvn spring-boot:run
```


## Docker
You'll need [Docker](https://www.docker.com/) installed on your computer in order to build this app.
```bash
$ git clone https://github.com/eric-souzams/auth-microservices.git
$ cd auth-microservices

$ cd {service-name}
$ mvn install
$ mvn package

after package all applications
$ docker-compose up
```


## Contributing
This repository is currently under development. If you want to contribute please fork the repository and get your hands dirty, and make the changes as you'd like and submit the Pull request.