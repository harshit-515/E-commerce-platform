<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Placed</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <style>
        body {
            background: #f3f4f6;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }
        .success-box {
            max-width: 640px;
            margin: 60px auto;
            background: #fff;
            border-radius: 18px;
            box-shadow: 0 15px 35px rgba(15,23,42,0.1);
            padding: 32px 40px 36px;
            text-align: center;
        }
        .icon {
            font-size: 32px;
            color: #16a34a;
        }
        .btn-primary {
            background-color: #2563eb;
            border-color: #2563eb;
        }
        .btn-primary:hover {
            background-color: #1d4ed8;
            border-color: #1d4ed8;
        }
    </style>
</head>
<body>

<div class="success-box">
    <div class="icon mb-3">✅</div>
    <h3 class="mb-2 text-success">Order Placed Successfully</h3>

    <p class="mb-1">
        Your order ID is
        <strong>#<c:out value="${orderId}" /></strong>
    </p>
    <p class="mb-4">
        Total amount:
        <strong>₹ <c:out value="${totalAmount}" /></strong>
    </p>

    <div class="d-flex justify-content-center gap-2">
        <a href="${pageContext.request.contextPath}/products"
           class="btn btn-primary">
            Continue Shopping
        </a>
        <a href="${pageContext.request.contextPath}/buyer/cart"
           class="btn btn-outline-secondary">
            View Cart
        </a>
        <a href="${pageContext.request.contextPath}/buyer/orders"
           class="btn btn-outline-secondary">
            View My Orders
        </a>
    </div>
</div>

</body>
</html>
