# Order & Payment System (Backend Service)

## 1. Problem Statement
Build a backend service to manage an **Order & Payment System**.

This service should allow users to:
- Create new orders
- Cancel orders
- Make payments

The system should handle:
- Rollback if an order fails
- Rollback if a payment fails
- Avoid duplicate orders
- Handle system failures gracefully

---

## 2. In Scope
- **Order Management**
- **Product Management** (related to order: quantity, name, description)
- **Payment Management**

Scope includes **API design, implementation, and documentation only** (no UI).

Functional scope:
1. Create an order for a user
2. Retry an order if it is in `ORDER_FAILED` state
3. Cancel an order for a user
4. Allow payment for an order
5. Retry payment if order state is `PAYMENT_FAILED`
6. Allow users to modify an order if the order is in payment state
7. Local deployment and Docker deployment

---

## 3. Out of Scope
- Notifications
- Cart management
- Actual integration with payment gateway
- Cloud deployment
- Shipping management
- Admin user modifying orders on behalf of users

---

## 4. Order Lifecycle States
An order can be in one of the following states:

- **CREATED** – Initial state when an order is created
- **ORDER_FAILED** – Order failed (e.g., product unavailable)
- **PAYMENT_IN_PROGRESS** – Payment initiated
- **PAYMENT_DONE** – Payment successful
- **PAYMENT_FAILED** – Payment failed
- **ORDER_CANCELLED** – Order cancelled by user

---

## 5. State Transition Rules
- `ORDER_FAILED → CREATED` (retry order)
- `CREATED → PAYMENT_IN_PROGRESS → PAYMENT_DONE`
- `CREATED → PAYMENT_IN_PROGRESS → PAYMENT_FAILED`
- `CREATED → ORDER_CANCELLED`

---

## 6. Failure Handling Rules
- **Payment failure**: rollback all changes, unblock reserved products, mark order as `PAYMENT_FAILED`
- **Duplicate orders**: prevent multiple identical orders for the same products
- **Payment retry**: prevent duplicate payments if user retries or refreshes during payment
- **Delayed acknowledgement**: if payment succeeds but system doesn’t acknowledge within 3 minutes, handle gracefully

---

## 7. API List (Names Only)
- `POST /order` – Create order  
- `GET /order/{id}` – Get order details  
- `PUT /order/{id}` – Modify order  
- `POST /order/cancelorder/{id}` – Cancel order  
- `POST /payment` – Process payment for an order  
- `POST /retrypayment` – Retry payment for an order  
- `GET /payment/{paymentId}` – Get payment details  

> No API versioning or advanced filtering in this phase.

---

## 8. Key Design Decisions
- Monolithic design for Phase 1 (Order + Payment in one service)
- Future enhancement: split into microservices with inter‑service communication

---

## 9. Non‑Goals
- High availability
- Horizontal scaling
