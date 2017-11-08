/**
* CategoryDTO Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.dto;

import java.util.ArrayList;
import com.dkaken.admin.model.Category;
import com.dkaken.admin.utils.Functions;

public class CategoryDTO {

	/**
	* Validate Category Method
	* this method is used to validate the category fields
	* 
	* @param category
	* @param action
	* 
	* @return errors
	**/
	public ArrayList<String> validateCategory(Category category, String action){
		
		ArrayList<String> errors = new ArrayList<String>();
		
		if(action.equals("edit")){
			if(category.getName() == null || category.getName().equals("")){
				errors.add("Name can't be empty.");
			}
			if(!category.getName().equals("") && category.getName().length() < 2){
				errors.add("Name can't be less than 2 letters."); 
			}
			if(category.getName() != null && category.getName().length() > 50){
				errors.add("Name can't be greater than 50 letters.");
			}
			if(category.getNameAr() == null || category.getNameAr().equals("")){
				errors.add("Arabic Name can't be empty.");
			}
			if(!category.getNameAr().equals("") && category.getNameAr().length() < 2){
				errors.add("Arabic Name can't be less than 2 letters.");
			}
			if(category.getNameAr() != null && category.getNameAr().length() > 50){
				errors.add("Arabic Name can't be greater than 50 letters.");
			}
			if(category.getDescription() != null && category.getDescription().length() > 100){
				errors.add("Description can't be greater than 100 letters.");
			}
			if(category.getDescriptionAr() != null && category.getDescriptionAr().length() > 100){
				errors.add("Arabic Description can't be greater than 100 letters.");
			}
			if(!category.getOrdering().equals("") && !(Functions.isNumeric(category.getOrdering()))){
				errors.add("Category order should be numbers.");
			}
			if(!category.getOrdering().equals("") && Functions.isNumeric(category.getOrdering())){
				if( Integer.valueOf(category.getOrdering()) > 9999999 ){
					errors.add("Category order can't be greater than 9999999.");
				}
				if( Integer.valueOf(category.getOrdering()) < 0 ){
					errors.add("Category order can't be less than 0.");
				}
			}
			if(category.getIsVisible() != 0 && category.getIsVisible() != 1){
				errors.add("visibility can't be anything else Yes or No.");
			}
			if(category.getIsCommentAllowed() != 0 && category.getIsCommentAllowed() != 1){
				errors.add("Allowing Comments can't be anything else Yes or No.");
			}
			if(category.getIsAdsAllowed() != 0 && category.getIsAdsAllowed() != 1){
				errors.add("Allowing Ads can't be anything else Yes or No.");
			}
		}else if(action.equals("add")){
			if(category.getName() == null || category.getName().equals("")){
				errors.add("Name can't be empty.");
			}
			if(!category.getName().equals("") && category.getName().length() < 2){
				errors.add("Name can't be less than 2 letters.");
			}
			if(category.getName() != null && category.getName().length() > 50){
				errors.add("Name can't be greater than 50 letters.");
			}
			if(category.getNameAr() == null || category.getNameAr().equals("")){
				errors.add("Arabic Name can't be empty.");
			}
			if(!category.getNameAr().equals("") && category.getNameAr().length() < 2){
				errors.add("Arabic Name can't be less than 2 letters.");
			}
			if(category.getNameAr() != null && category.getNameAr().length() > 50){
				errors.add("Arabic Name can't be greater than 50 letters.");
			}
			if(category.getDescription() != null && category.getDescription().length() > 100){
				errors.add("Description can't be greater than 100 letters.");
			}
			if(category.getDescriptionAr() != null && category.getDescriptionAr().length() > 100){
				errors.add("Arabic Description can't be greater than 100 letters.");
			}
			if(!category.getOrdering().equals("") && !(Functions.isNumeric(category.getOrdering()))){
				errors.add("Category order should be numbers.");
			}
			if(!category.getOrdering().equals("") && Functions.isNumeric(category.getOrdering())){
				if( Integer.valueOf(category.getOrdering()) > 9999999 ){
					errors.add("Category order can't be greater than 9999999.");
				}
			}
			if(category.getIsVisible() != 0 && category.getIsVisible() != 1){
				errors.add("visibility can't be anything else Yes or No.");
			}
			if(category.getIsCommentAllowed() != 0 && category.getIsCommentAllowed() != 1){
				errors.add("Allowing Comments can't be anything else Yes or No.");
			}
			if(category.getIsAdsAllowed() != 0 && category.getIsAdsAllowed() != 1){
				errors.add("Allowing Ads can't be anything else Yes or No.");
			}
		}
		return errors;
	}
}