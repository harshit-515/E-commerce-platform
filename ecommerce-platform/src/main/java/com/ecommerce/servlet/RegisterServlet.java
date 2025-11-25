package com.ecommerce.servlet;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.dao.UserDAOImpl;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
        System.out.println("[RegisterServlet] init() called – UserDAOImpl ready.");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String email    = request.getParameter("email");
        String phone    = request.getParameter("phone");      // NEW
        String address  = request.getParameter("address");    // NEW
        String password = request.getParameter("password");
        String confirm  = request.getParameter("confirmPassword");

        System.out.println("[RegisterServlet] Received: "
                + "username=" + username + ", email=" + email);

        // Basic validation (phone/address optional, so not included here)
        if (isEmpty(fullName) || isEmpty(username) ||
            isEmpty(email) || isEmpty(password) || isEmpty(confirm)) {

            request.setAttribute("errorMessage",
                    "Full name, username, email and password are required.");
            request.getRequestDispatcher("/register.jsp")
                   .forward(request, response);
            return;
        }

        if (!password.equals(confirm)) {
            request.setAttribute("errorMessage",
                    "Password and Confirm Password do not match.");
            request.getRequestDispatcher("/register.jsp")
                   .forward(request, response);
            return;
        }

        if (userDAO.usernameExists(username)) {
            request.setAttribute("errorMessage",
                    "This username is already taken. Please choose another.");
            request.getRequestDispatcher("/register.jsp")
                   .forward(request, response);
            return;
        }

        if (userDAO.emailExists(email)) {
            request.setAttribute("errorMessage",
                    "This email is already registered. Please use another email.");
            request.getRequestDispatcher("/register.jsp")
                   .forward(request, response);
            return;
        }

        // Create User
        User user = new User();
        user.setFullName(fullName.trim());
        user.setUsername(username.trim());
        user.setEmail(email.trim());
        user.setPassword(password);
        user.setRole("BUYER");
        user.setActive(true);

        // NEW: phone & address (can be null or empty, DB allows it)
        if (phone != null)   user.setPhone(phone.trim());
        if (address != null) user.setAddress(address.trim());

        boolean success = userDAO.registerUser(user);

        if (success) {
            System.out.println("[RegisterServlet] Registration SUCCESS for: " + username);

            request.getSession().setAttribute(
                    "successMessage",
                    "Registration successful! Please login."
            );

            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            System.out.println("[RegisterServlet] Registration FAILED for: " + username);
            request.setAttribute("errorMessage",
                    "Registration failed. Please try again later.");
            request.getRequestDispatcher("/register.jsp")
                   .forward(request, response);
        }
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
