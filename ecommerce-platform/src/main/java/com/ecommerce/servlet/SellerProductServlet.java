package com.ecommerce.servlet;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.dao.ProductDAOImpl;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/seller/products")
public class SellerProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAOImpl();
        System.out.println("[SellerProductServlet] init() – ProductDAOImpl ready.");
    }

    private User getLoggedInSeller(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (session != null)
                ? (User) session.getAttribute("currentUser")
                : null;

        if (currentUser == null || !"SELLER".equalsIgnoreCase(currentUser.getRole())) {
            // Not logged in as seller -> go to login
            String ctx = request.getContextPath();
            response.sendRedirect(ctx + "/login");
            return null;
        }
        return currentUser;
    }

    // GET -> list products OR show form
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        User seller = getLoggedInSeller(request, response);
        if (seller == null) return; // redirected already

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                // Empty form for new product
                request.setAttribute("pageTitle", "Add New Product");
                request.getRequestDispatcher("/seller/product-form.jsp")
                        .forward(request, response);
                break;

            case "edit":
                handleEditForm(request, response, seller);
                break;

            default: // "list"
                handleList(request, response, seller);
                break;
        }
    }

    private void handleList(HttpServletRequest request,
                            HttpServletResponse response,
                            User seller)
            throws ServletException, IOException {

        int sellerId = seller.getUserId();
        List<Product> products = productDAO.getProductsBySeller(sellerId);

        request.setAttribute("products", products);
        request.setAttribute("seller", seller);

        request.getRequestDispatcher("/seller/products.jsp")
                .forward(request, response);
    }

    private void handleEditForm(HttpServletRequest request,
                                HttpServletResponse response,
                                User seller)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        int productId = -1;
        try {
            productId = Integer.parseInt(idParam);
        } catch (Exception ignore) {}

        if (productId <= 0) {
            response.sendRedirect(request.getContextPath() + "/seller/products");
            return;
        }

        Product product = productDAO.getProductById(productId);
        if (product == null || product.getSellerId() != seller.getUserId()) {
            // Either product not found or not owned by this seller
            response.sendRedirect(request.getContextPath() + "/seller/products");
            return;
        }

        request.setAttribute("product", product);
        request.setAttribute("pageTitle", "Edit Product");
        request.getRequestDispatcher("/seller/product-form.jsp")
                .forward(request, response);
    }

    // POST -> save (add/update) OR delete
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        User seller = getLoggedInSeller(request, response);
        if (seller == null) return;

        String action = request.getParameter("action");

        try {
            if ("save".equalsIgnoreCase(action)) {
                handleSave(request, seller);
            } else if ("delete".equalsIgnoreCase(action)) {
                handleDelete(request, seller);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute(
                    "sellerProductError",
                    "There was a problem saving product. Please try again.");
        }

        String ctx = request.getContextPath();
        response.sendRedirect(ctx + "/seller/products");
    }

    private void handleSave(HttpServletRequest request, User seller) {
        String idParam = request.getParameter("productId");
        int productId = -1;
        try {
            productId = Integer.parseInt(idParam);
        } catch (Exception ignore) {}

        String name = request.getParameter("productName");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String imageUrl = request.getParameter("imageUrl");

        double price = 0.0;
        int stock = 0;
        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (Exception ignore) {}
        try {
            stock = Integer.parseInt(request.getParameter("stockQuantity"));
        } catch (Exception ignore) {}

        if (productId > 0) {
            // Update
            Product existing = productDAO.getProductById(productId);
            if (existing == null || existing.getSellerId() != seller.getUserId()) {
                return; // not allowed
            }

            existing.setProductName(name);
            existing.setDescription(description);
            existing.setCategory(category);
            existing.setImageUrl(imageUrl);
            existing.setPrice(price);
            existing.setStockQuantity(stock);
            existing.setActive(true);

            productDAO.updateProduct(existing);
        } else {
            // Add new
            Product p = new Product();
            p.setSellerId(seller.getUserId());
            p.setProductName(name);
            p.setDescription(description);
            p.setCategory(category);
            p.setImageUrl(imageUrl);
            p.setPrice(price);
            p.setStockQuantity(stock);
            p.setActive(true);

            productDAO.addProduct(p);
        }
    }

    private void handleDelete(HttpServletRequest request, User seller) {
        String idParam = request.getParameter("productId");
        int productId = -1;
        try {
            productId = Integer.parseInt(idParam);
        } catch (Exception ignore) {}

        if (productId <= 0) return;

        Product product = productDAO.getProductById(productId);
        if (product == null || product.getSellerId() != seller.getUserId()) {
            return; // not allowed
        }

        // Soft delete (sets is_active = 0) as defined in DAO
        productDAO.deleteProduct(productId);
    }
}
