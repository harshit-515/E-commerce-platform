<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <style>
        body {
            background: #f3f4f6;
            font-family: system-ui, -apple-system, BlinkMacSystemFont,
                         "Segoe UI", sans-serif;
        }
        .checkout-box {
            max-width: 1000px;
            margin: 40px auto;
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 12px 30px rgba(15, 23, 42, .08);
            padding: 28px 32px 32px;
        }
        .btn-primary {
            background-color: #2563eb;
            border-color: #2563eb;
        }
        .btn-primary:hover {
            background-color: #1d4ed8;
            border-color: #1d4ed8;
        }
        .checkout-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 24px;
        }
    </style>
</head>
<body>

<div class="checkout-box">
    <div class="checkout-header">
        <div>
            <h3 class="mb-0">Checkout</h3>
            <small class="text-muted">
                Review your items and enter shipping details.
            </small>
        </div>
        <a href="${pageContext.request.contextPath}/buyer/cart"
           class="btn btn-outline-secondary btn-sm">
            Back to Cart
        </a>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger py-2">${errorMessage}</div>
    </c:if>

    <c:if test="${itemCount == 0}">
        <p class="text-muted mb-0">Your cart is empty.</p>
    </c:if>

    <c:if test="${itemCount > 0}">
        <form action="${pageContext.request.contextPath}/buyer/checkout"
              method="post">
            <div class="row g-4">
                <!-- Order summary -->
                <div class="col-md-6">
                    <h5>Order Summary</h5>
                    <table class="table table-sm align-middle mt-3">
                        <thead>
                        <tr>
                            <th>Product</th>
                            <th style="width: 60px;">Qty</th>
                            <th style="width: 90px;" class="text-end">Price</th>
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
                                <td class="text-end">
                                    ₹ ${item.subtotal}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="d-flex justify-content-between mt-3">
                        <span><b>Items:</b> ${itemCount}</span>
                        <span><b>Total:</b> ₹ ${totalAmount}</span>
                    </div>
                </div>

                <!-- Shipping / payment -->
                <div class="col-md-6">
                    <h5>Shipping Address</h5>
                    <div class="mt-2 mb-3">
                        <textarea name="shippingAddress"
                                  class="form-control"
                                  rows="5"
                                  placeholder="House no, street, city, pin code">${param.shippingAddress}</textarea>
                    </div>

                    <h5>Payment Method</h5>
                    <div class="mt-2 mb-4">
                        <select name="paymentMethod" class="form-select">
                            <option value="COD">Cash on Delivery</option>
                            <option value="CARD">Credit/Debit Card </option>
                            <option value="UPI">UPI </option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">
                        Place Order
                    </button>
                </div>
            </div>
        </form>
    </c:if>
</div>

</body>
</html>
