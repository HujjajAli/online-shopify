package net.haq.shopifybackend.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.haq.shopifybackend.dao.CartLineDAO;
import net.haq.shopifybackend.dao.CategoryDAO;
import net.haq.shopifybackend.dao.ProductDAO;
import net.haq.shopifybackend.dao.User_DetailDAO;
import net.haq.shopifybackend.dto.Cart;
import net.haq.shopifybackend.dto.CartLine;
import net.haq.shopifybackend.dto.Product;
import net.haq.shopifybackend.dto.User_Detail;

public class CartLineTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static User_DetailDAO user_DetailDAO;
	
	private Product product;
	private User_Detail user_Detail;
	private Cart cart;
	private CartLine cartLine;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("net.haq.shopifybackend");
		context.refresh();
		
		productDAO = (ProductDAO)context.getBean("productDAOImpl");
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAOImpl");
		user_DetailDAO = (User_DetailDAO)context.getBean("user_DetailDAOImpl");
	}
	
	@Test
	public void addNewCartLine(){
		user_Detail = user_DetailDAO.getByEmail("aslam@gmail.com");
		cart = user_Detail.getCart();
		product = productDAO.get(14);
		
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getCart_id());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the Cart Line",true,cartLineDAO.add(cartLine));
		
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCart_Lines(cart.getCart_Lines() + 1);
		
		assertEquals("Failed to update the Cart ",true,cartLineDAO.updateCart(cart));
	}

}
