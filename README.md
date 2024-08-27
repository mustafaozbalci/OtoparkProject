# Parking Management System

This project is a web application for managing parking lots. Users can add their parking lots, make reservations, and manage their existing parking reservations.

## Features

- Add parking lot
- Make parking reservations
- View current reservations
- View past reservations
- User registration and login

## Technologies

- **Frontend**: React.js
- **Backend**: Spring Boot (Java)
- **Database**: PostgreSQL

## Setup and Run

### Requirements

- Java 17 or higher
- Node.js and npm/yarn
- PostgreSQL

### Steps

#### Backend Setup

1. Clone this project to your local machine:
    ```bash
    git clone https://github.com/mustafaozbalci/OtoparkProject.git
    cd OtoparkProject
    ```

2. Create your PostgreSQL database and configure the necessary settings. (It will be enough to just create a db named PARK, the system will automatically add the tables and add data into it for you to test.)

3. Configure the `application.properties` file:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/otopark_db
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Install Maven dependencies and run the project:
    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

#### Frontend Setup

1. Navigate to the `OtoparkFrontend` directory:
    ```bash
    cd OtoparkFrontend
    ```

2. If you don't have the frontend code yet, click on the OtoparkFrontend folder and copy the code from the GitHub page that opens:
    - [OtoparkFrontend GitHub Page](https://github.com/mustafaozbalci/OtoparkFrontend)

3. Install the necessary dependencies:
    ```bash
    npm install
    # or
    yarn install
    ```

4. Start the frontend application:
    ```bash
    npm start
    # or
    yarn start
    ```

### Usage

1. Open your browser and go to `http://localhost:3000`.
2. Register a new user or log in with an existing user.
3. Use the interface to add parking lots, make reservations, and manage existing reservations.

## Contributing

If you want to contribute, please open an issue first to discuss what you would like to change. Then you can create a pull request.

## License

This project is licensed under the MIT License. For more details, see the `LICENSE` file.
