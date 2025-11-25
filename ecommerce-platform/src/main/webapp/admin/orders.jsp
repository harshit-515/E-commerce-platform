<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders Overview - Admin</title>
    <style>
        body {
            background: #eef2ff;
            font-family: Arial, sans-serif;
            padding: 40px;
        }
        .box {
            background: white;
            padding: 30px;
            border-radius: 14px;
            max-width: 700px;
            margin: auto;
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
            text-align: center;
        }
        h2 { color: #1e3a8a; }
        p  { color: #475569; font-size: 1rem; }
        .back {
            margin-top: 20px;
            display: inline-block;
            background: #4338ca;
            padding: 10px 20px;
            color: white;
            border-radius: 8px;
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="box">
    <h2>Orders Overview</h2>
    <p>Admin can track all orders across the entire platform.</p>
    <p style="font-weight:bold; font-size:1.2rem;">Coming Soon...</p>

    <a class="back" href="${pageContext.request.contextPath}/admin/admin-dashboard.jsp">
        ⟵ Back to Dashboard
    </a>
</div>

</body>
</html>
