/**
* ItemDTO Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.dto;

import java.util.ArrayList;
import com.dkaken.admin.model.Item;
import com.dkaken.admin.utils.Functions;

public class ItemDTO {
	
	/**
	* Validate Item Method
	* this method is used to validate the item fields
	* 
	* @param item
	* @param action
	* 
	* @return errors
	**/
	public ArrayList<String> validateItem(Item item, String action){	
		ArrayList<String> errors = new ArrayList<String>();
		
		if(action.equals("edit")){
			if(item.getName() == null || item.getName().equals("")){
				errors.add("Name can't be empty.");
			}
			if(!item.getName().equals("") && item.getName().length() < 2){
				errors.add("Name can't be less than 2 letters.");
			}
			if(!item.getName().equals("") && item.getName().length() > 50){
				errors.add("Name can't be greater than 50 letters.");
			}
			if(item.getDescription() == null || item.getDescription().equals("")){
				errors.add("Description can't be empty.");
			}
			if(!item.getDescription().equals("") && item.getDescription().length() < 10){
				errors.add("Description can't be less than 10 letters.");
			}
			if(!item.getDescription().equals("") && item.getDescription().length() > 1000){
				errors.add("Description can't be greater than 1000 letters.");
			}
			if(item.getPrice() == null || item.getPrice().equals("")){
				errors.add("Price can't be empty.");
			}
			if(!item.getPrice().equals("") && !(Functions.isNumeric(item.getPrice()))){
				errors.add("Price should be numbers.");
			}
			if(!item.getPrice().equals("") && Functions.isNumeric(item.getPrice())){
				if( Integer.valueOf(item.getPrice()) > 9999999 ){
					errors.add("Price can't be greater than 9999999.");
				}
				if( Integer.valueOf(item.getPrice()) < 0 ){
					errors.add("Price can't be less than 0.");
				}
			}
			if(item.getMadeIn() == null || item.getMadeIn().equals("")){
				errors.add("Country can't be empty.");
			}
			if(Integer.valueOf(item.getStatus()) == 0){
				errors.add("Please choose the status for the item.");
			}
			if(item.getUser().getId() == 0){
				errors.add("Please choose a user for this item.");
			}
			if(item.getCategory().getId() == 0){
				errors.add("Please choose a category for this item.");
			}
		}else if(action.equals("add")){
			if(item.getName() == null || item.getName().equals("")){
				errors.add("Name can't be empty.");
			}
			if(!item.getName().equals("") && item.getName().length() < 2){
				errors.add("Name can't be less than 2 letters.");
			}
			if(!item.getName().equals("") && item.getName().length() > 50){
				errors.add("Name can't be greater than 50 letters.");
			}
			if(item.getDescription() == null || item.getDescription().equals("")){
				errors.add("Description can't be empty.");
			}
			if(!item.getDescription().equals("") && item.getDescription().length() < 10){
				errors.add("Description can't be less than 10 letters.");
			}
			if(!item.getDescription().equals("") && item.getDescription().length() > 1000){
				errors.add("Description can't be greater than 1000 letters.");
			}
			if(item.getPrice() == null || item.getPrice().equals("")){
				errors.add("Price can't be empty.");
			}
			if(!item.getPrice().equals("") && !(Functions.isNumeric(item.getPrice()))){
				errors.add("Price should be numbers.");
			}
			if(!item.getPrice().equals("") && Functions.isNumeric(item.getPrice())){
				if( Integer.valueOf(item.getPrice()) > 9999999 ){
					errors.add("Price can't be greater than 9999999.");
				}
				if( Integer.valueOf(item.getPrice()) < 0 ){
					errors.add("Price can't be less than 0.");
				}
			}
			if(item.getMadeIn() == null || item.getMadeIn().equals("")){
				errors.add("Country can't be empty.");
			}
			if(Integer.valueOf(item.getStatus()) == 0){
				errors.add("Please choose the status for the item.");
			}
			if(item.getUser().getId() == 0){
				errors.add("Please choose a user for this item.");
			}
			if(item.getCategory().getId() == 0){
				errors.add("Please choose a category for this item.");
			}
		}
		return errors;
	}
}