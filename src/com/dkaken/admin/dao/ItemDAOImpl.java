/**
* ItemDAOImpl Class
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
import com.dkaken.admin.model.Item;
import com.dkaken.admin.model.User;
import com.dkaken.admin.utils.Database;

public class ItemDAOImpl implements ItemDAO{
	
	/**CONN**/
	private static Connection conn = null;
	/**STMT**/
	private static PreparedStatement stmt = null;
	/**RS**/
	private static ResultSet rs = null;

	/**
	* Add Item Method
	* This method is used to add new item
	*
	*@param name
	*@param description
	*@param price
	*@param madeIn
	*@param status
	*@param user
	*@param category
	*@param tags
	*@param fileName
	*
	*@return add result
	**/
	@Override
	public int addItem(Database dbConn, String name, String description,
			String price, String madeIn, String status, User user, Category category, String tags, String fileName) {
		
		String sql = "INSERT INTO ITEMS (NAME, DESCRIPTION, PRICE, CREATION_DATE, MADE_IN, STATUS, TAGS, IMAGE, USER_ID, CAT_ID ) VALUES (?,?,?,?,?,?,?,?,?,?)";
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setString(3, price);
			stmt.setDate(4, sqlDate);
			stmt.setString(5, madeIn);
			stmt.setString(6, status);
			stmt.setString(7, tags);
			stmt.setString(8, fileName);
			stmt.setInt(9, user.getId());
			stmt.setInt(10, category.getId());
			
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
	* Get All Items Method
	* This method is used to get all items
	*
	*@return items
	**/
	@Override
	public ArrayList<Item> getAllItems(Database dbConn) {
		Item item = null;
		Category category = null;
		User user = null;
		ArrayList<Item> items = null;
		String sql = "SELECT " +
						"ITEMS.*, " +
						"USERS.USERNAME AS USERNAME, " +
						"CATEGORIES.NAME AS CATEGORY_NAME " +
					"FROM " +
						"ITEMS " +
					"INNER JOIN " +
						"CATEGORIES " +
					"ON " +
						"CATEGORIES.ID = ITEMS.CAT_ID " +
					"INNER JOIN " +
						"USERS " +
					"ON " +
						"USERS.ID = ITEMS.USER_ID " +
					"ORDER BY " +
						"ITEMS.ID DESC";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			items = new ArrayList<Item>();
			while (rs.next()) {
				
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String description = rs.getString("DESCRIPTION");
				String price = rs.getString("PRICE");
				java.sql.Date creationDate = rs.getDate("CREATION_DATE");
				String madeIn = rs.getString("MADE_IN");
				String image = rs.getString("IMAGE");
				String status = rs.getString("STATUS");
				int rate = rs.getInt("RATE");
				int isApproved = rs.getInt("IS_APPROVED");
				String categoryName = rs.getString("CATEGORY_NAME");
				String username = rs.getString("USERNAME");
				String tags = rs.getString("TAGS");

				item = new Item();
				category = new Category();
				user = new User();
				
				item.setId(id);
				item.setName(name);
				item.setDescription(description);
				item.setPrice(price);
				item.setCreationDate(creationDate);
				item.setMadeIn(madeIn);
				item.setImage(image);
				item.setStatus(status);
				item.setRate(rate);
				item.setIsApproved(isApproved);
				category.setName(categoryName);
				item.setCategory(category);
				user.setUsername(username);
				item.setUser(user);
				item.setTags(tags);
				
				items.add(item);
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
		return items;
	}
	
	/**
	* Get All Items Method
	* This method is used to get all items depending on there status
	* 
	* @param status
	*
	*@return items
	**/
	@Override
	public ArrayList<Item> getAllItems(Database dbConn, String status) {
		Item item = null;
		Category category = null;
		User user = null;
		ArrayList<Item> items = null;
		String sql = "SELECT " +
						"ITEMS.*, " +
						"USERS.USERNAME AS USERNAME, " +
						"CATEGORIES.NAME AS CATEGORY_NAME " +
					"FROM " +
						"ITEMS " +
					"INNER JOIN " +
						"CATEGORIES " +
					"ON " +
						"CATEGORIES.ID = ITEMS.CAT_ID " +
					"INNER JOIN " +
						"USERS " +
					"ON " +
						"USERS.ID = ITEMS.USER_ID " +
					"WHERE " +
						"IS_APPROVED = 0 " +
					"ORDER BY " +
						"ITEMS.ID DESC";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			items = new ArrayList<Item>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String description = rs.getString("DESCRIPTION");
				String price = rs.getString("PRICE");
				java.sql.Date creationDate = rs.getDate("CREATION_DATE");
				String madeIn = rs.getString("MADE_IN");
				String image = rs.getString("IMAGE");
				String statusVal = rs.getString("STATUS");
				int rate = rs.getInt("RATE");
				int isApproved = rs.getInt("IS_APPROVED");
				String categoryName = rs.getString("CATEGORY_NAME");
				String username = rs.getString("USERNAME");
				String tags = rs.getString("TAGS");

				item = new Item();
				category = new Category();
				user = new User();
				
				item.setId(id);
				item.setName(name);
				item.setDescription(description);
				item.setPrice(price);
				item.setCreationDate(creationDate);
				item.setMadeIn(madeIn);
				item.setImage(image);
				item.setStatus(statusVal);
				item.setRate(rate);
				item.setIsApproved(isApproved);
				category.setName(categoryName);
				item.setCategory(category);
				user.setUsername(username);
				item.setUser(user);
				item.setTags(tags);
				
				items.add(item);
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
		return items;
	}

	/**
	* Get Item Method
	* This method is used to get a single item
	* 
	* @param id
	*
	*@return item
	**/
	@Override
	public Item getItem(Database dbConn, int id) {
		Item item = null;
		Category category = null;
		User user = null;
		String sql = "SELECT * FROM ITEMS WHERE ID = ? LIMIT 1";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int itemId = rs.getInt("ID");
				String name = rs.getString("NAME");
				String description = rs.getString("DESCRIPTION");
				String price = rs.getString("PRICE");
				java.sql.Date creationDate = rs.getDate("CREATION_DATE");
				String madeIn = rs.getString("MADE_IN");
				String image = rs.getString("IMAGE");
				String status = rs.getString("STATUS");
				int rate = rs.getInt("RATE");
				int isApproved = rs.getInt("IS_APPROVED");
				int catId = rs.getInt("CAT_ID");
				int userId = rs.getInt("USER_ID");
				String tags = rs.getString("TAGS");
				
				item = new Item();
				user = new User();
				category = new Category();
				
				item.setId(itemId);
				item.setName(name);
				item.setDescription(description);
				item.setPrice(price);
				item.setCreationDate(creationDate);
				item.setMadeIn(madeIn);
				item.setImage(image);
				item.setStatus(status);
				item.setRate(rate);
				item.setIsApproved(isApproved);
				category.setId(catId);
				item.setCategory(category);
				user.setId(userId);
				item.setUser(user);
				item.setTags(tags);
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
		return item;
	}

	/**
	* Update Item Method
	* This method is used to update item
	* 
	* @param id
	* @param name
	* @param description
	* @param price
	* @param madeIn
	* @param status
	* @param user
	* @param category
	* @param tags
	* @param fileName
	*
	*@return update result
	**/
	@Override
	public int updateItem(Database dbConn, int id, String name, String description,
			String price, String madeIn, String status, User user,
			Category category, String tags, String fileName) {
		
		String sql = "UPDATE " +
						"ITEMS " +
					"SET " +
						"NAME = ?, " +
						"DESCRIPTION = ?, " +
						"PRICE = ?, " +
						"MADE_IN = ?,  " +
						"STATUS = ?, " +
						"CAT_ID = ?, " +
						"USER_ID = ?, " +
						"TAGS = ?, " +
						"IMAGE = ? " +
					"WHERE " +
						"ID = ?";
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setString(3, price);
			stmt.setString(4, madeIn);
			stmt.setString(5, status);
			stmt.setInt(6, category.getId());
			stmt.setInt(7, user.getId());
			stmt.setString(8, tags);
			stmt.setString(9, fileName);
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
	* Delete Item Method
	* This method is used to delete item
	* 
	* @param id
	*
	*@return delete result
	**/
	@Override
	public int deleteItem(Database dbConn, int id) {
		String sql = "DELETE FROM ITEMS WHERE ID = ?";
		
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
	* Approve Item Method
	* This method is used to approve item
	* 
	* @param id
	*
	*@return approve result
	**/
	@Override
	public int approveItem(Database dbConn, int id) {
		String sql = "UPDATE ITEMS SET IS_APPROVED = 1 WHERE ID = ?";
		
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
}