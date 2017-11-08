/**
* ItemDAO Interface
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.dao;

import java.util.ArrayList;
import com.dkaken.admin.model.Category;
import com.dkaken.admin.model.Item;
import com.dkaken.admin.model.User;
import com.dkaken.admin.utils.Database;

public interface ItemDAO {
	/**Add Item Method**/
	int addItem(Database db, String name, String description, String price, String madeIn, String status, User user, Category category, String tags, String fileName);
	/**Get All Items Method**/
	ArrayList<Item> getAllItems(Database db);
	/**Get All Items Method**/
	ArrayList<Item> getAllItems(Database db, String status);
	/**Get Item Method**/
	Item getItem(Database db, int id);
	/**Update Item Method**/
	int updateItem(Database db, int id, String name, String description, String price, String madeIn, String status, User user, Category category,String tags, String fileName);
	/**Delete Item Method**/
	int deleteItem(Database db, int id);
	/**Approve Item Method**/
	int approveItem(Database db, int id);
}