/**
* Comment Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.model;

import java.util.Date;

public class Comment {

	/**ID**/
	private int id;
	/**Comment**/
	private String comment;
	/**Status**/
	private int status;
	/**Creation Date**/
	private Date creationDate;
	/**Item**/
	private Item item;
	/**User**/
	private User user;
	
	public Comment(){
		
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
	* Get Comment Method
	* 
	* @return comment
	**/
	public String getComment() {
		return comment;
	}
	/**
	* Set Comment Method
	* 
	* @param comment
	**/
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	* Get Status Method
	* 
	* @return status
	**/
	public int getStatus() {
		return status;
	}
	/**
	* Set Status Method
	* 
	* @param status
	**/
	public void setStatus(int status) {
		this.status = status;
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
	* Get Item Method
	* 
	* @return item
	**/
	public Item getItem() {
		return item;
	}
	/**
	* Set Item Method
	* 
	* @param item
	**/
	public void setItem(Item item) {
		this.item = item;
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
}