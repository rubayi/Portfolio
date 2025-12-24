# 🌴 Travel Planner API

A RESTful API for travel itinerary management built with Spring Boot. Designed for trip planning, budget tracking, and group collaboration.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 🎯 Features

- **User Authentication** - JWT-based secure authentication & authorization
- **Itinerary Management** - Create, update, and organize travel plans
- **Budget Tracking** - Set budgets and track expenses per trip
- **Group Collaboration** - Invite travel companions and share itineraries
- **Place Integration** - Save and categorize destinations, hotels, restaurants

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| **Framework** | Spring Boot 3.2, Spring Security 6 |
| **Database** | MySQL 8.0, Spring Data JPA, Hibernate |
| **Authentication** | JWT (JSON Web Tokens) |
| **API Documentation** | Swagger / OpenAPI 3.0 |
| **Build Tool** | Maven |
| **Containerization** | Docker, Docker Compose |

---

## 📁 Project Structure

```
src/main/java/com/travelplanner/
├── config/          # Security, JWT, CORS configuration
├── controller/      # REST API endpoints
├── dto/             # Data Transfer Objects
├── entity/          # JPA entities
├── exception/       # Custom exceptions & handlers
├── repository/      # Spring Data JPA repositories
├── security/        # JWT filter, authentication
└── service/         # Business logic layer
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8.0+ (or use Docker)

### Quick Start with Docker

```bash
# Clone the repository
git clone https://github.com/rubayi/Portfolio.git
cd travel-planner-api

# Start MySQL with Docker
docker-compose up -d

# Run the application
./mvnw spring-boot:run
```

### Manual Setup

```bash
# Configure database in application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/travel_planner
    username: your_username
    password: your_password

# Run the application
./mvnw spring-boot:run
```

---

## 📚 API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login & get JWT token |
| POST | `/api/auth/refresh` | Refresh access token |

### Trips
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/trips` | Get all user trips |
| POST | `/api/trips` | Create new trip |
| GET | `/api/trips/{id}` | Get trip by ID |
| PUT | `/api/trips/{id}` | Update trip |
| DELETE | `/api/trips/{id}` | Delete trip |

### Itineraries
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/trips/{tripId}/itineraries` | Get trip itineraries |
| POST | `/api/trips/{tripId}/itineraries` | Add itinerary item |
| PUT | `/api/itineraries/{id}` | Update itinerary |
| DELETE | `/api/itineraries/{id}` | Delete itinerary |

### Budget
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/trips/{tripId}/budget` | Get trip budget summary |
| POST | `/api/trips/{tripId}/expenses` | Add expense |
| GET | `/api/trips/{tripId}/expenses` | Get all expenses |

📖 **Full API Documentation:** Available at `/swagger-ui.html` when running locally

---

## 🗄️ Database Schema

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│   users     │     │   trips     │     │ itineraries │
├─────────────┤     ├─────────────┤     ├─────────────┤
│ id          │────<│ user_id     │     │ trip_id     │>───┐
│ email       │     │ id          │────<│ id          │    │
│ password    │     │ title       │     │ day_number  │    │
│ name        │     │ start_date  │     │ place_name  │    │
│ created_at  │     │ end_date    │     │ description │    │
└─────────────┘     │ budget      │     │ start_time  │    │
                    └─────────────┘     └─────────────┘    │
                                                           │
┌─────────────┐     ┌─────────────┐                       │
│  expenses   │     │ trip_members│                       │
├─────────────┤     ├─────────────┤                       │
│ trip_id     │>────│ trip_id     │>──────────────────────┘
│ id          │     │ user_id     │
│ category    │     │ role        │
│ amount      │     │ invited_at  │
│ description │     └─────────────┘
└─────────────┘
```

---

## 🔐 Security

- **Password Encryption:** BCrypt hashing
- **Token-based Auth:** JWT with configurable expiration
- **Role-based Access:** USER, ADMIN roles
- **CORS:** Configurable allowed origins

---

## 🧪 Testing

```bash
# Run all tests
./mvnw test

# Run with coverage report
./mvnw test jacoco:report
```

---

## 🐳 Docker Deployment

```bash
# Build Docker image
docker build -t travel-planner-api .

# Run with Docker Compose (includes MySQL)
docker-compose up -d
```

---

## 📈 Future Enhancements

- [ ] Integration with Google Places API
- [ ] Email notifications for trip reminders
- [ ] Export itinerary to PDF
- [ ] Mobile app API optimization
- [ ] Redis caching for performance

---

## 👤 Author

**Misook Lee**
- 15+ years Full Stack Development experience
- Specialized in Java/Spring Boot enterprise applications
- [LinkedIn](https://linkedin.com/in/misookyi) | [Email](mailto:rubayi@gmail.com)

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
