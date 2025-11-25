<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Buyer Dashboard</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>

    <style>
        body {
            background: #f5f7fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }

        .page-wrapper {
            max-width: 1200px;
            margin: 40px auto;
        }

        .page-header {
            background: #ffffff;
            border-radius: 16px;
            padding: 18px 24px;
            box-shadow: 0 10px 25px rgba(15, 23, 42, 0.06);
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 28px;
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

        .btn-logout {
            border-radius: 999px;
            font-weight: 500;
        }

        /* Dashboard tiles */

        .card-tile {
            border-radius: 18px;
            border: none;
            background: #ffffff;
            box-shadow: 0 12px 30px rgba(15, 23, 42, 0.08);
            transition: transform .15s ease, box-shadow .15s ease;
            height: 100%;
        }

        .card-tile:hover {
            transform: translateY(-4px);
            box-shadow: 0 18px 40px rgba(15, 23, 42, 0.12);
        }

        .card-icon {
            width: 52px;
            height: 52px;
            border-radius: 18px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.9rem;
            margin-bottom: 14px;
        }

        .icon-browse { background: #eff6ff; color: #2563eb; }
        .icon-cart   { background: #ecfdf5; color: #16a34a; }
        .icon-orders { background: #fff7ed; color: #f97316; }

        .card-title {
            font-size: 1.1rem;
            font-weight: 600;
            margin-bottom: 6px;
            color: #111827;
        }

        .card-text {
            font-size: 0.92rem;
            color: #6b7280;
        }

        a.tile-link {
            text-decoration: none;
            color: inherit;
        }
    </style>
</head>
<body>

<div class="page-wrapper">

    <!-- Header -->
    <div class="page-header">
        <div>
            <h1 class="page-title">Buyer Dashboard</h1>
            <p class="page-subtitle">
                Welcome, <strong><c:out value="${sessionScope.currentUser.username}" /></strong>
            </p>
        </div>

        <a href="${pageContext.request.contextPath}/logout"
           class="btn btn-outline-secondary btn-sm btn-logout">
            Logout
        </a>
    </div>

    <!-- Tiles -->
    <div class="row g-4">

        <!-- Browse Products -->
        <div class="col-12 col-md-4">
            <a class="tile-link"
               href="${pageContext.request.contextPath}/products">
                <div class="card card-tile">
                    <div class="card-body">
                        <div class="card-icon icon-browse">
                            🛒
                        </div>
                        <h5 class="card-title">Browse Products</h5>
                        <p class="card-text">
                            Explore items from all sellers and add them to your cart.
                        </p>
                    </div>
                </div>
            </a>
        </div>

        <!-- My Cart -->
        <div class="col-12 col-md-4">
            <a class="tile-link"
               href="${pageContext.request.contextPath}/buyer/cart">
                <div class="card card-tile">
                    <div class="card-body">
                        <div class="card-icon icon-cart">
                            🛍️
                        </div>
                        <h5 class="card-title">My Cart</h5>
                        <p class="card-text">
                            View and manage items currently in your shopping cart.
                        </p>
                    </div>
                </div>
            </a>
        </div>

        <!-- My Orders -->
        <div class="col-12 col-md-4">
            <a class="tile-link"
               href="${pageContext.request.contextPath}/buyer/orders">
                <div class="card card-tile">
                    <div class="card-body">
                        <div class="card-icon icon-orders">
                            📦
                        </div>
                        <h5 class="card-title">My Orders</h5>
                        <p class="card-text">
                            Track your past orders and delivery status in one place.
                        </p>
                    </div>
                </div>
            </a>
        </div>

    </div>
</div>

</body>
</html>
