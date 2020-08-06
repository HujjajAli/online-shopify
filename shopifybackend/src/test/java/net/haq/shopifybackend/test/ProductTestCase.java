package net.haq.shopifybackend.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.haq.shopifybackend.dao.CategoryDAO;
import net.haq.shopifybackend.dao.ProductDAO;
import net.haq.shopifybackend.dao.User_DetailDAO;
import net.haq.shopifybackend.dto.Category;
import net.haq.shopifybackend.dto.Product;
import net.haq.shopifybackend.dto.User_Detail;

public class ProductTestCase {
	
	static List<Product> products = new ArrayList<Product>();
	
	private static AnnotationConfigApplicationContext context;
	
	private static ProductDAO productDAO;
	private static CategoryDAO categoryDAO;
	private static User_DetailDAO user_DetailDAO;
	
	private Product product;
	private Category category;
	private User_Detail user_Detail;
	
	
	static{
		
		
		
		Product p4 = new Product();
		p4.setName("Samsung 4K OLED");
		p4.setBrand("Samsung");
		p4.setDescription("Best Performance Samsung 4K OLED TV 50 Inches");
		p4.setUnitPrice(105000);
		
		
		
		
		products.add(p4);
	}
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("net.haq.shopifybackend");
		context.refresh();
		
		productDAO = (ProductDAO)context.getBean("productDAOImpl");
		categoryDAO = (CategoryDAO)context.getBean("categoryDAOImpl");
		user_DetailDAO = (User_DetailDAO)context.getBean("user_DetailDAOImpl");
	}
	
	
	@Test
	public void testListMethods(){
		//category    = categoryDAO.get(4);
		List<Product> data = productDAO.listLatestActiveProducts(3);
		for (Product product : data) {
			System.out.println(product);
		}
		assertEquals("getting list of products error",3,productDAO.listLatestActiveProducts(3).size());
	}
	
	
	
	/*
	@Test
	public void testProductAdd(){
		product     = products.get(7);
		category    = categoryDAO.get(3);
		user_Detail = user_DetailDAO.get(5); //5,6,7
		
		product.setCategory(category);
		product.setUser_details(user_Detail);
		
		assertEquals("Something Went Wrong While Saving the Product",true,productDAO.add(product));
	}*/
	
	/*
	@Test
	public void testProductUpdate(){
		product = productDAO.get(12);
		//product.setName("Samsung Galaxy S7");
		//product.setDescription("Best Performance Samsung Galaxy S7 Mobile");
		//product.setUnitPrice(38000);
		product.setActive(false);
		assertEquals("Something Went Wrong While Updating the Product",true,productDAO.update(product));
	}*/
	
	/*
	@Test
	public void testProductCRUD(){
		//product =
		
	}*/

}
