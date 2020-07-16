package net.haq.shopifybackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.haq.shopifybackend.dao.CategoryDAO;
import net.haq.shopifybackend.dto.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	
	private static List<Category> catgories = new ArrayList<Category>();
	
	static{
		Category cat1 = new Category();
		
		cat1.setId(1);
		cat1.setName("Mobile");
		cat1.setDescription("Cheap Mobiles");
		cat1.setImageURL("img1");
		
		Category cat2 = new Category();
		
		cat2.setId(2);
		cat2.setName("Television");
		cat2.setDescription("Cheap TVs");
		cat2.setImageURL("img2");
		
		Category cat3 = new Category();
		
		cat3.setId(3);
		cat3.setName("Laptops");
		cat3.setDescription("Cheap Laptops");
		cat3.setImageURL("img3");
		
		Category cat4 = new Category();
		
		cat4.setId(4);
		cat4.setName("Tablets");
		cat3.setDescription("Cheap Tablets");
		cat3.setImageURL("img4");
		
		catgories.add(cat1);
		catgories.add(cat2);
		catgories.add(cat3);
		catgories.add(cat4);
		
	}
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return catgories;
	}

	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		for (Category category : catgories) {
			if(category.getId() == id) return category;
		}
		return null;
	}

}
