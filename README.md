# Spring Data JPA Employee Management System

A web application for managing employees and departments using Spring MVC and Spring Data JPA.

## Technologies Used

- Java 21
- Spring Framework 6.2.6 (Spring MVC, Spring ORM)
- Spring Data JPA 3.4.5
- Hibernate 6.6.13
- PostgreSQL 42.7.5
- JSP and JSTL for views
- Maven for dependency management
- JUnit 5 and Mockito for testing
- H2 Database for testing

## Project Description

This application is an employee management system that allows users to:

- Create, read, update, and delete employee records
- Create, read, update, and delete department records
- Associate employees with departments

The system stores employee information including:
- Personal details (name, gender, birthdate)
- Contact information (email, mobile)
- Professional information (programming languages)
- User credentials (username, password)
- Department association

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── org/chintanpatel/springdatajpa/
│   │       ├── config/         # Application configuration
│   │       ├── controller/     # MVC controllers
│   │       ├── model/          # Entity classes
│   │       ├── repository/     # Spring Data JPA repositories
│   │       ├── service/        # Service layer
│   │       └── validator/      # Custom validators
│   ├── resources/              # Application properties
│   └── webapp/                 # Web resources
│       ├── WEB-INF/
│       │   └── views/          # JSP views
│       └── resources/
│           ├── css/            # Stylesheets
│           └── js/             # JavaScript files
└── test/                       # Test classes
```

## Setup and Installation

### Prerequisites

- Java 21 or higher
- Maven
- PostgreSQL database

### Database Setup

1. Create a PostgreSQL database
2. Update the database configuration in `src/main/resources/db.properties` if needed:
   ```properties
   jdbc.driverClassName=org.postgresql.Driver
   jdbc.url=jdbc:postgresql://localhost:5432/postgres
   jdbc.username=postgres
   jdbc.password=root
   ```

### Building and Running the Application

1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/SpringDataJpa.git
   cd SpringDataJpa
   ```

2. Build the application
   ```bash
   mvn clean package
   ```

3. Deploy the generated WAR file to a servlet container (like Tomcat)
   - Copy the WAR file from `target/SpringDataJpa-1.0-SNAPSHOT.war` to your servlet container's deployment directory

4. Alternatively, you can use Maven to run the application with an embedded Tomcat server
   ```bash
   mvn tomcat7:run
   ```

5. Access the application at `http://localhost:8080/SpringDataJpa/`

## Usage

### Department Management

- View all departments: Navigate to the Departments page
- Add a new department: Click "Add Department" and fill in the form
- Edit a department: Click "Edit" next to the department you want to modify
- Delete a department: Click "Delete" next to the department you want to remove

### Employee Management

- View all employees: Navigate to the Employees page
- Add a new employee: Click "Add Employee" and fill in the form
- Edit an employee: Click "Edit" next to the employee you want to modify
- Delete an employee: Click "Delete" next to the employee you want to remove

## Testing

Run the tests using Maven:

```bash
mvn test
```

The application uses H2 an in-memory database for testing to ensure tests are isolated from the production database.

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

Chintan Patel