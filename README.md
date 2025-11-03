#  MerchantOS - Multi-Tenant E-commerce Platform

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green.svg)](https://spring.io)
[![React](https://img.shields.io/badge/Next.js-14-blue.svg)](https://nextjs.org)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

**SaaS platform that allows businesses to launch their own online stores in minutes.** Built with scalable multi-tenant architecture and real-time analytics.

![Architecture Diagram](https://via.placeholder.com/800x400/2D3748/FFFFFF?text=MerchantOS+Architecture)

## ✨ Features

### 🏢 Multi-Tenant Architecture
- Complete data isolation between stores
- JWT-based authentication with tenant context
- Scalable design supporting thousands of stores

### 🛍️ Store Management
- Product catalog with categories and inventory
- Order processing and payment integration
- Customizable store settings

### 📊 Real-time Analytics
- Kafka-powered event streaming
- Python data processing pipeline
- Dashboard with sales metrics and insights

### 💳 Payments
- Stripe & MercadoPago integration
- Secure payment processing
- Webhook handling for payment confirmation

## 🏗️ System Architecture
Frontend (Next.js) → API Gateway (Spring Boot) → Microservices → PostgreSQL & Kafka
↑ ↑ ↑ ↑
Tenant UI Auth & Routing Business Logic Data & Events


### Multi-Tenant Strategy
- **Database:** Single database with `tenant_id` segregation
- **Security:** Row-level data isolation through JWT claims
- **Scalability:** Prepared for database-per-tenant future scaling

## 🛠️ Tech Stack

### Backend
- **Java 17** + **Spring Boot 3** 
- **Spring Security** with JWT
- **Spring Data JPA** + **Hibernate**
- **PostgreSQL** for primary data storage
- **Apache Kafka** for event streaming
- **Docker** for containerization

### Frontend
- **Next.js 14** with App Router
- **TypeScript** for type safety
- **Tailwind CSS** for styling
- **React Query** for state management

### DevOps
- **Docker** + **Docker Compose**
- **GitHub Actions** for CI/CD
- **Railway/Render** for deployment

## 🚀 Quick Start

### Prerequisites
- Java 17
- Node.js 18+
- Docker & Docker Compose

### Local Development

1. **Clone the repository**
```bash
git clone https://github.com/your-username/merchantos.git
cd merchantos
```
📁 Project Structure
merchantos/
├── backend/
│   ├── application/     # Use cases and service layer
│   ├── domain/          # Business entities and logic
│   ├── infrastructure/  # Technical implementations
│   └── shared/          # Cross-cutting concerns
├── frontend/
│   ├── app/             # Next.js app router
│   ├── components/      # Reusable React components
│   └── lib/             # Utilities and configurations
├── analytics/
│   └── python/          # Data processing scripts
└── docker/              # Docker configurations
