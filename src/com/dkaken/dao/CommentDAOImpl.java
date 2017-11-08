/**
* CommentDAOImpl Class
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
import com.dkaken.model.Comment;
import com.dkaken.model.Item;
import com.dkaken.model.User;

public class CommentDAOImpl implements CommentDAO{
	
	/**CONN**/
	private static Connection conn = null;
	/**STMT**/
	private static PreparedStatement stmt = null;
	/**RS**/
	private static ResultSet rs = null;	

	/**
	* Add Comment Method
	* 
	* @param commentVal
	* @param item
	* @param user
	* 
	* @return add result
	**/
	@Override
	public int addComment(Database dbConn, String commentVal, Item item, User user) {
		String sql = "INSERT INTO COMMENTS (COMMENT, STATUS, CREATION_DATE, ITEM_ID, USER_ID) VALUES (?,?,?,?,?)";
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		int result = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, commentVal);
			stmt.setInt(2, 0);
			stmt.setDate(3, sqlDate);
			stmt.setInt(4, item.getId());
			stmt.setInt(5, user.getId());

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
	* 
	* @param itemId
	* 
	* @return item comments
	**/
	@Override
	public ArrayList<Comment> getItemComments(Database dbConn, int itemId) {
		Comment comment = null;
		User user = null;
		ArrayList<Comment> comments = null;
		String sql = "SELECT " +
						"COMMENTS.*, " +
						"USERS.USERNAME AS USERNAME, " +
						"USERS.PROFILE_PIC AS PROFILE_PIC " +
					"FROM " +
						"COMMENTS " +
					"INNER JOIN " +
						"USERS " +
					"ON " +
						"USERS.ID = COMMENTS.USER_ID " +
					"WHERE " +
						"COMMENTS.ITEM_ID = ? " +
					"AND " +
						"COMMENTS.STATUS = 1";
		
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
				String userProfilePic = rs.getString("PROFILE_PIC");

				comment = new Comment();
				user = new User();
				
				comment.setId(id);
				comment.setComment(commentVal);
				comment.setStatus(status);
				comment.setCreationDate(creationDate);
				user.setUsername(username);
				user.setProfilePic(userProfilePic);
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