package net.haq.onlineshopify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.haq.shopifybackend.dao.CategoryDAO;
import net.haq.shopifybackend.daoimpl.CategoryDAOImpl;
import net.haq.shopifybackend.dto.Category;

@Controller
public class PageController{
	
	
	@Autowired
	CategoryDAO categoryDAO;
    
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
	
	
	//if you use this URL the Same page will be loaded with resources
	@RequestMapping(value = "/show")
	public ModelAndView showAllProductsWorkingURL() {
		ModelAndView mv = new  ModelAndView("page");
		
		//Passing the List of Categories
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("title","All Products");
		mv.addObject("userClickAllProducts",true);
		
		return mv;
	}
	
	// Methods to Load Products based on category and all
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new  ModelAndView("page");
		
		//Passing the List of Categories
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("title","All Products");
		mv.addObject("userClickAllProducts",true);
		
		return mv;
	}
	
	@RequestMapping(value = "/show/category/{id}")
	public ModelAndView showCategoryProducts(@PathVariable("id")int id){
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
}
