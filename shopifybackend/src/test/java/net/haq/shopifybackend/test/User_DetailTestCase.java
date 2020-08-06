package net.haq.shopifybackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.haq.shopifybackend.dao.User_DetailDAO;
import net.haq.shopifybackend.dto.Address;
import net.haq.shopifybackend.dto.Cart;
import net.haq.shopifybackend.dto.User_Detail;

public class User_DetailTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static User_DetailDAO user_DetailDAO; 
	
	User_Detail user;
	Address address;
	Cart cart;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("net.haq.shopifybackend");
		context.refresh();
		
		user_DetailDAO = (User_DetailDAO)context.getBean("user_DetailDAOImpl");
	}
	
	@Test
	public void testAddress(){
		user = user_DetailDAO.get(27);
		
		assertEquals("Failed to Analyze Shipping Address",1, user_DetailDAO.getBillingAddress(user).getAddress_id());
	}
	
	/*
	@Test
	public void testAdd(){
		user  = new User_Detail();
		
		user.setFirstName("Sajjad");
		user.setLastName("Qambrani");
		user.setContact("03318345677");
		user.setEmail("sajjad@yahoo.com");
		user.setPassword("1234");
		user.setRole("USER");
		
		if(user.getRole().equals("USER")){
			cart = new Cart();
			cart.setUser_Detail(user);
			user.setCart(cart);
		}
		
		assertEquals("Failed to Add the User",true ,user_DetailDAO.add(user));
		
		user = user_DetailDAO.get(7);
		
		address = new Address();
		address.setShipping(true);
		address.setAddressLineOne("Main Market");
		address.setAddressLineTwo("Near NBP Bank");
		address.setCity("Tando Bago");
		address.setStateOrprovince("Sindh");
		address.setCountry("Pakistan");
		address.setPostalCode("22014");
		address.setUser_Detail(user);;
		
		
		
		assertEquals("Failed to Add the Address",true,user_DetailDAO.addAddress(address));
	} */

	/*
	@Test
	public void cartTest(){
		user = user_DetailDAO.getByEmail("sajjad@yahoo.com");
		
		cart = user.getCart();
		cart.setCart_Lines(3);
		cart.setGrandTotal(56000);
		
		assertEquals("Failed to Add the Cart",true,user_DetailDAO.updateCart(cart));
	}*/
	
	/*
	@Test
	public void addMultipleUser(){
		/*
		 * User    ADMIN    123 hujajali@gmail.com   
		 * User Amjad Ali   SUPPLIER 124 amjadali@gmail.com   
		 * User Fiza Sheikh SUPPLIER 121 fizashaikh@gmail.com 
		 * */
		//user = new User_Detail();
		/*
		user.setFirstName("Hujaj");
		user.setLastName("Ali");
		user.setRole("ADMIN");
		user.setPassword("123");
		user.setEmail("hujajali@gmail.com");
		user.setContact("03333706512");
		assertEquals("Suucessfully Added User :)",true,user_DetailDAO.add(user));*/
	/*	User_Detail user1 = new User_Detail();
		
		user1.setFirstName("Fiza");
		user1.setLastName("Shaikh");
		user1.setRole("SUPPLIER");
		user1.setPassword("121");
		user1.setEmail("fizashaikh@gmail.com");
		user1.setContact("03123234563");
		assertEquals("Suucessfully Added User :)",true,user_DetailDAO.add(user1));
		
		User_Detail user2 = new User_Detail();
		user2.setFirstName("Amjad");
		user2.setLastName("Ali");
		user2.setRole("SUPPLIER");
		user2.setPassword("124");
		user2.setEmail("amjadali@gmail.com");
		user2.setContact("03458278394");
		assertEquals("Suucessfully Added User :)",true,user_DetailDAO.add(user2));
	}*/

}
