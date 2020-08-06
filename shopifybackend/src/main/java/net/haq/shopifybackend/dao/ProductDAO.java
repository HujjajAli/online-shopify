package net.haq.shopifybackend.dao;

import java.util.List;

import net.haq.shopifybackend.dto.Category;
import net.haq.shopifybackend.dto.Product;

public interface ProductDAO {
	
	//crud methods
	boolean       add(Product product);
	boolean       update(Product product);
	boolean       delete(Product product);
	Product       get(int id);
	List<Product> list();
	
	//business methods;
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int category);
	List<Product> listLatestActiveProducts(int count);
}
