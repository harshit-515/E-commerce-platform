<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reports & Stats - Admin</title>
    <style>
        body {
            background: #fdf4ff;
            font-family: Arial, sans-serif;
            padding: 40px;
        }
        .box {
            background: white;
            padding: 30px;
            border-radius: 14px;
            max-width: 700px;
            margin: auto;
            box-shadow: 0 10px 25px rgba(0,0,0,0.12);
            text-align: center;
        }
        h2 { color: #86198f; }
        p  { color: #6b7280; }
        a {
            margin-top: 20px;
            display: inline-block;
            background: #a21caf;
            padding: 10px 20px;
            color: white;
            border-radius: 8px;
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="box">
    <h2>Reports & Analytics</h2>
    <p>Admin can view analytics, trends and system insights.</p>
    <p style="font-weight:bold; font-size:1.2rem;">Coming Soon...</p>

    <a href="${pageContext.request.contextPath}/admin/admin-dashboard.jsp">
        ⟵ Back to Dashboard
    </a>
</div>

</body>
</html>
