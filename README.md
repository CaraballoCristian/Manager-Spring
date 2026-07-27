# 🚀 Product Management API

REST API for **product and category management**, built with **Spring Boot**.  
Designed with focus on **clean architecture, authentication, testing and modular backend design**.

---

![Cover](./cover.png)

---

## ⚙️ Requirements
- Java 17+
- MySQL

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
('bfeffa29-61fe-4fd7-b089-7acfbad9240e', 'Yoga Mat', 'Non-slip eco-friendly yoga mat.', 'FlexLiving', 22.49, 'SPORTS', 'ARGENTINA', NOW(), NOW()),
('c1a72f0e-9b55-4a43-b5f7-12c6f4e91a01', 'Wireless Mouse', 'Ergonomic wireless mouse with adjustable DPI.', 'LogiTech', 29.99, 'TECH', 'USA', NOW(), NOW()),
('f2d4b8a1-3e11-4f7a-9f28-91ab3c7de202', 'Gaming Keyboard', 'Mechanical gaming keyboard with RGB lighting.', 'HyperGear', 79.99, 'TECH', 'CHINA', NOW(), NOW()),
('a93b7e62-0c33-4d71-bc49-55fbc4d8f303', 'Smart Home Hub', 'Smart device hub for managing connected home devices.', 'SmartLife', 99.99, 'TECH', 'INDIA', NOW(), NOW());
```

---

## 🗄️ Database Configuration
```bash
Example application.properties:

#Update these values according to your local MySQL configuration.

spring.datasource.url=jdbc:mysql://localhost:3306/springboot
spring.datasource.username=yourusername
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
```

---

## 🚀 Installation & Setup

```bash
# Clone the repository
git clone https://github.com/CaraballoCristian/Manager-Spring.git

cd Manager-Spring

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

---

## API Key Configuration
```bash
Example api_key.properties:

#Create a key here: https://api-ninjas.com/api/profanityfilter
#Update this value according to your API KEY

PROFANITY_FILTER_API_KEY=YOUR_KEY

```

---

## 🔐 Authentication Flow
```bash
## 🔐 Authentication Flow

1. Register a user
POST /auth/register

2. Login
POST /auth/login

3. Use returned token:
Authorization: Bearer YOUR_TOKEN
```

---

## 🔎 Example Endpoints

**Authentication**
```bash
POST /auth/register
POST /auth/login
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
```

**Filtering Example**
```bash
# Query Params
term     → word fragment matched against product name or description
category → [TECH, FINANCE, HEALTH, SPORTS, FOOD]
orderBy  → [priceAsc, priceDesc, nameAsc, nameDesc]

# Example 
GET /products?term=wireles&category=TECH&orderBy=priceAsc
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
