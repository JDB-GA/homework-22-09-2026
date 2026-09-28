# Todo App

A Spring Boot REST API built with Java, Spring Data JPA, Spring Security (JWT), and PostgreSQL.

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
* User and UserProfile models added (1:1).
* User registration and login added.
* JWT authentication added with Spring Security.

## Configuration

> **Note:** `application-dev.properties` is intentionally not ignored by Git, for educational purposes, so the project
> can be run and reviewed as-is. It contains the database password and JWT secret; in a real project this file should be
> ignored and the secrets supplied through environment variables.

The JWT settings must be added to the properties file:

```properties
jwt-secret=<Base64-encoded key, at least 32 bytes>
jwt-expiration-ms=86400000
```

A key can be generated with `openssl rand -base64 48`. The secret must be Base64, and HS256 needs at least 256 bits.

## Authentication

Authentication uses JSON Web Tokens (JWT):

1. Register a user with `POST /auth/users/register`.
2. Log in with `POST /auth/users/login`. The response contains the token.
3. Send the token in the `Authorization` header of every private request:

```
Authorization: Bearer <token>
```

Private endpoints called without a valid token return `403 Forbidden`.

## Endpoints

Base URL: `http://localhost:8080`

### Auth Endpoints

| Method | Path                   | Functionality    | Access |
|--------|------------------------|------------------|--------|
| POST   | `/auth/users/register` | Registers a user | PUBLIC |
| POST   | `/auth/users/login`    | Logs a user in   | PUBLIC |

### Category Endpoints

| Method | Path                         | Functionality                  | Access  |
|--------|------------------------------|--------------------------------|---------|
| GET    | `/api/hello`                 | Returns a hello message        | PRIVATE |
| GET    | `/api/categories`            | Lists all categories           | PRIVATE |
| POST   | `/api/categories`            | Creates a new category         | PRIVATE |
| GET    | `/api/categories/{id}`       | Gets a single category         | PRIVATE |
| PUT    | `/api/categories/{id}`       | Updates a category             | PRIVATE |
| DELETE | `/api/categories/{id}`       | Deletes a category             | PRIVATE |
| POST   | `/api/categories/{id}/image` | Uploads an image (JPEG or PNG) | PRIVATE |

### Item Endpoints

| Method | Path                                          | Functionality                         | Access  |
|--------|-----------------------------------------------|---------------------------------------|---------|
| GET    | `/api/categories/{categoryId}/items`          | Lists all items in the category       | PRIVATE |
| POST   | `/api/categories/{categoryId}/items`          | Creates a new item in the category    | PRIVATE |
| GET    | `/api/categories/{categoryId}/items/{itemId}` | Gets a single item in the category    | PRIVATE |
| PUT    | `/api/categories/{categoryId}/items/{itemId}` | Updates an item in the category       | PRIVATE |
| DELETE | `/api/categories/{categoryId}/items/{itemId}` | Deletes an item in the category       | PRIVATE |

## Examples

### Register

```bash
curl -X POST http://localhost:8080/auth/users/register \
-H 'Content-Type: application/json' \
-d '{
  "username": "muntadher",
  "emailAddress": "user@example.com",
  "password": "password123",
  "userProfile": {
    "firstName": "First",
    "lastName": "Last",
    "profileDescription": "Todo app user"
  }
}'
```

### Login

```bash
curl -X POST http://localhost:8080/auth/users/login \
-H 'Content-Type: application/json' \
-d '{
  "email": "user@example.com",
  "password": "password123"
}'
```

The response contains the JWT:

```json
{
  "message": "<token>"
}
```

The following examples require the token. Replace `<token>` with the value returned by login.

### Get All Items in a Category

```bash
curl http://localhost:8080/api/categories/1/items \
-H 'Authorization: Bearer <token>'
```

### Create Item

```bash
curl -X POST http://localhost:8080/api/categories/1/items \
-H 'Authorization: Bearer <token>' \
-H 'Content-Type: application/json' \
-d '{
  "name": "Complete Spring Boot homework",
  "description": "Finish the Todo App assignment",
  "dueDate": "2026-09-30"
}'
```

### Get Item

```bash
curl http://localhost:8080/api/categories/1/items/1 \
-H 'Authorization: Bearer <token>'
```

### Update Item

```bash
curl -X PUT http://localhost:8080/api/categories/1/items/1 \
-H 'Authorization: Bearer <token>' \
-H 'Content-Type: application/json' \
-d '{
  "name": "Complete Todo App",
  "description": "Finish and submit the Todo App assignment",
  "dueDate": "2026-10-01"
}'
```

### Delete Item

```bash
curl -X DELETE http://localhost:8080/api/categories/1/items/1 \
-H 'Authorization: Bearer <token>'
```

## What Went Right

The Category and Item CRUD functionality and PostgreSQL database integration are working successfully.

## Challenges

One challenge was keeping the Java entities and PostgreSQL database schema synchronized when adding new fields and
relationships.

## What I Enjoyed

I enjoyed building the application step by step and testing each part as I completed it.
