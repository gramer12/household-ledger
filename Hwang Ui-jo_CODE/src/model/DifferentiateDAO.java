package model;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DifferentiateDAO {
	String url ="jdbc:mysql://127.0.0.1/test?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true&user=root&password=alcls5dha";
	private Connection conn;
	private ResultSet rs;
	Statement stmt=null;
	
	public DifferentiateDAO() {
		 try {
			 
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection(url);
			 stmt=conn.createStatement();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	}



public void deleteDifferentiate() {
	String SQL = "DELETE FROM 판별";
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		pstmt.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
	
 }

public int insertDifferentiate(Differentiate differentiate) {
	String SQL="INSERT INTO 판별 VALUES (?,?,?)";
	try {
		int i;
		//for(i=0;i<expenses.size();i++) {
			//Expense expense = expenses.get(i);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, differentiate.getID());
			pstmt.setDate(2, Date.valueOf(differentiate.getDate()));
			pstmt.setInt(3, differentiate.getDayExpense());
			pstmt.executeUpdate();
		
		//return i;
			return 0;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
}
public ObservableList<Differentiate> getDifferentiate(){
	String SQL = "SELECT * FROM 판별";
	ObservableList<Differentiate> differentiate = FXCollections.observableArrayList();	
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			Differentiate differentiate1 =new Differentiate(rs.getString(1),rs.getDate(2).toLocalDate(),rs.getInt(3));
			differentiate.add(differentiate1);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return differentiate;
}



public int saveDifferentiate(Differentiate differentiate) {
	/*if(deleteExpense() == -1) {
		return -1;
	}*/
	if(insertDifferentiate(differentiate) == 0) {
		return 0;
	}
	return 1;
}




}