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

* Spring Boot setup completed.
* Category CRUD implemented.
* Item CRUD implemented.
* Items can be created under categories.
* PostgreSQL database connected.
* Category timestamps added.
* Category image uploads added.

## Endpoints

Base URL: `http://localhost:8080/api`

### Category Endpoints

| Method | Path                     | Example                                                                                                                                        |
|--------|--------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| GET    | `/hello`                 | `curl http://localhost:8080/api/hello`                                                                                                         |
| GET    | `/categories`            | `curl http://localhost:8080/api/categories`                                                                                                    |
| GET    | `/category/{id}`         | `curl http://localhost:8080/api/category/1`                                                                                                    |
| POST   | `/categories`            | `curl -X POST http://localhost:8080/api/categories -H 'Content-Type: application/json' -d '{"name":"Work","description":"Work tasks"}'`        |
| POST   | `/categories/{id}/image` | `curl -X POST http://localhost:8080/api/categories/1/image -F 'image=@/full/path/image.jpg'`                                                   |
| PUT    | `/category/{id}`         | `curl -X PUT http://localhost:8080/api/category/1 -H 'Content-Type: application/json' -d '{"name":"Personal","description":"Personal tasks"}'` |
| DELETE | `/category/{id}`         | `curl -X DELETE http://localhost:8080/api/category/1`                                                                                          |

### Item Endpoints

| Method | Path                             | Example                                                                                                                           |
|--------|----------------------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| GET    | `/items`                         | `curl http://localhost:8080/api/items`                                                                                            |
| GET    | `/item/{id}`                     | `curl http://localhost:8080/api/item/1`                                                                                           |
| POST   | `/categories/{categoryId}/items` | `curl -X POST http://localhost:8080/api/categories/1/items -H 'Content-Type: application/json' -d '{"name":"Complete homework"}'` |
| PUT    | `/item/{id}`                     | `curl -X PUT http://localhost:8080/api/item/1 -H 'Content-Type: application/json' -d '{"name":"Complete Spring Boot homework"}'`  |
| DELETE | `/item/{id}`                     | `curl -X DELETE http://localhost:8080/api/item/1`                                                                                 |

## What Went Right

The Category and Item CRUD functionality and PostgreSQL database integration are working successfully.

## Challenges

One challenge was keeping the Java entities and PostgreSQL database schema synchronized when adding new fields and
relationships.

## What I Enjoyed

I enjoyed building the application step by step and testing each part as I completed it.
