/**
* Functions Class
* 
* @author  Mohammed Darwish
* @version 1.0
* @since   2017-11-07
**/
package com.dkaken.utils;

import com.dkaken.admin.utils.Database;
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
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Part;
import com.dkaken.model.User;
import com.dkaken.model.Category;
import com.dkaken.model.Item;

public class Functions {
	
	/**VALID EMAIL ADDRESS REGEX**/
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
	    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	/**PROFILE PIC SAVE DIR**/
	private static final String PROFILE_PIC_SAVE_DIR = "uploads\\profile_pics";
	/**ADMIN PROFILE PIC SAVE DIR**/
	private static final String ADMIN_PROFILE_PIC_SAVE_DIR = "admin\\uploads\\profile_pics";
	/**ITEM PIC SAVE DIR**/
	private static final String ITEM_PIC_SAVE_DIR = "uploads\\item_pics";
	/**ADMIN ITEM PIC SAVE DIR**/
	private static final String ADMIN_ITEM_PIC_SAVE_DIR = "admin\\uploads\\item_pics";

	/**CONN**/
	private static Connection conn = null;
	/**STMT**/
	private static PreparedStatement stmt = null;
	/**RS**/
	private static ResultSet rs = null;	
	
	/**
	* Replace Method
	* This method is used to replace string second value with first value.
	* 
	* @param firstVal
	* @param secondVal
	* @param in
	* 
	* @return replaced string
	**/
	public static String replace(String firstVal, String secondVal, String in){
		in = in.replaceAll(firstVal, secondVal);
		return in;
	}
	
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
	* Is Numeric Method
	* This method is used to check if the String argument is a number.
	* 
	* @param str
	* 
	* @return Boolean value
	**/
	public static boolean isNumeric(String str){  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	/**
	* Get Parent Categories Method
	* This method is used to get parent categories.
	* 
	* @return parent categories
	**/
	public static ArrayList<Category> getParentCategories(Database dbConn) {
		Category category = null;
		ArrayList<Category> categories = null;
		String sql = "SELECT * FROM CATEGORIES WHERE PARENT = 0 ORDER BY ID ASC ";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			categories = new ArrayList<Category>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String nameAr = rs.getString("NAME_AR");
				
				category = new Category();
				
				category.setId(id);
				category.setName(name);
				category.setNameAr(nameAr);
				
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
	* Get Category Items Method
	* This method is used to get items inside the selected category.
	* 
	* @param categoryId
	* 
	* @return category items
	**/
	public static ArrayList<Item> getCategoryItems(Database dbConn, int categoryId){
		Item item = null;
		User user = null;
		Category category = null;
		ArrayList<Item> items = null;
		String sql = "SELECT " +
						"ITEMS.*, " +
						"USERS.PROFILE_PIC " +
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
						"ITEMS.CAT_ID = ? " +
					"AND " +
						"ITEMS.IS_APPROVED = 1 " +
					"UNION " +
					"SELECT " +
						"ITEMS.*, " +
						"USERS.PROFILE_PIC " +
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
						"CATEGORIES.PARENT = ? " +
					"AND " +
						"ITEMS.IS_APPROVED = 1 " +
					"ORDER BY " +
						"ID " +
					"DESC";
						
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			stmt.setInt(2, categoryId);

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
				int catId = rs.getInt("CAT_ID");
				int userId = rs.getInt("USER_ID");
				String userProfilePic = rs.getString("PROFILE_PIC");
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
				category.setId(catId);
				item.setCategory(category);
				user.setId(userId);
				user.setProfilePic(userProfilePic);
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
	* Get Child Categories For Users Method
	* This method is used to get child categories.
	* 
	* @param obj
	* @param parentId
	* @param limit
	* 
	* @return child categories
	**/
	public static Object[] getChildCategoriesForUsers(Object obj, Integer parentId, Integer limit){
		Category category = null;
		ArrayList<Category> categories = null;
		String sql = "SELECT * FROM CATEGORIES WHERE PARENT = ? ORDER BY ORDERING ";
		if(limit != null){
			sql += "LIMIT "+limit;
		}
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
		Object[] objects = categories.toArray();
		
		return objects;
	}
	
	/**
	* Get Items Method
	* This method is used to get items.
	* 
	* @return items
	**/
	public static ArrayList<Item> getItems(Database dbConn){
		Item item = null;
		User user = null;
		Category category = null;
		ArrayList<Item> items = null;
		String sql = "SELECT " +
						"ITEMS.*, " +
						"USERS.PROFILE_PIC " +
					"FROM " +
						"ITEMS " +
					"INNER JOIN " +
						"USERS " +
					"ON " +
						"USERS.ID = ITEMS.USER_ID " +
					"WHERE " +
						"IS_APPROVED = 1 " +
					"ORDER BY " +
						"ITEMS.ID " +
					"DESC";
						
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
				int catId = rs.getInt("CAT_ID");
				int userId = rs.getInt("USER_ID");
				String userProfilePic = rs.getString("PROFILE_PIC");
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
				category.setId(catId);
				item.setCategory(category);
				user.setId(userId);
				user.setProfilePic(userProfilePic);
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
	* Check User Status Method
	* This method is used to check the user status.
	* 
	* @param username
	* 
	* @return Boolean value
	**/
	public static boolean checkUserStatus(Database dbConn, String username){
		boolean isRegistered = false;
		User user = null;
		String sql = "SELECT USERNAME, REG_STATUS FROM USERS WHERE USERNAME = ? AND REG_STATUS = 0";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String uname = rs.getString("USERNAME");
				int regStatus = rs.getInt("REG_STATUS");
				
				user = new User();
				
				user.setUsername(uname);
				user.setRegStatus(regStatus);
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
		
		if( user == null ){
			isRegistered = true;
		}
		
		return isRegistered;
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
	* Get Category Items By Tag Name Method
	* This method is used to get category items by tag name.
	* 
	* @param tagName
	* 
	* @return items
	**/
	public static ArrayList<Item> getCategoryItemsByTagName(Database dbConn, String tagName){
		Item item = null;
		User user = null;
		Category category = null;
		ArrayList<Item> items = null;
		String sql = "SELECT ITEMS.*, USERS.PROFILE_PIC FROM ITEMS INNER JOIN USERS ON USERS.ID = ITEMS.USER_ID WHERE TAGS LIKE '%"+tagName+"%' AND IS_APPROVED = 1 ORDER BY ID DESC";
						
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
				int catId = rs.getInt("CAT_ID");
				int userId = rs.getInt("USER_ID");
				String tags = rs.getString("TAGS");
				String profilePic = rs.getString("PROFILE_PIC");
				
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
				category.setId(catId);
				item.setCategory(category);
				user.setId(userId);
				user.setProfilePic(profilePic); 
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
	* Get Tags Method
	* This method is used to get tags for the item.
	* 
	* @param item
	* 
	* @return tags
	**/
	public static List<String> getTags(Item item){
		String tags = item.getTags();
		String[] tagsStringArray = tags.split(",");
		List<String> tagsList = Arrays.asList(tagsStringArray);
		List<String> cleanTagsList = new ArrayList<String>();
		for(String tag : tagsList){
			tag = tag.trim();
			tag = tag.toLowerCase();
			cleanTagsList.add(tag);
		}
		return cleanTagsList;
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
        String savePath2 = appPath + File.separator + ADMIN_PROFILE_PIC_SAVE_DIR;
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
        String savePath2 = appPath + File.separator + ADMIN_ITEM_PIC_SAVE_DIR;
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
		if(fileName == null || fileName.equals("")){
			return null;
		}     
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
       try  
       {  
          fileType = Files.probeContentType(file.toPath());  
       }  
       catch (IOException ioException)  
       {  
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