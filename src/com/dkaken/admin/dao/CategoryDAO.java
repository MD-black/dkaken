/**
* CategoryDAO Interface
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.dao;

import java.util.ArrayList;
import com.dkaken.admin.model.Category;
import com.dkaken.admin.utils.Database;

public interface CategoryDAO {
	/**Add Category Method**/
	int addCategory(Database db, String name, String nameAr, String description, String descriptionAr, int parent, int ordering, int isVisible, int isCommentAllowed, int isAdsAllowed);
	/**Get All Categories Method**/
	ArrayList<Category> getAllCategories(Database db, String order);
	/**Get Parent Categories Method**/
	ArrayList<Category> getPrentCategories(Database db);
	/**Get Category Method**/
	Category getCategory(Database db, int id);
	/**Update Category Method**/
	int updateCategory(Database db, int id, String name, String nameAr, String description, String descriptionAr, int parent, int ordering, int isVisible, int isCommentAllowed, int isAdsAllowed);
	/**Delete Category Method**/
	int deleteCategory(Database db, int id);
}
