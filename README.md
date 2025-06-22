# 🛒 Spring Boot E-store Application

A comprehensive e-store platform built using **Java 17**, **Spring Boot 3.2**, **Thymeleaf**, and **MySQL**, offering end-to-end online shopping functionality.



## 💻 Tech Stack

**Backend**  
🟦 Java 17  
🟩 Spring Boot 3.2.0  
🟨 Spring Data JPA  
🟨 RESTful API

**Frontend**  
🟧 Thymeleaf  
🔵 Bootstrap 5  
🟨 HTML5, CSS3, JavaScript

**Database**  
🟨 H2 (for development)  
🟦 MySQL / PostgreSQL (for production)

**Build Tool**  
🔧 Maven



## 📋 Features

- 👤 **User Management**: Registration, login, roles, profile updates  
- 🛍️ **Product Catalog**: View, search, and filter products by category  
- 🗂️ **Category Management**: Hierarchical product classification  
- 🛒 **Shopping Cart**: Add, update, remove items, calculate totals  
- 📱 **Responsive Design**: Works on both mobile and desktop  
- 🔐 **Security**: Role-based access, BCrypt encoding, CSRF protection  



## 🏗️ Project Structure

```

com.ecommerce/
├── config/          # Security & application config
├── controller/      # REST & Web controllers
├── model/           # JPA Entities
├── repository/      # Spring Data JPA interfaces
├── service/         # Business logic
└── EcommerceApplication.java  # Entry point

```



## 🚀 Getting Started

### ✅ Prerequisites

- JDK 17+
- Maven 3.6+
- IntelliJ IDEA
- Git

### ▶️ Run with IntelliJ

1. Open IntelliJ and import the project as a Maven project  
2. Locate `EcommerceApplication.java`  
3. Right-click > Run

### ▶️ Run with Maven

```

mvn clean install
mvn spring-boot\:run

```

> App will run at `http://localhost:8080`



## ⚙️ Configuration

### Database

By default, uses in-memory **H2**. To use **MySQL**:

`src/main/resources/application.properties`
```

spring.datasource.url=jdbc\:mysql://localhost:3306/ecommercedb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

```



## 🔒 Security

- Spring Security + BCrypt Password Encoder  
- Form-based login with session timeout  
- Role-based authorization (ADMIN / USER)  
- XSS and CSRF protection  
- (Optional) JWT for APIs



## 📄 API Documentation

- Swagger UI → `http://localhost:8080/swagger-ui.html`  
- OpenAPI Docs → `http://localhost:8080/v3/api-docs`



## 🧪 Testing

```

mvn test                     # Run all tests
mvn test -Dtest=UserServiceTest  # Run specific test

```

---

## 📸 Screenshots

> (Insert screenshots here once available)



## 📚 What I Learned

- Integrating Spring Security with form login and roles  
- CRUD implementation using Spring Data JPA  
- Using Thymeleaf for server-side rendering  
- Building REST APIs and securing them  
- Environment configuration for dev vs prod  
- End-to-end flow from product to checkout



## 🚢 Deployment Considerations

- Use MySQL/PostgreSQL in production  
- Set server port, logging, and connection pooling  
- Enable HTTPS  
- Add Redis or in-memory caching if needed



## 🤝 Contributing

1. Fork this repo  
2. Create a feature branch: `git checkout -b feature/awesome-feature`  
3. Commit changes: `git commit -m "Add awesome feature"`  
4. Push: `git push origin feature/awesome-feature`  
5. Open a pull request ✅



## 📜 License

Licensed under the **MIT License**. See the `LICENSE` file for details.



## 🙏 Acknowledgements

- Spring Boot & Spring Security Teams  
- Thymeleaf for templating  
- Bootstrap for design  
- All open-source contributors
