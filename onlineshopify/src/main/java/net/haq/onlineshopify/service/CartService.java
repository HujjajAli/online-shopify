package net.haq.onlineshopify.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.haq.onlineshopify.model.UserModel;
import net.haq.shopifybackend.dao.CartLineDAO;
import net.haq.shopifybackend.dao.ProductDAO;
import net.haq.shopifybackend.dto.Cart;
import net.haq.shopifybackend.dto.CartLine;
import net.haq.shopifybackend.dto.Product;

@Service
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;
	
	//return the cart of the user who has logged in
	private Cart getCart(){
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	//return the entire cart lines
	public List<CartLine> getCartLines(){
		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getCart_id());
	}

	public String updateCartLine(int cartLineId, int count) {
		//fetch the cart line object
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null){
			return "result=error";
		}else{
			
			Product product = cartLine.getProduct();
			
			double oldTotal = cartLine.getTotal();
			
			if(product.getQuantity() < count){
				return "result=unavailable";
			}
			
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			
			cartLineDAO.update(cartLine);
			
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}
	}

	public String deleteCartLine(int cartLineId) {
		//fetch the cart line object
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null){
			return "result=error";
		}else{
			//update the cart
			Cart cart = this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCart_Lines(cart.getCart_Lines() - 1);
			cartLineDAO.updateCart(cart);
			
			//remove the cart line
			cartLineDAO.delete(cartLine);
			return "result=deleted";
		}
	}

	public String addCartLine(int productId) {
		String response = null;

		Cart cart = this.getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getCart_id(), productId);
		
		if (cartLine == null) {
			//add new Cart Line
			cartLine = new CartLine();
			
			//fetch Product
			Product product = productDAO.get(productId);
			
			//Cart Line
			cartLine.setCartId(cart.getCart_id());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
 			cartLineDAO.add(cartLine);
			
 			//User Cart
			cart.setCart_Lines(cart.getCart_Lines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}else{
			
			//check if the Cart Line has reached the maximum count = 3
			if (cartLine.getProductCount() < 3) {
				//update the product count for that Cart Line
				response = this.updateCartLine(cartLine.getId(),cartLine.getProductCount() + 1);
			}else{
				response = "result=maximum";
			}
		}
		
		return response;
	}
}
