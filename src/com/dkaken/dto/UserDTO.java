/**
* UserDTO Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.dto;

import java.util.ArrayList;
import com.dkaken.model.User;
import com.dkaken.utils.Functions;

public class UserDTO {

	/**
	* Validate User Method
	* This method is used to validate user.
	* 
	* @param user
	* @param action
	* 
	* @return errors
	**/
	public ArrayList<String> validateUser(User user, String action){
		ArrayList<String> errors = new ArrayList<String>();
		
		if(action.equals("login")){
			if(user.getUsername() == null || user.getUsername().equals("")){
				errors.add("Username can't be empty.");
			}
			if(!user.getUsername().equals("") && user.getUsername().length() < 5){
				errors.add("Username can't be less than 5 letters.");
			}
			if(!user.getUsername().equals("") && user.getUsername().length() > 20){
				errors.add("Username can't be greater than 20 letters.");
			}
			if(user.getPassword() == null || user.getPassword().equals("")){
				errors.add("Password can't be empty.");
			}
			if( !user.getPassword().equals("") && user.getPassword().length() < 5 ){
				errors.add("Password can't be less than 5 letters.");
			}
			if( !user.getPassword().equals("") && user.getPassword().length() > 20 ){
				errors.add("Password can't be greater than 20 letters.");
			}
		}else if(action.equals("signup")){
			if(user.getUsername() == null || user.getUsername().equals("")){
				errors.add("Username can't be empty.");
			}
			if(!user.getUsername().equals("") && user.getUsername().length() < 5){
				errors.add("Username can't be less than 5 letters.");
			}
			if(!user.getUsername().equals("") && user.getUsername().length() > 20){
				errors.add("Username can't be greater than 20 letters.");
			}
			if(user.getPassword() == null || user.getPassword().equals("")){
				errors.add("Password can't be empty.");
			}
			if( !user.getPassword().equals("") && user.getPassword().length() < 5 ){
				errors.add("Password can't be less than 5 letters.");
			}
			if( !user.getPassword().equals("") && user.getPassword().length() > 20 ){
				errors.add("Password can't be greater than 20 letters.");
			}
			if(user.getEmail() == null || user.getEmail().equals("")){
				errors.add("Email can't be empty.");
			}
			if(!user.getEmail().equals("") && !Functions.validate(user.getEmail())){
				errors.add("Please enter a valid email address.");
			}
		}else if(action.equals("edit")){
			if(user.getUsername() == null || user.getUsername().equals("")){
				errors.add("Username can't be empty.");
			}
			if(!user.getUsername().equals("") && user.getUsername().length() < 5){
				errors.add("Username can't be less than 5 letters.");
			}
			if(!user.getUsername().equals("") && user.getUsername().length() > 20){
				errors.add("Username can't be greater than 20 letters.");
			}
			if( !user.getPassword().equals("") && user.getPassword().length() < 5 ){
				errors.add("Password can't be less than 5 letters.");
			}
			if( !user.getPassword().equals("") && user.getPassword().length() > 20 ){
				errors.add("Password can't be greater than 20 letters.");
			}
			if(user.getEmail() == null || user.getEmail().equals("")){
				errors.add("Email can't be empty.");
			}
			if(!user.getEmail().equals("") && !Functions.validate(user.getEmail())){
				errors.add("Please enter a valid email address.");
			}
			if(user.getFullName() == null || user.getFullName().equals("")){
				errors.add("Full name can't be empty.");
			}
			if(!user.getFullName().equals("") && user.getFullName().length() < 10 ){
				errors.add("Full name can't be less than 10 letters.");
			}
			if(!user.getFullName().equals("") && user.getFullName().length() > 100 ){
				errors.add("Full name can't be greater than 100 letters.");
			}
		}
		return errors;
	}
}