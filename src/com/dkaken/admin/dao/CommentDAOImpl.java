/**
* CommentDAOImpl Class
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
import com.dkaken.admin.model.Comment;
import com.dkaken.admin.model.Item;
import com.dkaken.admin.model.User;
import com.dkaken.admin.utils.Database;

public class CommentDAOImpl implements CommentDAO{
	
	/**CONN**/
	private static Connection conn = null;
	/**STMT**/
	private static PreparedStatement stmt = null;
	/**RS**/
	private static ResultSet rs = null;

	/**
	* Get All Comments Method
	* This method is used to get all the comments
	*
	*@return comments
	**/
	@Override
	public ArrayList<Comment> getAllComments(Database dbConn) {
		
		Comment comment = null;
		Item item = null;
		User user = null;
		ArrayList<Comment> comments = null;
		String sql = "SELECT " +
						"COMMENTS.*, " +
						"ITEMS.NAME AS ITEM_NAME, " +
						"USERS.USERNAME AS USERNAME " +
					"FROM " +
						"COMMENTS " +
					"INNER JOIN " +
						"ITEMS " +
					"ON " +
						"ITEMS.ID = COMMENTS.ITEM_ID " +
					"INNER JOIN " +
						"USERS " +
					"ON " +
						"USERS.ID = COMMENTS.USER_ID " +
					"ORDER BY " +
						"COMMENTS.ID DESC";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			comments = new ArrayList<Comment>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String commentVal = rs.getString("COMMENT");
				int status = rs.getInt("STATUS");
				java.sql.Date creationDate = rs.getDate("CREATION_DATE");
				String itemName = rs.getString("ITEM_NAME");
				String username = rs.getString("USERNAME");
				

				comment = new Comment();
				item = new Item();
				user = new User();
				
				comment.setId(id);
				comment.setComment(commentVal);
				comment.setStatus(status);
				comment.setCreationDate(creationDate);
				item.setName(itemName);
				comment.setItem(item);
				user.setUsername(username);
				comment.setUser(user);
				comments.add(comment);
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
		return comments;
	}

	/**
	* Get All Comments Method
	* This method is used to get all the comments depending on the comment status
	*
	*@param status
	*
	*@return comments
	**/
	@Override
	public ArrayList<Comment> getAllComments(Database dbConn, String status) {
		Comment comment = null;
		Item item = null;
		User user = null;
		ArrayList<Comment> comments = null;
		String sql = "SELECT " +
						"COMMENTS.*, " +
						"ITEMS.NAME AS ITEM_NAME, " +
						"USERS.USERNAME AS USERNAME " +
					"FROM " +
						"COMMENTS " +
					"INNER JOIN " +
						"ITEMS " +
					"ON " +
						"ITEMS.ID = COMMENTS.ITEM_ID " +
					"INNER JOIN " +
						"USERS " +
					"ON " +
						"USERS.ID = COMMENTS.USER_ID " +
					"WHERE " +
						"COMMENTS.STATUS = 0 " +
					"ORDER BY " +
						"COMMENTS.ID DESC";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			comments = new ArrayList<Comment>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String commentVal = rs.getString("COMMENT");
				int statusVal = rs.getInt("STATUS");
				java.sql.Date creationDate = rs.getDate("CREATION_DATE");
				String itemName = rs.getString("ITEM_NAME");
				String username = rs.getString("USERNAME");
				

				comment = new Comment();
				item = new Item();
				user = new User();
				
				comment.setId(id);
				comment.setComment(commentVal);
				comment.setStatus(statusVal);
				comment.setCreationDate(creationDate);
				item.setName(itemName);
				comment.setItem(item);
				user.setUsername(username);
				comment.setUser(user);
				comments.add(comment);
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
		return comments;
	}

	/**
	* Get Comment Method
	* This method is used to get a single comment
	*
	*@param id
	*
	*@return comment
	**/
	@Override
	public Comment getComment(Database dbConn, int id) {
		Comment comment = null;
		String sql = "SELECT * FROM COMMENTS WHERE ID = ?";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int commentId = rs.getInt("ID");
				String commentVal = rs.getString("COMMENT");
				
				
				comment = new Comment();
				comment.setId(commentId);
				comment.setComment(commentVal);
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
		return comment;
	}

	/**
	* Update Comment Method
	* This method is used to update comment
	*
	*@param id
	*@param comment
	*
	*@return update result
	**/
	@Override
	public int updateComment(Database dbConn, int id, String comment) {
		String sql = "UPDATE COMMENTS SET COMMENT = ? WHERE ID = ?";
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comment);
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
	* Delete Comment Method
	* This method is used to update comment
	*
	*@param id
	*
	*@return delete result
	**/
	@Override
	public int deleteComment(Database dbConn, int id) {
		String sql = "DELETE FROM COMMENTS WHERE ID = ?";
		
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
	* Approve Comment Method
	* This method is used to approve comment
	*
	*@param id
	*
	*@return approve result
	**/
	@Override
	public int approveComment(Database dbConn, int id) {
	String sql = "UPDATE COMMENTS SET STATUS = 1 WHERE ID = ?";
		
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
	* Get Item Comments Method
	* This method is used to get comments for an item
	*
	*@param itemId
	*
	*@return item comments
	**/
	@Override
	public ArrayList<Comment> getItemComments(Database dbConn, int itemId) {
		Comment comment = null;
		User user = null;
		ArrayList<Comment> comments = null;
		String sql = "SELECT " +
						"COMMENTS.*, " +
						"USERS.USERNAME AS USERNAME " +
					"FROM " +
						"COMMENTS " +
					"INNER JOIN " +
						"USERS " +
					"ON " +
						"USERS.ID = COMMENTS.USER_ID " +
					"WHERE " +
						"COMMENTS.ITEM_ID = ?";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemId);

			ResultSet rs = stmt.executeQuery();
			comments = new ArrayList<Comment>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String commentVal = rs.getString("COMMENT");
				int status = rs.getInt("STATUS");
				java.sql.Date creationDate = rs.getDate("CREATION_DATE");
				String username = rs.getString("USERNAME");
				

				comment = new Comment();
				user = new User();
				
				comment.setId(id);
				comment.setComment(commentVal);
				comment.setStatus(status);
				comment.setCreationDate(creationDate);
				user.setUsername(username);
				comment.setUser(user);
				comments.add(comment);
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
		return comments;
	}
}