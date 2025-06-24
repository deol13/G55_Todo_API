# 🌱 Spring REST API Guidelines

### 1. Use Proper HTTP Methods
- `GET` → Retrieve resources
- `POST` → Create new resources
- `PUT` → Update entire resources
- `DELETE` → Remove resources

- `HEAD` → Retrieve headers only
- `OPTIONS` → Retrieve supported HTTP methods
- `TRACE` → Echo back the request for debugging
- `CONNECT` → Establish a tunnel to the server
- `PATCH` → Apply partial modifications to a resource

### 2. Use Meaningful Resource URIs
- Use **nouns**, not verbs:
    - ✅ `/users/42`
    - ❌ `/getUser?id=42`
- Use sub-resources:
    - `/users/42/orders`

### 3. Use Correct HTTP Status Codes
- `200 OK` → Successful GET/PUT/PATCH
- `201 Created` → Resource created
- `204 No Content` → Successful DELETE
- `400 Bad Request` → Validation or client error
- `404 Not Found` → Resource not found
- `500 Internal Server Error` → Server-side issue
- `401 Unauthorized` → Authentication required
- `403 Forbidden` → Access denied

---

## 🔐 Security
- Use **Spring Security**
- Prefer **JWT tokens** or **OAuth 2.0**
- Secure endpoints by roles and permissions

---

## 💬 Request & Response

- Use **JSON** (or XML if needed)
- Use **DTOs** to abstract internal entities
- Validate inputs using annotations

---

## 📚 API Versioning

- URI-based versioning:
    - `/api/v1/users`
- Header-based versioning:
    - `Accept: application/vnd.company.v1+json`

---

## 🔄 Pagination, Filtering, Sorting

Use query parameters:
- `/users?page=1&size=10`
- `/products?category=shoes&sort=price`

---

## 🧪 Error Handling

Use `@ControllerAdvice` and `@ExceptionHandler` to standardize error responses:

Example error response:
```json
{
  "timestamp": "2025-04-24T12:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid input"
}
```

## 📝 Documentation
- Use SpringDoc OpenAPI or Swagger for auto-generating docs.