# 🚀 Product Management API

REST API for **product and category management**, built with **Spring Boot**.  
Designed with focus on **clean architecture, authentication, testing and modular backend design**.

---

## ⚙️ Requirements
- Java 17+
- MySQL


## 🚀 Installation & Setup

```bash
# Clone the repository
git clone https://github.com/CaraballoCristian/Manager-Spring.git

cd manager-spring

# Run the application (recommended)
./mvnw spring-boot:run
```

```bash
A running MySQL instance is required.

The API will be available at:
http://localhost:8080
```

**Windows**
```bash
mvnw.cmd spring-boot:run
```

## 🗄️ Database Configuration
```bash
Example application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/springboot
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
```

---

## DB Creation & Mock Data MySQL Commands
```bash
CREATE database IF NOT EXISTS springboot;

INSERT INTO category (value) VALUES
('TECH'),
('FOOD'),
('SPORTS'),
('FINANCE'),
('HEALTH');


INSERT INTO product 
(id, name, description, manufacturer, price, category_value, region, created_at, updated_at)
VALUES
('9f7e2c54-0e79-4d20-b3c8-3ac6c1e53f01', 'Wireless Keyboard', 'A compact low-profile wireless keyboard.', 'LogiTech', 49.99, 'TECH', 'USA', NOW(), NOW()),
('6b5775c2-bf1a-4c83-b8f3-f2036df9b09a', 'Noise Cancelling Headphones', 'Over-ear headphones with active noise cancelling.', 'Sony', 129.99, 'TECH', 'CHINA', NOW(), NOW()),
('ad8c6c11-1e09-447c-b4cd-10acc7479a13', 'Organic Peanut Butter', '100% organic roasted peanuts, no sugar added.', 'NaturalFoods Co', 7.49, 'FOOD', 'ARGENTINA', NOW(), NOW()),
('8df322b3-1243-49cd-9872-986a2a2ef34c', 'Fitness Smartwatch', 'Tracks heart rate, sleep, and workouts.', 'FitPulse', 89.90, 'SPORTS', 'INDIA', NOW(), NOW()),
('79c8fbc2-447b-475c-8e00-ac4471c1b414', 'Running Shoes', 'Lightweight running shoes with breathable mesh.', 'RunnerX', 59.99, 'SPORTS', 'USA', NOW(), NOW()),
('e6c0c8f4-1b8d-4a12-8ce5-61d514fe92c9', 'Financial Planner Notebook', 'A structured planner for budgeting and goals.', 'PaperWise', 12.49, 'FINANCE', 'USA', NOW(), NOW()),
('5de142e1-5028-4314-a1bd-ccc38e21e4b3', 'Vitamin C Supplement', '1000mg daily vitamin C tablets.', 'HealthMax', 14.99, 'HEALTH', 'INDIA', NOW(), NOW()),
('34e450da-9bbf-4c8d-bba5-ea09f2ffc738', 'Electric Kettle', 'Stainless steel 1.7L kettle with auto shutoff.', 'KitchenPro', 24.99, 'FOOD', 'CHINA', NOW(), NOW()),
('4f0a0d3c-8638-4579-acea-82f126622a0a', 'Gaming Mouse', 'Programmable 6-button gaming mouse.', 'HyperGear', 39.99, 'TECH', 'USA', NOW(), NOW()),
('bfeffa29-61fe-4fd7-b089-7acfbad9240e', 'Yoga Mat', 'Non-slip eco-friendly yoga mat.', 'FlexLiving', 22.49, 'SPORTS', 'ARGENTINA', NOW(), NOW());
```
---

## 🔐 Authentication Flow
```bash
## 🔐 Authentication Flow

1. Register a user
POST /register

2. Login
POST /login

3. Use returned token:
Authorization: Bearer YOUR_TOKEN
```

---

## 🔎 Example Endpoints

**Authentication**
```bash
POST /register
POST /login
```

**Products**
```bash
GET /products
GET /products/{id}
POST /products
PUT /products/{id}
DELETE /products/{id}
```

**Categories**
```bash
GET /categories
POST /categories
```

**Filtering Example**
```bash
GET /products?name=phone&category=electronics
```

---

## ✨ Features

- 🔐 JWT Authentication with Spring Security
- 👤 User registration and login
- 📦 Product CRUD operations
- 🔎 Advanced filtering with Specifications
- ⚠️ Custom exception handling
- ⚡ Basic caching support
- 🌐 External API integration (content validation)
- 🧪 Unit testing with JUnit and Mockito

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- Hibernate / JPA
- MySQL
- Lombok
- JUnit
- Mockito
  
---

## 📂 Project Structure

```bash
project/
│── product/
│   ├── controller
│   ├── services
│   ├── repository
│   ├── dto
│   ├── models
│   ├── validations
│   └── utils

│── category/
│   ├── controller
│   ├── services
│   ├── repository
│   ├── models

│── utils/

│── user/
│   ├── repository
│   ├── models

│── security/
│   ├── auth
│   ├── jwt
│   ├── configuration
│   └── service

│── integration/
│   └── profanity/

│── exceptions/
│   ├── base
│   ├── handler
│   ├── product
│   ├── profanity
│   ├── response

└── configuration/
```
---

👨‍💻 Author

Developed by Cristian Caraballo
