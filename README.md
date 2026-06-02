# Global Class Offering Booking System

## Project Overview

This project is a backend service for a global live-learning platform where teachers create course offerings and parents/students can book them.

The system supports:

* Course Management
* Offering Management
* Session Scheduling
* Parent Bookings
* Timezone Conversion
* Booking Conflict Detection
* Concurrent Booking Handling
* Exception Handling
* Database Migration using Flyway

---

## Tech Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL

### Build Tool

* Maven

### Other Libraries

* Lombok
* Flyway Migration

---

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.undoschool.global_class_booking_system
│   │       ├── controller
│   │       │   ├── TeacherController
│   │       │   ├── ParentController
│   │       │   └── OfferingController
│   │       │
│   │       ├── service
│   │       │   ├── BookingService
│   │       │   ├── OfferingService
│   │       │   └── SessionService
│   │       │
│   │       ├── service.impl
│   │       │   ├── BookingServiceImpl
│   │       │   ├── OfferingServiceImpl
│   │       │   └── SessionServiceImpl
│   │       │
│   │       ├── repository
│   │       │   ├── BookingRepository
│   │       │   ├── CourseRepository
│   │       │   ├── OfferingRepository
│   │       │   ├── ParentRepository
│   │       │   ├── SessionRepository
│   │       │   └── TeacherRepository
│   │       │
│   │       ├── entity
│   │       │   ├── BaseEntity
│   │       │   ├── Teacher
│   │       │   ├── Parent
│   │       │   ├── Course
│   │       │   ├── Offering
│   │       │   ├── Session
│   │       │   └── Booking
│   │       │
│   │       ├── dto
│   │       │   ├── request
│   │       │   └── response
│   │       │
│   │       ├── mapper
│   │       │   └── OfferingMapper
│   │       │
│   │       ├── exception
│   │       │   ├── ResourceNotFoundException
│   │       │   ├── BookingConflictException
│   │       │   └── GlobalExceptionHandler
│   │       │
│   │       └── util
│   │           ├── BookingConflictUtil
│   │           ├── SessionValidationUtil
│   │           └── TimezoneUtil
│   │
│   └── resources
│       ├── application.properties
│       └── db
│           └── migration
│               ├── V1__create_tables.sql
│               ├── V2__create_indexes.sql
│               └── V3__seed_data.sql
│
└── pom.xml
```

---

## Database Schema

### Teachers

```sql
teachers
--------
id
name
timezone
created_at
updated_at
```

### Parents

```sql
parents
--------
id
name
timezone
created_at
updated_at
```

### Courses

```sql
courses
--------
id
title
description
created_at
updated_at
```

### Offerings

```sql
offerings
---------
id
name
teacher_id
course_id
created_at
updated_at
```

### Sessions

```sql
sessions
--------
id
offering_id
start_time
end_time
created_at
updated_at
```

### Bookings

```sql
bookings
--------
id
parent_id
offering_id
created_at
updated_at
```

---

## API Endpoints

### Teacher APIs

#### Create Course

```http
POST /api/teachers/courses
```

#### Create Offering

```http
POST /api/teachers/offerings
```

#### Add Sessions

```http
POST /api/teachers/offerings/{offeringId}/sessions
```

#### Get Teacher Offerings

```http
GET /api/teachers/{teacherId}/offerings
```

---

### Parent APIs

#### Get Available Offerings

```http
GET /api/parents/offerings
```

#### Book Offering

```http
POST /api/parents/bookings
```

#### Get Parent Bookings

```http
GET /api/parents/{parentId}/bookings
```

---

### Offering APIs

#### Get Offering Details

```http
GET /api/offerings/{id}
```

---

## Timezone Handling

Teachers create sessions in their local timezone.

Example:

Teacher Timezone:

```text
Asia/Kolkata
```

Session:

```text
06-Jun-2026
06:00 PM IST
```

Stored in database:

```text
UTC Instant
```

When parents view schedules:

```text
America/New_York
```

The session time is automatically converted into the parent's local timezone.

---

## Booking Conflict Detection

A parent cannot book overlapping offerings.

Example:

Booked:

```text
Saturday
5:00 PM - 6:00 PM
```

Trying to Book:

```text
Saturday
5:30 PM - 6:30 PM
```

Result:

```text
Booking Conflict Detected
```

---

## Concurrency Handling

To avoid race conditions:

```java
@Transactional
```

and database locking are used.

Features:

* Prevent duplicate bookings
* Prevent overlapping bookings
* Maintain data consistency
* Support concurrent requests

---

## Exception Handling

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

## Flyway Migrations

### V1

Create Tables

### V2

Create Indexes

### V3

Seed Initial Data

```sql
Teacher:
John Doe

Parent:
Mary Smith
```

---

## Environment Variables

Update in application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5434/class_booking_db
spring.datasource.username=postgres
spring.datasource.password=root
```

---

## Steps to Run

### Clone Repository

```bash
git clone <repository-url>
```

### Navigate

```bash
cd global-class-booking-system
```

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

---

## Assumptions

* Parent books entire offering.
* Sessions cannot overlap for the same parent.
* All session times are stored in UTC.
* Teacher and Parent timezones are valid IANA timezone IDs.
* One booking corresponds to one offering.

---

## Future Improvements

* JWT Authentication
* Role Based Access Control
* Swagger/OpenAPI Documentation
* Docker Support
* Unit Testing
* Integration Testing
* CI/CD Pipeline

---

## Author

Mayank Srivastava
Backend Engineer Assignment Submission
