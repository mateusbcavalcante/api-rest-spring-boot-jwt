# API-REST-SPRING-BOOT-JWT

- Authentication using a JWT for REST APIs
- Spring Boot backend

### Used technologies

* <a href="https://www.java.com/pt_BR/download"> Java 1.8 </a>
* <a href="https://spring.io/projects/spring-boot"> Spring Boot </a>
* <a href="https://spring.io/guides/gs/accessing-data-jpa"> JPA </a>
* <a href="https://jwt.io"> JSON Web Tokens </a>
* <a href="https://www.h2database.com"> H2 Database Engine </a>
* <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html"> Logging </a>
* <a href="https://maven.apache.org"> Maven </a>
* <a href="https://junit.org/junit5/"> JUnit </a>
* <a href="https://swagger.io"> Swagger </a>
* <a href="https://www.docker.com"> Docker </a>

## Pre-requisites
You need to have:
- a Docker installation available locally. See [Docker website](https://docs.docker.com/install/) on installation instructions.

## To run the application
Basic usage with defaults: server IP 127.0.0.1, server port 9081

**1. Docker Pull Command**

`docker pull mateusbcavalcante/movieapp-repo`

**2. Run**

`docker run movieapp`

**3. Documentation Swagger**

`http://<SERVER_IP>:<SERVER_PORT>/swagger-ui.html#/`

**4. Authentication data**

`username=admin | password=admin`


## Client sample

### Authentication

  Returns json data token.

* **URL**

  /auth

* **Method:**

  `POST`
  
*  **Header Params**
  
   **Required:**
 
   None

*  **Request Body**

   **Required:**
 
   `username=[string] | password=[string]`

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

### Search Movies

  Returns json data about a movie.

* **URL**

  /api/v1/movie/{query}

* **Method:**

  `GET`
  
*  **Header Params**
  
   **Required:**
 
   `Authorization=[string]`

*  **URL Params**

   **Required:**
 
   `query=[string]`

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />
  
### Top Rated

  Get the top rated movies.

* **URL**

  /api/v1/movie/top_rated

* **Method:**

  `GET`

*  **Header Params**
  
   **Required:**
 
   `Authorization=[string]`
  
*  **URL Params**

   **Required:**
 
   None

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

### Top favorited movies

  Get the top favorited movies.

* **URL**

  /api/v1/movie/top_favorited

* **Method:**

  `GET`

*  **Header Params**
  
   **Required:**
 
   `Authorization=[string]`
  
*  **URL Params**

   **Required:**
 
   None

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />
  
### Favorited movies by user

  Get the favorited movies by user.

* **URL**

  /api/v1/user/movie/favorites

* **Method:**

  `GET`

*  **Header Params**
  
   **Required:**
 
   `Authorization=[string]`
  
*  **URL Params**

   **Required:**
 
   None

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />
  
### Mark as favorite item

  This method allows you to mark a movie as a favorite item.

* **URL**

  /api/v1/user/mark_as_favorite

* **Method:**

  `POST`

*  **Header Params**
  
   **Required:**
 
   `Authorization=[string]`
  
*  **URL Params**

   **Required:**
 
   `tmdb_movie_id=[integer]`

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />

## Author

* **Mateus Bastos** - <a href="https://github.com/mateusbcavalcante"> GitHub </a>
