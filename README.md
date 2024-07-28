<a id="readme-top"></a>

<!-- PROJECT SHIELDS -->
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#how-to-explore">How To Explore The Repository</a></li>
      </ul>
    </li>
    <li>
      <a href="#project-implementation">Project Implementation</a>
      <ul>
        <li><a href="#git-workflow">Git Workflow</a></li>
        <li><a href="#hexagonal-architecture">Hexagonal Architecture</a></li>
        <li><a href="#testing">Testing</a></li>
        <li><a href="#project-configuration">Project Configuration</a></li>
      </ul>
    </li>
    <li>
      <a href="#usage">Usage</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#run-the-application">Run The Application</a></li>
        <li><a href="#use-the-application">Use The Application</a></li>
        <li><a href="#docker-image">Docker Image</a></li>
        <li><a href="#live-demo">Live Demo</a></li>
      </ul>
    </li>
    <li><a href="#further-work">Further Work</a></li>
    <li><a href="#contribution">Contribution</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## ABOUT THE PROJECT
<a id="about-the-project"></a>

This project is a technical demonstration of how to build a simple project using Kotlin and Spring Boot. This project also wants to reflect clean code and clean architecture techniques and best practices.

The goal of the app is to display all the albums hosted in JSON Placeholder API and the details of the photos in those albums.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### BUILT WITH
<a id="built-with"></a>
The application was created using [Spring Initializr](https://start.spring.io/) based on **Spring Boot 3.3.2** and **java 21**.

These are the tools with which the application has been built:

* [![SpringBoot][springboot]][springboot-url]
* [![Kotlin][kotlin]][kotlin-url]
* [![Junit][junit]][junit-url]
* [![Mockito][mockito]][mockito-url]
* [![Swagger][swagger]][swagger-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### How To Explore The Repository
<a id="how-to-explore"></a>

The repository was tagged each time a significant change was made so that the entire development process could be explored.

These are the different tags available:

* [base-project](https://github.com/SaulEiros/album-explorer/tree/base-project): The project created with Spring Initializr
* [base-skeleton](https://github.com/SaulEiros/album-explorer/tree/base-skeleton): Added domain objects, separated the application into layers and added service interfaces.
* [album-service-impl](https://github.com/SaulEiros/album-explorer/commits/album-service-impl/): Added Mockito dependency, added Album Service Unit Tests and Implementation.
* [photo-service-impl](https://github.com/SaulEiros/album-explorer/tree/photo-service-impl): Added unit tests and Photo Service implementation. Lately a small refactoring has been done in [input-adapters-impl](https://github.com/SaulEiros/album-explorer/tree/input-adapters-impl).
* [output-adapters-impl](https://github.com/SaulEiros/album-explorer/tree/output-adapters-impl) Added Integration with JSON Placeholder API.
* [input-adapters-impl](https://github.com/SaulEiros/album-explorer/tree/input-adapters-impl): Added Swagger dependencies and Rest Controllers implementation.
* [dockerized-app](https://github.com/SaulEiros/album-explorer/tree/dockerized-app): Added Docker and Docker Compose files. Added Live Demo and Enhanced README.md.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## PROJECT IMPLEMENTATION
<a id="project-implementation"></a>

This section explores how different aspects of development were addressed for the reader's clarity.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### GIT WORKFLOW
<a id="git-flow"></a>

All the developments were done in a **feature branch** and merged to **main** once the branch scope was complete. Once the changes reach main, a new **tag** is created so that they can be easily located.

Also, Conventional Commits specification ([see more](https://www.conventionalcommits.org/en/v1.0.0/)) was followed.

Perhaps in a real scenario, merging the changes by squashing commits would have been appropriate, but in this case I have chosen to merge all commits so that the entire development process could be seen.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### HEXAGONAL ARCHITECTURE
<a id="hexagonal-architecture"></a>

Hexagonal Architecture, also known as Ports and Adapters Architecture ([read more](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))), is an architectural pattern that aims to create applications with low coupling between its different components.

This allows its components to be easily replaced without compromising other parts of the software. In addition, it also facilitates the maintenance and extension of the code, as well as its testing.

It may seem that for such a simple application it is not necessary. But if, in the future, we would like to add more functionality, integrate with a database to create a real repository of images or add extra functionality, having developed the application following this pattern will make the process much easier.

The main code is separated into the following folder hierarchy:

```bash
main
├── AlbumExplorerApplication.kt
├── application
    ...
├── domain
    ...
└── infra
    ...
```

* **application**: This layer contains the implementation of the application's business logic. It also defines interfaces of input adapters (services that implement the business logic) and output adapters (connections to external repositories and services).
* **domain**: This layer implements the objects that define the core elements of the business.
* **infra**: This layer implements the input and output adapters (The input and output connections with external services.)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### TESTING
<a id="testing"></a>

Unit tests for the application layer were developed using **Junit 5** and **Mockito**. Also, TTD ([see more](https://en.wikipedia.org/wiki/Test-driven_development)) was followed, creating first the test suit and implementing the services later.

To track this during development, you can consult the commit history for the tags [album-service-impl](https://github.com/SaulEiros/album-explorer/commits/album-service-impl/) and [photo-service-impl](https://github.com/SaulEiros/album-explorer/tree/photo-service-impl).

Additionally, to structure the tests, I have tried to follow the **GIVEN/THEN/WHEN** pattern ([see more](https://en.wikipedia.org/wiki/Given-When-Then)). Kotlin has an awesome language feature that allow us to use backticks to write freeform function names. This helps to declare test names that are descriptive of the purpose of the test.

Here is an example of such tests:

```kotlin
@Test
fun `GIVEN an albumId without photos WHEN photos by albumId are requested THEN an empty list is returned`() {
    // GIVEN
    Mockito.`when`(photoRepository.findByAlbumId(1L)).thenReturn(emptyList())

        // WHEN
        val result = photoService.find(1L)

        // THEN
        assertNotNull(result)
        assertTrue(result.isEmpty())
        Mockito.verify(photoRepository, Mockito.times(1)).findByAlbumId(1L)
}
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### PROJECT CONFIGURATION
<a id="project-configuration"></a>

There are a few properties configured for the smooth running of the project:

```properties
spring.application.name=album-explorer
springdoc.writer-with-order-by-keys=true
springdoc.swagger-ui.tags-sorter=alpha
json-placeholder.url=https://jsonplaceholder.typicode.com
```

The most relevant is **json-placeholder.url**, which is injected into the services that consume the JSON Placeholder API.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## USAGE
<a id="usage"></a>

### PREREQUISITES
<a id="prerequisites"></a>

If you want to run the application, you must make sure that you have the following dependencies installed:

* [Java 21](https://www.java.com)
* [Gradle](https://docs.gradle.org/current/userguide/installation.html)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### RUN THE APPLICATION
<a id="run-the-application"></a>

To run the application it is necessary to type the following commands:

```bash
gradle build
```

```bash
gradle bootRun
```

If you only want to run the test, run the following command:

```bash
gradle test
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### USE THE APPLICATION
<a id="use-the-application"></a>

To use the application you can access the Swagger UI. To do so, you can open a browser and go to this url:

```http request
http://localhost:8080/swagger-ui/index.html
```

You can also perform request to the API invoking directly the available endpoints:

#### Get a certain Photo. 

```http request
http://localhost:8080/photos/{{id}}
```

{{id}} Should be a Long. It will return a 404 Error if the photo does not exist.

#### Get all Photos

```http request
http://localhost:8080/photos
```

You can use the query parameter **albumId** to retrieve all the pictures that belongs to that album.

```http request
http://localhost:8080/photos?albumId={{id}}
```

{{id}} Should be a Long. If the album does not exist, It will return an empty list.

#### Get a certain Album

```http request
http://localhost:8080/albums/{{id}}
```

{{id}} Should be a Long. It will return a 404 Error if the album does not exist.

#### Get all albums

```http request
http://localhost:8080/albums
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### DOCKER IMAGE
<a id="docker-image"></a>

To facilitate the deployment of the application, if you have Docker on your system you can run the application with the following command:
```bash
docker-compose up -d
```

Once the container is up and running, you can access it as normal:

On the swagger front end:

```http request
http://localhost:8080/swagger-ui/index.html
```

By querying the api directly, as in this example:

```http request
http://localhost:8080/photos
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### LIVE DEMO
<a id="live-demo"></a>

A live demo is also available without downloading the code. You can consult the swagger panel at the following link:

```http request
http://album-explorer.sauleiros.com/swagger-ui/index.html
```

Or make requests directly to the api as in this example:

```http request
https://album-explorer.sauleiros.com/photos
```

**Note that although the swagger panel is accessible via https, requests will not return content. Enabling SSL connections in Swagger is a work in progress.**

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## FURTHER WORK
<a id="further-work"></a>

#### Activate SSL on Swagger Connections: 

There is currently a problem accessing the Swagger panel in the Live Demo. If accessed via https, it will not be possible to test operations.

#### Integrating a CI/CD flow in github:

It would be great to be able to integrate the changes automatically into the Demo server. For this, a CI/CD plan could be created using the pipelines offered by github.

#### Improving exception handling at the REST layer:

Currently, the exception handling done at the REST layer is limited and could be improved by providing for a larger volume of exceptions.

## Contribution
<a id="contribution"></a>

The project has been developed for training purposes, so if you have any suggestions, you are more than welcome to leave a comment by opening an Issue in the repository or contacting me through my Linkedin.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[issues-shield]: https://img.shields.io/github/issues/sauleiros/album-explorer.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/sauleiros/album-explorer.svg?style=for-the-badge
[license-url]: https://github.com/SaulEiros/album-explorer/blob/input-adapters-impl/LICENSE
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/saul-eiros/

[springboot]: https://img.shields.io/badge/spring_boot-000000?style=for-the-badge&logo=springboot&logoColor=white&color=%236DB33F
[springboot-url]: https://spring.io/projects/spring-boot
[kotlin]: https://img.shields.io/badge/kotlin-000000?style=for-the-badge&logo=kotlin&logoColor=white&color=%237F52FF
[kotlin-url]: https://kotlinlang.org/
[junit]: https://img.shields.io/badge/JUnit%205-000000?style=for-the-badge&logo=junit5&logoColor=white&color=%2325A162
[junit-url]: https://junit.org/junit5/
[mockito]: https://img.shields.io/badge/Mockito-000000?style=for-the-badge&logoColor=white&color=%23FF6633
[mockito-url]: https://site.mockito.org/
[swagger]: https://img.shields.io/badge/Swagger-000000?style=for-the-badge&logo=swagger&logoColor=white&color=%2383B81A
[swagger-url]: https://swagger.io/
