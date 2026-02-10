# Vending Machine REST API

This project is a Spring Boot RESTful API developed as the Endterm Project.

The system manages vending machines, items, and storage cells.
It demonstrates the integration of Design Patterns, Component Principles,
SOLID architecture, and database interaction.

Technologies used:

- Java 17
- Spring Boot
- JDBC
- PostgreSQL

---

# REST API Documentation 

Base URL http://localhost:8080/api

| Method | Endpoint    | Description    |
| ------ | ----------- | -------------- |
| GET    | /items      | Get all items  |
| GET    | /items/{id} | Get item by id |
| POST   | /items      | Create item    |
| PUT    | /items/{id} | Update item    |
| DELETE | /items/{id} | Delete item    |

Example JSON

```json
{
  "name": "Cola",
  "price": 2.5
}
```

Design Patterns

---

# Singleton

Implemented in: 
- DatabaseConnection

Purpose: 
- Manage single database connection
- Avoid multiple expensive connections

---

# Factory

Implemented in:
- CellFactory

Purpose:
- Encapsulate object creation
- Return base type objects

---

# Builder

Implemented in:
- Cell.Builder

Purpose:
- Construct complex junction entities
- Support fluent creation

---

# Component Principles

## REP

Reusable modules:
- repository
- service
- utils
- models

## CCP

Classes that change together are grouped:
- Cell
- CellService
- CellRepository

## CRP

Modules depend only on required classes.

---

# SOLID & OOP

Applied principles:
- SRP -> Services handle logic
- OCP -> Factory allows extension
- DIP -> Controllers depend on services
- Encapsulation -> Models protect fields
- Inheritance -> BaseEntity hierarchy

---

# Database Schema

Main tables:
- vending_machine
- item
- cell

Relationships:
- Cell links vending machine and item
- Quantity stored per machine slot


![Description of the image](./docs/DatabaseShema.png)

---

# System Architecture

Layered architecture:
- Controller -> Service -> Repository -> Database

---

# How to Run

```bash
git clone <repo>
cd project
```

Configure DB in:

```bash
application.properties
```

Run:

```bash
mvn spring-boot:run
```

---

# API Testing

API tested using:
- Postman
- JSON requests
- CRUD verification


![Description of the image](./docs/ExampleRequestAPI.png)

---

# Reflection

This project helped me understand how to transform a layered Java
application into a full RESTful backend.

I learned how to implement Singleton, Factory, and Builder patterns
in real-world architecture and apply component principles in package design.
