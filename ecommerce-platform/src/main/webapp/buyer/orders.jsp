<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Orders</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            background: #f4f6fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }
        .page-box {
            max-width: 1000px;
            margin: 40px auto;
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 12px 30px rgba(15, 23, 42, 0.08);
            padding: 24px 32px 32px;
        }
        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 24px;
        }
    </style>
</head>
<body>

<div class="page-box">
    <div class="page-header">
        <div>
            <h3 class="mb-0">My Orders</h3>
            <small class="text-muted">
                Orders placed by
                <strong>${sessionScope.currentUser.username}</strong>
            </small>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/buyer/cart"
               class="btn btn-outline-secondary btn-sm">
                View Cart
            </a>
            <a href="${pageContext.request.contextPath}/logout"
               class="btn btn-outline-danger btn-sm">
                Logout
            </a>
        </div>
    </div>

    <c:if test="${empty orders}">
        <div class="alert alert-info mb-0">
            You have not placed any orders yet.
        </div>
    </c:if>

    <c:if test="${not empty orders}">
        <table class="table table-hover align-middle">
            <thead>
            <tr>
                <th>#Order ID</th>
                <th>Date</th>
                <th>Status</th>
                <th class="text-end">Total (₹)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="o" items="${orders}">
                <tr>
                    <td>#${o.orderId}</td>
                    <td>${o.orderDate}</td>
                    <td>
                        <span class="badge bg-secondary">
                            ${o.status}
                        </span>
                    </td>
                    <td class="text-end">
                        ${o.totalAmount}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="mt-3">
        <a href="${pageContext.request.contextPath}/products"
           class="btn btn-primary">
            Browse Products
        </a>
    </div>
</div>

</body>
</html>
