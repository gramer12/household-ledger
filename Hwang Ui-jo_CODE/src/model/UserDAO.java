package model;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class UserDAO {
	String url ="jdbc:mysql://127.0.0.1/test?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true&user=root&password=alcls5dha";
	private Connection conn;
	private ResultSet rs;
	Statement stmt=null;
	
	public UserDAO() {
		 try {
			 
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection(url);
			 stmt=conn.createStatement();
			
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	}



public int deleteUser() {
	String SQL = "DELETE FROM 회원정보";
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
	
 }

public int insertUser(User user) {
	String SQL="INSERT INTO 회원정보 VALUES (?,?,?,?,?,?,?)";
	try { 
		int i;
		//for(i=0;i<expenses.size();i++) {
			//Expense expense = expenses.get(i);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getFirstNumber());
			pstmt.setString(3, user.getLastNumber());
			pstmt.setString(4, user.getID());
			pstmt.setString(5, user.getPassword());
			pstmt.setString(6, user.getEmail());
			pstmt.setString(7, user.getPhoneNumber());
			pstmt.executeUpdate();
		
		//return i;
			return 0;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
}
public ObservableList<User> getUser(){
	String SQL = "SELECT * FROM 회원정보";
	ObservableList<User> users = FXCollections.observableArrayList();	
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			User user =new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			users.add(user);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return users;
}
public int saveUser(User user) {
	/*if(deleteExpense() == -1) {
		return -1;
	}*/
	if(insertUser(user) == 0) {
		return 0;
	}
	return 1;
}
}