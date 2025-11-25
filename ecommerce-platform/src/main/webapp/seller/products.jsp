<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Products</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            background: #f4f6fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }
        .page-wrapper {
            max-width: 1100px;
            margin: 30px auto;
        }
        .page-header {
            background: #fff;
            border-radius: 14px;
            padding: 20px 24px;
            box-shadow: 0 8px 24px rgba(15, 23, 42, 0.08);
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .btn-primary {
            background-color: #2563eb;
            border-color: #2563eb;
        }
        .btn-primary:hover {
            background-color: #1d4ed8;
            border-color: #1d4ed8;
        }
    </style>
</head>
<body>
<div class="page-wrapper">

    <div class="page-header">
        <div>
            <h3 class="mb-0">My Products</h3>
            <small class="text-muted">
                Manage products you are selling.
            </small>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/seller/products?action=new"
               class="btn btn-primary btn-sm">
                + Add Product
            </a>
            <a href="${pageContext.request.contextPath}/logout"
               class="btn btn-outline-danger btn-sm ms-2">
                Logout
            </a>
        </div>
    </div>

    <c:if test="${not empty sessionScope.sellerProductError}">
        <div class="alert alert-danger">
            ${sessionScope.sellerProductError}
        </div>
        <c:remove var="sellerProductError" scope="session"/>
    </c:if>

    <c:if test="${empty products}">
        <div class="alert alert-info">
            You have not added any products yet. Click <b>Add Product</b> to get started.
        </div>
    </c:if>

    <c:if test="${not empty products}">
        <table class="table table-hover align-middle bg-white rounded-3 shadow-sm">
            <thead>
            <tr>
                <th>Name</th>
                <th>Category</th>
                <th class="text-end">Price (₹)</th>
                <th class="text-end">Stock</th>
                <th class="text-center">Status</th>
                <th class="text-end">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${products}">
                <tr>
                    <td>${p.productName}</td>
                    <td>${p.category}</td>
                    <td class="text-end">${p.price}</td>
                    <td class="text-end">${p.stockQuantity}</td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${p.active}">
                                <span class="badge bg-success-subtle text-success">Active</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-secondary-subtle text-secondary">Inactive</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-end">
                        <a href="${pageContext.request.contextPath}/seller/products?action=edit&id=${p.productId}"
                           class="btn btn-sm btn-outline-primary">
                            Edit
                        </a>

                        <form action="${pageContext.request.contextPath}/seller/products"
                              method="post"
                              style="display:inline-block"
                              onsubmit="return confirm('Delete this product?');">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="productId" value="${p.productId}">
                            <button type="submit"
                                    class="btn btn-sm btn-outline-danger ms-1">
                                Delete
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <a href="${pageContext.request.contextPath}/seller/dashboard.jsp"
       class="btn btn-link mt-3">&larr; Back to Dashboard</a>
</div>
</body>
</html>
