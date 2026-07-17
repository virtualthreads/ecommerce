# ecommerce
This is an enterprise e-commerce application built to demonstrate how real-world online shopping platforms are designed and developed using Java and modern software engineering practices.

The application is divided into the following microservices. The goal is to build a production-style application step by step, starting from the basics.

```text
                    +----------------+
                    |    Frontend    |
                    |  React/HTML    |
                    +--------+-------+
                             |
                             |
                    +--------v--------+
                    | API Gateway     |
                    +--------+--------+
                             |
      ----------------------------------------------------
      |          |            |            |             |
      |          |            |            |             |
+-----v----+ +---v-----+ +----v-----+ +----v-----+ +-----v-----+
| User     | | Product | | Cart     | | Order    | | Inventory |
| Service  | | Service | | Service  | | Service  | | Service   |
+----------+ +----------+ +----------+ +----------+ +-----------+
```

## Services

- **User Service** – Manages user registration, login, and profile details.
- **Product Service** – Manages products and product information.
- **Cart Service** – Manages the user's shopping cart.
- **Order Service** – Handles order placement and order history.
- **Inventory Service** – Manages product stock and availability.

## Current Status

Development has started with the **Product Service** because it does not depend on any other service.

## Future Plan

Once all the services are completed, We'll plan to create a **Common Service** (shared module) to move reusable code into one place and reduce duplicate code across the application.