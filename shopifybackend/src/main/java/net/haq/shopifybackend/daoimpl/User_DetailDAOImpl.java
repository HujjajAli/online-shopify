package net.haq.shopifybackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.haq.shopifybackend.dto.Address;
import net.haq.shopifybackend.dto.Cart;
import net.haq.shopifybackend.dto.Product;
import net.haq.shopifybackend.dto.User_Detail;

@Repository
@Transactional
public class User_DetailDAOImpl implements net.haq.shopifybackend.dao.User_DetailDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addUser(User_Detail user) {
		try{
			sessionFactory.
			   getCurrentSession().persist(user);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User_Detail get(int id) {
		User_Detail user = null;
		try{
			user = sessionFactory.
					     getCurrentSession().
					        get(User_Detail.class, Integer.valueOf(id));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean update(User_Detail user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User_Detail user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAddress(Address address) {
		try{
			sessionFactory.
			   getCurrentSession().persist(address);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<User_Detail> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User_Detail getByEmail(String email) {
		String query = "FROM User_Detail WHERE email = :email";
		
		try{
			return sessionFactory
					 .getCurrentSession()
					   .createQuery(query,User_Detail.class)
					     .setParameter("email", email)
					       .getSingleResult();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Address getBillingAddress(User_Detail user) {
		String selectQuery = "FROM Address WHERE user_Detail = :user_Detail AND billing = :billing";
		
		try{
			return sessionFactory
					 .getCurrentSession()
					    .createQuery(selectQuery,Address.class)
					       .setParameter("user_Detail", user)
					       .setParameter("billing", true)
					          .getSingleResult();
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
	}

	@Override
	public List<Address> listShippingAddresses(User_Detail user) {
		String selectQuery = "FROM Address WHERE user_Detail = :user_Detail AND shipping = :shipping";
		
		try{
			return sessionFactory
					 .getCurrentSession()
					    .createQuery(selectQuery,Address.class)
					       .setParameter("user_Detail", user)
					       .setParameter("shipping", true)
					          .getResultList();
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
