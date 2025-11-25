<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet"/>

    <style>
        body {
            background: #f5f7fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }
        .card {
            border: none;
            border-radius: 18px;
            box-shadow: 0 18px 40px rgba(15, 23, 42, 0.12);
        }
        .card-header {
            background: #0d6efd;
            color: #fff;
            border-radius: 18px 18px 0 0 !important;
            text-align: center;
            padding: 1.8rem 1rem;
        }
        .card-header h3 {
            margin: 0;
            font-weight: 650;
            letter-spacing: 0.03em;
        }
        .btn-primary {
            border-radius: 999px;
            padding: 0.7rem 1rem;
            font-weight: 600;
            width: 100%;
        }
        .form-control {
            border-radius: 12px;
        }
    </style>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="col-md-5">
        <div class="card">
            <div class="card-header">
                <h3>Create Account</h3>
            </div>

            <div class="card-body">

                <!-- Error message -->
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">
                        ${errorMessage}
                    </div>
                </c:if>

                <!-- Success message (optional) -->
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">
                        ${successMessage}
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/register" method="post">

                    <div class="mb-3">
                        <label class="form-label" for="fullName">Full Name</label>
                        <input type="text" class="form-control" id="fullName"
                               name="fullName" placeholder="Enter your name"
                               value="${param.fullName}">
                    </div>

                    <div class="mb-3">
                        <label class="form-label" for="username">Username</label>
                        <input type="text" class="form-control" id="username"
                               name="username" placeholder="Choose username"
                               value="${param.username}">
                    </div>

                    <div class="mb-3">
                        <label class="form-label" for="email">Email</label>
                        <input type="email" class="form-control" id="email"
                               name="email" placeholder="Enter email"
                               value="${param.email}">
                    </div>

                    <!-- NEW: Phone -->
                    <div class="mb-3">
                        <label class="form-label" for="phone">Phone</label>
                        <input type="text" class="form-control" id="phone"
                               name="phone" placeholder="Enter phone number"
                               value="${param.phone}">
                    </div>

                    <!-- NEW: Address -->
                    <div class="mb-3">
                        <label class="form-label" for="address">Address</label>
                        <textarea class="form-control" id="address"
                                  name="address" rows="2"
                                  placeholder="Enter address">${param.address}</textarea>
                    </div>
					<div class="mb-3">
    <label class="form-label" for="role">Select Role</label>
    <select class="form-control" id="role" name="role">
        <option value="BUYER">Buyer</option>
        <option value="SELLER">Seller</option>
    </select>
</div>
					
                    <div class="mb-3">
                        <label class="form-label" for="password">Password</label>
                        <input type="password" class="form-control" id="password"
                               name="password" placeholder="Enter password">
                    </div>

                    <div class="mb-4">
                        <label class="form-label" for="confirmPassword">Confirm Password</label>
                        <input type="password" class="form-control" id="confirmPassword"
                               name="confirmPassword" placeholder="Re-enter password">
                    </div>

                    <button type="submit" class="btn btn-primary">
                        Register
                    </button>
                </form>

                <div class="text-center mt-3">
                    Already have an account?
                    <a href="${pageContext.request.contextPath}/login">Login here</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
