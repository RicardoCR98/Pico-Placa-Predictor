# Pico y Placa Backend

This project is an application designed to predict whether a vehicle can circulate based on the **Pico y Placa** restrictions. The prediction is determined by the day of the week, the vehicle's license plate number, and the time of day.

## Table of Contents

- [Pico y Placa Backend](#pico-y-placa-backend)
  - [Table of Contents](#table-of-contents)
  - [Technologies Used](#technologies-used)
  - [Architecture](#architecture)
    - [Key Layers](#key-layers)
  - [Project Structure](#project-structure)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
  - [Running the Application](#running-the-application)
    - [Download and Run Docker Containers](#download-and-run-docker-containers)
  - [API Documentation](#api-documentation)
    - [Endpoint: Predict Circulation](#endpoint-predict-circulation)
      - [Request Body](#request-body)
      - [Response](#response)
      - [Example using `curl`](#example-using-curl)
  - [Automated Testing](#automated-testing)
    - [Testing Tools](#testing-tools)
    - [Running Tests](#running-tests)
    - [Test Cases](#test-cases)

---

## Technologies Used

- **Java**: Main backend programming language.
- **Spring Boot**: Framework for building the backend application.
- **JUnit 5 & Mockito**: For unit and integration testing.
- **HTML, CSS, JS, Bootstrap**: Frontend technologies.
- **Docker**: For containerization and deployment.
- **Maven**: Dependency management and build tool.

## Architecture

The application follows an **N-layered architecture**, ensuring separation of concerns and scalability.

![Architecture](https://github.com/user-attachments/assets/5330ef5c-7121-400b-9f0e-b164182ee75d)

### Key Layers

- **Controller**: Handles incoming HTTP requests and responses.
- **Service**: Contains the business logic for predicting circulation restrictions.
- **Model**: Defines data structures for requests and responses.
- **Exception**: Manages custom exceptions and global error handling.
- **Utils**: Provides utility functions such as date parsing and validation.

## Project Structure

```
Pico-Plate-Predictor/
├── backend/                   # Backend (Spring Boot)
│   ├── src/
│   └── pom.xml
│
├── frontend/                  # Frontend (HTML, CSS, Bootstrap)
│   ├── index.html
│   ├── styles.css
│   └── scripts.js
├── README.md                  # Readme
│── docker-compose.yml         # Configurations
└── .gitignore                 # Ignore unnecessary files
```

## Prerequisites

Before you begin, ensure you have the following installed:

- **Docker**: Install Docker on your machine. [Docker Installation Guide](https://docs.docker.com/get-docker/).

## Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/RicardoCR98/Pico-Placa-Predictor.git
   cd Pico-Plate-Predictor
   ```

## Configuration

No need to modify any configuration files as the project is pre-configured to run using Docker.

## Running the Application

### Download and Run Docker Containers

Instead of building and running the application manually, you can run the pre-built Docker containers for both the backend and frontend.

#### 1. **Download and run the backend container**:

   ```bash
   docker pull ricardocr98/pico-placa-backend:latest
   docker run -d -p 8080:8080 ricardocr98/pico-placa-backend:latest
   ```

   - This will download the backend image and run it in the background, exposing it on port **8080**.

#### 2. **Download and run the frontend container**:

   ```bash
   docker pull ricardocr98/pico-placa-frontend:latest
   docker run -d -p 80:80 ricardocr98/pico-placa-frontend:latest
   ```

   - This will download the frontend image and run it, exposing it on port **80**.

#### 3. **Access the application**:

   - **Backend**: Access the backend API at `http://localhost:8080`.
   - **Frontend**: Open your browser and navigate to `http://localhost` to interact with the application.

## API Documentation

### Endpoint: Predict Circulation

- **URL**: `http://localhost:8080/predict`
- **Method**: `POST`
- **Description**: Predicts whether a vehicle can circulate based on the license plate number, date, and time.

#### Request Body

```json
{
  "licensePlateNumber": "ABC-1234",
  "date": "27-04-2024",
  "time": "08:30"
}
```

- **licensePlateNumber**: `String` - The vehicle's license plate number.
- **date**: `String` (dd-MM-yyyy) - The date to check.
- **time**: `String` (HH:mm) - The time to check.

#### Response

If the response is **TRUE**:
```json
{
  "canDrive": true,
  "message": "The vehicle with plate number ABC-1234 is ALLOWED to drive on SATURDAY at 08:30."
}
```

If the response is **FALSE**:
```json
{
  "canDrive": false,
  "message": "The vehicle with plate number ABC-1234 is RESTRICTED from driving on 27-04-2024 at 08:30."
}
```

#### Example using `curl`
For windows:

```bash
curl --location "http://localhost:8080/predict" ^ --header "Content-Type: application/json" ^ --data "{\"licensePlateNumber\":\"PBM-1233\", \"date\":\"17-09-2024\", \"time\":\"07:38\"}"
```

## Automated Testing

The project includes comprehensive automated tests to ensure functionality and reliability.

### Testing Tools

- **JUnit 5**: For writing and running tests.
- **Mockito**: For mocking dependencies.
- **Spring Test**: For integration testing with the Spring context.

### Running Tests

Execute the following Maven command to run all tests:

```bash
mvn test
```

### Test Cases

1. **Vehicle Restricted During Prohibited Hours and Days**
    - Ensures that a vehicle is correctly identified as restricted when its license plate ends with a restricted digit on a restricted day and during restricted hours.

2. **Unrestricted Vehicle Outside of Prohibited Hours**
    - Verifies that a vehicle is allowed to circulate if the time is outside the restricted hours, even on a restricted day.

3. **Vehicle Not Restricted on Weekends**
    - Confirms that vehicles can circulate freely on Saturdays and Sundays, regardless of the license plate number.

4. **Invalid License Plate Handling**
    - Tests the application's response to invalid license plate formats or missing data.

5. **Handling of Blank Inputs**
    - Checks the application's behavior when license plate number, date, or time fields are blank or null.
