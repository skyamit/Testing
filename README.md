## Order Service - Testing Project (JUnit + Mockito)

## Overview

This project demonstrates how to implement **unit testing, controller testing, and integration testing** in a Spring Boot application using real-world examples.

The application simulates an **Order Management System** where users can place orders, and the system interacts with product and payment services.

---

## Tech Stack

- Java 17+
- Spring Boot
- JUnit 5
- Mockito
- Spring Boot Test
- H2 Database
- MockMvc

---

## Project Structure

src/
 ├── main/
 │   ├── controller/
 │   ├── service/
 │   ├── repository/
 │   ├── model/
 │   └── dto/
 │
 └── test/
     ├── controller/
     ├── service/
     └── integration/

---

## Features

- Place order API
- Request validation
- Exception handling
- External service mocking
- Database interaction testing

---

## Testing Strategy

### 1. Unit Tests (Service Layer)

- Uses Mockito
- Mocks dependencies (Repository, External Clients)
- Tests business logic in isolation

Example:
- `OrderServiceTest`

---

### 2. Controller Tests

- Uses `@WebMvcTest`
- Uses MockMvc to simulate HTTP requests
- Mocks service layer using `@MockBean`

Example:
- `OrderControllerTest`

---

### 3. Integration Tests

- Uses `@SpringBootTest`
- Loads full application context
- Uses H2 in-memory database

Example:
- `OrderIntegrationTest`

---

## Setup & Run

### 1. Clone Repository

```bash
git clone <repo-url>
cd order-service
2. Run Application
mvn spring-boot:run
3. Run Tests
mvn test
### H2 Database
In-memory database used for testing
Console URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (empty)
```
### Key Annotations
@SpringBootTest → Full context (integration test)
@WebMvcTest → Controller layer test
@DataJpaTest → Repository test
@Mock → Mockito mock
@MockBean → Mock Spring bean
@InjectMocks → Inject mocks into class
