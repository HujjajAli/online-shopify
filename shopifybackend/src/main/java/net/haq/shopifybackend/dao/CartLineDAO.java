package net.haq.shopifybackend.dao;

import java.util.List;

import net.haq.shopifybackend.dto.Cart;
import net.haq.shopifybackend.dto.CartLine;

public interface CartLineDAO {
	
	CartLine get(int id);
	boolean add(CartLine cartLine);
	boolean update(CartLine cartLine);
	boolean delete(CartLine cartLine);
	List<CartLine> list(int cartId);
	
	boolean updateCart(Cart cart);
	
	List<CartLine> listAvailable(int cartId);
	CartLine getByCartAndProduct(int cartId,int productId);
}
