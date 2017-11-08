/**
* UserDAOImpl Class
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
import com.dkaken.admin.model.User;
import com.dkaken.admin.utils.Database;
import com.dkaken.admin.utils.Functions;

public class UserDAOImpl implements UserDAO{

	/**CONN**/
	private static Connection conn = null;
	/**STMT**/
	private static PreparedStatement stmt = null;
	/**RS**/
	private static ResultSet rs = null;
	
	/**
	* Get Admin Method
	* This method is used to get the admin
	* 
	* @param username
	* @param password
	*
	*@return user
	**/
	@Override
	public User getAdmin(Database dbConn, String username, String password) {
		String sha1Password = Functions.encryptPassword(password);
		User user = null;
		String sql = "SELECT " +
								"ID, USERNAME, PASSWORD " +
							"FROM " +
								"USERS " +
							"WHERE " +
								"USERNAME = ? " +
							"AND " +
								"PASSWORD = ? " +
							"AND " +
								"GROUP_ID = 1 " +
							"LIMIT " +
								"1";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, sha1Password);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String uname = rs.getString("USERNAME");
				int uid = rs.getInt("ID");
				
				user = new User();
				user.setUsername(uname);
				user.setId(uid);
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
	* This method is used to get the user
	* 
	* @param id
	*
	*@return user
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
	* Update User Method
	* This method is used to update the user
	* 
	* @param id
	* @param username
	* @param fullName
	* @param email
	* @param newPassword
	* @param fileName
	*
	*@return update result
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
	* Add User Method
	* This method is used to add new user
	* 
	* @param username
	* @param password
	* @param email
	* @param fullName
	* @param fileName
	*
	*@return add result
	**/
	@Override
	public int addUser(Database dbConn, String username, String password,
			String email, String fullName, String fileName) {
		
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
			stmt.setString(4, fullName);
			stmt.setDate(5, sqlDate);
			stmt.setString(6, fileName);
			stmt.setInt(7, 1);

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
	* Get All Users Method
	* This method is used to get all users
	*
	*@return users
	**/
	@Override
	public ArrayList<User> getAllUsers(Database dbConn) {
		User user = null;
		ArrayList<User> users = null;
		String sql = "SELECT * FROM USERS WHERE GROUP_ID != 1 ORDER BY USERS.ID DESC";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			users = new ArrayList<User>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String username = rs.getString("USERNAME");
				String email = rs.getString("EMAIL");
				String fullName = rs.getString("FULL_NAME");
				int trustStatus = rs.getInt("TRUST_STATUS");
				int regStatus = rs.getInt("REG_STATUS");
				java.sql.Date registeredDate = rs.getDate("CREATION_DATE");
				String profilePic = rs.getString("PROFILE_PIC");

				user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setEmail(email);
				user.setFullName(fullName);
				user.setTrustStatus(trustStatus);
				user.setRegStatus(regStatus);
				user.setCreationDate(registeredDate);//
				user.setProfilePic(profilePic);
				users.add(user);
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
		return users;
	}
	
	/**
	* Get All Users Method
	* This method is used to get all users depending on there status
	*
	*@param status
	*
	*@return users
	**/
	@Override
	public ArrayList<User> getAllUsers(Database dbConn, String status) {
		User user = null;
		ArrayList<User> users = null;
		String sql = "SELECT * FROM USERS WHERE GROUP_ID != 1 AND REG_STATUS = 0 ORDER BY USERS.ID DESC";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			users = new ArrayList<User>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String username = rs.getString("USERNAME");
				String email = rs.getString("EMAIL");
				String fullName = rs.getString("FULL_NAME");
				int trustStatus = rs.getInt("TRUST_STATUS");
				int regStatus = rs.getInt("REG_STATUS");
				java.sql.Date registeredDate = rs.getDate("CREATION_DATE");
				String profilePic = rs.getString("PROFILE_PIC");

				user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setEmail(email);
				user.setFullName(fullName);
				user.setTrustStatus(trustStatus);
				user.setRegStatus(regStatus);
				user.setCreationDate(registeredDate);//
				user.setProfilePic(profilePic);
				users.add(user);
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
		return users;
	}

	/**
	* Is Admin Method
	* This method is used to check if user is adming depending on its id
	*
	*@param id
	*
	*@return boolean value
	**/
	@Override
	public boolean isAdmin(Database dbConn, int id) {
		boolean isAdmin = false;
		String sql = "SELECT * USERS WHERE ID = ? AND GROUP_ID = 1";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			// execute select SQL stetement
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				isAdmin = true;
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
		return isAdmin;
	}

	/**
	* Delete User Method
	* This method is used to delete user
	*
	*@param id
	*
	*@return delete result
	**/
	@Override
	public int deleteUser(Database dbConn, int id) {
		String sql = "DELETE FROM USERS WHERE ID = ?";
		
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
	* Approve User Method
	* This method is used to approve user
	*
	*@param id
	*
	*@return approve result
	**/
	@Override
	public int approveUser(Database dbConn, int id) {
		String sql = "UPDATE USERS SET REG_STATUS = 1 WHERE ID = ?";
		
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