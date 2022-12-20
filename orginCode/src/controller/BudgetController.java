package controller;

import java.time.YearMonth;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.GoalMoney;
import model.GoalMoneyDAO;

public class BudgetController {
	private LayoutController layoutCon;
	private HomeController homeCon;
	private LoginController loginCon;
	private ObservableList<String> type = FXCollections.observableArrayList("교통","식비","생활","의료","유흥","기타");
	public static ObservableList<GoalMoney> monthGoalMoney = FXCollections.observableArrayList();
	public static ObservableList<GoalMoney> yearGoalMoney = FXCollections.observableArrayList();
	@FXML private ComboBox typeCheck;
	@FXML private TextField txtMoney;
	@FXML private Label monthLabel;
	@FXML private Label yearLabel;
	@FXML private TableView<GoalMoney> goalMoneyTable;
	@FXML private TableColumn<GoalMoney, YearMonth> goalMoneyMonthColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyTrafficColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyFoodColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyLifeColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyMedicalColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyPleasureColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyGuitarColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyTotalColumn;
	private boolean select;
	
	@FXML
	private void previousMonthButton() {
		homeCon.date = homeCon.date.minusMonths(1);
		monthLabel.setText(homeCon.date.getMonthValue()+"월");
		yearLabel.setText(homeCon.date.getYear()+"년");
	}
	@FXML
	private void nextMonthButton() {
		homeCon.date = homeCon.date.plusMonths(1);
		monthLabel.setText(homeCon.date.getMonthValue()+"월");
		yearLabel.setText(homeCon.date.getYear()+"년");
	}
	@FXML
	private void previousYearButton() {
		homeCon.date = homeCon.date.minusYears(1);
		yearLabel.setText(homeCon.date.getYear()+"년");
		yearGoalMoney.clear();
		for(int i=0;i<monthGoalMoney.size();i++) {
	    	if(monthGoalMoney.get(i).getDate().getYear() == homeCon.date.getYear()) {
	    		yearGoalMoney.add(monthGoalMoney.get(i));
	    	}
	    }
		goalMoneyMonthColumn.setSortType(TableColumn.SortType.ASCENDING);
		goalMoneyTable.getSortOrder().add(goalMoneyMonthColumn);
		goalMoneyMonthColumn.setSortable(true);
		goalMoneyTable.sort();
	}
	@FXML
	private void nextYearButton() {
		homeCon.date = homeCon.date.plusYears(1);
		yearLabel.setText(homeCon.date.getYear()+"년");
		yearGoalMoney.clear();
		for(int i=0;i<monthGoalMoney.size();i++) {
	    	if(monthGoalMoney.get(i).getDate().getYear() == homeCon.date.getYear()) {
	    		yearGoalMoney.add(monthGoalMoney.get(i));
	    	}
	    }
		goalMoneyMonthColumn.setSortType(TableColumn.SortType.ASCENDING);
		goalMoneyTable.getSortOrder().add(goalMoneyMonthColumn);
		goalMoneyMonthColumn.setSortable(true);
		goalMoneyTable.sort();
	}
	public BudgetController() {
	}
	@FXML
	private void initialize() {
		typeCheck.setItems(type);
		typeCheck.setDisable(true);
	    monthLabel.setText(homeCon.date.getMonthValue()+"월");
	    yearLabel.setText(homeCon.date.getYear()+"년");
	    yearGoalMoney.clear();
	    for(int i=0;i<monthGoalMoney.size();i++) {
	    	if(monthGoalMoney.get(i).getDate().getYear() == homeCon.date.getYear()) {
	    		if(monthGoalMoney.get(i).getDate().getMonthValue() == homeCon.date.getMonthValue()) {
	    			yearGoalMoney.add(monthGoalMoney.get(i));
	    		}
	    			}
	    		}
	    goalMoneyMonthColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
	    goalMoneyTrafficColumn.setCellValueFactory(cellData -> cellData.getValue().getTrafficProperty().asObject());
	    goalMoneyFoodColumn.setCellValueFactory(cellData -> cellData.getValue().getFoodProperty().asObject());
	    goalMoneyLifeColumn.setCellValueFactory(cellData -> cellData.getValue().getLifeProperty().asObject());
	    goalMoneyMedicalColumn.setCellValueFactory(cellData -> cellData.getValue().getMedicalProperty().asObject());
	    goalMoneyPleasureColumn.setCellValueFactory(cellData -> cellData.getValue().getPleasureProperty().asObject());;
	    goalMoneyGuitarColumn.setCellValueFactory(cellData -> cellData.getValue().getGuitarProperty().asObject());
	    goalMoneyTotalColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalProperty().asObject());
	}
	@FXML
	private void setTotal() {
		typeCheck.setDisable(true);
		select=false;
	}
	@FXML
	private void setType() {
		typeCheck.setDisable(false);
		select=true;
	}
	@FXML
	private void addButton() {
		int dateCheck =0;
		if(select == false) {
			int overType=0;
			for(int i=0;i<monthGoalMoney.size();i++) {
			if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date))) {
				if(Integer.parseInt(txtMoney.getText())<(monthGoalMoney.get(i).getTraffic()+monthGoalMoney.get(i).getFood()+monthGoalMoney.get(i).getLife()+monthGoalMoney.get(i).getMedical()+monthGoalMoney.get(i).getPleasure()+monthGoalMoney.get(i).getGuitar())) {
					dateCheck=1;
					overType=1;
					break;
				}
				monthGoalMoney.get(i).setTotal(Integer.parseInt(txtMoney.getText()));//데이터값 수정
				dateCheck=1;
			}
			}
			if(dateCheck==0) {
				GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
				System.out.println(homeCon.date);
				int result = goalMoneyDAO.saveGoalMoney(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,0,0,0,0,Integer.parseInt(txtMoney.getText())));
				//monthGoalMoney.add(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,0,0,0,0,Integer.parseInt(txtMoney.getText())));
		}
			if(overType==1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("월 총 목표금액 설정 실패");
				alert.setHeaderText("월 총 목표금액이 월 유형별 목표금액총액보다 작습니다.");
				alert.setContentText("다시 입력해주세요.");
				alert.show();
			}else {
				monthGoalMoney.clear();
				GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
				ObservableList<GoalMoney> tempList4 = goalMoneyDAO.getGoalMoney();
				for(int b = 0; b<tempList4.size();b++){
					if(loginCon.users.get(loginCon.userNumber).getID().equals(tempList4.get(b).getID())) {
				BudgetController.monthGoalMoney.add(tempList4.get(b));
				}
				}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("설정 성공");
			alert.setHeaderText("월 총 지출목표금액이 설정되었습니다.");
			alert.setContentText(homeCon.date.getYear()+"년 "+homeCon.date.getMonthValue()+"월"+" 총 지출목표금액: "+txtMoney.getText());
			alert.show();
			}
		}else {
			if(typeCheck.getValue() == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("유형 오류");
				alert.setHeaderText("유형이 선택되지 않았습니다.");
				alert.setContentText("유형을 선택해주세요.");
				alert.show();
			}else {
				int overTotal=0;
				if(typeCheck.getValue() == "교통") {
			for(int i=0;i<monthGoalMoney.size();i++) {
				if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date))) {
					if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
						dateCheck=1;
						overTotal=1;
						break;
					}
					monthGoalMoney.get(i).setTraffic(Integer.parseInt(txtMoney.getText())); //데이터 수정
					dateCheck=1;
				}
			}
			if(dateCheck==0) {
				GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
				goalMoneyDAO.saveGoalMoney(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),Integer.parseInt(txtMoney.getText()),0,0,0,0,0,Integer.parseInt(txtMoney.getText())));
					}
			if(overTotal ==1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("교통 지출목표액 설정 실패");
				alert.setHeaderText("월 교통 지출목표액이 월 총 목푝금액보다 높습니다.");
				alert.setContentText("다시 입력해주세요.");
				alert.show();
			}else {
				monthGoalMoney.clear();
				GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
				ObservableList<GoalMoney> tempList4 = goalMoneyDAO.getGoalMoney();
				for(int b = 0; b<tempList4.size();b++){
					if(loginCon.users.get(loginCon.userNumber).getID().equals(tempList4.get(b).getID())) {
				BudgetController.monthGoalMoney.add(tempList4.get(b));
				}
				}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("설정 성공");
			alert.setHeaderText("교통 목표금액이 설정되었습니다.");
			alert.setContentText(homeCon.date.getYear()+"년 "+homeCon.date.getMonthValue()+"월"+" 교통 목표금액: "+txtMoney.getText());
			alert.show();
			        }
				}else {
					if(typeCheck.getValue() == "식비") {
						for(int i=0;i<monthGoalMoney.size();i++) {
							if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date))) {
								if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
									dateCheck=1;
									overTotal=1;
									break;
								}
								monthGoalMoney.get(i).setFood(Integer.parseInt(txtMoney.getText()));//식비 수정
								dateCheck=1;
							}
						}
						if(dateCheck==0) {
							GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
							int result = goalMoneyDAO.saveGoalMoney(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,Integer.parseInt(txtMoney.getText()),0,0,0,0,Integer.parseInt(txtMoney.getText())));
								}
						if(overTotal ==1) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("식비 지출목표액 설정 실패");
							alert.setHeaderText("월 식비 지출목표액이 월 총 목푝금액보다 높습니다.");
							alert.setContentText("다시 입력해주세요.");
							alert.show();
						}else {
							monthGoalMoney.clear();
							GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
							ObservableList<GoalMoney> tempList4 = goalMoneyDAO.getGoalMoney();
							for(int b = 0; b<tempList4.size();b++){
								if(loginCon.users.get(loginCon.userNumber).getID().equals(tempList4.get(b).getID())) {
							BudgetController.monthGoalMoney.add(tempList4.get(b));
							}
							}
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("설정 성공");
						alert.setHeaderText("식비 목표금액이 설정되었습니다.");
						alert.setContentText(homeCon.date.getYear()+"년 "+homeCon.date.getMonthValue()+"월"+" 식비 목표금액: "+txtMoney.getText());
						alert.show();
						        }
					}else {
						if(typeCheck.getValue() == "생활") {
							for(int i=0;i<monthGoalMoney.size();i++) {
								if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date))) {
									if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
										dateCheck=1;
										overTotal=1;
										break;
									}
									monthGoalMoney.get(i).setLife(Integer.parseInt(txtMoney.getText())); //생활 수정
									dateCheck=1;
								}
							}
							if(dateCheck==0) {
								GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
								goalMoneyDAO.saveGoalMoney(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,Integer.parseInt(txtMoney.getText()),0,0,0,Integer.parseInt(txtMoney.getText())));
									}
							if(overTotal ==1) {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("생활 지출목표액 설정 실패");
								alert.setHeaderText("월 생활 지출목표액이 월 총 목푝금액보다 높습니다.");
								alert.setContentText("다시 입력해주세요.");
								alert.show();
							}else {
								monthGoalMoney.clear();
								GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
								ObservableList<GoalMoney> tempList4 = goalMoneyDAO.getGoalMoney();
								for(int b = 0; b<tempList4.size();b++){
									if(loginCon.users.get(loginCon.userNumber).getID().equals(tempList4.get(b).getID())) {
								BudgetController.monthGoalMoney.add(tempList4.get(b));
								}
								}
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("설정 성공");
							alert.setHeaderText("생활 목표금액이 설정되었습니다.");
							alert.setContentText(homeCon.date.getYear()+"년 "+homeCon.date.getMonthValue()+"월"+" 식비 목표금액: "+txtMoney.getText());
							alert.show();
							        }
						}else {
							if(typeCheck.getValue() == "의료") {
								for(int i=0;i<monthGoalMoney.size();i++) {
									if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date))) {
										if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
											dateCheck=1;
											overTotal=1;
											break;
										}
										monthGoalMoney.get(i).setMedical(Integer.parseInt(txtMoney.getText()));//의료 수정
										dateCheck=1;
									}
								}
								if(dateCheck==0) {
									GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
									goalMoneyDAO.saveGoalMoney(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,0,Integer.parseInt(txtMoney.getText()),0,0,Integer.parseInt(txtMoney.getText())));
										}
								if(overTotal ==1) {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("의료 지출목표액 설정 실패");
									alert.setHeaderText("월 의료 지출목표액이 월 총 목푝금액보다 높습니다.");
									alert.setContentText("다시 입력해주세요.");
									alert.show();
								}else {
									monthGoalMoney.clear();
									GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
									ObservableList<GoalMoney> tempList4 = goalMoneyDAO.getGoalMoney();
									for(int b = 0; b<tempList4.size();b++){
										if(loginCon.users.get(loginCon.userNumber).getID().equals(tempList4.get(b).getID())) {
									BudgetController.monthGoalMoney.add(tempList4.get(b));
									}
									}
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("설정 성공");
								alert.setHeaderText("의료 목표금액이 설정되었습니다.");
								alert.setContentText(homeCon.date.getYear()+"년 "+homeCon.date.getMonthValue()+"월"+" 의료 목표금액: "+txtMoney.getText());
								alert.show();
								        }
							}else {
								if(typeCheck.getValue() == "유흥") {
									for(int i=0;i<monthGoalMoney.size();i++) {
										if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date))) {
											if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
												dateCheck=1;
												overTotal=1;
												break;
											}
											monthGoalMoney.get(i).setPleasure(Integer.parseInt(txtMoney.getText())); //유흥 수정
											dateCheck=1;
										}
									}
									if(dateCheck==0) {
										GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
										goalMoneyDAO.saveGoalMoney(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,0,0,Integer.parseInt(txtMoney.getText()),0,Integer.parseInt(txtMoney.getText())));
											}
									if(overTotal ==1) {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("유흥 지출목표액 설정 실패");
										alert.setHeaderText("월 유흥 지출목표액이 월 총 목푝금액보다 높습니다.");
										alert.setContentText("다시 입력해주세요.");
										alert.show();
									}else {
										monthGoalMoney.clear();
										GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
										ObservableList<GoalMoney> tempList4 = goalMoneyDAO.getGoalMoney();
										for(int b = 0; b<tempList4.size();b++){
											if(loginCon.users.get(loginCon.userNumber).getID().equals(tempList4.get(b).getID())) {
										BudgetController.monthGoalMoney.add(tempList4.get(b));
										}
										}
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("설정 성공");
									alert.setHeaderText("유흥 목표금액이 설정되었습니다.");
									alert.setContentText(homeCon.date.getYear()+"년 "+homeCon.date.getMonthValue()+"월"+" 식비 목표금액: "+txtMoney.getText());
									alert.show();
									        }
								}else {
									for(int i=0;i<monthGoalMoney.size();i++) {
										if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date))) {
											if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
												dateCheck=1;
												overTotal=1;
												break;
											}
											monthGoalMoney.get(i).setGuitar(Integer.parseInt(txtMoney.getText()));
											dateCheck=1;
										}
									}
									if(dateCheck==0) {
										GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
										goalMoneyDAO.saveGoalMoney(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,0,0,0,Integer.parseInt(txtMoney.getText()),Integer.parseInt(txtMoney.getText())));
										}
									if(overTotal ==1) {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("기타 지출목표액 설정 실패");
										alert.setHeaderText("월 기타 지출목표액이 월 총 목푝금액보다 높습니다.");
										alert.setContentText("다시 입력해주세요.");
										alert.show();
									}else {
										monthGoalMoney.clear();
										GoalMoneyDAO goalMoneyDAO = new GoalMoneyDAO();
										ObservableList<GoalMoney> tempList4 = goalMoneyDAO.getGoalMoney();
										for(int b = 0; b<tempList4.size();b++){
											if(loginCon.users.get(loginCon.userNumber).getID().equals(tempList4.get(b).getID())) {
										BudgetController.monthGoalMoney.add(tempList4.get(b));
										}
										}
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("설정 성공");
									alert.setHeaderText("기타 목표금액이 설정되었습니다.");
									alert.setContentText(homeCon.date.getYear()+"년 "+homeCon.date.getMonthValue()+"월"+" 식비 목표금액: "+txtMoney.getText());
									alert.show();
										        }
								}
							}
						}
					}
				}
			}
		}
		/*boolean check =false;
		for(int j =0;j<yearGoalMoney.size();j++) {
			for(int a=1;a<=yearGoalMoney.size();a++) {
				if(yearGoalMoney.get(j).equals(yearGoalMoney.get(a))) {
					check = true;
					break;
				}
			}
			if(check == true) {
				break;
			}
		}
		if(check == true) {
			
		}*/
		/*boolean check = false;
		for(int j=0;j<monthGoalMoney.size();j++) {
			if(monthGoalMoney.get(j).getDate() == YearMonth.from(homeCon.date)) {
				check = true;
				break;
			}
		}
		if(check = true) {
			
		}*/
		if(dateCheck==0) {
			for(int i=0;i<monthGoalMoney.size();i++) {
		    	if(monthGoalMoney.get(i).getDate().getYear() == homeCon.date.getYear()) {
		    		yearGoalMoney.add(monthGoalMoney.get(i));
		    		}
		    	}
		}
		goalMoneyMonthColumn.setSortType(TableColumn.SortType.ASCENDING);
		goalMoneyTable.getSortOrder().add(goalMoneyMonthColumn);
		goalMoneyMonthColumn.setSortable(true);
		goalMoneyTable.sort();
	}
	private ObservableList<GoalMoney> getYearGoalMoney(){
		return yearGoalMoney;
	}
	public void setBudget(LayoutController layoutCon) {
		this.layoutCon = layoutCon;
		//if(!monthGoalMoney.isEmpty())
		goalMoneyTable.setItems(getYearGoalMoney());
		goalMoneyMonthColumn.setSortType(TableColumn.SortType.ASCENDING);
		goalMoneyTable.getSortOrder().add(goalMoneyMonthColumn);
		goalMoneyMonthColumn.setSortable(true);
		goalMoneyTable.sort();
	}

}
