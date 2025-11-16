# 🚀 MerchantOS - Multi-Tenant E-commerce Platform

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-blue?style=for-the-badge&logo=springboot)](https://spring.io/)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-3.6-black?style=for-the-badge&logo=apachekafka)](https://kafka.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)](https://postgresql.org/)
[![Next.js](https://img.shields.io/badge/Next.js-14-black?style=for-the-badge&logo=next.js)](https://nextjs.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

**Build your own Shopify-like platform** - A complete SaaS solution that enables businesses to launch their own online stores in minutes. Built with scalable multi-tenant architecture and real-time event-driven systems.

---

## ✨ Features

### 🏢 **Multi-Tenant Architecture**
- **Complete data isolation** between stores with automatic tenant resolution
- **Subdomain-based routing** (your-store.merchantos.com)
- **JWT authentication** with built-in tenant context
- **Plan-based limitations** (FREE, STARTER, PRO, ENTERPRISE)

### 🛍️ **Store Management**
- **Product catalog** with categories, inventory, and variants
- **Shopping cart** with guest checkout support
- **Order processing** with status tracking and notifications
- **Customizable storefronts** per tenant

### 🔄 **Event-Driven System**
- **Apache Kafka** for asynchronous processing and real-time updates
- **Real-time notifications** (email, SMS, push notifications)
- **Live analytics dashboard** with Kafka Streams
- **Resilient event handling** with retry mechanisms

### 💳 **Payment Integration**
- **Stripe & MercadoPago** support with multiple payment methods
- **Secure payment processing** with webhook verification
- **Automatic receipt generation** and order confirmation
- **Subscription billing** for tenant plans

### 📊 **Advanced Analytics**
- **Real-time sales metrics** and business intelligence
- **Customer behavior tracking** and conversion analytics
- **Inventory forecasting** and sales predictions
- **Customizable reports** per tenant

---

## 🏗️ System Architecture

### High-Level Overview
```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Storefront    │    │   Admin Dashboard │    │   Mobile App    │
│   (Next.js)     │    │   (React)         │    │   (React Native)│
└─────────────────┘    └──────────────────┘    └─────────────────┘
         │                        │                        │
         └────────────┬───────────┘                        │
                      │                                    │
               ┌──────┴──────┐                             │
               │   API Gateway   │                          │
               │  (Spring Boot)  │                          │
               └──────┬──────┘                             │
                      │                                    │
         ┌────────────┼──────────────────────────┐         │
         │            │                          │         │
    ┌────┴─────┐ ┌────┴─────┐              ┌─────┴────┐    │
    │  Auth    │ │  Tenant  │              │  Orders  │    │
    │ Service  │ │ Service  │              │ Service  │    │
    └────┬─────┘ └────┬─────┘              └─────┬────┘    │
         │            │                          │         │
         └────────────┼─────────────┬────────────┘         │
                      │             │                      │
               ┌──────┴──────┐ ┌────┴─────┐                │
               │  PostgreSQL  │ │  Kafka   │◄───────────────┘
               │   Database   │ │  Events  │
               └──────────────┘ └──────────┘
```

### Multi-Tenant Strategy
- **Database**: Single PostgreSQL database with `tenant_id` segregation
- **Security**: Row-level security and automatic tenant filtering
- **Isolation**: Complete data separation through JWT claims and context
- **Scalability**: Prepared for database-per-tenant future scaling

---

## 🛠️ Tech Stack

### **Backend Services**
- **Java 21** + **Spring Boot 3.2** - Core application framework
- **Spring Security 6** - JWT-based authentication & authorization
- **Spring Data JPA** + **Hibernate** - Database operations & ORM
- **Apache Kafka 3.6** - Event streaming & asynchronous processing
- **PostgreSQL 16** - Primary relational database
- **Redis** - Caching & session storage
- **Flyway** - Database migrations & version control

### **Frontend Applications**
- **Next.js 14** - Storefront & admin dashboard
- **TypeScript** - Type safety & developer experience
- **Tailwind CSS** - Utility-first CSS framework
- **React Query** - Server state management
- **Framer Motion** - Animations & interactions

### **DevOps & Infrastructure**
- **Docker & Docker Compose** - Containerization & local development
- **GitHub Actions** - CI/CD pipelines & automated testing
- **Railway/Render** - Cloud deployment platform
- **Stripe** - Payment processing & subscription management
- **SendGrid** - Transactional email service

---

## 🚀 Quick Start

### Prerequisites
- **Java 21** or later
- **Node.js 18** or later
- **Docker & Docker Compose**
- **Git**

### Local Development Setup

1. **Clone the repository**
```bash
git clone https://github.com/your-username/merchantos.git
cd merchantos
```

2. **Start infrastructure services**
```bash
# Start PostgreSQL, Kafka, Redis
docker-compose up -d postgres kafka redis

# Or start all services including the application
docker-compose up -d
```

3. **Configure environment variables**
```bash
cp backend/.env.example backend/.env
cp frontend/.env.example frontend/.env.local

# Edit the files with your configurations
```

4. **Run the backend application**
```bash
cd backend
./mvnw spring-boot:run
```

5. **Run the frontend application**
```bash
cd frontend
npm install
npm run dev
```

6. **Access the applications**
- **API Server**: http://localhost:8080
- **API Documentation**: http://localhost:8080/swagger-ui.html
- **Storefront**: http://localhost:3000
- **Admin Dashboard**: http://localhost:3000/admin

---

## 📁 Project Structure

```
merchantos/
├── 📁 backend/                          # Spring Boot Application
│   ├── 📁 src/main/java/com/merch/MerchantOS/
│   │   ├── 📁 application/              # Application Layer (Use Cases)
│   │   │   ├── 📁 auth/                 # Authentication use cases
│   │   │   ├── 📁 tenants/              # Tenant management
│   │   │   ├── 📁 products/             # Product catalog management
│   │   │   └── 📁 orders/               # Order processing
│   │   ├── 📁 domain/                   # Domain Layer (Business Logic)
│   │   │   ├── 📁 auth/                 # Auth entities & business rules
│   │   │   ├── 📁 tenants/              # Tenant domain models
│   │   │   ├── 📁 products/             # Product domain logic
│   │   │   ├── 📁 orders/               # Order domain logic
│   │   │   └── 📁 events/               # Domain events definitions
│   │   └── 📁 infrastructure/           # Infrastructure Layer
│   │       ├── 📁 persistence/          # Database repositories & config
│   │       ├── 📁 security/             # JWT, filters & security config
│   │       ├── 📁 messaging/            # Kafka producers & consumers
│   │       └── 📁 web/                  # REST controllers & DTOs
│   ├── 📁 src/test/                     # Comprehensive test suite
│   └── 📁 src/main/resources/           # Configuration files
│
├── 📁 frontend/                         # Next.js Application
│   ├── 📁 app/                          # App router directory
│   │   ├── 📁 (storefront)/             # Public storefront routes
│   │   ├── 📁 admin/                    # Admin dashboard routes
│   │   └── 📁 api/                      # Frontend API routes
│   ├── 📁 components/                   # Reusable React components
│   │   ├── 📁 ui/                       # Basic UI components
│   │   ├── 📁 storefront/               # Store-specific components
│   │   └── 📁 admin/                    # Admin-specific components
│   ├── 📁 lib/                          # Utilities & configurations
│   └── 📁 public/                       # Static assets
│
├── 📁 analytics/                        # Data Processing
│   └── 📁 python/                       # Python data processing scripts
│
├── 📁 docker/                           # Docker configurations
├── 📄 docker-compose.yml               # Service orchestration
├── 📄 Dockerfile                       # Backend container definition
└── 📄 README.md                        # Project documentation
```

---

## 🔧 API Endpoints

### **Authentication & Users**
```http
POST   /api/auth/login                  # User login
POST   /api/auth/register               # User registration
POST   /api/auth/logout                 # User logout
GET    /api/auth/me                     # Current user profile
POST   /api/auth/refresh                # Refresh JWT token
```

### **Tenant Management**
```http
POST   /api/tenants/register            # Register new tenant/store
GET    /api/tenants/me                  # Get current tenant info
PUT    /api/tenants/config              # Update tenant configuration
GET    /api/tenants/plans               # List available plans
```

### **Storefront API (Public)**
```http
GET    /api/storefront/products         # List products with filters
GET    /api/storefront/products/{id}    # Get product details
GET    /api/storefront/categories       # List product categories
POST   /api/storefront/cart/items       # Add item to cart
GET    /api/storefront/cart             # Get current cart
POST   /api/storefront/orders           # Create new order
```

### **Admin API (Protected)**
```http
GET    /api/admin/products              # List products (admin)
POST   /api/admin/products              # Create new product
PUT    /api/admin/products/{id}         # Update product
DELETE /api/admin/products/{id}         # Delete product
GET    /api/admin/orders                # List orders
PUT    /api/admin/orders/{id}/status    # Update order status
GET    /api/admin/analytics             # Get business analytics
```

### **Payment Processing**
```http
POST   /api/payments/create-intent      # Create payment intent
POST   /api/payments/confirm            # Confirm payment
POST   /api/webhooks/stripe             # Stripe webhook handler
```

---

## 🛠️ Development

### Running Tests
```bash
# Backend tests
cd backend
./mvnw test                          # Unit tests
./mvnw verify                        # Integration tests
./mvnw jacoco:report                 # Test coverage report

# Frontend tests
cd frontend
npm test                             # Unit tests
npm run test:e2e                     # E2E tests
```

### Code Quality
```bash
# Backend code quality
./mvnw checkstyle:check              # Code style validation
./mvnw spotbugs:check                # Bug pattern detection
./mvnw pmd:check                     # Static code analysis

# Frontend code quality
npm run lint                         # ESLint validation
npm run type-check                   # TypeScript type checking
```

### Database Management
```bash
# Database migrations
./mvnw flyway:migrate                # Run migrations
./mvnw flyway:clean                  # Clean database (dev only)
./mvnw flyway:info                   # Migration status

# Database inspection
docker-compose exec postgres psql -U merchantos -d merchantos
```

---

## 🤝 Contributing

We welcome contributions from the community! Here's how you can help:

### **Development Process**
1. **Fork** the repository
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Commit your changes** (`git commit -m 'Add amazing feature'`)
4. **Push to the branch** (`git push origin feature/amazing-feature`)
5. **Open a Pull Request**

### **Code Standards**
- Follow **Google Java Style Guide** for backend code
- Use **ESLint & Prettier** for frontend code
- Write **comprehensive tests** for new features
- Update **documentation** for API changes
- Use **conventional commits** for commit messages

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


---

<div align="center">

**Built with ❤️ by the MerchantOS Team**

[![Twitter](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/merchantos)
[![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/merchantos)

</div>
}
