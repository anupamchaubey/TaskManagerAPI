# Taskify

A secure RESTful API built with **Spring Boot 3+** and **Java 21** for managing tasks. This backend service allows users to register, securely log in, and manage their personal tasks. It features robust JWT-based authentication, object-relational mapping via Spring Data JPA, and comprehensive global exception handling.

## 🚀 Key Features

* **Secure Authentication:** User registration and login utilizing Spring Security, BCrypt password hashing, and stateless JSON Web Tokens (JWT).
* **User-Isolated Task Management:** Full CRUD (Create, Read, Update, Delete) operations for tasks. A user can only view and modify their own tasks.
* **Input Validation:** Strict payload validation using Jakarta Bean Validation to ensure data integrity (e.g., enforcing valid emails and password constraints).
* **Global Exception Handling:** Clean, structured JSON error responses mapped to appropriate HTTP status codes using `@RestControllerAdvice`.
* **Layered Architecture:** Clear separation of concerns utilizing Controllers, Services, Repositories, Entities, and DTOs. Includes a dedicated `TaskMapper` for clean data transfer.

## 🛠️ Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot (v4.0.5)
* **Security:** Spring Security & jwt (v0.11.5)
* **Database:** MySQL & Spring Data JPA (Hibernate)
* **Boilerplate Reduction:** Lombok
* **Build Tool:** Maven

## 📂 Project Structure

```
src/main/java/com/anupamchaubey/Taskify/
├── config/         # Security, JWT utilities, and custom UserDetails
├── controller/     # REST API endpoints (AuthController, TaskController)
├── dto/            # Data Transfer Objects for requests and responses
├── exceptions/     # Custom exceptions and GlobalExceptionHandler
├── mapper/         # Component logic mapping Entities <-> DTOs
├── model/          # JPA Database Entities (User, Task)
├── repository/     # Spring Data JPA Interfaces
└── service/        # Core business logic and database interactions
```

## 📡 API Endpoints

### Public Endpoints (Authentication)

| Method | Endpoint | Description | Request Body |
| :--- | :--- | :--- | :--- |
| **POST** | `/auth/register` | Register a new user | `{"name": "...", "email": "...", "password": "..."}` |
| **POST** | `/auth/login` | Authenticate & get JWT | `{"email": "...", "password": "..."}` |


### Protected Endpoints (Task Management)

> **Authorization Required:** `Bearer <JWT_TOKEN>` header must be included in all requests.

| Method | Endpoint | Description | Request Body |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/tasks` | Get all tasks for the logged-in user | *None* |
| **POST** | `/api/tasks` | Create a new task | `{"taskName": "...", "taskDescription": "...", "deadline": "..."}` |
| **PUT** | `/api/tasks/{taskId}` | Update an existing task | `{"taskName": "...", "taskDescription": "...", "deadline": "..."}` |
| **DELETE** | `/api/tasks/{taskId}` | Delete a task | *None* |

## ⚙️ Setup and Installation

### Prerequisites
* **Java 21** or higher installed.
* **MySQL Server** running on your local machine.
* **Maven** installed (or use the included `mvnw` wrapper).

---

### 1. Database Configuration

Create a new MySQL database named `taskmanager`:

```sql
CREATE DATABASE taskmanager;
```

## Properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager
spring.datasource.username=root
spring.datasource.password=root
```
## Build and Run

### Mac/Linux:
```
./mvnw clean install
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Windows:
```
mvnw.cmd clean install
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
```

```
💡 Note: The server will start on http://localhost:8080. The dev profile is active by default.
```