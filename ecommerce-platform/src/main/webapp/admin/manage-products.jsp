<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Catalogue - Admin</title>
    <style>
        body {
            background: #f3f4f6;
            font-family: Arial, sans-serif;
            padding: 40px;
        }
        .box {
            background: white;
            padding: 30px;
            border-radius: 14px;
            max-width: 700px;
            margin: auto;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
            text-align: center;
        }
        h2 { color: #111827; }
        p  { color: #6b7280; font-size: 1.05rem; }
        a.back {
            margin-top: 20px;
            display: inline-block;
            background: #2563eb;
            padding: 10px 20px;
            color: white;
            border-radius: 8px;
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="box">
    <h2>Product Catalogue</h2>
    <p>Admin will be able to view, verify and remove products.</p>
    <p style="font-weight:bold; font-size:1.2rem;">Coming Soon...</p>

    <a class="back" href="${pageContext.request.contextPath}/admin/admin-dashboard.jsp">
        ⟵ Back to Dashboard
    </a>
</div>

</body>
</html>
