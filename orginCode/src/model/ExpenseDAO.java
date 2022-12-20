package model;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ExpenseDAO {
	String url ="jdbc:mysql://127.0.0.1/?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true&user=root&password=123123";
	private Connection conn;
	private ResultSet rs;
	Statement stmt=null;
	
	public ExpenseDAO() {
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



public int deleteExpense() {
	String SQL = "DELETE FROM 지출";
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
	
 }

public int insertExpense(Expense expense) {
	String SQL="INSERT INTO 지출 VALUES (?,?,?,?,?,?)";
	try {
		int i;
		//for(i=0;i<expenses.size();i++) {
			//Expense expense = expenses.get(i);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, expense.getID());
			pstmt.setDate(2, Date.valueOf(expense.getDate()));
			pstmt.setString(3, expense.getType());
			pstmt.setString(4, expense.getName());
			pstmt.setInt(5, expense.getExpense());
			pstmt.setString(6, expense.getContent());
			pstmt.executeUpdate();
		
		//return i;
			return 0;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
}
public ObservableList<Expense> getExpense(){
	String SQL = "SELECT * FROM 지출";
	ObservableList<Expense> expenses = FXCollections.observableArrayList();	
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			Expense expense =new Expense(rs.getString(1),rs.getDate(2).toLocalDate(),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
			expenses.add(expense);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return expenses;
}
public int saveExpense(Expense expense) {
	/*if(deleteExpense() == -1) {
		return -1;
	}*/
	if(insertExpense(expense) == 0) {
		return 0;
	}
	return 1;
}




}