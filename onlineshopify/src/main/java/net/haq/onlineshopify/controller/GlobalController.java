package net.haq.onlineshopify.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.haq.onlineshopify.model.UserModel;
import net.haq.shopifybackend.dao.User_DetailDAO;
import net.haq.shopifybackend.dto.User_Detail;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private User_DetailDAO userDAO;
	
	private UserModel userModel = null;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel(){
		
		if(session.getAttribute("userModel") == null){
			// add the user model
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User_Detail user = userDAO.getByEmail(authentication.getName());
			
			if(user != null){
				//create a new UserModel Object to pass the details
				userModel = new UserModel();
				
				userModel.setId(user.getUser_id());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName()+" "+user.getLastName());
				if(userModel.getRole().equals("USER")){
					//set the cart only if user a buyer
					userModel.setCart(user.getCart());
				}
				//set the user Model in the session
				session.setAttribute("userModel", userModel);
				return userModel;
			}
			
		}
		
		return (UserModel)session.getAttribute("userModel");
	}

}
