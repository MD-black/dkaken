/**
* Item Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.model;

import java.util.Date;

public class Item {

	/**ID**/
	private int id;
	/**Name**/
	private String name;
	/**Description**/
	private String description;
	/**Price**/
	private String price;
	/**Creation Date**/
	private Date creationDate;
	/**Made In**/
	private String madeIn;
	/**Image**/
	private String image;
	/**Status**/
	private String status;
	/**Rate**/
	private int rate;
	/**Is Approved**/
	private int isApproved;
	/**Category**/
	private Category category;
	/**User**/
	private User user;
	/**Tags**/
	private String tags;
	
	public Item(){
		
	}

	/**
	* Get Id Method
	* 
	* @return id
	**/
	public int getId() {
		return id;
	}
	/**
	* Set Id Method
	* 
	* @param id
	**/
	public void setId(int id) {
		this.id = id;
	}
	/**
	* Get Name Method
	* 
	* @return name
	**/
	public String getName() {
		return name;
	}
	/**
	* Set Name Method
	* 
	* @param name
	**/
	public void setName(String name) {
		this.name = name;
	}
	/**
	* Get Description Method
	* 
	* @return description
	**/
	public String getDescription() {
		return description;
	}
	/**
	* Set Description Method
	* 
	* @param description
	**/
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	* Get Price Method
	* 
	* @return price
	**/
	public String getPrice() {
		return price;
	}
	/**
	* Set Price Method
	* 
	* @param price
	**/
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	* Get Creation Date Method
	* 
	* @return creationDate
	**/
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	* Set Creation Date Method
	* 
	* @param creationDate
	**/
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	* Get Made In Method
	* 
	* @return madeIn
	**/
	public String getMadeIn() {
		return madeIn;
	}
	/**
	* Set Made In Method
	* 
	* @param madeIn
	**/
	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}
	/**
	* Get Image Method
	* 
	* @return image
	**/
	public String getImage() {
		return image;
	}
	/**
	* Set Image Method
	* 
	* @param image
	**/
	public void setImage(String image) {
		this.image = image;
	}
	/**
	* Get Status Method
	* 
	* @return status
	**/
	public String getStatus() {
		return status;
	}
	/**
	* Set Status Method
	* 
	* @param status
	**/
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	* Get Rate Method
	* 
	* @return rate
	**/
	public int getRate() {
		return rate;
	}
	/**
	* Set Rate Method
	* 
	* @param rate
	**/
	public void setRate(int rate) {
		this.rate = rate;
	}
	/**
	* Get Is Approved Method
	* 
	* @return isApproved
	**/
	public int getIsApproved() {
		return isApproved;
	}
	/**
	* Set Is Approved Method
	* 
	* @param isApproved
	**/
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}
	/**
	* Get Category Method
	* 
	* @return category
	**/
	public Category getCategory() {
		return category;
	}
	/**
	* Set Category Method
	* 
	* @param category
	**/
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	* Get User Method
	* 
	* @return user
	**/
	public User getUser() {
		return user;
	}
	/**
	* Set User Method
	* 
	* @param user
	**/
	public void setUser(User user) {
		this.user = user;
	}
	/**
	* Get Tags Method
	* 
	* @return tags
	**/
	public String getTags() {
		return tags;
	}
	/**
	* Set Tags Method
	* 
	* @param tags
	**/
	public void setTags(String tags) {
		this.tags = tags;
	}
}