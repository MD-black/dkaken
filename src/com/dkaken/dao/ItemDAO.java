/**
* ItemDAO Interface
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.dao;

import com.dkaken.model.Item;
import com.dkaken.admin.utils.Database;

public interface ItemDAO {
	/**Update Item Method**/
	int updateItem(Database db,int itemId, String name, String description, String price, String madeIn, String status,String tags);
	/**Add Item Image Method**/
	int addItemImage(Database db, int userId, int catId, String fileName);
	/*String getItemImage(Database db, int userId);*/
	/**Get Latest User Item Method**/
	Item getLatestUserItem(Database db, int userId, int catId);
	/**Get Item Method**/
	Item getItem(Database db, int id);
}