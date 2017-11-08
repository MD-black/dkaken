/**
* CommentDTO Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.dto;

import java.util.ArrayList;
import com.dkaken.admin.model.Comment;

public class CommentDTO {

	/**
	* Validate Comment Method
	* this method is used to validate the comment fields
	* 
	* @param comment
	* @param action
	* 
	* @return errors
	**/
	public ArrayList<String> validateComment(Comment comment, String action){
		
		ArrayList<String> errors = new ArrayList<String>();
		
		if(action.equals("edit")){
			if(comment.getComment() == null || comment.getComment().equals("")){
				errors.add("Comment can't be empty.");
			}
			if(!comment.getComment().equals("") && comment.getComment().length() > 1000){
				errors.add("Comment can't be greater than 1000 letters.");
			}
		}
		return errors;
	}
}