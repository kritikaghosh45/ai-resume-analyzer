# AI Resume Analyzer

[![Java CI](https://github.com/yourusername/ai-resume-analyzer/actions/workflows/maven.yml/badge.svg)](https://github.com/yourusername/ai-resume-analyzer/actions/workflows/maven.yml)
[![Java Version](https://img.shields.io/badge/Java-17+-blue.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

A Java Spring Boot prototype for AI-powered resume analysis with file upload, text extraction, skill detection, and scoring. This version uses only standard Java libraries for text extraction, supporting plain text resume files.

## 🚀 Features

- 📄 **Resume Upload**: Accept plain text resume files (.txt)
- 🔍 **Text Extraction**: Extract resume content using standard Java I/O
- 🧠 **Skill Detection**: Identify key technical and professional skills
- 📊 **Scoring System**: Calculate relevance scores for job-title matching
- 🌐 **REST API**: Simple, clean API for integration
- 🐳 **Docker Support**: Containerized deployment ready
- ✅ **Unit Tests**: Comprehensive test coverage

## 📋 Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Examples](#examples)
- [Development](#development)
- [Docker](#docker)
- [Contributing](#contributing)
- [License](#license)

## 🛠 Installation

### Prerequisites

- Java 17 or higher
- Maven 3.8+ (or use the included Maven wrapper)

### Clone and Build

```bash
git clone https://github.com/kritikaghosh45/ai-resume-analyzer.git
cd ai-resume-analyzer

# Build the project
./mvnw clean package

# Run tests
./mvnw test
```

## 🚀 Usage

### Running the Application

```bash
# Run with Maven wrapper
./mvnw spring-boot:run

# Or run the JAR directly
java -jar target/ai-resume-analyzer-0.1.0.jar
```

The application will start on `http://localhost:8080`

### API Endpoints

#### POST `/api/v1/resume/analyze`

Analyze a resume file and return skill detection and scoring results.

**Parameters:**
- `file` (required): Plain text resume file (.txt)
- `jobTitle` (optional): Job title to bias scoring

**Response:**
```json
{
  "fileName": "resume.txt",
  "detectedSkills": ["java", "spring", "docker", "aws"],
  "score": 45,
  "relevance": 0.75,
  "jobTitleMatchScore": 15,
  "extractedText": "Experienced Java developer..."
}
```

## 📖 API Documentation

### Base URL
```
http://localhost:8080/api/v1
```

### Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/resume/analyze` | Analyze resume file |

### Request/Response Examples

#### Successful Analysis
```bash
curl -X POST http://localhost:8080/api/v1/resume/analyze \
  -F "file=@sample_resume.txt" \
  -F "jobTitle=Java Developer"
```

Response:
```json
{
  "fileName": "sample_resume.txt",
  "detectedSkills": ["java", "spring", "hibernate", "docker"],
  "score": 52,
  "relevance": 0.8666666666666667,
  "jobTitleMatchScore": 20,
  "extractedText": "Experienced Java Developer with 5+ years..."
}
```

#### Error Response
```json
{
  "timestamp": "2026-05-14T10:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Unsupported file type. Only plain text files are supported in this pure Java version.",
  "path": "/api/v1/resume/analyze"
}
```

## 💡 Examples

### 1. Basic Resume Analysis

Create a sample resume file `sample_resume.txt`:

```
Experienced Java Developer

Skills: Java, Spring Boot, Hibernate, Docker, Kubernetes, AWS
Experience: 5+ years in software development
```

Then analyze it:

```bash
curl -X POST http://localhost:8080/api/v1/resume/analyze \
  -F "file=@sample_resume.txt"
```

### 2. Job-Specific Analysis

```bash
curl -X POST http://localhost:8080/api/v1/resume/analyze \
  -F "file=@sample_resume.txt" \
  -F "jobTitle=Full Stack Developer"
```

### 3. Using with Postman

1. Open Postman
2. Create a new POST request to `http://localhost:8080/api/v1/resume/analyze`
3. In Body tab, select "form-data"
4. Add key "file" with type "File" and select your .txt resume
5. Add key "jobTitle" with type "Text" and value "Java Developer"
6. Send the request

## 🧪 Development

### Project Structure

```
src/
├── main/
│   ├── java/com/aianalyzer/
│   │   ├── AIResumeAnalyzerApplication.java
│   │   ├── controller/ResumeController.java
│   │   ├── service/ResumeAnalysisService.java
│   │   ├── dto/ResumeAnalysisResponse.java
│   │   └── util/ResumeTextExtractor.java
│   └── resources/application.yml
└── test/
    └── java/com/aianalyzer/service/ResumeAnalysisServiceTest.java
```

### Running Tests

```bash
./mvnw test
```

### Code Style

This project follows standard Java conventions. Use your IDE's formatting tools or:

```bash
./mvnw spotless:apply
```

## 🐳 Docker

### Build Docker Image

```bash
docker build -t ai-resume-analyzer .
```

### Run Container

```bash
docker run -p 8080:8080 ai-resume-analyzer
```

### Docker Compose

```yaml
version: '3.8'
services:
  ai-resume-analyzer:
    build: .
    ports:
      - "8080:8080"
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines

- Write unit tests for new features
- Follow Java naming conventions
- Update documentation as needed
- Ensure all tests pass before submitting PR

## 🙏 Acknowledgments

- Built with Spring Boot
- Inspired by modern resume analysis tools
- Pure Java implementation for educational purposes
