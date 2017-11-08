/**
* Category Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.model;

public class Category {

	/**ID**/
	private int id;
	/**Name**/
	private String name;
	/**Arabic Name**/
	private String nameAr;
	/**Description**/
	private String description;
	/**Arabic Description**/
	private String descriptionAr;
	/**Parent**/
	private int parent;
	/**Ordering**/
	private int ordering;
	/**Is Visible**/
	private int isVisible;
	/**Is Comment Allowed**/
	private int isCommentAllowed;
	/**Is ADS Allowed**/
	private int isAdsAllowed;
	
	public Category(){
		
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
	* Get Name Ar Method
	* 
	* @return nameAr
	**/
	public String getNameAr() {
		return nameAr;
	}
	/**
	* Set Name Ar Method
	* 
	* @param nameAr
	**/
	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
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
	* Get Description Ar Method
	* 
	* @return descriptionAr
	**/
	public String getDescriptionAr() {
		return descriptionAr;
	}
	/**
	* Set Description Ar Method
	* 
	* @param descriptionAr
	**/
	public void setDescriptionAr(String descriptionAr) {
		this.descriptionAr = descriptionAr;
	}
	/**
	* Get Parent Method
	* 
	* @return parent
	**/
	public int getParent() {
		return parent;
	}
	/**
	* Set Parent Method
	* 
	* @param parent
	**/
	public void setParent(int parent) {
		this.parent = parent;
	}
	/**
	* Get Ordering Method
	* 
	* @return ordering
	**/
	public int getOrdering() {
		return ordering;
	}
	/**
	* Set Ordering Method
	* 
	* @param ordering
	**/
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	/**
	* Get Is Visible Method
	* 
	* @return isVisible
	**/
	public int getIsVisible() {
		return isVisible;
	}
	/**
	* Set Is Visible Method
	* 
	* @param isVisible
	**/
	public void setIsVisible(int isVisible) {
		this.isVisible = isVisible;
	}
	/**
	* Get Is Comment Allowed Method
	* 
	* @return isCommentAllowed
	**/
	public int getIsCommentAllowed() {
		return isCommentAllowed;
	}
	/**
	* Set Is Comment Allowed Method
	* 
	* @param isCommentAllowed
	**/
	public void setIsCommentAllowed(int isCommentAllowed) {
		this.isCommentAllowed = isCommentAllowed;
	}
	/**
	* Get Is Ads Allowed Method
	* 
	* @return isAdsAllowed
	**/
	public int getIsAdsAllowed() {
		return isAdsAllowed;
	}
	/**
	* Set Is Ads Allowed Method
	* 
	* @param isAdsAllowed
	**/
	public void setIsAdsAllowed(int isAdsAllowed) {
		this.isAdsAllowed = isAdsAllowed;
	}
}