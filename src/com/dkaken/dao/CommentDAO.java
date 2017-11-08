/**
* CommentDAO Interface
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.dao;

import java.util.ArrayList;
import com.dkaken.model.Comment;
import com.dkaken.admin.utils.Database;
import com.dkaken.model.Item;
import com.dkaken.model.User;

public interface CommentDAO {
	/**Add Comment Method**/
	int addComment(Database db, String comment, Item item, User user);
	/**Get Item Comments Method**/
	ArrayList<Comment> getItemComments(Database db, int itemId);
}