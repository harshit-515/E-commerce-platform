package com.ecommerce.servlet;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.dao.CartDAOImpl;
import com.ecommerce.model.Cart;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/buyer/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        cartDAO = new CartDAOImpl();
        System.out.println("[CartServlet] init() – CartDAOImpl ready.");
    }

    // GET -> show cart page
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (session != null)
                ? (User) session.getAttribute("currentUser")
                : null;

        if (currentUser == null || !"BUYER".equalsIgnoreCase(currentUser.getRole())) {
            // Not logged in or not a buyer -> go to login
            String ctx = request.getContextPath();
            response.sendRedirect(ctx + "/login");
            return;
        }

        int buyerId = currentUser.getUserId();

        // Load cart items from DB using DAO
        List<Cart> cartItems = cartDAO.getCartByBuyer(buyerId);
        int itemCount = cartDAO.getCartItemCount(buyerId);
        double total = cartDAO.getCartTotal(buyerId);

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("itemCount", itemCount);
        request.setAttribute("totalAmount", total);

        // Forward to JSP
        request.getRequestDispatcher("/buyer/cart.jsp")
               .forward(request, response);
    }

    // POST -> add/remove/clear actions
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
            String ctx = request.getContextPath();
            response.sendRedirect(ctx + "/login");
            return;
        }

        int buyerId = currentUser.getUserId();
        String action = request.getParameter("action");

        try {
            if ("add".equalsIgnoreCase(action)) {
                // Add to cart
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = 1;
                try {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                } catch (NumberFormatException ignore) { }

                if (quantity < 1) {
                    quantity = 1;
                }

                Cart cart = new Cart();
                cart.setBuyerId(buyerId);
                cart.setProductId(productId);
                cart.setQuantity(quantity);

                boolean success = cartDAO.addToCart(cart);
                System.out.println("[CartServlet] addToCart success = " + success);

            } else if ("remove".equalsIgnoreCase(action)) {
                int cartId = Integer.parseInt(request.getParameter("cartId"));
                boolean success = cartDAO.removeFromCart(cartId);
                System.out.println("[CartServlet] removeFromCart success = " + success);

            } else if ("clear".equalsIgnoreCase(action)) {
                boolean success = cartDAO.clearCart(buyerId);
                System.out.println("[CartServlet] clearCart success = " + success);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // You can set an error message in session if you want
            session.setAttribute("cartError", "There was a problem while updating the cart.");
        }

        // After POST -> redirect to GET (PRG pattern)
        String ctx = request.getContextPath();
        response.sendRedirect(ctx + "/buyer/cart");
    }
}
