package net.haq.shopifybackend.dao;

import java.util.List;

import net.haq.shopifybackend.dto.Category;

public interface CategoryDAO {
	
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
	
	List<Category> list();
	Category  get(int id);

}
