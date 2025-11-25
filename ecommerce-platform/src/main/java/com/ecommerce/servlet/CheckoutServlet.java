package com.ecommerce.servlet;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.dao.CartDAOImpl;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.OrderDAOImpl;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/buyer/checkout")
public class CheckoutServlet extends HttpServlet {

    private CartDAO cartDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        cartDAO = new CartDAOImpl();
        orderDAO = new OrderDAOImpl();
        System.out.println("[CheckoutServlet] Initialized");
    }

    // GET → Load checkout page with cart items
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("currentUser") : null;

        if (user == null || !"BUYER".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int buyerId = user.getUserId();

        // Fetch cart items
        List<Cart> cartItems = cartDAO.getCartByBuyer(buyerId);
        double totalAmount = cartDAO.getCartTotal(buyerId);
        int itemCount = cartDAO.getCartItemCount(buyerId);

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("itemCount", itemCount);
        request.setAttribute("totalAmount", totalAmount);

        request.getRequestDispatcher("/buyer/checkout.jsp").forward(request, response);
    }

    // POST → Process Order Creation
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User currentUser = (session != null)
                ? (User) session.getAttribute("currentUser")
                : null;

        if (currentUser == null || !"BUYER".equalsIgnoreCase(currentUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int buyerId = currentUser.getUserId();

        String shippingAddress = request.getParameter("shippingAddress");
        String paymentMethod   = request.getParameter("paymentMethod");

        if (shippingAddress == null || shippingAddress.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Please enter a shipping address.");
            // reload checkout with existing data
            List<Cart> cartItems = cartDAO.getCartByBuyer(buyerId);
            double totalAmount   = cartDAO.getCartTotal(buyerId);
            int itemCount        = cartDAO.getCartItemCount(buyerId);

            request.setAttribute("cartItems", cartItems);
            request.setAttribute("totalAmount", totalAmount);
            request.setAttribute("itemCount", itemCount);

            request.getRequestDispatcher("/buyer/checkout.jsp")
                   .forward(request, response);
            return;
        }

        try {
            // 1) Load cart
            List<Cart> cartItems = cartDAO.getCartByBuyer(buyerId);
            if (cartItems == null || cartItems.isEmpty()) {
                request.setAttribute("errorMessage",
                        "Your cart is empty. Please add items before placing an order.");
                request.getRequestDispatcher("/buyer/checkout.jsp")
                       .forward(request, response);
                return;
            }

            // 2) Build Order object
            Order order = new Order();
            order.setBuyerId(buyerId);

            double totalAmount = cartDAO.getCartTotal(buyerId);
            order.setTotalAmount(totalAmount);

            order.setStatus("PENDING");
            order.setShippingAddress(shippingAddress.trim());
            order.setPaymentMethod(paymentMethod);

            // 3) Convert Cart → OrderItems
            List<OrderItem> orderItems = new ArrayList<>();
            for (Cart item : cartItems) {
                OrderItem oi = new OrderItem();
                oi.setProductId(item.getProductId());
                oi.setSellerId(item.getSellerId());      // IMPORTANT for FK
                oi.setQuantity(item.getQuantity());
                oi.setPriceAtPurchase(item.getProductPrice());
                orderItems.add(oi);
            }

            // 4) Create order (transaction)
            int orderId = orderDAO.createOrder(order, orderItems);

            if (orderId <= 0) {
                request.setAttribute("errorMessage",
                        "There was a problem placing your order. Please try again.");
                // reload checkout
                request.setAttribute("cartItems", cartItems);
                request.setAttribute("totalAmount", totalAmount);
                request.setAttribute("itemCount", cartItems.size());
                request.getRequestDispatcher("/buyer/checkout.jsp")
                       .forward(request, response);
                return;
            }

            // 5) Clear cart after successful order
            cartDAO.clearCart(buyerId);

            // 6) Put data for success page in *request* and FORWARD
            request.setAttribute("orderId", orderId);
            request.setAttribute("totalAmount", totalAmount);

            request.getRequestDispatcher("/buyer/order-success.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage",
                    "Unexpected error while placing order. Please try again.");
            request.getRequestDispatcher("/buyer/checkout.jsp")
                   .forward(request, response);
        }
    }
}
