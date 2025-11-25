<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>

    <style>
        body {
            background: white; 
            min-height: 100vh;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }

        .page-wrapper {
            max-width: 1200px;
            margin: 40px auto;
        }

        .page-header {
            background: white;
            border-radius: 18px;
            padding: 22px 28px;
            box-shadow: 0 12px 26px rgba(0,0,0,0.08);
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 28px;
            color: #111827;
        }

        .page-title {
            font-size: 1.7rem;
            font-weight: 600;
            margin: 0;
        }

        .page-subtitle {
            margin: 4px 0 0;
            color: #6b7280;
            font-size: 0.94rem;
        }

        .btn-logout {
            border-radius: 999px;
            font-weight: 500;
        }

        .card-tile {
            border: none;
            border-radius: 18px;
            background: white;
            box-shadow: 0 10px 24px rgba(0,0,0,0.08);
            transition: transform .15s ease, box-shadow .15s ease, border .15s ease;
            cursor: pointer;
            height: 100%;
            border: 1px solid #e5e7eb;
            color: #111827;
        }

        .card-tile:hover {
            transform: translateY(-4px);
            box-shadow: 0 22px 40px rgba(0,0,0,0.14);
            border-color: #4f46e5;
        }

        .card-icon {
            width: 52px;
            height: 52px;
            border-radius: 18px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.8rem;
            margin-bottom: 16px;
        }

        .icon-users   { background: rgba(59, 130, 246, 0.1); color: #2563eb; }
        .icon-products{ background: rgba(34, 197, 94, 0.1);  color: #16a34a; }
        .icon-orders  { background: rgba(249, 115, 22, 0.1); color: #ea580c; }
        .icon-report  { background: rgba(236, 72, 153, 0.1); color: #db2777; }

        .card-title {
            font-size: 1.06rem;
            font-weight: 600;
            margin-bottom: 4px;
        }

        .card-text {
            font-size: 0.9rem;
            color: #6b7280;
            margin-bottom: 10px;
        }

        .card-meta {
            font-size: 0.8rem;
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
            <h1 class="page-title">
                Admin Panel
                <span style="font-size:0.9rem; font-weight:400; color:#6b7280;">
                    – <c:out value="${sessionScope.currentUser.username}" />
                </span>
            </h1>
            <p class="page-subtitle">
                Manage users, products & orders across the platform.
            </p>
        </div>

        <a href="${pageContext.request.contextPath}/logout"
           class="btn btn-outline-dark btn-sm btn-logout">
            Logout
        </a>
    </div>

    <!-- Tiles -->
    <div class="row g-4">

        <!-- Manage Users -->
        <div class="col-12 col-md-6 col-lg-3">
            <a class="tile-link"
               href="${pageContext.request.contextPath}/admin/manage-users.jsp">
                <div class="card card-tile">
                    <div class="card-body">
                        <div class="card-icon icon-users">👥</div>
                        <h5 class="card-title">User Management</h5>
                        <p class="card-text">
                            View, activate / deactivate and manage users.
                        </p>
                        <div class="card-meta">
                            <span class="badge bg-primary">Admin only</span>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <!-- Manage Products -->
        <div class="col-12 col-md-6 col-lg-3">
            <a class="tile-link"
               href="${pageContext.request.contextPath}/admin/manage-products.jsp">
                <div class="card card-tile">
                    <div class="card-body">
                        <div class="card-icon icon-products">📦</div>
                        <h5 class="card-title">Product Catalogue</h5>
                        <p class="card-text">
                            View all listed products and manage them.
                        </p>
                        <div class="card-meta">
                            <span class="badge bg-success">Global products</span>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <!-- Orders Overview -->
        <div class="col-12 col-md-6 col-lg-3">
            <a class="tile-link"
               href="${pageContext.request.contextPath}/admin/orders.jsp">
                <div class="card card-tile">
                    <div class="card-body">
                        <div class="card-icon icon-orders">🧾</div>
                        <h5 class="card-title">Orders Overview</h5>
                        <p class="card-text">
                            Track all platform-wide orders & statuses.
                        </p>
                        <div class="card-meta">
                            <span class="badge bg-warning text-dark">All buyers</span>
                        </div>
                    </div>
                </div>
            </a>
        </div>

        <!-- Reports -->
        <div class="col-12 col-md-6 col-lg-3">
            <a class="tile-link"
               href="${pageContext.request.contextPath}/admin/reports.jsp">
                <div class="card card-tile">
                    <div class="card-body">
                        <div class="card-icon icon-report">📊</div>
                        <h5 class="card-title">Reports & Stats</h5>
                        <p class="card-text">
                            Placeholder dashboards (Review 2).
                        </p>
                        <div class="card-meta">
                            <span class="badge bg-danger">Coming soon</span>
                        </div>
                    </div>
                </div>
            </a>
        </div>

    </div>

</div>

</body>
</html>
