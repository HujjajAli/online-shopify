package net.haq.shopifybackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.haq.shopifybackend.dao.CategoryDAO;
import net.haq.shopifybackend.dto.Category;

@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Category> list() {
		String selectActiveCategories = "from Category WHERE active = :active";
		return sessionFactory
				 .getCurrentSession()
				    .createQuery(selectActiveCategories,Category.class)
				        .setParameter("active", true)
				           .getResultList();
	}

	//getting single unique category
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class,Integer.valueOf(id));
	}

	@Override
	public boolean add(Category category) {
		try{
			//add the category to the database
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//updating a category based on id
	@Override
	public boolean update(Category category) {
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

}
