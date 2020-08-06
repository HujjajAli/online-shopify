package net.haq.shopifybackend.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User_Detail implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_id;
	@NotBlank(message = "Please Enter First Name!")
	private String firstName;
	@NotBlank(message = "Please Enter Last Name!")
	private String lastName;
	@JsonIgnore
	private String role;
	@Column(name = "is_enabled")
	private boolean enabled=true;
	@JsonIgnore
	@NotBlank(message = "Please Enter Password!")
	private String password;
	@JsonIgnore
	@NotBlank(message = "Please Enter Email Name!")
	private String email;
	@JsonIgnore
	@NotBlank(message = "Please Enter Contact Number!")
	private String contact;
	@OneToOne(mappedBy = "user_Detail",cascade = CascadeType.ALL)
	private Cart cart;
	
	//confirm password transient field
	@Transient
	private String confirmPassword;
	public String getConfirmPassword(){return confirmPassword;}
	public void setConfirmPassword(String confirmPassword){this.confirmPassword = confirmPassword;}
	
	
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "User_Detail [user_id=" + user_id + ", firstName=" + firstName + ", lastName=" + lastName + ", role="
				+ role + ", enabled=" + enabled + ", password=" + password + ", email=" + email + ", contact=" + contact
				+ "]";
	}
	
	
	
	
}
