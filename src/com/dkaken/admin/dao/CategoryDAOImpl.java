/**
* CategoryDAOImpl Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.dkaken.admin.model.Category;
import com.dkaken.admin.utils.Database;

public class CategoryDAOImpl implements CategoryDAO{
	
	/**CONN**/
	private static Connection conn = null;
	/**STMT**/
	private static PreparedStatement stmt = null;
	/**RS**/
	private static ResultSet rs = null;

	/**
	* Add Category Method
	* This method is used to add new category
	* 
	*@param name
	*@param nameAr
	*@param description
	*@param descriptionAr
	*@param ordering
	*@param isVisible
	*@param isCommentAllowed
	*@param isAdsAllowed 
	*
	*@return add result
	**/
	@Override
	public int addCategory(Database dbConn, String name,String nameAr, String description, String descriptionAr, int parent, 
			int ordering, int isVisible, int isCommentAllowed, int isAdsAllowed) {
		
		String sql = "INSERT INTO CATEGORIES (NAME, NAME_AR, DESCRIPTION, DESCRIPTION_AR, ORDERING, IS_VISIBLE, IS_COMMENT_ALLOWED,IS_ADS_ALLOWED, PARENT) VALUES (?,?,?,?,?,?,?,?,?)";
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, nameAr);
			stmt.setString(3, description);
			stmt.setString(4, descriptionAr);
			stmt.setInt(5, ordering);
			stmt.setInt(6, isVisible);
			stmt.setInt(7, isCommentAllowed);
			stmt.setInt(8, isAdsAllowed);
			stmt.setInt(9, parent);

			result = stmt.executeUpdate();
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
		return result;
	}

	/**
	* Get All Categories Method
	* This method is used to get all the categories
	* 
	*@param order
	*
	*@return categories
	**/
	@Override
	public ArrayList<Category> getAllCategories(Database dbConn, String order) {
		Category category = null;
		ArrayList<Category> categories = null;
		String sql = "SELECT * FROM CATEGORIES WHERE PARENT = 0 ORDER BY ORDERING "+order;
		
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
				category.setOrdering(String.valueOf(ordering));
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

	/**
	* Get Category Method
	* This method is used to get category
	* 
	*@param id
	*
	*@return category
	**/
	@Override
	public Category getCategory(Database dbConn, int id) {
		Category category = null;
		String sql = "SELECT * FROM CATEGORIES WHERE ID = ? LIMIT 1";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int categoryId = rs.getInt("ID");
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
				category.setId(categoryId);
				category.setName(name);
				category.setNameAr(nameAr);
				category.setDescription(description);
				category.setDescriptionAr(descriptionAr);
				category.setParent(parent);
				category.setOrdering(String.valueOf(ordering));
				category.setIsVisible(isVisible);
				category.setIsCommentAllowed(isCommentAllowed);
				category.setIsAdsAllowed(isAdsAllowed);
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
		return category;
	}

	/**
	* Update Category Method
	* This method is used to update category
	* 
	*@param id
	*@param name
	*@param nameAr
	*@param description
	*@param descriptionAr
	*@param parent
	*@param ordering
	*@param isVisible
	*@param isCommentAllowed
	*@param isAdsAllowed
	*
	*@return update result
	**/
	@Override
	public int updateCategory(Database dbConn, int id, String name, String nameAr,
			String description, String descriptionAr, int parent, int ordering, int isVisible,
			int isCommentAllowed, int isAdsAllowed) {
		String sql = "UPDATE " +
						"CATEGORIES " +
					 "SET " +
					 	"NAME = ?, " +
					 	"NAME_AR = ?, " +
					 	"DESCRIPTION = ?, " +
					 	"DESCRIPTION_AR = ?, " +
					 	"PARENT = ?, " +
					 	"ORDERING = ?, " +
					 	"IS_VISIBLE = ?, " +
					 	"IS_COMMENT_ALLOWED = ?, " +
					 	"IS_ADS_ALLOWED = ? " +
					 "WHERE " +
					 	"ID = ?";
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, nameAr);
			stmt.setString(3, description);
			stmt.setString(4, descriptionAr);
			stmt.setInt(5, parent);
			stmt.setInt(6, ordering);
			stmt.setInt(7, isVisible);
			stmt.setInt(8, isCommentAllowed);
			stmt.setInt(9, isAdsAllowed);
			stmt.setInt(10, id);

			result = stmt.executeUpdate();
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
		return result;
	}

	/**
	* Delete Category Method
	* This method is used to delete category
	* 
	*@param id
	*
	*@return delete result
	**/
	@Override
	public int deleteCategory(Database dbConn, int id) {
		String sql = "DELETE FROM CATEGORIES WHERE ID = ?";
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			result = stmt.executeUpdate();
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
		return result;
	}

	/**
	* Get Prent Categories Method
	* This method is used to get all the parent categories
	*
	*@return parent categories
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
				category.setOrdering(String.valueOf(ordering));
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