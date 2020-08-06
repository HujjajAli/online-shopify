package net.haq.shopifybackend.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.haq.shopifybackend.dao.CategoryDAO;
import net.haq.shopifybackend.daoimpl.CategoryDAOImpl;
import net.haq.shopifybackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("net.haq.shopifybackend");
		context.refresh();
		
		categoryDAO = (CategoryDAO)context.getBean("categoryDAOImpl");
	}
	
	
	@Test
	public void testAddCategory() {
		category = new Category();
		
		category.setName("Headphones");
		category.setDescription("Cheap Headphones");
		category.setImageURL("catimg5.jpg");
		
		assertEquals("Successfully Added Category in DataBase :)",true,categoryDAO.add(category));
	}
	
	/*
	@Test
	public void testGetCategory(){
		category = categoryDAO.get(3);
		assertEquals("Successfully get Single Category from Table :)","Glass",category.getName());
	}*/
	
	/*
	@Test
	public void testUpdateCategory(){
		category = categoryDAO.get(1);
		
		category.setName("TV");
		
		assertEquals("Successfully Updated a Single Category in Table :)",true,categoryDAO.update(category));
	}*/
	
	
	/*
	@Test
	public void testDeleteCategory(){
		category = categoryDAO.get(3);
		assertEquals("Successfully Deleted a Category from Table :)",true,categoryDAO.delete(category));
	}*/
	
	/*
	@Test
	public void testListCategory(){
		//List<Category> list = null;
		//category = categoryDAO.get(3);
		assertEquals("Successfully fatched list of Categories from table :)",2,categoryDAO.list().size());
	}*/
	/*
	@Test
	public void testCRUDCategory(){
		//adding new category
		category = new Category();
		
		category.setName("Laptop");
		category.setDescription("Cheap Laptops");
		category.setImageURL("catimg4.jpg");
		
		assertEquals("Successfully Added Category in DataBase :)",true,categoryDAO.add(category));
		
		//fetching and updating category
		category = categoryDAO.get(3);
		
		category.setName("Tablets");
		category.setDescription("Cheap Tablets");
		category.setActive(true);
		
		assertEquals("Successfully Updated a Single Category in Table :)",true,categoryDAO.update(category));
		
		//deleting a category
		assertEquals("Successfully Deleted a Category from Table :)",true,categoryDAO.delete(category));
		
		assertEquals("Successfully fetched list of Categories from table :)",2,categoryDAO.list().size());
	}*/
}
