package net.haq.shopifybackend.dto;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Entity
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int product_id;
	@Column(unique=true)
	private String code;
	@NotBlank(message = "Please Enter the Product Name!")
	private String name;
	@NotBlank(message = "Please Enter the Brand Name!")
	private String brand;
	@NotBlank(message = "Please Enter the Description for Product!")
	private String description;
	@Min(value=500,message = "The Price cannot be less then 500 PKR")
	private double unitPrice;
	private int quantity;
	@Column(name = "is_active")
	private boolean active = true; 
	private int purchases;
	private int views;
	@Column(name="category_id")
	private int category;
	@Column(name="supplier_id")
	private int user_details;
	
	public Product(){
		code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getPurchases() {
		return purchases;
	}

	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getUser_details() {
		return user_details;
	}

	public void setUser_details(int user_details) {
		this.user_details = user_details;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", code=" + code + ", name=" + name + ", brand=" + brand
				+ ", description=" + description + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", active="
				+ active + ", purchases=" + purchases + ", views=" + views + ", category=" + category
				+ ", user_details=" + user_details + "]";
	}
	
	
	@Transient
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
