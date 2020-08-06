package net.haq.onlineshopify.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException(){
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","The Page is Not Constructed! ");
		mv.addObject("errorDescription","The Page your are looking for is not available now! :(");
		mv.addObject("title","404 Error Page");
		
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotException(){
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","Product Not Available! ");
		mv.addObject("errorDescription","The Product your are looking for is not available now! :(");
		mv.addObject("title","Product Unavailable");
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception e){
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","Contact Your Adminstrator! ");
		mv.addObject("errorDescription",e.toString() +"! :(");
		mv.addObject("title","Page Error");
		
		return mv;
	}

}
