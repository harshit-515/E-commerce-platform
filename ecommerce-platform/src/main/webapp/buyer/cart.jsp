<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <style>
        body {
            background: #f4f6fb;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
        }
        .cart-box {
            max-width: 900px;
            margin: 40px auto;
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(15, 23, 42, 0.08);
            padding: 24px 32px 32px;
        }
        .cart-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 24px;
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

<div class="cart-box">

    <!-- HEADER -->
    <div class="cart-header">
        <div>
            <h3 class="mb-0">Your Cart</h3>
            <small class="text-muted">
                <c:choose>
                    <c:when test="${itemCount > 0}">
                        ${itemCount} item(s) in cart
                    </c:when>
                    <c:otherwise>No items in cart.</c:otherwise>
                </c:choose>
            </small>
        </div>

        <div>
            <a class="btn btn-outline-secondary btn-sm"
   href="${pageContext.request.contextPath}/products">
    Continue Shopping
</a>


            <c:if test="${itemCount > 0}">
                <form action="${pageContext.request.contextPath}/buyer/cart"
                      method="post" style="display:inline;">
                    <input type="hidden" name="action" value="clear">
                    <button type="submit" class="btn btn-outline-danger btn-sm">
                        Clear Cart
                    </button>
                </form>
            </c:if>
        </div>
    </div>

    <!-- ERROR MESSAGE -->
    <c:if test="${not empty sessionScope.cartError}">
        <div class="alert alert-danger py-2">
            ${sessionScope.cartError}
        </div>
        <c:remove var="cartError" scope="session"/>
    </c:if>

    <!-- EMPTY CART -->
    <c:if test="${itemCount == 0}">
        <p class="text-muted mb-0">Your cart is empty.</p>
    </c:if>

    <!-- CART TABLE -->
    <c:if test="${itemCount > 0}">
        <table class="table align-middle">
            <thead>
            <tr>
                <th>Product</th>
                <th style="width: 120px;">Quantity</th>
                <th style="width: 120px;">Price</th>
                <th style="width: 120px;">Subtotal</th>
                <th style="width: 80px;"></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="item" items="${cartItems}">
                <tr>
                    <td>
                        <b>${item.productName}</b><br/>
                        <small class="text-muted">${item.category}</small>
                    </td>

                    <td>${item.quantity}</td>

                    <!-- FIXED -->
                    <td>₹ ${item.productPrice}</td>
                    <td>₹ ${item.subtotal}</td>

                    <td>
                        <form action="${pageContext.request.contextPath}/buyer/cart"
                              method="post">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="cartId" value="${item.cartId}">
                            <button type="submit" class="btn btn-link text-danger p-0">
                                Remove
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="d-flex justify-content-between align-items-center mt-3">
            <h5 class="mb-0">Total: <span class="text-primary">₹ ${totalAmount}</span></h5>

            <a href="${pageContext.request.contextPath}/buyer/checkout"
   class="btn btn-primary">Proceed to Checkout</a>

        </div>
    </c:if>

</div>

</body>
</html>
