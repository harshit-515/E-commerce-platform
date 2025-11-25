package com.ecommerce.dao;

import com.ecommerce.model.Cart;
import java.util.List;

public interface CartDAO {

    boolean addToCart(Cart cart);

    boolean removeFromCart(int cartId);

    boolean clearCart(int buyerId);

    List<Cart> getCartByBuyer(int buyerId);

    int getCartItemCount(int buyerId);

    double getCartTotal(int buyerId);

    // REQUIRED METHODS (must implement)
    Cart findCartItem(int buyerId, int productId);

    boolean updateQuantity(int cartId, int quantity);
}
