# Weather Dashboard — Microservices System
This project is a complete **Java Spring Boot microservices ecosystem** consisting of three independent services that communicate with each other and external systems.  
The system demonstrates secure authentication, user-focused data handling, and integration with third-party APIs.

The entire system is fully containerized using **Docker** and orchestrated with **docker-compose**.

---

# Architecture Overview

The system consists of three microservices:

1. **AuthService** — Authentication & JWT token generation  
2. **ProfileService** — User profile features & inter-service communication  
3. **WeatherService** — Integration with external weather API  

Each service has its own:

- Spring Boot project  
- Dedicated Dockerfile  
- Independent runtime environment  
- Isolated PostgreSQL database (where needed)

Containers communicate over an internal Docker network using service names (e.g., `auth-service`, `profile-service`, `weather-service`).

---

# AuthService  
### **Purpose:**  
Provides authentication and JWT-based security for the whole system.

### **Main responsibilities:**
- User registration and login  
- Password hashing  
- JWT token generation  
- JWT validation endpoint used by other microservices  
- Stateless security (no sessions, no cookies)  
- Own PostgreSQL database for storing users  

---

# ProfileService  
### **Purpose:**  
Acts as the user-facing service that aggregates data and requires authentication.

### **Main responsibilities:**
- Uses AuthService to validate JWT tokens  
- Retrieves and processes user-related data  
- Communicates with WeatherService to fetch weather results  
- Own PostgreSQL database  
- Includes endpoints that require authentication via `Authorization: Bearer <token>`  
- Shows how microservices are supposed to collaborate internally  

### **Why this service is important:**  
It demonstrates real microservice interaction:
- ProfileService → AuthService (token validation)  
- ProfileService → WeatherService (weather data)  

It acts as the middle layer of the whole system.

---

# WeatherService  
### **Purpose:**  
Provides weather data from an external API.

### **Main responsibilities:**
- Makes HTTP requests to a real external weather API  
- Accepts requests from ProfileService  
- No database needed  
- Pure logic + external integration  

### **Why this service is important:**  
Shows how to integrate external APIs within microservices.

---

### Microservice Communication Flow

1. **User/Postman** sends a request to **ProfileService (localhost:8081)**.

2. ProfileService sends the JWT token to **AuthService (localhost:8082)**  
   to verify whether the token is valid.

3. If the token is valid, ProfileService requests weather data from  
   **WeatherService (localhost:8080)**.

4. ProfileService combines the authentication result and the weather data  
   and returns the final response to the user.

---

### Internal Docker communication:

- ProfileService → AuthService  
  `http://auth-service:8082`

- ProfileService → WeatherService  
  `http://weather-service:8080`

---

### External communication (Postman / browser):

- ProfileService → `http://localhost:8081`  
- AuthService → `http://localhost:8082`  
- WeatherService → `http://localhost:8080`


# Dockerization

Each service has its own **Dockerfile**:

```
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/<service>.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

At the top level, one **docker-compose.yml** orchestrates:

- 2 PostgreSQL databases  
- 3 Spring Boot microservices  
- Environment variable injection  
- Networking setup  
- Port mapping  
- Volumes for persistent DB data  

The system includes a `.env` file for secrets:

```
DB_USERNAME=...
DB_PASSWORD=...
JWT_SECRET=...
WEATHER_API_KEY=...
```

All sensitive values are externalized and not stored in code.

---

# Running the System

### 1. Build all microservices:
```
cd AuthService
./mvnw clean package -DskipTests

cd ../ProfileService
./mvnw clean package -DskipTests

cd ../WeatherService
./mvnw clean package -DskipTests
```

### 2. Start the full system:
```
docker-compose up --build
```

### 3. Access the services (Postman):

WeatherService -> http://localhost:8080 
ProfileService -> http://localhost:8081 
AuthService -> http://localhost:8082 

---

# Testing Flow

### 1. Register a new user  
POST http://localhost:8082/auth/register

Body:
```json
{
  "username": "12341234",
  "password": "secret"
}

```

### 2. Login  
POST http://localhost:8082/auth/login

Body:
```json
{
  "username": "12341234",
  "password": "secret"
}

```

You receive a JWT token.

### 3. Call ProfileService with JWT  
```
GET http://localhost:8081/profile/weather/{city}
```

Headers:
```
Authorization: Bearer <token>
```

ProfileService internally:

- validates token via AuthService  
- fetches weather data via WeatherService  
- returns combined result  

---

# Technologies Used

- **Java 21**
- **Spring Boot 3**
- **Spring Security**
- **JWT**
- **PostgreSQL**
- **Docker & Docker Compose**
- **RestTemplate / WebClient**
- **External APIs**
- **Microservice communication (HTTP)**  
- **Environment variable configuration via .env**

---

# 📦 Summary

This project demonstrates a fully operational **microservices architecture** with:

- secure authentication  
- internal service-to-service communication  
- isolated databases  
- external API integration  
- complete Docker orchestration  
- environment variable–based configuration  
- clean separation of responsibilities  

It provides a solid foundation for real-world cloud-native applications.
