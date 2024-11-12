# Number Range Summerizer

A simple application that accepts a collection of comma-separated numbers and summarizes them into a range format (e.g., “1, 2, 3, 4, 5” becomes “1-5”). The backend is built with Spring Boot, and the frontend is built with React using TypeScript (Vite). The application is containerized using Docker and orchestrated with Docker Compose.

#

### Prerequisites

To run this application, ensure you have the following installed:

- **Docker**: For running the application in containers.
- **Docker Compose**: For running the containers together.
- **Java 17**: (If you need to make backend modifications outside Docker.)
- **Node & npm**: (If you need to make frontend modifications outside Docker.)

##

### Running the Application with Docker

Follow these steps to run the application containers:

1. **Clone the Repository**

   ```
   git clone <repository-url>
   cd number-range-summarizer
   ```

   ##

2. **Start the Application with Docker Compose**

   Run the following command in the root directory of the repository to build and start both the frontend and backend containers:

   ```
   docker-compose up -d --build
   ```

   This command will:

   - Build both the backend and frontend Docker images.
   - Start the backend container on port 8081 and the frontend container on port 5174.

   ##

3. **Access the Application**

   **API Endpoints (Backend)**

   The backend API provides the following endpoint for number range summarization:

   - **(POST) /api/ranges/summarize** - Accepts a comma-separated list of numbers and returns the summarized range.

    __Example Request:__

        {
            "numbers": "1, 2, 3, 4, 5, 10, 11, 12"
        }

    __Example Response:__

        {
            "data": "1-5, 10-12"
        }

   ##

### Troubleshooting

- **Port Conflicts:** Ensure that ports **8081** (backend) and **5174** (frontend) are not in use by other applications on your system.
- **Docker Not Found:** If Docker commands are not recognized, ensure Docker and Docker Compose are installed and properly configured on your machine.
