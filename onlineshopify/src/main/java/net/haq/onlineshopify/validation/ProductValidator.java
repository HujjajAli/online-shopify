package net.haq.onlineshopify.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.haq.shopifybackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product product = (Product)target;
		
		//whether file has been selected or not
		if(product.getFile() == null || product.getFile().getOriginalFilename().equals("")){
			errors.rejectValue("file", null, "Please Select an Image File to Upload!");
			return;
		}
		
		if(! product.getFile().getContentType().equals("image/jpeg") ){
			errors.rejectValue("file", null, "Please Only Select a JPG Image File for Upload");
			return;
		}
	}

}
