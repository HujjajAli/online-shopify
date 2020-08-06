package net.haq.onlineshopify.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.haq.onlineshopify.util.FileUploadUtility;
import net.haq.onlineshopify.validation.ProductValidator;
import net.haq.shopifybackend.dao.CategoryDAO;
import net.haq.shopifybackend.dao.ProductDAO;
import net.haq.shopifybackend.dao.User_DetailDAO;
import net.haq.shopifybackend.dto.Category;
import net.haq.shopifybackend.dto.Product;
import net.haq.shopifybackend.dto.User_Detail;

@Controller
public class ManagmentController {
	
	
	//fetching DAOs
	@Autowired
	User_DetailDAO user_DAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	ProductDAO productDAO;
	
	User_Detail user;
	
	@RequestMapping(value="/manage_products",method=RequestMethod.GET)
	public ModelAndView showManageProduct(@RequestParam(name="opration",required=false)String opration){
		System.out.println("data Printing OK");
		ModelAndView mv = new ModelAndView("page");
		Product nProduct = new Product();
		
		user = user_DAO.get(5);
		nProduct.setUser_details(user.getUser_id());
		
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		mv.addObject("product", nProduct);
		
		if(opration != null){
			if(opration.equals("product")){
				mv.addObject("msg", "Product Submitted Successfully");
			}else if(opration.equals("category")){
				mv.addObject("msg", "Category Submitted Successfully");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/manageProductUpdate",method=RequestMethod.GET)
	public ModelAndView showEditProduct(@RequestParam("product_id")int id){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		//fetch product from database
		Product nProduct = productDAO.get(id);
		
		mv.addObject("product", nProduct);
		return mv;
	}
	
	@RequestMapping(value="/add_product",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute Product product,BindingResult results,Model model,HttpServletRequest request){
		
		if(product.getProduct_id() == 0){
			new ProductValidator().validate(product, results);
		}else{
			if(!product.getFile().getOriginalFilename().equals("")){
				//if during editing product user is choosing a file then validate it
				new ProductValidator().validate(product, results);
			}
		}
		
		//Check for Errors
		if(results.hasErrors()){
			
			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("msg", "Please Enter Values According to Subjects");
			return "page";
		}
		
		System.out.println(product);
		
		if(product.getProduct_id() == 0){
			//create new product
			productDAO.add(product);
		}else{
			//update product
			productDAO.update(product);
		}
		
		
		if(!product.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,product.getFile(),product.getCode());
		}
		
		return "redirect:/manage_products?opration=product";
	}
	
	@RequestMapping(value="/product_activation_deactivation",method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@RequestParam("prdID")int id){
		//fetch product from database
		Product product = productDAO.get(id);
		boolean is_active = product.isActive();
		//Activating or Deactivating Product based on the value
		product.setActive(!product.isActive());
		
		productDAO.update(product);
		
		return (is_active)?"You Have Successfully Deactivated the Product :"+product.getName():"You Have Successfully Activated the Product"+product.getName();
	}
	
	//to handle Category Submission
	@RequestMapping(value="/add_category",method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
		//add new category
		categoryDAO.add(category);
		return "redirect:/manage_products?opration=category";
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}

}
