# 🚀 CareerFlow - Job Portal Backend API

**CareerFlow** is a robust, scalable backend API for a modern recruitment platform. It connects **Employers** with **Candidates**, facilitating job posting, application tracking, and profile management. Built with a focus on security, performance, and developer experience.

---

## 🌟 Key Features

* **🔐 Secure Authentication:**
* JWT-based stateless authentication.
* Role-Based Access Control (RBAC) for `ADMIN`, `EMPLOYER`, and `USER`.


* **📂 Job Management:**
* Employers can Create, Update, and Delete job posts.
* Rich data model supporting Salary Ranges, Job Types (Remote/Onsite), and Company profiles.


* **🔍 Advanced Search Engine:**
* Dynamic filtering by Keyword, Location, Type, and Salary Range.
* Powered by **JPA Specifications** for optimized database queries.


* **📝 Application System:**
* Candidates can apply to jobs with resume links.
* Duplicate application prevention logic.
* Employers can review applicants for their specific posts.


* **📜 API Documentation:**
* Integrated **Swagger UI (OpenAPI 3.0)** for interactive API exploration.



---

## 🛠️ Tech Stack

* **Core:** Java 17, Spring Boot 3.2
* **Database:** PostgreSQL, Hibernate (JPA)
* **Security:** Spring Security, JWT (JSON Web Tokens)
* **Documentation:** SpringDoc OpenAPI (Swagger UI)
* **Tools:** Maven, Docker, Git

---

## 🚀 Getting Started

### Prerequisites

* Java 17+
* PostgreSQL (Local or Docker)
* Maven

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/CareerFlow.git
cd CareerFlow

```

### 2. Configure Database

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/careerflow_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

```

### 3. Build & Run

```bash
mvn clean install
mvn spring-boot:run

```

The application will start at `http://localhost:8080`.

---

## 📖 API Documentation (Swagger)

Once the application is running, access the interactive API docs at:

👉 **[http://localhost:8080/swagger-ui/index.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui/index.html)**

Use the **Authorize** button to test secured endpoints with your JWT token.

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

---

### **👨‍💻 Author**

**Dhananjaya**

* [LinkedIn](https://linkedin.com/in/dhananjaya-jayaweera001)
