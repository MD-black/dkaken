/**
* CategoryDAOImpl Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.dkaken.admin.utils.Database;
import com.dkaken.model.Category;

public class CategoryDAOImpl implements CategoryDAO{
	
	/**CONN**/
	private static Connection conn = null;
	/**STMT**/
	private static PreparedStatement stmt = null;
	/**RS**/
	private static ResultSet rs = null;
	
	/**
	* Get All Categories Method
	* This method is used to get all the categories.
	* 
	* @return categories
	**/
	@Override
	public ArrayList<Category> getAllCategories(Database dbConn) {
		Category category = null;
		ArrayList<Category> categories = null;
		String sql = "SELECT * FROM CATEGORIES ORDER BY ID DESC ";
		
		try { 
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			categories = new ArrayList<Category>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");

				category = new Category();
				
				category.setId(id);
				category.setName(name);
				
				categories.add(category);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return categories;
	}	

	/**
	* Get Parent Categories Method
	* This method is used to get all the parent categories.
	* 
	* @return parent categories
	**/
	@Override
	public ArrayList<Category> getPrentCategories(Database dbConn) {
		Category category = null;
		ArrayList<Category> categories = null;
		String sql = "SELECT * FROM CATEGORIES WHERE PARENT = 0";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			categories = new ArrayList<Category>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String nameAr = rs.getString("NAME_AR");
				String description = rs.getString("DESCRIPTION");
				String descriptionAr = rs.getString("DESCRIPTION_AR");
				int parent = rs.getInt("PARENT");
				int ordering = rs.getInt("ORDERING");
				int isVisible = rs.getInt("IS_VISIBLE");
				int isCommentAllowed = rs.getInt("IS_COMMENT_ALLOWED");
				int isAdsAllowed = rs.getInt("IS_ADS_ALLOWED");

				category = new Category();
				category.setId(id);
				category.setName(name);
				category.setNameAr(nameAr);
				category.setDescription(description);
				category.setDescriptionAr(descriptionAr);
				category.setParent(parent);
				category.setOrdering(ordering);
				category.setIsVisible(isVisible);
				category.setIsCommentAllowed(isCommentAllowed);
				category.setIsAdsAllowed(isAdsAllowed);
				categories.add(category);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return categories;
	}
}