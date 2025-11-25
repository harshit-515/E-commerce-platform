<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${pageTitle != null ? pageTitle : 'Product Form'}"/></title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            background: #f4f6fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }
        .form-box {
            max-width: 700px;
            margin: 40px auto;
            background: #fff;
            border-radius: 16px;
            padding: 24px 30px 30px;
            box-shadow: 0 10px 30px rgba(15, 23, 42, 0.08);
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
<div class="form-box">
    <h4 class="mb-3">
        <c:out value="${pageTitle != null ? pageTitle : 'Product'}"/>
    </h4>

    <form action="${pageContext.request.contextPath}/seller/products" method="post">
        <input type="hidden" name="action" value="save">

        <c:if test="${not empty product}">
            <input type="hidden" name="productId" value="${product.productId}">
        </c:if>

        <div class="mb-3">
            <label class="form-label">Product Name</label>
            <input type="text" name="productName"
                   class="form-control"
                   required
                   value="<c:out value='${product.productName}'/>">
        </div>

        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description" rows="4"
                      class="form-control"
                      required><c:out value='${product.description}'/></textarea>
        </div>

        <div class="row">
            <div class="mb-3 col-md-4">
                <label class="form-label">Price (₹)</label>
                <input type="number" step="0.01" min="0"
                       name="price" class="form-control"
                       required
                       value="<c:out value='${product.price}'/>">
            </div>

            <div class="mb-3 col-md-4">
                <label class="form-label">Stock Quantity</label>
                <input type="number" min="0"
                       name="stockQuantity" class="form-control"
                       required
                       value="<c:out value='${product.stockQuantity}'/>">
            </div>

            <div class="mb-3 col-md-4">
                <label class="form-label">Category</label>
                <input type="text" name="category"
                       class="form-control"
                       value="<c:out value='${product.category}'/>">
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label">Image URL (optional)</label>
            <input type="text" name="imageUrl"
                   class="form-control"
                   value="<c:out value='${product.imageUrl}'/>">
            <div class="form-text">
                For now we store a simple URL. Later you can implement real file upload.
            </div>
        </div>

        <div class="d-flex justify-content-between mt-4">
            <a href="${pageContext.request.contextPath}/seller/products"
               class="btn btn-outline-secondary">
                Cancel
            </a>
            <button type="submit" class="btn btn-primary">
                Save Product
            </button>
        </div>
    </form>
</div>
</body>
</html>
