# Patient Management System — Microservices with Java Spring Boot, Kafka, gRPC & AWS

A production-ready microservices-based **Patient Management System** built using **Spring Boot**, **Docker**, **Kafka**, **gRPC**, and deployed using **AWS CDK on LocalStack**.

This project is based on the architecture taught in the *Build & Deploy a Production-Ready Patient Management System* course by **Chris Blakely** — extended and customized with additional modules and improvements.

---

## 🏥 System Overview

This system manages:

- Patient onboarding & validation  
- User authentication & JWT issuance  
- Billing service with gRPC  
- Event-driven analytics (Kafka consumer)  
- API gateway routing + request filtering  
- CI-ready infrastructure deployment using AWS CDK  

It is fully containerized with Docker and can run in both dev and production environments.

---

## 🧱 **Microservices Architecture**

The project uses a **multi-module microservices architecture**, with each service independently buildable and deployable.

### **Services Included**

| Service | Tech Stack | Purpose |
|---------|------------|---------|
| **patient-service** | Spring Boot, Kafka Producer, gRPC Client | CRUD operations for Patient data, emits Kafka events |
| **billing-service** | Spring Boot, gRPC Server | Provides gRPC interface to create billing accounts |
| **analytics-service** | Spring Boot, Kafka Consumer | Processes patient events and stores analytics |
| **auth-service** | Spring Boot, JWT, Spring Security | Handles login, token generation & validation |
| **api-gateway** | Spring Cloud Gateway | Routes external requests, validates JWT, handles exceptions |
| **infrastructure** | AWS CDK, LocalStack | Infrastructure-as-Code: VPC, MSK, Databases, ECS, ALB |
| **integration-test** | JUnit, RestAssured | End-to-end tests across all services |

---

## 🚀 Features

 **Modular Microservices**  
 **JWT-based authentication**  
 **Kafka event streaming**  
 **gRPC communication between services**  
 **Dockerized services for easy deployment**  
 **Infrastructure-as-Code with AWS CDK**  
 **Local AWS emulation using LocalStack**  
 **PostgreSQL database provisioning**  
 **API Gateway with filters & exception handling**  
 **Full integration tests: Auth + Patient + Gateway**  



