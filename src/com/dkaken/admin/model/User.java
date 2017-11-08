/**
* User Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.model;

import java.util.Date;

public class User {
	
	/**ID**/
	private int id;
	/**User Name**/
	private String username;
	/**Password**/
	private String password;
	/**Email**/
	private String email;
	/**Full Name**/
	private String fullName;
	/**Group ID**/
	private int groupId;
	/**Trust Status**/
	private int trustStatus;
	/**Registration Status**/
	private int regStatus;
	/**Creation Date**/
	private Date creationDate;
	/**Profile Picture**/
	private String profilePic;
	
	public User(){
		
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
	* Get User Name Method
	* 
	* @return username
	**/
	public String getUsername() {
		return username;
	}
	/**
	* Set User Name Method
	* 
	* @param username
	**/
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	* Get Password Method
	* 
	* @return password
	**/
	public String getPassword() {
		return password;
	}
	/**
	* Set Password Method
	* 
	* @param password
	**/
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	* Get Email Method
	* 
	* @return email
	**/
	public String getEmail() {
		return email;
	}
	/**
	* Set Email Method
	* 
	* @param email
	**/
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	* Get Full Name Method
	* 
	* @return fullName
	**/
	public String getFullName() {
		return fullName;
	}
	/**
	* Set Full Name Method
	* 
	* @param fullName
	**/
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	* Get Group Id Method
	* 
	* @return groupId
	**/
	public int getGroupId() {
		return groupId;
	}
	/**
	* Set Group Id Method
	* 
	* @param groupId
	**/
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	/**
	* Get Trust Status Method
	* 
	* @return trustStatus
	**/
	public int getTrustStatus() {
		return trustStatus;
	}
	/**
	* Set Trust Status Method
	* 
	* @param trustStatus
	**/
	public void setTrustStatus(int trustStatus) {
		this.trustStatus = trustStatus;
	}
	/**
	* Get Registration Status Method
	* 
	* @return regStatus
	**/
	public int getRegStatus() {
		return regStatus;
	}
	/**
	* Set Registration Status Method
	* 
	* @param regStatus
	**/
	public void setRegStatus(int regStatus) {
		this.regStatus = regStatus;
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
	* Get Profile Picture Method
	* 
	* @return profilePic
	**/
	public String getProfilePic() {
		return profilePic;
	}
	/**
	* Set Profile Picture Method
	* 
	* @param profilePic
	**/
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
}