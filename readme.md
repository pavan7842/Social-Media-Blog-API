# 📌 Social Media Blog API

## **📌 Overview**
The Social Media Blog API is a **backend service** designed for a micro-blogging platform. It enables **user account management** and **message creation, retrieval, and updates**. The API is built using **Java, Javalin, and JDBC** for relational database interactions.

## **🛠️ Tech Stack**
- **Backend:** Java, Javalin, JDBC
- **Database:** SQL-based relational database
- **Tools:** Postman (API Testing), IntelliJ IDEA

## **🚀 Features**
- **User Registration & Login** with secure credential management.
- **Message Posting, Editing, and Deletion**.
- **Retrieve all messages** or **filter by user**.
- **RESTful API** with structured error handling.

## **🔗 Endpoints**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/register` | Register a new user |
| `POST` | `/login` | Authenticate user login |
| `POST` | `/messages` | Create a new message |
| `GET` | `/messages` | Fetch all messages |
| `GET` | `/messages/{id}` | Retrieve message by ID |
| `PATCH` | `/messages/{id}` | Update message text |
| `DELETE` | `/messages/{id}` | Delete a message |

## **📜 Setup & Execution**
1. **Clone the repository:**
   ```sh
   git clone https://github.com/pavan7842/pavan7842-pep-project.git
   cd social-media-blog-api

2. **Run the application:**

mvn clean install
java -jar target/social-media-blog-api.jar

3. **Test APIs using Postman or Curl.**

**Documentation**

For detailed project requirements, see PROJECT_DETAILS.md.