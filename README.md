# 🤖 AI Lead Analyzer - Enterprise Backend

> **Revolutionize Your Sales Operations with Intelligent Lead Qualification & Scoring**

A sophisticated, production-grade Spring Boot application powered by OpenAI, designed to automatically analyze, qualify, and score sales leads with enterprise-level reliability and performance.

## ✨ Core Capabilities

### 🎯 Intelligent Lead Analysis
- **AI-Powered Scoring System** - Leverage state-of-the-art machine learning to score leads based on engagement signals and business fit
- **Intent Detection** - Automatically identify buyer intent from lead messages
- **Budget Estimation** - ML-driven budget assessment for smarter sales prioritization
- **Urgency Analysis** - Real-time urgency level classification to accelerate deals

### 📧 Automated Lead Engagement
- **Smart Email Integration** - Automatically send personalized, AI-generated responses to every prospect
- **Lead Status Tracking** - Monitor lead lifecycle from initial contact to conversion
- **RESTful API** - Enterprise-grade REST API with full CRUD operations

### 🏗️ Enterprise Architecture
- **Spring Boot 4.0.6** - Latest version with cutting-edge performance optimizations
- **OpenAI Integration** - Advanced GPT models for intelligent analysis
- **Persistent Storage** - H2 database with full JPA support
- **Health Monitoring** - Built-in Spring Actuator for production observability
- **CORS Support** - Seamless integration with modern frontend frameworks

## 🛠️ Technical Specifications

- **Java 25** - Modern JVM with latest language features and performance improvements
- **Spring Boot 4.0.6** - Enterprise framework with comprehensive ecosystem
- **Spring Data JPA** - ORM for clean, efficient database interactions
- **Spring Mail** - Integrated email service for lead communication
- **Jackson** - High-performance JSON serialization
- **H2 Database** - Embedded relational database for rapid development and testing

## 🚀 Quick Start

```bash
# Build the application
./gradlew build

# Run the application
./gradlew bootRun

# Run tests
./gradlew test

# Build Docker-ready JAR
./gradlew bootJar
```

## 📡 API Endpoints

- `POST /api/leads` - Create and analyze a new lead
- `GET /api/leads` - Retrieve all leads with analysis
- `GET /api/leads/{id}` - Get detailed lead analysis
- `PUT /api/leads/{id}` - Update lead information and re-analyze

## 🔧 Configuration

Set the following environment variables:
```bash
OPENAI_API_KEY=sk-...           # OpenAI API key for AI analysis
SPRING_MAIL_USERNAME=...         # Gmail address for outbound emails
SPRING_MAIL_PASSWORD=...         # Gmail app password
```

## 🎯 Use Cases

- **Sales Acceleration** - Automatically qualify and prioritize leads
- **Lead Nurturing** - Send intelligent, personalized follow-ups at scale
- **Data-Driven Sales** - Make smarter decisions with AI-powered insights
- **Enterprise Lead Management** - Handle thousands of leads with zero manual qualification

## 🌟 Why Choose This Solution?

✅ **Fully Automated** - No manual lead scoring or email composition  
✅ **Production Ready** - Enterprise-grade Spring Boot with proven reliability  
✅ **AI-Powered** - State-of-the-art OpenAI models for intelligent analysis  
✅ **Scalable** - Handle enterprise-level lead volumes effortlessly  
✅ **Modern Stack** - Java 25 with latest Spring framework innovations  
✅ **Extensible** - Clean architecture for easy customization and enhancement  

---

**Transform your sales operations today. Let AI do the heavy lifting of lead qualification.**
