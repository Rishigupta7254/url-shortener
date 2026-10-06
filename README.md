# 🔗 URL Shortener

A simple and efficient **URL Shortener web application** built using **Spring Boot, React.js, and MySQL**.

The application converts long URLs into short, easy-to-share URLs. It also provides user registration and login functionality using **BCrypt password encryption and JWT-based authentication**.

---

## 🚀 Features

* 🔗 Convert long URLs into short URLs
* 👤 User Registration
* 🔐 User Login
* 🔑 JWT token generation
* 🔒 Password encryption using BCrypt
* 📊 Track URL click count
* 🌐 Redirect short URLs to original URLs
* 💾 Store URL and user data in MySQL
* ⚡ RESTful APIs using Spring Boot
* 🎨 React.js frontend
* 📱 Simple and responsive user interface
* ⏳ URL expiration support
* 🟢 URL active/inactive status
* 📝 Store click information such as IP address, User-Agent and Referrer

---

## 🛠️ Technologies Used

### Backend

* Java 17
* Spring Boot 4
* Spring Web
* Spring Data JPA
* Spring Security
* Hibernate
* JWT
* BCrypt
* MySQL
* Maven

### Frontend

* React.js
* JavaScript
* HTML5
* CSS3
* Vite

### Tools

* Git
* GitHub
* Eclipse / VS Code
* Postman
* MySQL Workbench

---

## 🏗️ Project Architecture

```text
URL Shortener
│
├── Backend
│   ├── Controller
│   ├── Service
│   ├── Repository
│   ├── Entity
│   ├── DTO
│   ├── Configuration
│   ├── Exception
│   └── Redirect
│
├── Frontend
│   ├── React Components
│   ├── Login
│   ├── Register
│   └── App
│
└── Database
    ├── Users
    ├── URLs
    └── URL Clicks
```

---

## 🔄 How It Works

### 1. User Registration

The user creates an account by providing:

* Username
* Email
* Password

The password is encrypted using **BCrypt** before storing it in the database.

### 2. User Login

The user logs in using their username and password.

After successful authentication, the backend generates a **JWT token**, which is stored in the frontend's local storage.

### 3. Create Short URL

The user enters a long URL.

Example:

```text
https://www.example.com/products/category/spring-boot-development
```

The backend generates a unique short code.

Example:

```text
http://localhost:8080/aB12xY
```

### 4. URL Redirection

When a user opens the short URL:

```text
http://localhost:8080/aB12xY
```

the backend finds the original URL and redirects the user to it.

### 5. Click Tracking

Every redirect can record click information such as:

* IP Address
* User-Agent
* Referrer
* Click Time

The URL's click count is also updated.

---

## 🔐 Authentication

The project uses:

* **BCrypt** for password hashing
* **JWT** for token generation
* **Spring Security** for endpoint security

JWT tokens are generated after successful login.

Example:

```text
User Login
    ↓
Validate Username & Password
    ↓
Generate JWT Token
    ↓
Store Token in Browser
```

---

## 📡 REST APIs

### Authentication APIs

#### Register

```http
POST /api/auth/register
```

Example request:

```json
{
  "username": "rishi",
  "email": "rishi@gmail.com",
  "password": "12345"
}
```

#### Login

```http
POST /api/auth/login
```

Example request:

```json
{
  "username": "rishi",
  "password": "12345"
}
```

---

### URL API

#### Create Short URL

```http
POST /api/urls
```

Example request:

```json
{
  "originalUrl": "https://www.example.com"
}
```

Example response:

```json
{
  "shortUrl": "http://localhost:8080/Ab12Cd",
  "originalUrl": "https://www.example.com"
}
```

---

### Redirect API

```http
GET /{shortCode}
```

Example:

```text
http://localhost:8080/Ab12Cd
```

This redirects the user to the original URL.

---

## 🗄️ Database

The application uses **MySQL**.

Database:

```text
url_shortener
```

Main tables include:

```text
users
urls
url_clicks
```

The `urls` table stores information such as:

* Original URL
* Short URL
* Creation time
* Expiration time
* Active status
* Click count

---

## ⚙️ Configuration

Create a MySQL database:

```sql
CREATE DATABASE url_shortener;
```

Configure the database using environment variables:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/url_shortener}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:12345}
```

For production, configure these values using environment variables instead of hardcoding credentials.

JWT secret can also be provided using:

```text
JWT_SECRET
```

---

## ▶️ How to Run the Project

### Backend

Go to the backend project directory:

```bash
cd url-shortener
```

Run the Spring Boot application using Maven:

```bash
mvn spring-boot:run
```

Backend will start on:

```text
http://localhost:8080
```

---

### Frontend

Go to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the React application:

```bash
npm run dev
```

Frontend will be available at:

```text
http://localhost:5173
```

---

## 🖥️ Application Flow

```text
              ┌──────────────┐
              │    React     │
              │  Frontend    │
              └──────┬───────┘
                     │
                     │ REST API
                     ▼
              ┌──────────────┐
              │ Spring Boot  │
              │   Backend    │
              └──────┬───────┘
                     │
              ┌──────┴───────┐
              │              │
              ▼              ▼
        ┌───────────┐  ┌─────────────┐
        │   MySQL   │  │ JWT / BCrypt│
        │ Database  │  │  Security   │
        └───────────┘  └─────────────┘
```

---

## 📂 Project Structure

```text
url-shortener/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── rishi/
│       │           ├── config/
│       │           ├── controller/
│       │           ├── dto/
│       │           ├── entity/
│       │           ├── exception/
│       │           ├── redirect/
│       │           ├── repository/
│       │           └── service/
│       │
│       └── resources/
│           └── application.properties
│
├── frontend/
│   ├── src/
│   │   ├── App.jsx
│   │   ├── Login.jsx
│   │   ├── Register.jsx
│   │   └── App.css
│   │
│   ├── package.json
│   └── vite.config.js
│
├── pom.xml
├── .gitignore
└── README.md
```

---

## 🧪 Testing

The REST APIs can be tested using **Postman**.

Example testing flow:

```text
Register User
     ↓
Login User
     ↓
Receive JWT Token
     ↓
Create Short URL
     ↓
Open Short URL
     ↓
Redirect to Original URL
     ↓
Track Click
```

---

## 📸 Screenshots

Add screenshots of the application here:

```text
Frontend Home Page
Login Page
Registration Page
Short URL Result
```

Example:

```markdown
![Home Page](screenshots/home.png)
![Login Page](screenshots/login.png)
![Register Page](screenshots/register.png)
```

---

## 🔮 Future Enhancements

The following features can be added in future versions:

* User-specific URL dashboard
* URL analytics dashboard
* QR code generation
* Custom short URLs
* Advanced JWT authentication filter
* Refresh tokens
* URL management
* Redis caching
* Rate limiting
* Docker deployment
* Cloud deployment
* Custom domain support

---

## 👨‍💻 Author

**Rishi Raj**

MCA Graduate | Java Full Stack Developer

### Technical Skills

```text
Java
Spring Boot
Spring Data JPA
Hibernate
Spring Security
REST APIs
MySQL
React.js
JavaScript
Git & GitHub
```

---

## ⭐ Project Highlights

* Full-stack web application
* REST API based architecture
* Spring Boot backend
* React.js frontend
* MySQL database
* JWT authentication
* BCrypt password encryption
* URL redirection
* Click tracking
* Clean layered architecture

---

## 📄 License

This project is created for **learning, portfolio, and educational purposes**.
