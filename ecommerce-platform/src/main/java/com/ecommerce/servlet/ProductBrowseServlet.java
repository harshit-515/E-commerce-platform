package com.ecommerce.servlet;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.dao.ProductDAOImpl;
import com.ecommerce.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductBrowseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAOImpl();
        System.out.println("[ProductBrowseServlet] init() – ProductDAOImpl ready.");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // ✅ Simple login + role check (only BUYER allowed for now)
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            // Not logged in → go to login page
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String role = String.valueOf(session.getAttribute("role"));
        if (!"BUYER".equalsIgnoreCase(role)) {
            // Only buyers can see this page for now
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    "Only buyers can browse products.");
            return;
        }

        try {
            // ⚠️ If your DAO method name is different, adjust here
            // e.g. use getAllProducts() or getAllActiveProducts()
            List<Product> products = productDAO.getAllProducts();

            request.setAttribute("products", products);
            request.getRequestDispatcher("/buyer/browse.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage",
                    "Could not load products. Please try again later.");
            request.getRequestDispatcher("/buyer/browse.jsp")
                   .forward(request, response);
        }
    }
}
