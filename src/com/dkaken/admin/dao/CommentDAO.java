/**
* CommentDAO Interface
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.dao;

import java.util.ArrayList;
import com.dkaken.admin.model.Comment;
import com.dkaken.admin.utils.Database;

public interface CommentDAO {
	/**Get All Comments Method**/
	ArrayList<Comment> getAllComments(Database db);
	/**Get All Comments Method**/
	ArrayList<Comment> getAllComments(Database db, String status);
	/**Get Item Comments Method**/
	ArrayList<Comment> getItemComments(Database db, int itemId);
	/**Get Comment Method**/
	Comment getComment(Database db, int id);
	/**Update Comment Method**/
	int updateComment(Database db, int id, String comment);
	/**Delete Comment Method**/
	int deleteComment(Database db, int id);
	/**Approve Comment Method**/
	int approveComment(Database db, int id);
}
