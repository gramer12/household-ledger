package controller;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Expense;
import model.Income;
import model.Schedule;

public class ReportController {
	@FXML private TableView<Expense> expenseTable;
	@FXML private TableColumn<Expense, LocalDate> expenseDateColumn;
	@FXML private TableColumn<Expense, String> typeColumn;
	@FXML private TableColumn<Expense, String> expenseNameColumn;
	@FXML private TableColumn<Expense, Integer> expenseColumn;
	@FXML private TableColumn<Expense, String> expenseContentColumn;
	@FXML private TableView<Income> incomeTable;
	@FXML private TableColumn<Income, LocalDate> incomeDateColumn;
	@FXML private TableColumn<Income, String> incomeNameColumn;
	@FXML private TableColumn<Income, Integer> incomeColumn;
	@FXML private TableView<Schedule> scheduleTable;
	@FXML private TableColumn<Schedule, LocalDate> scheduleDateColumn;
	@FXML private TableColumn<Schedule, String> scheduleNameColumn;
	@FXML private TableColumn<Schedule, String> scheduleContentColumn;
	private ObservableList<Expense> monthExpenses =FXCollections.observableArrayList();
	private ObservableList<Income> monthIncomes = FXCollections.observableArrayList();
	private ObservableList<Schedule> monthSchedules = FXCollections.observableArrayList();
	private LayoutController layoutCon;
	private LoginController loginCon;
	private HomeController homeCon;
	@FXML Label dateTitle;
	private int number;
	private Expense expenseNewValue;
	private Stage primaryStage;
	private int select;
	
	@FXML
	private void editButton() {
		if(select==0) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("오류");
			alert.setHeaderText("선택 오류");
			alert.setContentText("아무것도 선택하지 않았습니다.");
		}else {
		try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../view/Edit.fxml"));
		VBox edit =(VBox) loader.load();
		Stage dialogStage = new Stage();
		dialogStage.setTitle("지출수정");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene =new Scene(edit);
		dialogStage.setScene(scene);
		EditController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		if(select==1) {
			controller.setExpense(this,number);
		}else {
			if(select==2) {
				controller.setIncome(this, number);
			}else {
				controller.setSchedule(this, number);
			}
		}
		dialogStage.showAndWait();
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	}

	@FXML
	private void previousMonthButton() {
		homeCon.date = homeCon.date.minusMonths(1);
		dateTitle.setText(homeCon.date.getYear()+"년 "+homeCon.date.getMonthValue()+"월");
		monthExpenses.clear();
		monthIncomes.clear();
		monthSchedules.clear();
		for(int i=0;i<layoutCon.expenses.size();i++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.expenses.get(i).getID())) {
			if(layoutCon.expenses.get(i).getDate().getMonth().equals(homeCon.date.getMonth())) {
			monthExpenses.add(layoutCon.expenses.get(i));
			}
			}
		}
		for(int j=0;j<layoutCon.incomes.size();j++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.incomes.get(j).getID())) {
			if(layoutCon.incomes.get(j).getDate().getMonth().equals(homeCon.date.getMonth())) {
				monthIncomes.add(layoutCon.incomes.get(j));
			}
			}
		}
		for(int a=0;a<layoutCon.schedules.size();a++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.schedules.get(a).getID())) {
			if(layoutCon.schedules.get(a).getDate().getMonth().equals(homeCon.date.getMonth())) {
				monthSchedules.add(layoutCon.schedules.get(a));
			}
			}
		}
		expenseDateColumn.setSortType(TableColumn.SortType.ASCENDING);
		expenseTable.getSortOrder().add(expenseDateColumn);
		expenseDateColumn.setSortable(true);
		expenseTable.sort();
		incomeDateColumn.setSortType(TableColumn.SortType.ASCENDING);
		incomeTable.getSortOrder().add(incomeDateColumn);
		incomeDateColumn.setSortable(true);
		incomeTable.sort();
		scheduleDateColumn.setSortType(TableColumn.SortType.ASCENDING);
		scheduleTable.getSortOrder().add(scheduleDateColumn);
		scheduleDateColumn.setSortable(true);
		scheduleTable.sort();
	}
	@FXML
	private void nextMonthButton() {
		homeCon.date =homeCon.date.plusMonths(1);
		dateTitle.setText(homeCon.date.getYear()+"년 "+homeCon.date.getMonthValue()+"월");
		monthExpenses.clear();
		monthIncomes.clear();
		monthSchedules.clear();
		for(int i=0;i<layoutCon.expenses.size();i++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.expenses.get(i).getID())) {
			if(layoutCon.expenses.get(i).getDate().getMonth().equals(homeCon.date.getMonth())) {
			monthExpenses.add(layoutCon.expenses.get(i));
			}
			}
		}
		for(int j=0;j<layoutCon.incomes.size();j++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.incomes.get(j).getID())) {
			if(layoutCon.incomes.get(j).getDate().getMonth().equals(homeCon.date.getMonth())) {
				monthIncomes.add(layoutCon.incomes.get(j));
			}
			}
		}
		for(int a=0;a<layoutCon.schedules.size();a++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.schedules.get(a).getID())) {
			if(layoutCon.schedules.get(a).getDate().getMonth().equals(homeCon.date.getMonth())) {
				monthSchedules.add(layoutCon.schedules.get(a));
			}
			}
		}
		expenseDateColumn.setSortType(TableColumn.SortType.ASCENDING);
		expenseTable.getSortOrder().add(expenseDateColumn);
		expenseDateColumn.setSortable(true);
		expenseTable.sort();
		incomeDateColumn.setSortType(TableColumn.SortType.ASCENDING);
		incomeTable.getSortOrder().add(incomeDateColumn);
		incomeDateColumn.setSortable(true);
		incomeTable.sort();
		scheduleDateColumn.setSortType(TableColumn.SortType.ASCENDING);
		scheduleTable.getSortOrder().add(scheduleDateColumn);
		scheduleDateColumn.setSortable(true);
		scheduleTable.sort();
	}

	public ReportController() {
		for(int i=0;i<layoutCon.expenses.size();i++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.expenses.get(i).getID())) {
			if(layoutCon.expenses.get(i).getDate().getMonth().equals(homeCon.date.getMonth())) {
			monthExpenses.add(layoutCon.expenses.get(i));
			}
			}
		}
		for(int j=0;j<layoutCon.incomes.size();j++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.incomes.get(j).getID())) {
			if(layoutCon.incomes.get(j).getDate().getMonth().equals(homeCon.date.getMonth())) {
				monthIncomes.add(layoutCon.incomes.get(j));
			}
			}
		}
		for(int a=0;a<layoutCon.schedules.size();a++) {
			if(loginCon.users.get(loginCon.userNumber).getID().equals(layoutCon.schedules.get(a).getID())) {
			if(layoutCon.schedules.get(a).getDate().getMonth().equals(homeCon.date.getMonth())) {
				monthSchedules.add(layoutCon.schedules.get(a));
			}
			}
		}
		
		//expenses.add(new Expense(loginCon.users.get(loginCon.userNumber).getID(),LocalDate.of(2018,6,11),"교통","버스비",5000,"티머니 충전"));
		//incomes.add(new Income(loginCon.users.get(loginCon.userNumber).getID(),LocalDate.of(2018,6,6),"알바비",20000));
		//schedules.add(new Schedule(loginCon.users.get(loginCon.userNumber).getID(),LocalDate.of(2018,6,2),"축구","오메가배 풋살결승"));
	}
	@FXML
	private void initialize() {	
		expenseDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
		expenseNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		expenseColumn.setCellValueFactory(cellData -> cellData.getValue().getExpenseProperty().asObject());
		expenseContentColumn.setCellValueFactory(cellData -> cellData.getValue().getContentProperty());
		incomeDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
		incomeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		incomeColumn.setCellValueFactory(cellData -> cellData.getValue().getIncomeProperty().asObject());
		scheduleDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
		scheduleNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		scheduleContentColumn.setCellValueFactory(cellData -> cellData.getValue().getContentProperty());
		dateTitle.setText(homeCon.date.getYear()+"년 "+homeCon.date.getMonthValue()+"월");
	    expenseTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Expense>() {
	    	@Override
	    public void changed(ObservableValue<? extends Expense> observable,Expense oldValue, Expense newValue) {
	    		for(int i=0;i<layoutCon.expenses.size();i++) {
		    		 if(newValue.equals(layoutCon.expenses.get(i))) {
		    			 number = i;
		    			 select=1;
		    		 }
		    	 }
	    		/*if(select==0) {
	    		 for(int i=0;i<layoutCon.expenses.size();i++) {
		    		 if(newValue.equals(layoutCon.expenses.get(i))) {
		    			 number = i;
		    			 select=1;
		    		 }
		    	 }
	    	 }else {
	    		if(select==2) {
	    			incomeTable.getSelectionModel().clearSelection();
	    		} if(select==3) {
	    			scheduleTable.getSelectionModel().clearSelection();
	    		}
	    		for(int i=0;i<layoutCon.expenses.size();i++) {
		    		 if(newValue.equals(layoutCon.expenses.get(i))) {
		    			 number = i;
		    			 select=1;
		    		 }
		    	 }
	    	 }*/
	    }
	    });
	    incomeTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Income>() {
	    	@Override
	    public void changed(ObservableValue<? extends Income> observable,Income oldValue, Income newValue) {
	    		for(int i=0;i<layoutCon.incomes.size();i++) {
		    		 if(newValue.equals(layoutCon.incomes.get(i))) {
		    			 number = i;
		    			 select=2;
		    		 }
	    		}
	    		/*if(select==0) {
	    		for(int i=0;i<layoutCon.incomes.size();i++) {
	    		 if(newValue.equals(layoutCon.incomes.get(i))) {
	    			 number = i;
	    			 select=2;
	    		 }
	    	 }
	    		}else {
	    			if(select==1) {
	    				expenseTable.getSelectionModel().clearSelection();
	    			} if(select==3) {
		    			scheduleTable.getSelectionModel().clearSelection();
	    			}
	    				for(int i=0;i<layoutCon.incomes.size();i++) {
	    		    		 if(newValue.equals(layoutCon.incomes.get(i))) {
	    		    			 number = i;
	    		    			 select=2;
	    		    		 }
	    		    	 }    			
	    		}*/
	    }
	    });
	    scheduleTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Schedule>() {
	    	@Override
	    public void changed(ObservableValue<? extends Schedule> observable,Schedule oldValue, Schedule newValue) {
	    		for(int i=0;i<layoutCon.schedules.size();i++) {
		    		 if(newValue.equals(layoutCon.schedules.get(i))) {
		    			 number = i;
		    			 select=3;
		    		 }
		    	 }
	    		/* if(select==0) {
	    		 for(int i=0;i<layoutCon.schedules.size();i++) {
		    		 if(newValue.equals(layoutCon.schedules.get(i))) {
		    			 number = i;
		    			 select=3;
		    		 }
		    	 }
	    	 }else {
	    		 if(select==1) { 
	    			 expenseTable.getSelectionModel().clearSelection();
	    		 } if(select==2) {
		    		 incomeTable.getSelectionModel().clearSelection();
	    		 }
	    		for(int i=0;i<layoutCon.schedules.size();i++) {
	    		 if(newValue.equals(layoutCon.schedules.get(i))) {
	    			 number = i;
	    			 select=3;
	    		 }
	    	 }
	    	 }*/
	    }
	    });
	}
	public ObservableList<Expense> getExpenses(){
		return monthExpenses;
	}
	public ObservableList<Income> getIncome(){
		return monthIncomes;
	}
	public ObservableList<Schedule> getSchedule(){
		return monthSchedules;
	}
	public void setReprot(LayoutController layoutCon) {
		this.layoutCon = layoutCon;
		expenseTable.setItems(getExpenses());
		expenseDateColumn.setSortType(TableColumn.SortType.ASCENDING);
		expenseTable.getSortOrder().add(expenseDateColumn);
		expenseDateColumn.setSortable(true);
		expenseTable.sort();
		incomeTable.setItems(getIncome());
		incomeDateColumn.setSortType(TableColumn.SortType.ASCENDING);
		incomeTable.getSortOrder().add(incomeDateColumn);
		incomeDateColumn.setSortable(true);
		incomeTable.sort();
		scheduleTable.setItems(getSchedule());
		scheduleDateColumn.setSortType(TableColumn.SortType.ASCENDING);
		scheduleTable.getSortOrder().add(scheduleDateColumn);
		scheduleDateColumn.setSortable(true);
		scheduleTable.sort();
	}

}

