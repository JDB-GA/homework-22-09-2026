# Todo App

A Spring Boot REST API built with Java, Spring Data JPA, and PostgreSQL.

## Approach

I built the application one layer at a time:

1. **Model:** Created the entity and its fields.
2. **Repository:** Connected the model to the database using Spring Data JPA.
3. **Service:** Added the application logic.
4. **Controller:** Created the REST API endpoints.
5. **Testing:** Tested each endpoint using Postman before moving to the next part.

This approach keeps the development organized and makes it easier to identify and fix problems at each stage.

## Current Progress

- Spring Boot setup completed.
- Category CRUD implemented.
- PostgreSQL database connected.
- Category timestamps added.
- Item functionality is not included yet.

## Endpoints

Base URL: `http://localhost:8080/api`

| Method | Path | Example |
| --- | --- | --- |
| GET | `/hello` | `curl http://localhost:8080/api/hello` |
| GET | `/categories` | `curl http://localhost:8080/api/categories` |
| GET | `/category/{id}` | `curl http://localhost:8080/api/category/1` |
| POST | `/categories` | `curl -X POST http://localhost:8080/api/categories -H 'Content-Type: application/json' -d '{"name":"Work","description":"Work tasks"}'` |
| PUT | `/category/{id}` | `curl -X PUT http://localhost:8080/api/category/1 -H 'Content-Type: application/json' -d '{"name":"Personal","description":"Personal tasks"}'` |
| DELETE | `/category/{id}` | `curl -X DELETE http://localhost:8080/api/category/1` |

## What Went Right

The Category CRUD and database integration are working successfully.

## Challenges

One challenge was keeping the Java entity and PostgreSQL database schema synchronized when adding new fields.

## What I Enjoyed

I enjoyed building the application step by step and testing each part as I completed it.
