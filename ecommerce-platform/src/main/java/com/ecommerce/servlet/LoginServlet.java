package com.ecommerce.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import com.ecommerce.dao.UserDAO;
import com.ecommerce.dao.UserDAOImpl;
import com.ecommerce.model.User;
@WebServlet("/login") 
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {

        userDAO = new UserDAOImpl();
        System.out.println("[LoginServlet] init() called – UserDAOImpl ready.");
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("[LoginServlet] Username: " + username);
        System.out.println("[LoginServlet] Password received (length): "
                + (password == null ? 0 : password.length()));

        // 1) Empty field validation
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            request.setAttribute("errorMessage",
                    "Username and password both required.");
            // Wapas login.jsp par
            request.getRequestDispatcher("/login.jsp")
                   .forward(request, response);
            return;
        }

        // 2) DB ke through authenticate
        User user = userDAO.authenticateUser(username.trim(), password);

        if (user == null) {
            // 2a) FAIL → message dikhao
            System.out.println("[LoginServlet] Login FAILED for: " + username);

            request.setAttribute("errorMessage",
                    "Invalid username or password.");
            request.getRequestDispatcher("/login.jsp")
                   .forward(request, response);
            return;
        }

        // 2b) SUCCESS → session create karo
        System.out.println("[LoginServlet] Login SUCCESS for: "
                + user.getUsername() + " (role = " + user.getRole() + ")");

        HttpSession session = request.getSession(true);
        session.setAttribute("currentUser", user);
        session.setAttribute("role", user.getRole());

        String role = user.getRole();  // "ADMIN" / "SELLER" / "BUYER"
        String contextPath = request.getContextPath(); // /ecommerce-platform
        String target;

        if ("ADMIN".equalsIgnoreCase(role)) {
            target = contextPath + "/admin/dashboard.jsp";
        } else if ("SELLER".equalsIgnoreCase(role)) {
            target = contextPath + "/seller/dashboard.jsp";
        } else {
            // default BUYER
            target = contextPath + "/buyer/dashboard.jsp";
        }

        response.sendRedirect(target);
    }
}
