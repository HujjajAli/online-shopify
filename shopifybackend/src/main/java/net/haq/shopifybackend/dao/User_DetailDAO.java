package net.haq.shopifybackend.dao;

import java.util.List;

import net.haq.shopifybackend.dto.Address;
import net.haq.shopifybackend.dto.Cart;
import net.haq.shopifybackend.dto.User_Detail;

public interface User_DetailDAO {
	
	
	boolean update(User_Detail user);
	boolean delete(User_Detail user);
	User_Detail get(int id);
	User_Detail getByEmail(String email);
	
	boolean addUser(User_Detail user);
	boolean addAddress(Address adress);
	
	
	Address getBillingAddress(User_Detail user);
	
	List<Address> listShippingAddresses(User_Detail user);
	List<User_Detail> list();

}
