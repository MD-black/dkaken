/**
* Functions Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/

package com.dkaken.admin.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Part;
import com.dkaken.admin.model.Comment;
import com.dkaken.admin.model.Item;
import com.dkaken.admin.model.User;
import com.dkaken.admin.model.Category;

public class Functions {
	/**VALID EMAIL ADDRESS REGEX**/
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
	    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	/**PROFILE PIC SAVE DIR**/
	private static final String PROFILE_PIC_SAVE_DIR = "admin\\uploads\\profile_pics";
	/**USER PROFILE PIC SAVE DIR**/
	private static final String USER_PROFILE_PIC_SAVE_DIR = "uploads\\profile_pics";
	/**ITEM PIC SAVE DIR**/
	private static final String ITEM_PIC_SAVE_DIR = "admin\\uploads\\item_pics";
	/**USER ITEM PIC SAVE DIR**/
	private static final String USER_ITEM_PIC_SAVE_DIR = "uploads\\item_pics";
	
	/**CONN**/
	private static Connection conn = null;
	/**STMT**/
	private static PreparedStatement stmt = null;
	/**RS**/
	private static ResultSet rs = null;
	
	/**
	* Encrypt Password Method
	* This method is used to convert password to sha1 password.
	* 
	* @param password
	* 
	* @return sha1 password
	**/
	public static String encryptPassword(String password){
	    String sha1 = "";
	    try{
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e){
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e){
	        e.printStackTrace();
	    }
	    return sha1;
	}
	
	/**
	* Byte To Hex Method
	* This is a helper method to the encryptPassword method .
	* it is used to convert byte to hex.
	* 
	* @param hash
	* 
	* @return hex result
	**/
	private static String byteToHex(final byte[] hash){
	    Formatter formatter = new Formatter();
	    for (byte b : hash){
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	
	/**
	* Is Numeric Method
	* This method is used to check if the String argument is a number.
	* 
	* @param str
	* 
	* @return Boolean value
	**/
	public static boolean isNumeric(String str){  
	  try{  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe){  
	    return false;  
	  }  
	  return true;  
	}

	/**
	* Validate Method
	* This method is validate the email address.
	* 
	* @param emailStr
	* 
	* @return Boolean value
	**/
	public static boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	        return matcher.find();
	}
	
	/**
	* Check Method
	* This method is used to check if the selector exists in the DB.
	* 
	* @param select
	* @param from
	* @param value
	* 
	* @return exist result
	**/
	public static int check(Database dbConn, String select, String from, Object value){
		String sql = "SELECT "+select+" FROM "+from+" WHERE "+select+" = ?";
		int count = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			if(value instanceof String){
				stmt.setString(1, String.valueOf(value));
			}else if(value instanceof Integer){
				stmt.setInt(1, Integer.valueOf( String.valueOf(value) ));
			}
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				++count;
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
		return count;
	}
	
	/**
	* Check User On Update Method
	* This method is used to check if the user exists in case of update.
	* 
	* @param select
	* @param from
	* @param value
	* @param id
	* 
	* @return exist result
	**/
	public static int checkUserOnUpdate(Database dbConn, String select, String from, Object value, int id){
		String sql = "SELECT "+select+" FROM "+from+" WHERE "+select+" = ? AND ID != ?";
		int count = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			if(value instanceof String){
				stmt.setString(1, String.valueOf(value));
			}else if(value instanceof Integer){
				stmt.setInt(1, Integer.valueOf( String.valueOf(value) ));
			}
			stmt.setInt(2, id);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				++count;
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
		return count;
	}
	
	/**
	* Count Rows Method
	* This method is used to count the selected table rows.
	* 
	* @param select
	* @param table
	* 
	* @return rows number
	**/
	public static int countRows(Database db, String select , String table){
		String sql = "SELECT COUNT("+select+") FROM "+table ;
		int count = 0;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
	
	/**
	* Count Rows Method
	* This method is used to count the selected table rows under some condition.
	* 
	* @param select
	* @param table
	* @param where
	* 
	* @return rows number
	**/
	public static int countRows(Database db, String select , String table, String where){
		String sql = "SELECT COUNT("+select+") FROM "+table +" "+ where;
		int count = 0;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
	
	
	/**
	* Get Latest Method
	* This method is used to latest inserted records from the selected table.
	* 
	* @param table
	* @param order
	* @param limit
	* 
	* @return latest rows
	**/
	public static ArrayList<Object> getLatest(Database db , String table, String order, int limit){
		String sql = "SELECT * FROM "+table+" ORDER BY "+order+" DESC LIMIT "+limit;
		if(table.equals("COMMENTS")){
			sql = "SELECT COMMENTS.*, USERS.USERNAME FROM "+table+" INNER JOIN USERS ON USERS.ID = COMMENTS.USER_ID ORDER BY  "+order+" DESC LIMIT "+limit;
		}
		User user = null;
		Item item = null;
		Comment comment = null;
		ArrayList<Object> users = new ArrayList<Object>();
		ArrayList<Object> items = new ArrayList<Object>();
		ArrayList<Object> comments = new ArrayList<Object>();
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if(table.equals("USERS")){
					user = new User();
					int id = rs.getInt("ID");
					String username = rs.getString("USERNAME");
					String email = rs.getString("EMAIL");
					String fullName = rs.getString("FULL_NAME");
					int groupId = rs.getInt("GROUP_ID");
					int trustStatus = rs.getInt("TRUST_STATUS");
					int regStatus = rs.getInt("REG_STATUS");
					java.util.Date registeredDate = rs.getDate("CREATION_DATE");
					String profilePic = rs.getString("PROFILE_PIC");
					
					user.setId(id);
					user.setUsername(username);
					user.setEmail(email);
					user.setFullName(fullName);
					user.setGroupId(groupId);
					user.setTrustStatus(trustStatus);
					user.setRegStatus(regStatus);
					user.setCreationDate(registeredDate);
					user.setProfilePic(profilePic);
					
					users.add(user);
				}else if(table.equals("ITEMS")){
					item = new Item();
					int id = rs.getInt("ID");
					String name = rs.getString("NAME");
					int isApproved = rs.getInt("IS_APPROVED");
					item.setId(id);
					item.setName(name);
					item.setIsApproved(isApproved);
					items.add(item);
				}else if(table.equals("COMMENTS")){
					comment = new Comment();
					int id = rs.getInt("ID");
					String commentVal = rs.getString("COMMENT");
					String username = rs.getString("USERNAME");
					int status = rs.getInt("STATUS");
					
					comment.setId(id);
					comment.setComment(commentVal);
					comment.setStatus(status);
					user = new User();
					user.setUsername(username);
					comment.setUser(user);
					
					comments.add(comment);
				}
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
		if(table.equals("USERS")){
			return users;
		}else if(table.equals("ITEMS")){
			return items;
		}else if(table.equals("COMMENTS")){
			return comments;
		}
		return null;
	}
	
	/**
	* Get Biggest Order Method
	* This method is used to get the biggest order for the category.
	* 
	* @return biggest order
	**/
	public static int getBiggestOrder(Database dbConn){
		String sql = "SELECT MAX(ORDERING) AS MAX_ORDER FROM CATEGORIES";
		int maxOrder = 0;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				maxOrder = rs.getInt(1);
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
		return maxOrder;
	}
	
	/**
	* Get Child Categories Method
	* This method is used to get the child categories for a parent category.
	* 
	* @param parentId
	* 
	* @return child categories
	**/
	public static Object[] getChildCategories(Object obj, Integer parentId){
		Category category = null;
		ArrayList<Category> categories = null;
		String sql = "SELECT * FROM CATEGORIES WHERE PARENT = ? ORDER BY ORDERING ";
		Database dbConn = (Database)obj;
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, parentId);
			
			ResultSet rs = stmt.executeQuery();
			categories = new ArrayList<Category>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String nameAr = rs.getString("NAME_AR");
				String description = rs.getString("DESCRIPTION");
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
		Object[] objects = categories.toArray();
		
		return objects;
	}
	
	/**
	* Upload Profile Picture Method
	* This method is used to upload profile picture.
	* 
	* @param part
	* 
	* @return file name
	**/
	public static String uploadProfilePic(Part part){
		// gets absolute path of the web application
        String appPath = "C:\\Users\\user\\workspace\\jsp_servlets_course\\tests\\dkaken\\WebContent";
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + PROFILE_PIC_SAVE_DIR;
        String savePath2 = appPath + File.separator + USER_PROFILE_PIC_SAVE_DIR;
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        File fileSaveDir2 = new File(savePath2);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        if (!fileSaveDir2.exists()) {
            fileSaveDir2.mkdir();
        }
        
        /*we can use this for loop in case there are more than one file to upload at the same request*/
        /*for (Part part : request.getParts()) {
	        String fileName = extractFileName(part);
	        // refines the fileName in case it is an absolute path
	        fileName = new File(fileName).getName();
	        part.write(savePath + File.separator + fileName);
    	}*/
        
        String fileName = extractFileName(part);
        
        Random rand = new Random();
		fileName = rand.nextInt(1000000000) + "_" + rand.nextInt(1000000000) + "_" + fileName;
		
		try {
			part.write(savePath + File.separator + fileName);
			part.write(savePath2 + File.separator + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	/**
	* Upload Item Picture Method
	* This method is used to upload item picture.
	* 
	* @param part
	* 
	* @return file name
	**/
	public static String uploadItemPic(Part part){
		// gets absolute path of the web application
        String appPath = "C:\\Users\\user\\workspace\\jsp_servlets_course\\tests\\dkaken\\WebContent";
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + ITEM_PIC_SAVE_DIR;
        String savePath2 = appPath + File.separator + USER_ITEM_PIC_SAVE_DIR;
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        File fileSaveDir2 = new File(savePath2);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        if (!fileSaveDir2.exists()) {
            fileSaveDir2.mkdir();
        }
        
        /*we can use this for loop in case there are more than one file to upload at the same request*/
        /*for (Part part : request.getParts()) {
	        String fileName = extractFileName(part);
	        // refines the fileName in case it is an absolute path
	        fileName = new File(fileName).getName();
	        part.write(savePath + File.separator + fileName);
    	}*/
        
        String fileName = extractFileName(part);
        
        Random rand = new Random();
		fileName = rand.nextInt(1000000000) + "_" + rand.nextInt(1000000000) + "_" + fileName;
		
		try {
			part.write(savePath + File.separator + fileName);
			part.write(savePath2 + File.separator + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	/**
	* Validate Part Method
	* This method is used to validate the part object.
	* 
	* @param part
	* 
	* @return errors
	**/
	public static ArrayList<String> validatePart(Part part){
		ArrayList<String> partErrors = new ArrayList<String>();
	
		String fileName = extractFileName(part);
		long fileSize = part.getSize(); 
		String fileExtention = getFileExtension(fileName);
		String[] allowedImageExtentionsList = {"jpeg","jpg","png","gif"}; 
		
		if(fileName == null || fileName.equals("")){
			partErrors.add("You must upload your profile picture.");
        }
        
        if(fileSize > (1024 * 1024 * 4)){
        	partErrors.add("Your picture size can't be more than 4 MB.");
        }
		
		if(fileName != null && !fileName.equals("") && !(Arrays.asList(allowedImageExtentionsList).contains(fileExtention))){
			partErrors.add("the extention "+fileExtention+" is not allowed for your profile picture, please choose one of the following extentions (jpeg,jpg,png,gif).");
        }
		return partErrors;
	}
	
	/**
	* Extract File Name Method
	* Extracts file name from HTTP header content-disposition
	* 
	* @param part
	* 
	* @return file name
	**/
    private static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
    
    /** 
     * Identify File Type Using Files Probe Content Type Method
     * This method is used to Identify file type of file with provided path and name 
     * using JDK 7's Files.probeContentType(Path). 
     * 
     * @param fileName Name of file whose type is desired. 
     * @return String representing identified type of file with provided name. 
     */  
    public static String identifyFileTypeUsingFilesProbeContentType(final String fileName){  
       String fileType = "Undetermined";  
       final File file = new File(fileName);  
       try{  
          fileType = Files.probeContentType(file.toPath());  
       }  
       catch (IOException ioException){  
          System.out.println(  
               "ERROR: Unable to determine file type for " + fileName  
                  + " due to exception " + ioException);  
       }  
       return fileType;  
    }
    
    /**
	* Get File Extension Method
	* This method is used to get the file extension from the file name.
	* 
	* @param fileName
	* 
	* @return file extension
	**/
    private static String getFileExtension(String fileName) {
        try {
        	fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        	fileName = fileName.toLowerCase();
            return fileName;
        } catch (Exception e) {
            return "";
        }
    }
}