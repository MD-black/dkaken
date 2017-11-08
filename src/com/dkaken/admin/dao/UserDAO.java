/**
* UserDAO Interface
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.dao;

import java.util.ArrayList;
import com.dkaken.admin.model.User;
import com.dkaken.admin.utils.Database;

public interface UserDAO {
	/**Get Admin Method**/
	User getAdmin(Database db, String username, String password);
	/**Is Admin Method**/
	boolean isAdmin(Database db, int id);
	/**Get User Method**/
	User getUser(Database db, int id);
	/**Update User Method**/
	int updateUser(Database db, int id, String username, String fullName, String email, String newPassword, String fileName);
	/**Add User Method**/
	int addUser(Database db, String username, String password, String email, String fullName, String fileName);
	/**Delete User Method**/
	int deleteUser(Database db, int id);
	/**Approve User Method**/
	int approveUser(Database db, int id);
	/**Get All Users Method**/
	ArrayList<User> getAllUsers(Database db);
	/**Get All Users Method**/
	ArrayList<User> getAllUsers(Database db, String status);
}
