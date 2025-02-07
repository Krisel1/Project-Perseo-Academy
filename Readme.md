# Perseo Academy

# 📚 User Course System

## 📖 Description

**Perseo Academy** is an educational management application built with Spring Boot and Java. It allows users to register, log in, manage course purchases, track professional experiences, and view purchased courses through a RESTFULL API.

## 🚀 Features
- ✅ **User registration and authentication**
- ✅ **Work experience management**
- ✅ **Course browsing and purchasing**
- ✅ **Shopping cart management**
- ✅ **Payments via Stripe and PayPal**
- ✅ **Data persistence with MySQL**
- ✅ **Testing with JUnit and Mockito**

## 🛠 Technologies Used
- **Backend:** Java 17, Spring Boot, Spring Data JPA
- **Spring Security**: Authentication with JWT
- **Database:** MySQL
- **Payments:** Stripe, PayPal
- **Testing:** JUnit 5, Mockito, Postman
- **Tools:** Docker, Lombok, Swagger

---

## 📂 Project Structure
```
user-course-system/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── models/        # JPA Entities
│   │   │   ├── controllers/   # REST Controllers
│   │   │   ├── services/      # Business Logic Services
│   │   │   ├── repositories/  # JPA Repositories
│   ├── test/                  # JUnit and Mockito Tests
│
├── pom.xml                     # Maven Dependencies
├── README.md                    # Documentation
```

---

## ⚙️ Setup and Installation

### 🔹 1. Clone the Repository
```bash
git clone https://github.com/Krisel1/Project-Perseo-Academy.git

```

### 🔹 2. Configure MySQL
- Create a database named **db_academy**
- Set the credentials in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_academy
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 🔹 3. Run the Application
```bash
mvn spring-boot:run
```
---

## 📌 API Endpoints
### 📍 Users
| Method | Endpoint             | Description         |
|--------|----------------------|---------------------|
| GET    | `/api/users`         | Get all users       |
| POST   | `/api/auth/register` | Register a new user |
| POST   | `/api/auth/login`    | Authenticate user   |
| POST   | `/api/users`         | Create a new user   |
| DELETE | `/api/users/{id}`    | Delete a user  |

### 📍 Courses
| Method | Endpoint            | Description                 |
|--------|--------------------|-----------------------------|
| GET    | `/api/courses`     | Get all courses             |
| POST   | `/api/courses`     | Create a new course         |

### 📍 Cart
| Method | Endpoint          | Description                   |
|--------|-------------------|------------------------------|
| GET    | `/api/carts`      | View cart contents           |
| POST   | `/api/carts`      | Add a course to the cart     |
| DELETE | `/api/carts/{id}` | Remove a course from the cart |


---

## 🔥 API Requests with Postman

### 1️⃣ Register a User
- **Endpoint:** `POST /api/auth/register`
- **Request Body:**
```json
{
  "username": "krisel",
  "email": "hola@example.com",
  "password": "password",
  "role": "ADMIN"
}
```
###  Login a User
- **Endpoint:** `POST /api/auth/login`
- **Request Body:**
```json
{
  "username": "krisel",
  "password": "password"
}
```
### 🛠️ Expected response:
```json
{
  "token": "eyHUYniJTRz......."
}
```

### 2️⃣ Add a Course to the Cart
- **Endpoint:** `POST /api/carts`
- **Request Body:**
```json
{
  "userId": 1,
  "courseId": 101
}
```

### 3️⃣ Process a Payment
- **Endpoint:** `POST /api/payments`
- **Request Body:**
```json
{
  "userId": 1,
  "paymentMethod": "Stripe",
  "amount": 99.99
}
```
### 4️⃣ Create Course (ADMIN)
- **Endpoint:** `POST /api/courses`
- **Request Body:**
```json
{
"title": "Spring Boot Masterclass",
"description": "Learn Spring Boot from scratch",
"price": 49.99
}
```
### 5️⃣ Update Course (ADMIN o MANAGER)
- **Endpoint:** `PUT /api/courses`
- **Request Body:**
```json
{
"title": "Updated Course Title",
"description": "Updated Description",
"price": 59.99
}
```

---

## ✅ Testing
Run the tests with:
```bash
mvn test
```

---

## 🏗️ Future Enhancements
- [ ] Add email notification support
- [ ] Improve UI with a React frontend

---




