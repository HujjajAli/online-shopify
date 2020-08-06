package net.haq.onlineshopify.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String messsage;
	
	public ProductNotFoundException(){
		this("Product Not Available :(");
	}
	
	public ProductNotFoundException(String message){
		this.messsage = System.currentTimeMillis() + " : " + message;
	}

	public String getMesssage() {
		return messsage;
	}
	
	
}
