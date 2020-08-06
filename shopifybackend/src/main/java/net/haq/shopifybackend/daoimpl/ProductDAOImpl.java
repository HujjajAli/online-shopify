package net.haq.shopifybackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.haq.shopifybackend.dao.ProductDAO;
import net.haq.shopifybackend.dto.Category;
import net.haq.shopifybackend.dto.Product;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean add(Product product) {
		try{
			sessionFactory.
			   getCurrentSession().persist(product);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean update(Product product) {
		try{
			sessionFactory.
			   getCurrentSession().update(product);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		product.setActive(false);
		try{
			sessionFactory.
			   getCurrentSession().update(product);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Product get(int id) {
		Product product = null;
		try{
			product = sessionFactory.
					     getCurrentSession().
					        get(Product.class, Integer.valueOf(id));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Product> list() {
		return sessionFactory.
				 getCurrentSession().
				   createQuery("from Product",Product.class).getResultList();
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "from Product WHERE active = :active";
		return sessionFactory
				.getCurrentSession()
				   .createQuery(selectActiveProducts,Product.class)
				       .setParameter("active", true)
				          .getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int category) {
		String selectActiveProductsByCategory = "from Product WHERE active = :active AND category = :category";
		return sessionFactory
				.getCurrentSession()
				   .createQuery(selectActiveProductsByCategory,Product.class)
				       .setParameter("active", true)
				       .setParameter("category",category)
				          .getResultList();
	}

	@Override
	public List<Product> listLatestActiveProducts(int count) {
		String selectLatestActiveProducts = "from Product WHERE active = :active ORDER BY product_id";
		return sessionFactory
				.getCurrentSession()
				   .createQuery(selectLatestActiveProducts,Product.class)
				       .setParameter("active", true)
				          .setFirstResult(0)
				          .setMaxResults(count)
				             .getResultList();
	}

}
