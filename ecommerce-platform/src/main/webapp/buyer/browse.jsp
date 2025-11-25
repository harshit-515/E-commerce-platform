<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Browse Products</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <style>
        body {
            background: #f5f7fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }

        .page-wrapper {
            max-width: 1200px;
            margin: 30px auto;
        }

        .page-header {
            background: #ffffff;
            border-radius: 12px;
            padding: 20px 24px;
            box-shadow: 0 4px 12px rgba(15, 23, 42, 0.06);
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 24px;
        }

        .page-title {
            font-size: 1.6rem;
            font-weight: 600;
            margin: 0;
            color: #111827;
        }

        .page-subtitle {
            margin: 4px 0 0;
            color: #6b7280;
            font-size: 0.95rem;
        }

        .logout-link {
            color: #ef4444;
            font-weight: 500;
            text-decoration: none;
        }

        .logout-link:hover {
            text-decoration: underline;
            color: #b91c1c;
        }

        .product-card {
            border-radius: 14px;
            border: none;
            box-shadow: 0 10px 25px rgba(15, 23, 42, 0.08);
            transition: transform 0.15s ease, box-shadow 0.15s ease;
            height: 100%;
        }

        .product-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 16px 35px rgba(15, 23, 42, 0.15);
        }

        .product-image-placeholder {
            background: linear-gradient(135deg, #eef2ff, #e0f2fe);
            border-radius: 14px 14px 0 0;
            height: 160px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #4b5563;
            font-weight: 600;
            font-size: 1.1rem;
        }

        .price-tag {
            font-size: 1.1rem;
            font-weight: 700;
            color: #2563eb;
        }

        .category-badge {
            font-size: 0.75rem;
            text-transform: uppercase;
            letter-spacing: .07em;
            background: #eff6ff;
            color: #1d4ed8;
            border-radius: 999px;
            padding: 4px 10px;
        }

        .stock-badge {
            font-size: 0.8rem;
        }

        .btn-add-cart {
            background: #2563eb;
            border: none;
            border-radius: 999px;
            font-weight: 500;
        }

        .btn-add-cart:hover {
            background: #1d4ed8;
        }

        .quantity-input {
            max-width: 80px;
        }

        .empty-state {
            text-align: center;
            padding: 60px 0;
            color: #6b7280;
        }

        .empty-state h4 {
            font-weight: 600;
            color: #111827;
            margin-bottom: 8px;
        }
    </style>
</head>
<body>

<div class="page-wrapper">

    <!-- Header / Top bar -->
    <div class="page-header">
        <div>
            <h1 class="page-title">Browse Products</h1>
            <p class="page-subtitle">
                Discover items from different sellers and add them to your cart.
            </p>
        </div>
        <div class="text-end mt-3 mt-sm-0">
            <div class="small text-muted mb-1">
                Logged in as:
                
                <strong>${sessionScope.currentUser.username}</strong>
                (<c:out value="${sessionScope.currentUser.role}" />)
            </div>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
        </div>
    </div>

    <!-- Products Grid -->
    <c:if test="${empty products}">
        <div class="empty-state bg-white rounded-3 shadow-sm">
            <h4>No products available</h4>
            <p class="mb-0">Once sellers add products, they will appear here.</p>
        </div>
    </c:if>

    <c:if test="${not empty products}">
        <div class="row g-4">
            <c:forEach var="p" items="${products}">
                <div class="col-12 col-sm-6 col-lg-4">
                    <div class="card product-card">

                        <!-- Image / Placeholder -->
                        <div class="product-image-placeholder">
                            <!-- If you later have real images, replace this div with <img> -->
                            ${p.productName}
                        </div>

                        <div class="card-body d-flex flex-column">
                            <div class="d-flex justify-content-between align-items-start mb-2">
                                <h5 class="card-title mb-0">
                                    <c:out value="${p.productName}" />
                                </h5>
                                <span class="category-badge">
                                    <c:out value="${p.category}" />
                                </span>
                            </div>

                            <p class="card-text small text-muted mb-2">
                                <c:out value="${p.description}" />
                            </p>

                            <div class="d-flex justify-content-between align-items-center mb-2">
                                <span class="price-tag">₹<c:out value="${p.price}" /></span>
                                <span class="badge bg-success-subtle text-success stock-badge">
                                    In stock: <c:out value="${p.stockQuantity}" />
                                </span>
                            </div>

                            <!-- Add to Cart Form -->
                            <form action="${pageContext.request.contextPath}/buyer/cart"
      method="post"
      class="mt-auto">

    <input type="hidden" name="action" value="add">
    <input type="hidden" name="productId" value="${p.productId}">

    <div class="d-flex justify-content-between align-items-end">

        <div>
            <label class="form-label mb-1 small text-muted">Quantity</label>
            <input type="number"
                   name="quantity"
                   value="1"
                   min="1"
                   max="${p.stockQuantity}"
                   class="form-control form-control-sm quantity-input">
        </div>

        <button type="submit"
                class="btn btn-primary btn-add-cart px-4">
            Add to Cart
        </button>
    </div>
</form>

                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

</div>

<!-- Bootstrap JS (optional, for future components) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
