/**
* UserDAO Interface
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.dao;

import java.util.ArrayList;
import com.dkaken.admin.utils.Database;
import com.dkaken.model.Comment;
import com.dkaken.model.Item;
import com.dkaken.model.User;

public interface UserDAO {
	/**Get User Method**/
	User getUser(Database db, String username, String password);
	/**Get User Method**/
	User getUser(Database db, String username);
	/**Get User Method**/
	User getUser(Database db, int id);
	/**Update User Method**/
	int updateUser(Database db, int id, String username, String fullName, String email, String newPassword, String fileName);
	/**Update User Profile Picture Method**/
	int updateUserProfilePic(Database db, int id, String profilePic);
	/**Get User Profile Picture Method**/
	String getUserProfilePic(Database db, int id);
	/**Get User Items Method**/
	ArrayList<Item> getUserItems(Database db, int id);
	/**Get User Comments Method**/
	ArrayList<Comment> getUserComments(Database db, int id);
	/**Register User Method**/
	int registerUser(Database db, String username, String password, String email);
}
