# Global Class Offering Booking System

## Project Overview

This project is a backend service for a global live-learning platform where teachers create course offerings and parents/students can book them.

The system supports:

* Course Management
* Offering Management
* Session Scheduling
* Parent Bookings
* Booking Conflict Detection
* Timezone Conversion
* Concurrent Booking Handling
* Exception Handling
* Database Migration using Flyway

The application is designed with clean architecture principles, proper database normalization, transaction management, and production-ready backend practices.

---

# Tech Stack

## Backend

* Java 21
* Spring Boot 3
* Spring Data JPA
* Hibernate ORM

## Database

* PostgreSQL

## Build Tool

* Maven

## Additional Libraries

* Lombok
* Flyway Migration

---

# Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.undoschool.global_class_booking_system
│   │       ├── controller
│   │       ├── service
│   │       ├── service.impl
│   │       ├── repository
│   │       ├── entity
│   │       ├── dto
│   │       │   ├── request
│   │       │   └── response
│   │       ├── mapper
│   │       ├── exception
│   │       ├── util
│   │       └── GlobalClassBookingSystemApplication
│   │
│   └── resources
│       ├── application.properties
│       └── db
│           └── migration
│               ├── V1__create_tables.sql
│               ├── V2__create_indexes.sql
│               └── V3__seed_data.sql
│
├── postman_collection.json
├── README.md
├── pom.xml
└── .gitignore
```

---

# Database Schema Overview

## Teachers

| Column     | Type      |
| ---------- | --------- |
| id         | BIGINT    |
| name       | VARCHAR   |
| timezone   | VARCHAR   |
| created_at | TIMESTAMP |
| updated_at | TIMESTAMP |

---

## Parents

| Column     | Type      |
| ---------- | --------- |
| id         | BIGINT    |
| name       | VARCHAR   |
| timezone   | VARCHAR   |
| created_at | TIMESTAMP |
| updated_at | TIMESTAMP |

---

## Courses

| Column      | Type      |
| ----------- | --------- |
| id          | BIGINT    |
| title       | VARCHAR   |
| description | TEXT      |
| created_at  | TIMESTAMP |
| updated_at  | TIMESTAMP |

---

## Offerings

| Column     | Type      |
| ---------- | --------- |
| id         | BIGINT    |
| name       | VARCHAR   |
| teacher_id | BIGINT    |
| course_id  | BIGINT    |
| created_at | TIMESTAMP |
| updated_at | TIMESTAMP |

---

## Sessions

| Column      | Type                     |
| ----------- | ------------------------ |
| id          | BIGINT                   |
| offering_id | BIGINT                   |
| start_time  | TIMESTAMP WITH TIME ZONE |
| end_time    | TIMESTAMP WITH TIME ZONE |
| created_at  | TIMESTAMP                |
| updated_at  | TIMESTAMP                |

---

## Bookings

| Column      | Type      |
| ----------- | --------- |
| id          | BIGINT    |
| parent_id   | BIGINT    |
| offering_id | BIGINT    |
| created_at  | TIMESTAMP |
| updated_at  | TIMESTAMP |

---

# API Documentation

## Teacher APIs

### Create Course

```http
POST /api/teachers/courses
```

### Create Offering

```http
POST /api/teachers/offerings
```

### Add Sessions

```http
POST /api/teachers/offerings/{offeringId}/sessions
```

### Get Teacher Offerings

```http
GET /api/teachers/{teacherId}/offerings
```

---

## Parent APIs

### Get Available Offerings

```http
GET /api/parents/offerings
```

### Book Offering

```http
POST /api/parents/bookings
```

### Get Parent Bookings

```http
GET /api/parents/{parentId}/bookings
```

---

## Offering APIs

### Get Offering Details

```http
GET /api/offerings/{id}
```

---

# Environment Variables

Update the following values in:

```properties
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5434/class_booking_db
spring.datasource.username=postgres
spring.datasource.password=root
```

---

# Setup Instructions

## Clone Repository

```bash
git clone <repository-url>
```

## Navigate to Project

```bash
cd global-class-booking-system
```

## Create Database

```sql
CREATE DATABASE class_booking_db;
```

## Run Flyway Migrations

Flyway migrations execute automatically during application startup.

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

Application will start on:

```text
http://localhost:8080
```

---

# Timezone Handling Approach

Teachers create sessions in their local timezone.

Example:

Teacher Timezone:

```text
Asia/Kolkata
```

Session:

```text
06-Jun-2026 06:00 PM IST
```

The system converts all session times to UTC before storing them in the database.

When parents view offerings, session times are converted from UTC into the parent's timezone.

Example:

```text
Parent Timezone: America/New_York
```

This ensures accurate scheduling across countries and regions.

---

# Booking Conflict Detection

Parents book entire offerings rather than individual sessions.

Before creating a booking:

1. Existing bookings of the parent are loaded.
2. All sessions belonging to booked offerings are fetched.
3. Sessions of the new offering are fetched.
4. Every session is checked for overlap.
5. If any overlap exists, booking is rejected.

Example:

Existing Session:

```text
Saturday 5:00 PM - 6:00 PM
```

New Session:

```text
Saturday 5:30 PM - 6:30 PM
```

Result:

```text
Booking Conflict Detected
```

---

# Concurrency Handling Approach

The application uses:

```java
@Transactional
```

for transactional consistency.

Additional safeguards:

* Database unique constraints
* Parent booking locking
* Conflict validation before insert
* Atomic booking creation

This prevents:

* Duplicate bookings
* Race conditions
* Inconsistent booking data

---

# Exception Handling

Implemented using:

```java
@RestControllerAdvice
```

Custom Exceptions:

* ResourceNotFoundException
* BookingConflictException

Standard Error Response:

```json
{
  "message": "Offering not found",
  "status": 404,
  "timestamp": "2026-06-02T10:00:00Z"
}
```

---

# Flyway Database Migrations

## V1__create_tables.sql

Creates:

* teachers
* parents
* courses
* offerings
* sessions
* bookings

## V2__create_indexes.sql

Creates indexes for:

* bookings(parent_id)
* bookings(offering_id)
* sessions(offering_id)
* sessions(start_time)

## V3__seed_data.sql

Seeds initial records:

Teacher:

```text
John Doe
Asia/Kolkata
```

Parent:

```text
Mary Smith
America/New_York
```

---

# Assumptions Made

* Parents book an entire offering.
* Sessions belong to exactly one offering.
* Teachers and parents have valid timezone identifiers.
* All timestamps are stored in UTC.
* A parent cannot book overlapping sessions.
* A parent cannot book the same offering twice.

---

# Optional Enhancements

Potential future improvements:

* JWT Authentication
* Role-Based Access Control
* Swagger/OpenAPI Documentation
* Docker Support
* Unit Tests
* Integration Tests
* CI/CD Pipeline
* Caching

---

# Author

Mayank Srivastava

Backend Engineering Assignment Submission
