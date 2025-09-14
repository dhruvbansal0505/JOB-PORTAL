# Job Portal Application

A **Spring Boot Job Portal** application with role-based access using **JWT Authentication**.  
The project supports three roles:

- **Admin** → manages HR accounts  
- **HR** → manages job postings  
- **Candidate** → registers, uploads resume, and applies for jobs  

---

## 🚀 Features

- Candidate registration with:
  - Name
  - Qualification
  - Skills
  - Resume upload
- Pre-created Admin user (configured in DB)
- Admin can create HR accounts
- JWT-based login and authentication
- Role-based dashboards:
  - Candidate Dashboard
  - HR Dashboard
  - Admin Dashboard
- Frontend built with simple HTML/CSS (served from Spring Boot static resources)

---

## 🛠️ Tech Stack

- **Java 21/22**
- **Spring Boot 3**
- **Spring Security with JWT**
- **Spring Data JPA**
- **MySQL Database**
- **Maven**
- **HTML/CSS (frontend)**

---

## 📂 Project Structure

```

job-portal/
├── src/main/java/com/example/job\_portal/
│    ├── controller/        # REST Controllers
│    ├── entity/            # Entities (User, Candidate, HR)
│    ├── repository/        # Spring Data JPA Repositories
│    ├── security/          # JWT, SecurityConfig
│    └── JobPortalApplication.java
│
├── src/main/resources/
│    ├── static/            # Frontend HTML/CSS
│    ├── application.properties
│
└── pom.xml

````

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/dhruvbansal0505/JOB-PORTAL.git
cd JOB-PORTAL
````

### 2️⃣ Configure MySQL

Create a database in MySQL:

```sql
CREATE DATABASE job_portal;
```

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/job_portal
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=yourSecretKey
jwt.expiration=86400000
```

### 3️⃣ Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

OR run the JAR:

```bash
java -jar target/job-portal-0.0.1-SNAPSHOT.jar
```

---

## 🌐 Accessing the Application

* **Index Page (overview + login/register):**
  [http://localhost:8080/index.html](http://localhost:8080/index.html)

* **Auth APIs (via Postman):**

  * `POST /api/auth/login` → Login with username & password
  * `POST /api/auth/register` → Candidate registration

* **Dashboards:**

  * Candidate → `/candidate/dashboard`
  * HR → `/hr/dashboard`
  * Admin → `/admin/dashboard`

---

## 👤 Default Admin User

After running, ensure you have a pre-created admin user in DB:

```sql
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$yourBcryptPasswordHere', 'ADMIN');
```

👉 Password must be stored in **BCrypt** format.
You can generate one in Java:

```java
new BCryptPasswordEncoder().encode("admin123");
```

---

## 🧪 Testing

* Register a Candidate → `/api/auth/register`
* Login (Admin/HR/Candidate) → `/api/auth/login`
* Use the JWT token in Postman under `Authorization: Bearer <token>`

---

