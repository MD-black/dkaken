/**
* ItemDAOImpl Class
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
import com.dkaken.model.Category;
import com.dkaken.model.User;
import com.dkaken.model.Item;
import com.dkaken.admin.utils.Database;

public class ItemDAOImpl implements ItemDAO{
	
	/**CONN**/
	private static Connection conn = null;
	/**STMT**/
	private static PreparedStatement stmt = null;
	/**RS**/
	private static ResultSet rs = null;

	/**
	* Update Item Method
	* 
	* @param itemId
	* @param name
	* @param description
	* @param price
	* @param madeIn
	* @param status
	* @param tags
	* 
	* @return update result
	**/
	@Override
	public int updateItem(Database dbConn, int itemId, String name, String description,
			String price, String madeIn, String status, String tags) {
		String sql = " UPDATE ITEMS SET NAME = ?, DESCRIPTION = ?, PRICE = ?, CREATION_DATE = ?, MADE_IN = ?, STATUS = ?, TAGS = ? WHERE ID = ? ";
		
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
			stmt.setInt(8, itemId);

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
	* Add Item Image Method
	* 
	* @param userId
	* @param catId
	* @param fileName
	* 
	* @return add result
	**/
	@Override
	public int addItemImage(Database dbConn, int userId, int catId, String fileName){
		String sql = "INSERT INTO ITEMS (IMAGE, USER_ID, CAT_ID) VALUES (?,?,?)";
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fileName);
			stmt.setInt(2, userId);
			stmt.setInt(3, catId);

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
	
	/*@Override
	public String getItemImage(Database dbConn, int id) {
		
		String sql = "SELECT IMAGE FROM ITEMS WHERE USER_ID = ";
		
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
				String categoryName = rs.getString("CATEGORY_NAME");
				int userId = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
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
				category.setName(categoryName);
				item.setCategory(category);
				user.setId(userId);
				user.setUsername(username);
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
	}*/
	
	
	/**
	* Get Latest User Item Method
	* 
	* @param userId
	* @param catId
	* 
	* @return latest item for the selected user
	**/
	@Override
	public Item getLatestUserItem(Database dbConn, int userId, int catId) {
		Item item = null;
		Category category = null;
		User user = null;
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
						"ITEMS.USER_ID = ? " +
					"AND " +
						"ITEMS.CAT_ID = ? " +
					"ORDER BY " +
						"ITEMS.ID " +
					"DESC " +
						"LIMIT 1";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, catId);

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
				int cId = rs.getInt("CAT_ID");
				String categoryName = rs.getString("CATEGORY_NAME");
				int uId = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
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
				category.setId(cId);
				category.setName(categoryName);
				item.setCategory(category);
				user.setId(uId);
				user.setUsername(username);
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
	* Get Item Method
	* This method is used to get item depending on the id.
	* 
	* @param id
	* 
	* @return item
	**/
	@Override
	public Item getItem(Database dbConn, int id) {
		Item item = null;
		Category category = null;
		User user = null;
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
						"ITEMS.ID = ? " +
					"AND " +
						"ITEMS.IS_APPROVED = 1";
		
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
				String categoryName = rs.getString("CATEGORY_NAME");
				int userId = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
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
				category.setName(categoryName);
				item.setCategory(category);
				user.setId(userId);
				user.setUsername(username);
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
}