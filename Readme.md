# Perseo Academy

## Description

**Perseo Academy** is an educational management application built with Spring Boot and Java. It allows users to register, log in, manage course purchases, track professional experiences, and view purchased courses through a RESTful API.

## Features

### User Management
- User registration and authentication.
- User profiles with work experience and purchased courses.
- Role management: Users and Administrators.

  **API Endpoints:**
    - `POST /register`: Register a new user.
    - `POST /login`: Authenticate with email/password or via OAuth.
    - `POST /login/linkedin` or `POST /login/github`: Log in using LinkedIn or GitHub.

### Course Management
- Admins can manage and update courses through a control panel.
- View, create, update, and delete courses.
- Detailed course pricing and descriptions.

  **API Endpoints:**
    - `GET /courses`: List all available courses.
    - `POST /courses` (Super_Admin): Create a new course.
    - `PUT /courses/{id}` (Admins): Update a course.
    - `DELETE /courses/{id}` (Super_Admin): Remove a course.

### Work Experience
- Users can add and manage their work experience, showcasing their professional background.
- View, add, and update work experience linked to a user.

  **API Endpoints:**
    - `GET /experience`: List all work experiences.
    - `POST /experience` (User): Add new work experience.
    - `PUT /experience/{id}` (User): Update work experience.
    - `DELETE /experience/{id}` (User): Remove work experience.

### Purchased Courses
- Tracks courses purchased by users.

  **API Endpoints:**
    - `GET /my_courses`: List all purchased courses.
    - `GET /my_courses/{id}`: View details of a specific purchase by ID.

### Shopping Cart
- Add courses to a shopping cart and proceed with purchases.

  **API Endpoints:**
    - `POST /cart/add/{courseId}`: Add a course to the cart.
    - `GET /cart`: View cart content.
    - `POST /cart`: Complete the purchase.

## Technologies

- **Spring Boot**: Backend development and RESTful API creation.
- **Spring Security**: Authentication with JWT and OAuth (LinkedIn/GitHub).
- **OAuth2**: Social login integration with LinkedIn and GitHub.
- **JWT (JSON Web Tokens)**: Authentication and session handling.
- **MySQL**: Relational database management.
- **Postman**: API testing and automation.

