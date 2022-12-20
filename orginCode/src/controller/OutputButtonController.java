package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Expense;
import model.Income;
import model.Schedule;

public class OutputButtonController {
private Stage dialogStage;
@FXML private TableView<Expense> expenseTable;
@FXML private TableColumn<Expense, String> expenseNameColumn;
@FXML private TableColumn<Expense, Integer> expenseColumn;
@FXML private TableColumn<Expense, String> expenseContentColumn;
@FXML private TableView<Income> incomeTable;
@FXML private TableColumn<Income, String> incomeNameColumn;
@FXML private TableColumn<Income, Integer> incomeColumn;
@FXML private TableView<Schedule> scheduleTable;
@FXML private TableColumn<Schedule, String> scheduleNameColumn;
@FXML private TableColumn<Schedule, String> scheduleContentColumn;
public ObservableList<Expense> dayExpenses =FXCollections.observableArrayList();
public ObservableList<Income> dayIncomes = FXCollections.observableArrayList();
public ObservableList<Schedule> daySchedules = FXCollections.observableArrayList();
private LoginController loginCon;
private LayoutController layoutCon;
private HomeController homeCon;
	public OutputButtonController() {
		for(int i=0;i<layoutCon.expenses.size();i++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.expenses.get(i).getID())){
				if(layoutCon.expenses.get(i).getDate().getDayOfMonth()==homeCon.date.getDayOfMonth()) {
					dayExpenses.add(layoutCon.expenses.get(i));
				}
			}
		}
		for(int i=0;i<layoutCon.incomes.size();i++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.incomes.get(i).getID())){
				if(layoutCon.incomes.get(i).getDate().getDayOfMonth()==homeCon.date.getDayOfMonth()) {
					dayIncomes.add(layoutCon.incomes.get(i));
				}
			}
		}
		for(int i=0;i<layoutCon.schedules.size();i++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.schedules.get(i).getID())){
				if(layoutCon.schedules.get(i).getDate().getDayOfMonth()==homeCon.date.getDayOfMonth()) {
					daySchedules.add(layoutCon.schedules.get(i));
				}
			}
		}
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	@FXML
	private void initialize() {
		expenseNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		expenseColumn.setCellValueFactory(cellData -> cellData.getValue().getExpenseProperty().asObject());
		expenseContentColumn.setCellValueFactory(cellData -> cellData.getValue().getContentProperty());
		incomeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		incomeColumn.setCellValueFactory(cellData -> cellData.getValue().getIncomeProperty().asObject());
		scheduleNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		scheduleContentColumn.setCellValueFactory(cellData -> cellData.getValue().getContentProperty());
	}
	public ObservableList<Expense> getExpenses(){
		return dayExpenses;
	}
	public ObservableList<Income> getIncome(){
		return dayIncomes;
	}
	public ObservableList<Schedule> getSchedule(){
		return daySchedules;
	}
	public void setOutput(HomeController homeCOn) {
		this.layoutCon = layoutCon;
		expenseTable.setItems(getExpenses());
		incomeTable.setItems(getIncome());
		scheduleTable.setItems(getSchedule());
	}

	@FXML
	public void confirmButton() {
		dialogStage.close();
	}
}
