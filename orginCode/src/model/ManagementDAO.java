package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ManagementDAO {
	String url ="jdbc:mysql://127.0.0.1/?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true&user=root&password=123123";
	private Connection conn;
	private ResultSet rs;
	Statement stmt=null;
	
	public ManagementDAO() {
		 try {
			 
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection(url);
			 stmt=conn.createStatement();
			 String useopensourcedb="use opensourcedb";
			 stmt.executeUpdate(useopensourcedb);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	}



public int deleteManagement() {
	String SQL = "DELETE FROM 包府";
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
	
 }

public int insertManagement(Management management) {
	String SQL="INSERT INTO 包府 VALUES (?,?,?,?)";
	try {
		int i;
		//for(i=0;i<expenses.size();i++) {
			//Expense expense = expenses.get(i);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, management.getID());
			pstmt.setDate(2,Date.valueOf(LocalDate.of(management.getDate().getYear(), management.getDate().getMonth(), 1)));
			pstmt.setInt(3, management.getMonthExpense());
			pstmt.setInt(4, management.getMonthIncome());
			pstmt.executeUpdate();
		
		//return i;
			return 0;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
}
public ObservableList<Management> getManagement(){
	String SQL = "SELECT * FROM 包府";
	ObservableList<Management> management = FXCollections.observableArrayList();	
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			Management management1 =new Management(rs.getString(1),YearMonth.from(rs.getDate(2).toLocalDate()),rs.getInt(3),rs.getInt(4));
			management.add(management1);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return management;
}
public int saveManagement(Management management) {
	/*if(deleteExpense() == -1) {
		return -1;
	}*/
	if(insertManagement(management) == 0) {
		return 0;
	}
	return 1;
}




}