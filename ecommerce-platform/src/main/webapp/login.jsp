<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f3f6fc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: 'Segoe UI', sans-serif;
        }
        .login-container {
            width: 420px;
            background: #fff;
            padding: 0;
            border-radius: 12px;
            box-shadow: 0px 4px 16px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        .login-header {
            background: #0d6efd;
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 26px;
            font-weight: 600;
        }
        .form-control {
            height: 48px;
            border-radius: 8px;
        }
        .btn-login {
            background: #0d6efd;
            color: white;
            width: 100%;
            height: 48px;
            margin-top: 10px;
            border-radius: 8px;
            font-size: 18px;
            font-weight: 500;
        }
        .btn-login:hover {
            background: #0b5ed7;
        }
        .error-box {
            background: #ffe5e7;
            border-left: 4px solid #ff3b47;
            padding: 10px 12px;
            margin-bottom: 15px;
            border-radius: 6px;
            color: #d60018;
            font-size: 15px;
        }
        a {
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="login-container">

    <div class="login-header">Login</div>

    <div class="p-4">

        <!-- Show error message if login failed -->
        <c:if test="${not empty errorMessage}">
            <div class="error-box">
                ${errorMessage}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <!-- Username -->
            <label class="form-label">Username</label>
            <input type="text" name="username" class="form-control"
                   placeholder="Enter username"
                   value="${param.username}">

            <!-- Password -->
            <label class="form-label mt-3">Password</label>
            <input type="password" name="password" class="form-control"
                   placeholder="Enter password">

            <!-- Login button -->
            <button type="submit" class="btn btn-login mt-4">Login</button>
        </form>

        <div class="text-center mt-3">
    New User?
    <a href="${pageContext.request.contextPath}/register">
        Register here
    </a>
</div>


    </div>
</div>

</body>
</html>
