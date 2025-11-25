<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Management - Admin</title>
    <style>
        body {
            background: #f1f5f9;
            font-family: Arial, sans-serif;
            padding: 40px;
        }
        .box {
            background: #ffffff;
            padding: 30px;
            border-radius: 14px;
            max-width: 700px;
            margin: auto;
            box-shadow: 0 10px 25px rgba(15, 23, 42, 0.12);
            text-align: center;
        }
        h2 {
            color: #0f172a;
            margin-bottom: 10px;
        }
        p {
            color: #6b7280;
            font-size: 1.02rem;
        }
        .badge {
            display: inline-block;
            margin-top: 10px;
            padding: 4px 10px;
            border-radius: 999px;
            font-size: 0.75rem;
            letter-spacing: .08em;
            text-transform: uppercase;
            background: #eff6ff;
            color: #1d4ed8;
        }
        a.back {
            margin-top: 22px;
            display: inline-block;
            background: #2563eb;
            padding: 10px 22px;
            color: white;
            border-radius: 8px;
            text-decoration: none;
            font-weight: 500;
        }
        a.back:hover {
            background: #1d4ed8;
        }
    </style>
</head>
<body>

<div class="box">
    <h2>User Management</h2>
    <p>Admin will be able to view all users, manage roles and deactivate accounts.</p>

    <div class="badge">Review 2 Placeholder</div>

    <p style="font-weight:bold; font-size:1.2rem; margin-top:18px;">
        Coming Soon...
    </p>

    <a class="back" href="${pageContext.request.contextPath}/admin/admin-dashboard.jsp">
        ⟵ Back to Dashboard
    </a>
</div>

</body>
</html>
