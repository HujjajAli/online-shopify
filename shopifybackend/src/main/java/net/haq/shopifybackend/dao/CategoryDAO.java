package net.haq.shopifybackend.dao;

import java.util.List;

import net.haq.shopifybackend.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	Category  get(int id);

}
