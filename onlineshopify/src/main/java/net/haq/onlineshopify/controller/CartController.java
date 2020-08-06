package net.haq.onlineshopify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {
	
	@RequestMapping(value="/show_cart")
	public ModelAndView show(){
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("title", "Uer Cart");
		mv.addObject("userClickShowCart",true);
		mv.addObject("cartLines",null);
		return mv;
	}

}
