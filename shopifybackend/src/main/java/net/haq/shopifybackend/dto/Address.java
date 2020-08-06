package net.haq.shopifybackend.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Address implements Serializable{
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int address_id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User_Detail user_Detail;
	@NotBlank(message = "Please Enter Address Line One!")
	private String addressLineOne;
	@NotBlank(message = "Please Enter Address Line Two!")
	private String addressLineTwo;
	@NotBlank(message = "Please Enter City Name!")
	private String city;
	@NotBlank(message = "Please Enter State/Province Name!")
	private String stateOrprovince;
	@NotBlank(message = "Please Enter Country Name!")
	private String country;
	@NotBlank(message = "Please Enter Postal Code!")
	private String postalCode;
	private boolean shipping;
	private boolean billing;
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public User_Detail getUser_Detail() {
		return user_Detail;
	}
	public void setUser_Detail(User_Detail user_Detail) {
		this.user_Detail = user_Detail;
	}
	public String getAddressLineOne() {
		return addressLineOne;
	}
	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}
	public String getAddressLineTwo() {
		return addressLineTwo;
	}
	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateOrprovince() {
		return stateOrprovince;
	}
	public void setStateOrprovince(String stateOrprovince) {
		this.stateOrprovince = stateOrprovince;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public boolean isShipping() {
		return shipping;
	}
	public void setShipping(boolean shipping) {
		this.shipping = shipping;
	}
	public boolean isBilling() {
		return billing;
	}
	public void setBilling(boolean billing) {
		this.billing = billing;
	}
	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", user_Detail=" + user_Detail + ", addressLineOne="
				+ addressLineOne + ", addressLineTwo=" + addressLineTwo + ", city=" + city + ", stateOrprovince="
				+ stateOrprovince + ", country=" + country + ", postalCode=" + postalCode + ", shipping=" + shipping
				+ ", billing=" + billing + "]";
	}
	
	
	
}
