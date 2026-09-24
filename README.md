# Todo App

A Spring Boot REST API built with Java, Spring Data JPA, and PostgreSQL.

## Approach

I built the application one layer at a time:

1. **Model:** Created the entities and their fields.
2. **Repository:** Connected the models to the database using Spring Data JPA.
3. **Service:** Added the application logic.
4. **Controller:** Created the REST API endpoints.
5. **Testing:** Tested each endpoint using Postman before moving to the next part.

This approach keeps the development organized and makes it easier to identify and fix problems at each stage.

## Current Progress

* Spring Boot setup completed.
* Category CRUD implemented.
* Item CRUD implemented.
* Items are associated with categories.
* PostgreSQL database connected.
* Category timestamps added.
* Category image uploads added.

## Endpoints

Base URL: `http://localhost:8080/api`

### Category Endpoints

| Method | Path                         |
|--------|------------------------------|
| GET    | `/api/hello`                 |
| GET    | `/api/categories`            |
| GET    | `/api/category/{id}`         |
| POST   | `/api/categories`            |
| POST   | `/api/categories/{id}/image` |
| PUT    | `/api/category/{id}`         |
| DELETE | `/api/category/{id}`         |

### Item Endpoints

| Method | Path                                          |
|--------|-----------------------------------------------|
| GET    | `/api/categories/{categoryId}/items`          |
| POST   | `/api/categories/{categoryId}/items`          |
| GET    | `/api/categories/{categoryId}/items/{itemId}` |
| PUT    | `/api/categories/{categoryId}/items/{itemId}` |
| DELETE | `/api/categories/{categoryId}/items/{itemId}` |

## Examples

### Get All Items in a Category

```bash
curl http://localhost:8080/api/categories/1/items
```

### Create Item

```bash
curl -X POST http://localhost:8080/api/categories/1/items \
-H 'Content-Type: application/json' \
-d '{
  "name": "Complete Spring Boot homework",
  "description": "Finish the Todo App assignment",
  "dueDate": "2026-09-30"
}'
```

### Get Item

```bash
curl http://localhost:8080/api/categories/1/items/1
```

### Update Item

```bash
curl -X PUT http://localhost:8080/api/categories/1/items/1 \
-H 'Content-Type: application/json' \
-d '{
  "name": "Complete Todo App",
  "description": "Finish and submit the Todo App assignment",
  "dueDate": "2026-10-01"
}'
```

### Delete Item

```bash
curl -X DELETE http://localhost:8080/api/categories/1/items/1
```

## What Went Right

The Category and Item CRUD functionality and PostgreSQL database integration are working successfully.

## Challenges

One challenge was keeping the Java entities and PostgreSQL database schema synchronized when adding new fields and
relationships.

## What I Enjoyed

I enjoyed building the application step by step and testing each part as I completed it.
