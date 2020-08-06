package net.haq.onlineshopify.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.haq.onlineshopify.exception.ProductNotFoundException;
import net.haq.shopifybackend.dao.CategoryDAO;
import net.haq.shopifybackend.dao.ProductDAO;
import net.haq.shopifybackend.dto.Category;
import net.haq.shopifybackend.dto.Product;
import net.haq.shopifybackend.dto.User_Detail;

@Controller
public class PageController{
	
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
    
	@RequestMapping(value = {"/","/home","/index"})
	public ModelAndView index() {
		
		
		ModelAndView mv = new  ModelAndView("page");
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("title","Home");
		mv.addObject("userClickHome",true);
		return mv;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new  ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new  ModelAndView("page");
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact",true);
		return mv;
	}
	
	// Methods to Load Products based on category and all
	@RequestMapping(value = "/showall")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new  ModelAndView("page");
		
		//Passing the List of Categories
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("title","All Products");
		mv.addObject("userClickAllProducts",true);
		
		return mv;
	}
	
	@RequestMapping(value = "/category")
	public ModelAndView showCategoryProducts(@RequestParam("category")int id){
		Category category = null;
		//CategoryDAO to fetch our single Category;
		category  = categoryDAO.get(id);
		
		ModelAndView mv = new  ModelAndView("page");
		
		//Passing the List of Categories
		mv.addObject("categories", categoryDAO.list());
		
		//Passing the Single Category
		mv.addObject("category", category);
		
		mv.addObject("title",category.getName());
		mv.addObject("userClickCategoryProducts",true);
		return mv;
	}
	
	//View Single Product
	@RequestMapping(value = "/show_product")  //ProductNotFoundException
	public ModelAndView showSingleProduct(@RequestParam("product")int id) throws ProductNotFoundException {
		ModelAndView mv = new  ModelAndView("page");
		Product product = productDAO.get(id);
		
		//Throw Exception 
		if(product == null) throw new ProductNotFoundException();
		
		//get previous views and increase it by 1
		product.setViews(product.getViews() + 1);
		//update product for views
		productDAO.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickShowProduct",true);
		
		return mv;
	}
	
	//Login
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name="error", required=false)String error,@RequestParam(name="logout", required=false)String logout) {
		ModelAndView mv = new  ModelAndView("login");
		
		if(error!=null){
			mv.addObject("message", "Invalid Username And Password!");
		}
		
		if(logout!=null){
			mv.addObject("logout", "You are Successfully Logged Out!");
		}
		
		mv.addObject("title","Login");
		return mv;
	}
	
	//Access Denied Page
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new  ModelAndView("error");
		mv.addObject("title","403 - Acess Denied");
		mv.addObject("errorTitle","Acess Resitricted");
		mv.addObject("errorDescription","You are not authorized to view this page!");
		return mv;
	}
	
	//Logout Operation
	@RequestMapping(value="/perform-logout")
	public String logut(HttpServletRequest request,HttpServletResponse response){
		//fetch the athentication object
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/login?logout";
	}
	
}
