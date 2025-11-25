package com.ecommerce.dao;

import com.ecommerce.model.Wishlist;
import java.util.List;

public interface WishlistDAO {
    
    /**
     * Add product to wishlist
     * @param buyerId Buyer ID
     * @param productId Product ID
     * @return true if successful, false otherwise
     */
    boolean addToWishlist(int buyerId, int productId);
    
    /**
     * Remove product from wishlist
     * @param wishlistId Wishlist ID
     * @return true if successful, false otherwise
     */
    boolean removeFromWishlist(int wishlistId);
    
    /**
     * Remove product from wishlist by buyer and product
     * @param buyerId Buyer ID
     * @param productId Product ID
     * @return true if successful, false otherwise
     */
    boolean removeFromWishlistByProduct(int buyerId, int productId);
    
    /**
     * Get buyer's wishlist
     * @param buyerId Buyer ID
     * @return List of wishlist items
     */
    List<Wishlist> getWishlistByBuyer(int buyerId);
    
    /**
     * Check if product is in wishlist
     * @param buyerId Buyer ID
     * @param productId Product ID
     * @return true if in wishlist, false otherwise
     */
    boolean isInWishlist(int buyerId, int productId);
    
    /**
     * Clear buyer's wishlist
     * @param buyerId Buyer ID
     * @return true if successful, false otherwise
     */
    boolean clearWishlist(int buyerId);
}