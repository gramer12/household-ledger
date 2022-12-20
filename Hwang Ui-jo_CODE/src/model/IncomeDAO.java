package model;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class IncomeDAO {
	String url ="jdbc:mysql://127.0.0.1/test?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true&user=root&password=alcls5dha";
	private Connection conn;
	private ResultSet rs;
	Statement stmt=null;
	
	public IncomeDAO() {
		 try {
			 
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection(url);
			 stmt=conn.createStatement();
		
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	}



public int deleteIncome() {
	String SQL = "DELETE FROM 수입";
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
	
 }

public int insertIncome(Income income) {
	String SQL="INSERT INTO 수입 VALUES (?,?,?,?)";
	try {
		int i;
		//for(i=0;i<incomes.size();i++) {
			//Income income = incomes.get(i);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, income.getID());
			pstmt.setDate(2, Date.valueOf(income.getDate()));
			pstmt.setString(3, income.getName());
			pstmt.setInt(4, income.getIncome());
			pstmt.executeUpdate();
		
		return 0;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
}
public ObservableList<Income> getIncome(){
	String SQL = "SELECT * FROM 수입";
	ObservableList<Income> incomes = FXCollections.observableArrayList();	
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			Income income =new Income(rs.getString(1),rs.getDate(2).toLocalDate(),rs.getString(3),rs.getInt(4));
			incomes.add(income);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return incomes;
}
public int saveIncome(Income income) {
	/*if(deleteIncome() == -1) {
		return -1;
	}*/
	if(insertIncome(income) == 0) {
		return 0;
	}
	return 1;
}
}