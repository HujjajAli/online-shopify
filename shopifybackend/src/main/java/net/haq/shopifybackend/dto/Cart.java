package net.haq.shopifybackend.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Cart implements Serializable{
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cart_id;
	@OneToOne
	@JoinColumn(name = "uid")
	private User_Detail user_Detail;
	private double grandTotal;
	private int cart_Lines;
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public User_Detail getUser_Detail() {
		return user_Detail;
	}
	public void setUser_Detail(User_Detail user_Detail) {
		this.user_Detail = user_Detail;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getCart_Lines() {
		return cart_Lines;
	}
	public void setCart_Lines(int cart_Lines) {
		this.cart_Lines = cart_Lines;
	}
	
	
}
