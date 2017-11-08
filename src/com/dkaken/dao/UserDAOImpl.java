/**
* UserDAOImpl Class
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
import com.dkaken.model.Category;
import com.dkaken.admin.utils.Database;
import com.dkaken.model.Comment;
import com.dkaken.model.Item;
import com.dkaken.model.User;
import com.dkaken.utils.Functions;

public class UserDAOImpl implements UserDAO{

	/**CONN**/
	private static Connection conn = null;
	/**STMT**/
	private static PreparedStatement stmt = null;
	/**RS**/
	private static ResultSet rs = null;	
	
	/**
	* Get User Method
	* This method is used to get user.
	* 
	* @param username
	* @param password
	* 
	* @return user
	**/
	@Override
	public User getUser(Database dbConn, String username, String password) {
		String sha1Password = Functions.encryptPassword(password);
		User user = null;
		String sql = "SELECT " +
						"ID, USERNAME, PASSWORD " +
					"FROM " +
						"USERS " +
					"WHERE " +
						"USERNAME = ? " +
					"AND " +
						"PASSWORD = ? ";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, sha1Password);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String uname = rs.getString("USERNAME");
				
				user = new User();
				
				user.setId(id);
				user.setUsername(uname);
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
		return user;
	}

	/**
	* Get User Method
	* This method is used to get user depending on the username.
	* 
	* @param username
	* 
	* @return user
	**/
	@Override
	public User getUser(Database dbConn, String username) {
		User user = null;
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String uName = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				String email = rs.getString("EMAIL");
				String fullName = rs.getString("FULL_NAME");
				int groupId = rs.getInt("GROUP_ID");
				int trustStatus = rs.getInt("TRUST_STATUS");
				int regStatus = rs.getInt("REG_STATUS");
				java.sql.Date creationDate = rs.getDate("CREATION_DATE");
				String profilePic = rs.getString("PROFILE_PIC");
				
				user = new User();
				
				user.setId(id);
				user.setUsername(uName);
				user.setPassword(password);
				user.setEmail(email);
				user.setEmail(email);
				user.setFullName(fullName);
				user.setGroupId(groupId);
				user.setTrustStatus(trustStatus);
				user.setRegStatus(regStatus);
				user.setCreationDate(creationDate);
				user.setProfilePic(profilePic);
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
		return user;
	}
	
	/**
	* Get User Method
	* This method is used to get user depending on the user id.
	* 
	* @param id
	* 
	* @return user
	**/
	@Override
	public User getUser(Database dbConn, int id) {
		User user = null;
		String sql = "SELECT * FROM USERS WHERE ID = ? LIMIT 1";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt("ID");
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				String email = rs.getString("EMAIL");
				String fullName = rs.getString("FULL_NAME");
				int groupId = rs.getInt("GROUP_ID");
				int trustStatus = rs.getInt("TRUST_STATUS");
				int regStatus = rs.getInt("REG_STATUS");
				String profilePic = rs.getString("PROFILE_PIC");
				
				user = new User();
				user.setId(userId);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setFullName(fullName);
				user.setGroupId(groupId);
				user.setTrustStatus(trustStatus);
				user.setRegStatus(regStatus);
				user.setProfilePic(profilePic);
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
		return user;
	}
	
	/**
	* Get User Profile Picture Method
	* This method is used to get user profile picture.
	* 
	* @param id
	* 
	* @return profile picture
	**/
	@Override
	public String getUserProfilePic(Database dbConn, int id) {
		String sql = "SELECT PROFILE_PIC FROM USERS WHERE ID = ?";
		String profilePic = null;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				profilePic = rs.getString("PROFILE_PIC");
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
		return profilePic;
	}
	
	/**
	* Update User Method
	* This method is used to Update User.
	* 
	* @param id
	* @param username
	* @param fullName
	* @param email
	* @param newPassword
	* @param fileName
	*   
	* 
	* @return update result
	**/
	@Override
	public int updateUser(Database dbConn, int id, String username, String fullName, String email, String newPassword, String fileName) {
		String sql = "UPDATE USERS SET USERNAME = ?, FULL_NAME = ?, EMAIL = ?, PASSWORD = ?, PROFILE_PIC = ? WHERE ID = ?";
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, fullName);
			stmt.setString(3, email);
			stmt.setString(4, newPassword);
			stmt.setString(5, fileName);
			stmt.setInt(6, id);

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
	* Update User Profile Picture Method
	* This method is used to Update User Profile Picture.
	* 
	* @param id
	* @param fileName
	*   
	* 
	* @return update user profile picture result
	**/
	@Override
	public int updateUserProfilePic(Database dbConn, int id, String fileName) {
		String sql = "UPDATE USERS SET PROFILE_PIC = ? WHERE ID = ?";
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fileName);
			stmt.setInt(2, id);

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
	* Get User Items Method
	* This method is used get user items.
	* 
	* @param userId
	*   
	* 
	* @return items
	**/
	@Override
	public ArrayList<Item> getUserItems(Database dbConn, int userId) {
		Item item = null;
		Category category = null;
		User user = null;
		ArrayList<Item> userItems = null;
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
					"ORDER BY " +
						"ITEMS.ID DESC";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();
			userItems = new ArrayList<Item>();
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
				
				userItems.add(item);
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
		return userItems;
	}

	/**
	* Get User Comments Method
	* This method is used get user comments.
	* 
	* @param userId
	*   
	* 
	* @return comments
	**/
	@Override
	public ArrayList<Comment> getUserComments(Database dbConn, int userId) {
		Comment comment = null;
		Item item = null;
		ArrayList<Comment> userComments = null;
		String sql = "SELECT " +
						"COMMENTS.*, " +
						"ITEMS.NAME AS ITEM_NAME " +
					"FROM " +
						"COMMENTS " +
					"INNER JOIN " +
						"ITEMS " +
					"ON " +
						"ITEMS.ID = COMMENTS.ITEM_ID " +
					"WHERE " +
						"COMMENTS.USER_ID = ? " +
					"ORDER BY " +
						"COMMENTS.ID DESC";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();
			userComments = new ArrayList<Comment>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String commentVal = rs.getString("COMMENT");
				int status = rs.getInt("STATUS");
				java.sql.Date creationDate = rs.getDate("CREATION_DATE");
				String itemName = rs.getString("ITEM_NAME");
				
				
				comment = new Comment();
				item = new Item();
				
				comment.setId(id);
				comment.setComment(commentVal);
				comment.setStatus(status);
				comment.setCreationDate(creationDate);
				item.setName(itemName);
				comment.setItem(item);
				
				userComments.add(comment);
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
		return userComments;
	}

	/**
	* Register User Method
	* This method is used to register user.
	* 
	* @param username
	* @param password
	*   
	* 
	* @return register user result
	**/
	@Override
	public int registerUser(Database dbConn, String username, String password,
			String email) {
		String sql = "INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, FULL_NAME, CREATION_DATE,PROFILE_PIC, REG_STATUS) VALUES (?,?,?,?,?,?,?)";
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, email);
			stmt.setString(4, "");
			stmt.setDate(5, sqlDate);
			stmt.setString(6, "");
			stmt.setInt(7, 0);

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