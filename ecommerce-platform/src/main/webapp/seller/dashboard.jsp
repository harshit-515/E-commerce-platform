<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Seller Dashboard</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <style>
        body {
            background: #f5f7fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }

        .dashboard-wrapper {
            max-width: 1200px;
            margin: 40px auto;
        }

        .seller-card {
            background: #ffffff;
            padding: 28px;
            border-radius: 18px;
            box-shadow: 0 5px 30px rgba(20, 29, 40, 0.08);
        }

        .seller-name {
            font-size: 1.8rem;
            font-weight: 700;
            color: #1f2937;
        }

        .seller-sub {
            color: #6b7280;
        }

        .action-card {
            background: #ffffff;
            border-radius: 16px;
            padding: 26px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.06);
            transition: all 0.2s ease;
            cursor: pointer;
            height: 100%;
        }

        .action-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 14px 30px rgba(0, 0, 0, 0.10);
        }

        .action-title {
            font-size: 1.2rem;
            font-weight: 600;
        }

        .logout-link {
            color: #ef4444;
            font-weight: 500;
            text-decoration: none;
        }

        .logout-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="dashboard-wrapper">

    <!-- Seller Profile Card -->
    <div class="seller-card mb-5 d-flex justify-content-between align-items-center">
        <div>
            <div class="seller-name">
                Welcome, <c:out value="${sessionScope.currentUser.username}"/>
            </div>
            <div class="seller-sub mt-1">
                Manage your store, products & orders.
            </div>
        </div>

        <div>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">
                Logout
            </a>
        </div>
    </div>

    <!-- Dashboard Options -->
    <div class="row g-4">

        <!-- Manage Products -->
        <div class="col-12 col-md-6 col-lg-4">
            <a href="${pageContext.request.contextPath}/seller/products" style="text-decoration:none; color:inherit;">
                <div class="action-card">
                    <img src="https://cdn-icons-png.flaticon.com/512/679/679720.png"
                         width="48" class="mb-3">
                    <div class="action-title">Manage My Products</div>
                    <p class="text-muted mt-1">View, update or add new products.</p>
                </div>
            </a>
        </div>

       
    

    </div>
</div>

</body>
</html>
