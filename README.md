# Library Management API

A RESTful Library Management API built with Java Spring Boot and MongoDB, featuring polymorphic book types, loan tracking, and DTO-based partial updates.

## Tech Stack

- Java 17
- Spring Boot 3.5.3
- MongoDB
- Maven

## Project Structure

```
src/main/java/com/library/libraryapp/
├── controller/    # REST endpoints
├── service/       # Business logic
├── repository/    # MongoDB data access
├── model/         # Domain entities
└── dto/           # Data Transfer Objects for partial updates
```

## Features

- Full CRUD for Books, Customers, Employees, Loans and Sections
- Polymorphic book model — HistoryBook, ScienceBook, LiteratureBook
- DTO layer for safe partial updates (PATCH)
- Input validation and global exception handling
- Role-based employee access (ADMIN, LIBRARIAN, ASSISTANT)

## Setup

1. Clone the repository
2. Make sure MongoDB is running locally on port 27017
3. Copy `application.properties.example` to `application.properties` in the same directory
4. Replace `your_database_name` with your preferred database name
5. Run `LibraryApplication.java`
6. Verify the application is running at `http://localhost:8080`

## Author

Developed by [NikosPoly](https://github.com/NikosPoly)

## License

This project is licensed under the Apache-2.0 license - see the [LICENSE](LICENSE) file for details.