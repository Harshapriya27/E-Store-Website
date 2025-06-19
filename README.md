# Spring Boot E-commerce Application

A comprehensive e-commerce platform built with Spring Boot, providing a complete solution for online shopping with user management, product catalog, shopping cart functionality, and more.

## 📋 Features

- **User Management**: Registration, authentication, and profile management
- **Product Catalog**: Browsing, searching, and filtering products
- **Category Management**: Hierarchical product categorization
- **Shopping Cart**: Add, update, remove items and calculate totals
- **Responsive Design**: Optimized for both desktop and mobile devices
- **Security**: Role-based access control and secure authentication

## 🛠️ Technology Stack

- **Backend**:
  - Java 17
  - Spring Boot 3.2.0
  - Spring Security
  - Spring Data JPA
  - RESTful API architecture
  
- **Frontend**:
  - Thymeleaf templates
  - Bootstrap 5
  - HTML5/CSS3/JavaScript
  
- **Database**:
  - H2 (Development)
  - MySQL/PostgreSQL (Production)
  
- **Build Tools**:
  - Maven

## 🏗️ Architecture

The application follows a standard Spring MVC architecture:

```
com.ecommerce/
├── config/          # Application configuration
├── controller/      # HTTP request handlers
├── model/           # Domain entities
├── repository/      # Data access interfaces
├── service/         # Business logic
└── util/            # Utility classes
```

## 🚀 Getting Started

### Prerequisites

- JDK 17+
- Maven 3.6+
- IntelliJ IDEA (recommended)
- Git

### Running in IntelliJ IDEA

1. Open IntelliJ IDEA
2. Select `File > Open` and navigate to the project directory
3. Wait for IntelliJ to import the Maven project
4. Run the application by right-clicking on `EcommerceApplication.java` and selecting `Run`

### Running with Maven

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Accessing the Application

Once running, the application is available at:
- http://localhost:8080

## ⚙️ Configuration

### Database Configuration

The application uses H2 in-memory database by default. To configure a persistent database:

1. Update `application.properties`:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ecommercedb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

2. Add the appropriate database dependency to `pom.xml`

### Application Properties

Customize application behavior in `application.properties`:

```properties
# Server configuration
server.port=8080

# Logging
logging.level.org.springframework=INFO
logging.level.com.ecommerce=DEBUG

# File uploads
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Session timeout (in seconds)
server.servlet.session.timeout=3600
```

## 🗄️ Database Schema

![Database Schema](https://via.placeholder.com/800x600?text=Database+Schema)

Key entities:
- User
- Product
- Category
- Cart
- CartItem
- Order
- OrderItem

## 🔒 Security Implementation

The application uses Spring Security with:

- Form-based authentication
- BCrypt password encoding
- JWT tokens for API authentication (if applicable)
- Role-based access control (USER, ADMIN)
- CSRF protection
- XSS prevention

## 📝 API Documentation

REST API documentation is available at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/v3/api-docs

## 🧪 Testing

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=UserServiceTest
```

## 🚢 Deployment

### Production Considerations

1. Configure a production database (MySQL/PostgreSQL)
2. Set appropriate logging levels
3. Enable HTTPS
4. Configure connection pooling
5. Set up caching where appropriate

## 👥 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgements

- Spring Boot and Spring Framework teams
- Thymeleaf template engine
- Bootstrap for responsive design
- All open-source libraries used
