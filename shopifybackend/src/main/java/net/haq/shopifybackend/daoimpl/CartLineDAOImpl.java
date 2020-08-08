package net.haq.shopifybackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.haq.shopifybackend.dao.CartLineDAO;
import net.haq.shopifybackend.dto.Cart;
import net.haq.shopifybackend.dto.CartLine;

@Repository
@Transactional
public class CartLineDAOImpl implements CartLineDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CartLine get(int id) {
		return sessionFactory.getCurrentSession().get(CartLine.class, id);
	}

	@Override
	public boolean add(CartLine cartLine) {
		try{
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		try{
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartLine) {
		try{
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	public List<CartLine> list(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId";
		return sessionFactory
				 .getCurrentSession()
				    .createQuery(query,CartLine.class)
				       .setParameter("cartId", cartId)
				          .getResultList();
	}
	
	@Override
	public boolean updateCart(Cart cart) {
		try{
			sessionFactory.
			   getCurrentSession().update(cart);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND available = :available";
		return sessionFactory
				 .getCurrentSession()
				    .createQuery(query,CartLine.class)
				       .setParameter("cartId", cartId)
				       .setParameter("available",true)
				          .getResultList();
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND product.product_id = :productId";
		try{
			return sessionFactory
					 .getCurrentSession()
					    .createQuery(query,CartLine.class)
					       .setParameter("cartId", cartId)
					       .setParameter("productId",productId)
					          .getSingleResult();
		}catch (Exception e) {
			return null;
		}
		
	}
	
	
}
