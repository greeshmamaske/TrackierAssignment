# TrackierAssignment

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security + JWT
- MySQL
- Maven

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/book-management.git
cd book-management
```

---


### 2. MySQL Setup

Create a database named `book_management`:

```sql
CREATE DATABASE book_management;
```

---

### 3. Configure the Application

Update the following in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/book_management
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password

jwt.secret=your_jwt_secret
```

---

### 4. Run the Application

Use Maven:
```bash
mvn clean install
mvn spring-boot:run
```

The app will be available at: `http://localhost:8080`

---

## 5. Authentication

### Register a New User
```http
POST /register
Content-Type: application/json

{
  "username": "john",
  "password": "password123"
}
```

### Login to Get Token
```http
POST /login
Content-Type: application/json

{
  "username": "john",
  "password": "password123"
}
```

> Copy the token from the login response and add it to your headers:
```
Authorization: Bearer <token>
```

---

## 6. Test the APIs

All APIs (except `/login` and `/register`) require authentication.

### 📘 Admins
- `POST /books` — Add a book  
- `PUT /books/{id}` — Update a book  
- `DELETE /books/{id}` — Delete a book  

### 👤 Users
- `GET /transactions/search?page=0&size=12` — Get paginated list of books
- `GET /transactions/search/title/Gatsby?page=0&size=10` - Search by title 
- `GET /transactions/search/author/Fitzgerald?page=0&size=12` - Search by author
- `POST /transactions/borrow` — Borrow books  
- `POST /transactions/return` — Return books  

### 🧾 Notes (Encrypted)
- `POST /notes/encrypt`  
- `POST /notes/decrypt`

---


## Comments
- SQL queries are included in an SQL file at `src/main/resources/queries.sql`. To execute manually, run the following command:
    ```bash
    mysql -u root -p book_management < src/main/resources/queries.sql
    ```
- Postman collection is also added as `BookManagement.postman_collection.json`