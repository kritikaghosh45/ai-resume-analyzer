# AI Resume Analyzer

[![Java CI](https://github.com/kritikaghosh45/ai-resume-analyzer/actions/workflows/maven.yml/badge.svg)](https://github.com/kritikaghosh45/ai-resume-analyzer/actions/workflows/maven.yml)
[![Java Version](https://img.shields.io/badge/Java-17+-blue.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

A Java Spring Boot application for AI-powered resume analysis with file upload, text extraction, skill detection, and scoring.

---

## Features

- Resume Upload — Accept plain text resume files (.txt)
- Text Extraction — Extract resume content using standard Java I/O
- Skill Detection — Identify key technical and professional skills
- Scoring System — Calculate relevance scores for job-title matching
- REST API — Simple, clean API for integration
- Docker Support — Containerized deployment ready
- Unit Tests — Comprehensive test coverage

---

## Installation

### Prerequisites
- Java 17 or higher
- Maven 3.8+ (or use the included Maven wrapper)

### Clone and Build

```bash
git clone https://github.com/kritikaghosh45/ai-resume-analyzer.git
cd ai-resume-analyzer

# Mac/Linux
./mvnw clean package
./mvnw test

# Windows
mvnw.cmd clean package
mvnw.cmd test
```

---

## Usage

```bash
# Mac/Linux
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run

# Or run the JAR directly
java -jar target/ai-resume-analyzer-0.1.0.jar
```

The application starts on `http://localhost:8080`

---

## API

### POST `/api/v1/resume/analyze`

| Key | Type | Required |
|-----|------|----------|
| file | File | Yes (.txt) |
| jobTitle | Text | No |

### Example Request

```bash
curl -X POST http://localhost:8080/api/v1/resume/analyze \
  -F "file=@sample_resume.txt" \
  -F "jobTitle=Java Developer"
```

### Example Response

```json
{
  "fileName": "sample_resume.txt",
  "detectedSkills": ["java", "spring", "hibernate", "docker"],
  "score": 52,
  "relevance": 0.87,
  "jobTitleMatchScore": 20,
  "extractedText": "Experienced Java Developer with 5+ years..."
}
```

---

## Project Structure

<img width="767" height="337" alt="image" src="https://github.com/user-attachments/assets/22b6d2a7-79f8-4328-9c0f-4af8c596e7c6" />


## Docker

```bash
docker build -t ai-resume-analyzer .
docker run -p 8080:8080 ai-resume-analyzer
```

---

## Using with Postman

1. Open Postman
2. New POST request → `http://localhost:8080/api/v1/resume/analyze`
3. Body → form-data
4. Add key `file` (type: File) → select your `.txt` resume
5. Add key `jobTitle` (type: Text) → e.g. `Java Developer`
6. Click Send

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add your feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a Pull Request

---

## Acknowledgments

- Built with Spring Boot
- Pure Java implementation for educational purposes
- Inspired by modern resume analysis tools

