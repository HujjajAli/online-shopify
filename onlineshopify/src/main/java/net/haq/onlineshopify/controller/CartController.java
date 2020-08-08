package net.haq.onlineshopify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.haq.onlineshopify.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="/show_cart")
	public ModelAndView showCart(@RequestParam(name = "result",required=false)String result){
		ModelAndView mv = new ModelAndView("page");
		
		if (result != null) {
			
			switch(result){
				case "updated":
					 mv.addObject("message", "Cart Line has been Updated Seccessfully");
					 break;
				case "added":
					 mv.addObject("message", "Cart Line has been Updated Seccessfully");
					 break;	 
				case "deleted":
					 mv.addObject("message", "Cart Line has been Removed Seccessfully");
					 break;
				case "maximum":
					 mv.addObject("message", "Cart Line has been Reached to its Maximum Limit!");
					 break;
				case "unavailable":
					 mv.addObject("message", "Product Quantity is not available");
					 break;	 
				case "error":
					 mv.addObject("message", "Something went Wrong!");
					 break;	 
			}
		}
		
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart",true);
		mv.addObject("cartLines", cartService.getCartLines());
		return mv;
	}
	
	@RequestMapping(value="/cart/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId,@RequestParam("count")int count){
		String response = cartService.updateCartLine(cartLineId,count);
		
		return "redirect:/show_cart?"+response; 
	}
	
	@RequestMapping(value="/cart/{cartLineId}/delete")
	public String deleteCartLine(@PathVariable int cartLineId){
		String response = cartService.deleteCartLine(cartLineId);	
		return "redirect:/show_cart?"+response;
	}
	
	@RequestMapping(value="/cart/add/{productId}/product")
	public String addCartLine(@PathVariable int productId){
		String response = cartService.addCartLine(productId);
		return "redirect:/show_cart?"+response;
	}

}
