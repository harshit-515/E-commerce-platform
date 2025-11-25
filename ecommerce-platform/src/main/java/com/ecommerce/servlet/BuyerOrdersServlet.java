package com.ecommerce.servlet;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.OrderDAOImpl;
import com.ecommerce.model.Order;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/buyer/orders")
public class BuyerOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAOImpl();
        System.out.println("[BuyerOrdersServlet] init() – OrderDAOImpl ready.");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (session != null)
                ? (User) session.getAttribute("currentUser")
                : null;

        // Must be logged in + role BUYER
        if (currentUser == null || !"BUYER".equalsIgnoreCase(currentUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int buyerId = currentUser.getUserId();

        // Load buyer's orders
        List<Order> orders = orderDAO.getOrdersByBuyer(buyerId);
        request.setAttribute("orders", orders);

        // Forward to JSP
        request.getRequestDispatcher("/buyer/orders.jsp")
               .forward(request, response);
    }
}
