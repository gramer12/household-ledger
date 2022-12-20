package controller;

import java.time.YearMonth;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
	private ReportController reportCon;
	@FXML DatePicker datePicker;
	@FXML ComboBox type;
	@FXML TextField txtName;
	@FXML TextField txtMoney;
	@FXML TextArea txtContent;
	
	private Stage dialogStage;
	private LayoutController layoutCon;
	private HomeController homeCon;
	private int number;
	private int select;
	
	@FXML
	private void ininialize() {
		
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public void setExpense(ReportController reportCon,int expenseNumber) {
		this.reportCon = reportCon;
		number =expenseNumber;
		datePicker.setValue(layoutCon.expenses.get(expenseNumber).getDate());
		type.setValue(layoutCon.expenses.get(expenseNumber).getType());
		txtName.setText(layoutCon.expenses.get(expenseNumber).getName());
		txtMoney.setText(String.valueOf(layoutCon.expenses.get(expenseNumber).getExpense()));
		txtContent.setText(layoutCon.expenses.get(expenseNumber).getContent());
		select =1;
	}
	public void setIncome(ReportController reportCon,int incomeNumber) {
		this.reportCon = reportCon;
		number = incomeNumber;
		datePicker.setValue(layoutCon.incomes.get(incomeNumber).getDate());
		type.setDisable(true);
		txtName.setText(layoutCon.incomes.get(incomeNumber).getName());
		txtMoney.setText(String.valueOf(layoutCon.incomes.get(incomeNumber).getIncome()));
		txtContent.setDisable(true);
		select =2;
	}
	public void setSchedule(ReportController reportCon,int scheduleNumber) {
		this.reportCon = reportCon;
		number = scheduleNumber;
		datePicker.setValue(layoutCon.schedules.get(scheduleNumber).getDate());
		type.setDisable(true);
		txtName.setText(layoutCon.schedules.get(scheduleNumber).getName());
		txtMoney.setDisable(true);
		txtContent.setText(layoutCon.schedules.get(scheduleNumber).getContent());
		select=3;
	}
	@FXML
	private void confirmButton() {
		int money =0;
		if(select==1) {
		money=layoutCon.expenses.get(number).getExpense();
		layoutCon.expenses.get(number).setDate(datePicker.getValue());
		layoutCon.expenses.get(number).setType((String)type.getValue());
		layoutCon.expenses.get(number).setName(txtName.getText());
		layoutCon.expenses.get(number).setExpense(Integer.parseInt(txtMoney.getText()));
		layoutCon.expenses.get(number).setContent(txtContent.getText());
		//지출 수정 후 동기화
		if(money!=layoutCon.expenses.get(number).getExpense()) {
			if(money>layoutCon.expenses.get(number).getExpense()) {
				for(int i=0;i<layoutCon.differentiate.size();i++) {
					if(layoutCon.expenses.get(number).getDate().equals(layoutCon.differentiate.get(i).getDate())) {
						layoutCon.differentiate.get(i).minusDayExpense(money-layoutCon.expenses.get(number).getExpense());
						//판별 수정 후 동기화
					}
				}
				for(int j=0;j<layoutCon.management.size();j++) {
					if(YearMonth.from(layoutCon.expenses.get(number).getDate()).equals(layoutCon.management.get(j).getDate())) {
						layoutCon.management.get(j).minusMonthExpense(money-layoutCon.expenses.get(number).getExpense());
						//관리 수정 후 동기화
					}
				}
			}else {
				for(int i=0;i<layoutCon.differentiate.size();i++) {
					if(layoutCon.expenses.get(number).getDate().equals(layoutCon.differentiate.get(i).getDate())) {
						layoutCon.differentiate.get(i).plusDayExpense(layoutCon.expenses.get(number).getExpense()-money);
						//판별 수정 후 동기화
					}
				}
				for(int j=0;j<layoutCon.management.size();j++) {
					if(YearMonth.from(layoutCon.expenses.get(number).getDate()).equals(layoutCon.management.get(j).getDate())) {
						layoutCon.management.get(j).plusMonthExpense(layoutCon.expenses.get(number).getExpense()-money);
						//관리 수정후 동기화
					}
				}
			}
		}
	} else {
		if(select==2) {
			money= layoutCon.incomes.get(number).getIncome();
			layoutCon.incomes.get(number).setDate(datePicker.getValue());
			layoutCon.incomes.get(number).setName(txtName.getText());
			layoutCon.incomes.get(number).setIncome(Integer.parseInt(txtMoney.getText()));
			//수입 수정 후 동기화
			if(money!=layoutCon.incomes.get(number).getIncome()) {
				if(money>layoutCon.incomes.get(number).getIncome()) {
					for(int j=0;j<layoutCon.management.size();j++) {
						if(YearMonth.from(layoutCon.incomes.get(number).getDate()).equals(layoutCon.management.get(j).getDate())) {
							layoutCon.management.get(j).minusMonthIncome(money-layoutCon.incomes.get(number).getIncome());
							//관리 수정 후 동기화
						}
					}
				}else {
					for(int j=0;j<layoutCon.management.size();j++) {
						if(YearMonth.from(layoutCon.incomes.get(number).getDate()).equals(layoutCon.management.get(j).getDate())) {
							layoutCon.management.get(j).plusMonthIncome(layoutCon.incomes.get(number).getIncome()-money);
							//관리 수정 후 동기화
						}
					}
				}
			}
		}else {
			layoutCon.schedules.get(number).setDate(datePicker.getValue());
			layoutCon.schedules.get(number).setName(txtName.getText());
			layoutCon.schedules.get(number).setContent(txtContent.getText());
			//일정 수정 후 동기화
		}
	}
		dialogStage.close();
	}
	
	@FXML
	private void cancelButton() {
		dialogStage.close();
	}

}
