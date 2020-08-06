package net.haq.onlineshopify.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.haq.onlineshopify.model.RegisterModel;
import net.haq.shopifybackend.dao.User_DetailDAO;
import net.haq.shopifybackend.dto.Address;
import net.haq.shopifybackend.dto.Cart;
import net.haq.shopifybackend.dto.User_Detail;

@Component
public class RegisterHandler {
	
	@Autowired
	private User_DetailDAO user_DetailDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init(){
		
		return new RegisterModel();
		
	}
	
	public void addUser(RegisterModel registerModel,User_Detail user){
		registerModel.setUser_Detail(user);
	}
	
	public void addBilling(RegisterModel registerModel,Address billing){
		registerModel.setBilling(billing);
	}
	
	public String validateUser(User_Detail user, MessageContext error){
		String transitionValue = "success";
		
		//checking if password matches confirm password
		if( !(user.getPassword().equals(user.getConfirmPassword())) ){
			String msg = "Password deos not match the Confirm Password!";
			error.addMessage(new MessageBuilder().error().source("confirmPassword").defaultText(msg).build());
			transitionValue = "failure";
		}
		
		//check the uniqueness of email
		if(user_DetailDAO.getByEmail(user.getEmail()) != null){
			String msg = "Email Address is already in Use!";
			error.addMessage(new MessageBuilder().error().source("email").defaultText(msg).build());
			transitionValue = "failure";
		}
		
		return transitionValue;
	}
	
	public String saveAll(RegisterModel model){
		String transitionValue = "success";
		
		//get user
		User_Detail user = model.getUser_Detail();
		if(user.getRole().equals("USER")){
			Cart cart = new Cart();
			cart.setUser_Detail(user);
			user.setCart(cart);
		}
		//encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//save the user
		user_DetailDAO.addUser(user);
		
		
		//get Address
		Address billing = model.getBilling();
		billing.setUser_Detail(user);
		billing.setBilling(true);
		//save the address
		user_DetailDAO.addAddress(billing);
		
		return transitionValue;
	}

}
