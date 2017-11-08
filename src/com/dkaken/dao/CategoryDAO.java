/**
* CategoryDAO Interface
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.dao;

import java.util.ArrayList;
import com.dkaken.model.Category;
import com.dkaken.admin.utils.Database;

public interface CategoryDAO {
	/**Get All Categories Method**/
	ArrayList<Category> getAllCategories(Database db);
	/**Get Parent Categories Method**/
	ArrayList<Category> getPrentCategories(Database db);
}