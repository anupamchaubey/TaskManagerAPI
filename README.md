
Task Manager API (Spring Boot + JWT)

📌 Description

A secure Task Manager REST API built using Spring Boot. It allows users to register, login, and manage their tasks with JWT-based authentication. The project follows a layered architecture with DTO abstraction and global exception handling.

🚀 Features

- User Registration & Login
- JWT-based Authentication
- Create, Update, Delete Tasks
- User-specific task management
- Secure REST APIs
- Global Exception Handling
- Input Validation

🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- MySQL / H2 Database
- Maven

📂 Project Structure

- controller → handles API requests
- service → business logic
- repository → database operations
- dto → request/response models
- entity → database models
- security → JWT & authentication logic

🔐 Authentication Flow

1. User logs in with email & password
2. Server validates credentials
3. JWT token is generated
4. Client sends token in Authorization header
5. Backend validates token using filter
6. Access is granted to protected APIs

📡 API Endpoints

Auth APIs

- POST /auth/register → Register user
- POST /auth/login → Login & get token

Task APIs (Protected)

- POST /tasks → Create task
- GET /tasks → Get all tasks
- PUT /tasks/{id} → Update task
- DELETE /tasks/{id} → Delete task

▶️ How to Run

1. Clone the repository
2. Open in IntelliJ / Eclipse
3. Configure database in application.properties
4. Run the Spring Boot application
5. Use Postman to test APIs

🎯 Future Improvements

- Pagination & Sorting
- Role-based authentication
- Docker deployment
