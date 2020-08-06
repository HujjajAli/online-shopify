package net.haq.onlineshopify.model;

import java.io.Serializable;

import net.haq.shopifybackend.dto.Address;
import net.haq.shopifybackend.dto.User_Detail;

public class RegisterModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private User_Detail user_Detail;
	private Address billing;
	
	public User_Detail getUser_Detail() {
		return user_Detail;
	}
	public void setUser_Detail(User_Detail user_Detail) {
		this.user_Detail = user_Detail;
	}
	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	
	

}
