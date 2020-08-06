package net.haq.onlineshopify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.haq.shopifybackend.dao.CategoryDAO;
import net.haq.shopifybackend.dao.ProductDAO;
import net.haq.shopifybackend.dto.Category;
import net.haq.shopifybackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
	
	@Autowired
	ProductDAO productDAO;
	@Autowired
	CategoryDAO categoryDAO;
	
	@RequestMapping("/products")
	@ResponseBody
	public List<Product> getAllProducts() {
		return productDAO.listActiveProducts();
	}
	
	@RequestMapping("all/products")
	@ResponseBody
	public List<Product> getAllProductsForAdmin() {
		return productDAO.list();
	}
	
	@RequestMapping("/products/category/{id}")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable("id")int id) {
		System.out.println("Category : "+id);
		return productDAO.listActiveProductsByCategory(id);
	}
}
