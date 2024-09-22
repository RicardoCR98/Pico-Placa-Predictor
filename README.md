# Pico y Placa Predictor

This project is an application designed to predict whether a vehicle can circulate based on the **Pico y Placa** restrictions. The prediction is determined by the day of the week, the vehicle's license plate number, and the time of day.

## Table of Contents
- [Pico y Placa Predictor](#pico-y-placa-predictor)
  - [Table of Contents](#table-of-contents)
  - [Technologies Used](#technologies-used)
  - [Architecture](#architecture)
    - [Key Layers](#key-layers)
  - [Project Structure](#project-structure)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
  - [Running the Application](#running-the-application)
    - [Run Docker Containers](#run-docker-containers)
      - [1. **Run the containers**:](#1-run-the-containers)
      - [2. **Access the application**:](#2-access-the-application)
  - [API Documentation](#api-documentation)
    - [Endpoint: Predict Circulation](#endpoint-predict-circulation)
      - [Request Body](#request-body)
      - [Response](#response)
      - [Example using `curl`](#example-using-curl)
  - [Automated Testing](#automated-testing)
    - [Testing Tools](#testing-tools)
    - [Running Tests](#running-tests)
    - [Test Cases](#test-cases)
    - [Screenshots](#screenshots)

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

![Architecture](/Images/Readme_Architecture.png)

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
│   ├── Dockerfile             # Backend Dockerfile 
│   └── pom.xml
│
├── frontend/                  # Frontend (HTML, CSS, Bootstrap)
│   ├── index.html
│   ├── styles.css
│   ├── Dockerfile             # Frontend Dockerfile
│   ├── nginx.conf             # File to configure nginx 
│   └── scripts.js
├── README.md                  # Readme
│── docker-compose.yml         # Configurations
└── .gitignore                 # Ignore unnecessary files
```

## Prerequisites

Before you begin, ensure you have the following installed:

- **Docker**: Install Docker on your machine. [Download Docker](https://www.docker.com/products/docker-desktop/).

## Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/RicardoCR98/Pico-Placa-Predictor.git
   cd Pico-Plate-Predictor
   ```

## Configuration

No need to modify any configuration files as the project is pre-configured to run using Docker.

## Running the Application

### Run Docker Containers

You can run the pre-built Docker containers for both the backend and frontend using Docker Compose.

#### 1. **Run the containers**:

   Simply run the following command to start the application using Docker Compose:

   ```bash
git clone https://github.com/RicardoCR98/Pico-Placa-Predictor.git
cd Pico-Plate-Predictor
docker-compose up
   ```

   - This will download the images from Docker Hub and run the frontend and backend containers, exposing the backend on port **8080** and the frontend on port **80**.

#### 2. **Access the application**:

   - **Backend**: Access the backend API at `http://localhost:8080/predict` (It is not necessary to enter the link directly unless you're making API requests).
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
cd .\Pico-Placa-Predictor\Backend
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


### Screenshots


![Allowed](/Images/Readme_Allowed.png)

![Restricted](/Images/Readme_Invalid.png)





![Invalid](/Images/Readme_Restricted.png)
