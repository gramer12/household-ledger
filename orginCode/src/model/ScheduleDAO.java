package model;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ScheduleDAO {
	String url ="jdbc:mysql://127.0.0.1/?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true&user=root&password=123123";
	private Connection conn;
	private ResultSet rs;
	Statement stmt=null;
	
	public ScheduleDAO() {
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



public int deleteSchedule() {
	String SQL = "DELETE FROM 일정";
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
	
 }

public int insertSchedule(Schedule schedule) {
	String SQL="INSERT INTO 일정 VALUES (?,?,?,?)";
	try {
		int i;
		//for(i=0;i<expenses.size();i++) {
			//Expense expense = expenses.get(i);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, schedule.getID());
			pstmt.setDate(2, Date.valueOf(schedule.getDate()));
			pstmt.setString(3, schedule.getName());
			pstmt.setString(4, schedule.getContent());
			pstmt.executeUpdate();
		
		//return i;
			return 0;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
}
public ObservableList<Schedule> getSchedule(){
	String SQL = "SELECT * FROM 일정";
	ObservableList<Schedule> schedules = FXCollections.observableArrayList();	
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			Schedule schedule =new Schedule(rs.getString(1),rs.getDate(2).toLocalDate(),rs.getString(3),rs.getString(4));
			schedules.add(schedule);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return schedules;
}
public int saveSchedule(Schedule schedule) {
	/*if(deleteExpense() == -1) {
		return -1;
	}*/
	if(insertSchedule(schedule) == 0) {
		return 0;
	}
	return 1;
}
}