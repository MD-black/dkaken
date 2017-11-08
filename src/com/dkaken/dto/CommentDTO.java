/**
* CommentDTO Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.dto;

import java.util.ArrayList;
import com.dkaken.model.Comment;

public class CommentDTO {
	
	/**
	* Validate Comment Method
	* This method is used to validate comment.
	* 
	* @param comment
	* 
	* @return errors
	**/
	public ArrayList<String> validateComment(Comment comment){
		ArrayList<String> errors = new ArrayList<String>();
		
		if(comment.getComment() == null || comment.getComment().equals("")){
			errors.add("Comment can't be empty.");
		}
		if(!comment.getComment().equals("") && comment.getComment().length() > 1000){
			errors.add("Comment can't be greater than 1000 letters.");
		}
		if(comment.getItem().getId() == 0){
			errors.add("There is no item to add this comment.");
		}
		if(comment.getUser().getId() == 0){
			errors.add("There is no user to add this comment.");
		}
		return errors;
	}
}