package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GoalMoneyDAO {
	String url ="jdbc:mysql://127.0.0.1/?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true&user=root&password=123123";
	private Connection conn;
	private ResultSet rs;
	Statement stmt=null;
	
	public GoalMoneyDAO() {
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



public int deleteGoalMoney() {
	String SQL = "DELETE FROM 목표금액";
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
	
 }


public int insertGoalMoney(GoalMoney goalMoney) {
	
	String SQL="INSERT INTO 목표금액 VALUES (?,?,?,?,?,?,?,?,?)";
	try {
		int i;
		//for(i=0;i<expenses.size();i++) {
			//Expense expense = expenses.get(i);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, goalMoney.getID());
			pstmt.setDate(2, Date.valueOf(LocalDate.of(goalMoney.getDate().getYear(), goalMoney.getDate().getMonthValue(), 15)));
			pstmt.setInt(3, goalMoney.getTraffic());
			pstmt.setInt(4, goalMoney.getFood());
			pstmt.setInt(5, goalMoney.getLife());
			pstmt.setInt(6, goalMoney.getMedical());
			pstmt.setInt(7, goalMoney.getPleasure());
			pstmt.setInt(8, goalMoney.getGuitar());
			pstmt.setInt(9, goalMoney.getTotal());
			pstmt.executeUpdate();
		
		//return i;
			return 0;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
}
public ObservableList<GoalMoney> getGoalMoney(){
	String SQL = "SELECT * FROM 목표금액";
	ObservableList<GoalMoney> monthGoalMoney = FXCollections.observableArrayList();	
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			GoalMoney goalMoney =new GoalMoney(rs.getString(1),YearMonth.from(rs.getDate(2).toLocalDate()),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9));
			monthGoalMoney.add(goalMoney);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return monthGoalMoney;
}
public int saveGoalMoney(GoalMoney goalMoney) {
	/*if(deleteExpense() == -1) {
		return -1;
	}*/
	if(insertGoalMoney(goalMoney) == 0) {
		return 0;
	}
	return 1;
}




}