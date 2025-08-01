# Football Manager API

## Overview
Football Manager API is a RESTful API developed with Spring Boot for managing football teams and players. It supports CRUD operations for teams and players, as well as player transfers between teams, with robust error handling and data validation using Spring Data JPA and Hibernate Validator.

## Features
- **Team Management**: Create, read, update, and delete football teams.
- **Player Management**: Create, read, update, and delete players, with validation for fields like names and dates.
- **Player Transfers**: Transfer players between teams via a dedicated endpoint.
- **Error Handling**: Comprehensive exception handling for resource not found, invalid requests, and unsupported media types.
- **Database Integration**: Uses Spring Data JPA for seamless interaction with a relational database.
- **Postman Collection**: Includes a Postman collection for testing all API endpoints.

## Technologies
- **Spring Boot**: Framework for building the REST API.
- **Spring Data JPA**: For database operations and repository management.
- **Hibernate Validator**: For input validation.
- **H2/PostgreSQL**: Configurable database (H2 for testing, PostgreSQL for production).
- **Maven**: Dependency management.

## Setup and Installation
1. **Prerequisites**:
   - Ensure **Java 17 or later** is installed.
   - Set the `JAVA_HOME` environment variable to your Java installation directory (e.g., `C:\Program Files\Java\jdk-17`).
   - Ensure **Maven** is installed.
2. **Clone the repository**.
3. **Run the application**:
   - Execute the script: run.bat
   
    The following script will:
    1. Verify that `JAVA_HOME` is set.
    2. Run `mvnw clean` to clear previous builds.
    3. Run `mvnw package` to build the project.
    4. Start the application using `%JAVA_HOME%\bin\java -jar target/FootballManager-0.0.1-SNAPSHOT.jar`.

4. **Access the API**:
   - The API will be available at: http://localhost:8080/api

## Import Postman Collection
1. Import `FootballAPI.postman_collection.json` from the repository into **Postman**.
2. Set the `base_url` variable to: **http://localhost:8080**


## API Endpoints
### Teams
- `POST /api/teams` — Create a new team  
- `GET /api/teams/{teamId}` — Get a team by ID  
- `PUT /api/teams/{teamId}` — Update a team by ID  
- `DELETE /api/teams/{teamId}` — Delete a team by ID  
- `GET /api/teams` — Get all teams  
- `POST /api/teams/{teamId}/players/{playerId}` — Transfer a player to a team  
### Players
- `POST /api/players` — Create a new player  
- `GET /api/players/{playerId}` — Get a player by ID  
- `PUT /api/players/{playerId}` — Update a player by ID  
- `DELETE /api/players/{playerId}` — Delete a player by ID  
- `GET /api/players` — Get all players  


## Example Requests
### Create Team
**POST** `http://localhost:8080/api/teams`  
**Headers**:  
`Content-Type: application/json`

**Body**:
```json
{
"teamName": "Barcelona",
"commission": 5.0,
"balance": 1000000.00,
"players": []
}
```

###Create Player
**POST** `http://localhost:8080/api/players`  
**Headers**:  
`Content-Type: application/json`

**Body**:
```json
{
  "firstName": "Lionel",
  "lastName": "Messi",
  "birthDate": "1987-06-24",
  "careerStart": "2004-10-16",
  "joiningTeamDate": "2023-01-01",
  "teamName": "Barcelona"
}
```

###Update Player
**PUT** `http://localhost:8080/api/players/14`  
**Headers**:  
`Content-Type: application/json`

**Body**:
```json
{
  "firstName": "Lionel",
  "lastName": "Messi",
  "birthDate": "1987-06-24",
  "careerStart": "2004-10-16",
  "joiningTeamDate": "2024-01-01",
  "teamName": "Barcelona Updated"
}
```

## Contributing
Contributions are welcome!  
Feel free to fork the repository, submit a pull request, or open an issue for any bugs, feature requests, or improvements.

## License
This project is licensed under the **MIT License**.  
See the [LICENSE](LICENSE) file for more details.
