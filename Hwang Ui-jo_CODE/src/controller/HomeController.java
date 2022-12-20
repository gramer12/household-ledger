package controller;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Differentiate;



public  class HomeController {
	
	private Stage primaryStage;
	private LoginController loginCon;
	public static LocalDate date;
	private LayoutController layoutCon;
	private BudgetController budgetCon;
	//public int i;
	@FXML  public Pane calendar;
	public HomeController() {
	//	layoutCon.expenses.add(new Expense(loginCon.users.get(loginCon.userNumber).getID(),LocalDate.of(2018,6,11),"교통","버스비",5000,"티머니 충전"));
		//layoutCon.incomes.add(new Income(loginCon.users.get(loginCon.userNumber).getID(),LocalDate.of(2018,6,6),"알바비",20000));
		//layoutCon.schedules.add(new Schedule(loginCon.users.get(loginCon.userNumber).getID(),LocalDate.of(2018,6,2),"축구","오메가배 풋살결승"));
		date = date.now();
		/*differentiate.clear();
		if(!layoutCon.expenses.isEmpty()) {
			differentiate.add(new Differentiate(layoutCon.expenses.get(0).getID(),layoutCon.expenses.get(0).getDate(),layoutCon.expenses.get(0).getExpense()));
			for(int i=1;i<layoutCon.expenses.size();i++) {
				for(int j=0;j<differentiate.size();j++) {
					if(layoutCon.expenses.get(i).getDate().equals(differentiate.get(j).getDate())) {
						differentiate.get(j).addDayExpense(layoutCon.expenses.get(i).getExpense());
					}else {
						differentiate.add(new Differentiate(layoutCon.expenses.get(i).getID(),layoutCon.expenses.get(i).getDate(),layoutCon.expenses.get(i).getExpense()));
					}
				}
			}
		}*/
			
			
	/*	differentiate.add(new Differentiate("",null,0));
		for(int i=0;i<layoutCon.expenses.size();i++) {
			for(int j=0;j<differentiate.size();j++) {
				if(layoutCon.expenses.get(i).getDate().equals(differentiate.get(j).getDate())) {
					System.out.println(i+" "+j+" "+differentiate.get(j).getDate()+" "+differentiate.get(j).getDayExpense());
					differentiate.get(j).addDayExpense(layoutCon.expenses.get(i).getExpense());
					System.out.println(i+" "+j+" "+differentiate.get(j).getDate()+" "+differentiate.get(j).getDayExpense());
				}else {
					System.out.println(i+" "+j+" "+differentiate.get(j).getDate()+" "+differentiate.get(j).getDayExpense());
					differentiate.add(new Differentiate(layoutCon.expenses.get(i).getID(),layoutCon.expenses.get(i).getDate(),layoutCon.expenses.get(i).getExpense()));
					System.out.println(i+" "+j+" "+differentiate.get(j).getDate()+" "+differentiate.get(j).getDayExpense());
				}
				}
			}
		}*/
		/*differentiate.clear();
		differentiate.add(new Differentiate("dlrfdnwkd",date,0));
		System.out.println(differentiate.size());
		for(int i=0;i<layoutCon.expenses.size();i++) {
			for(int j=0;j<differentiate.size();j++) {
				if(layoutCon.expenses.get(i).getDate().equals(differentiate.get(j).getDate())) {
					differentiate.get(j).addDayExpense(layoutCon.expenses.get(i).getExpense());
					}else {
						differentiate.add(new Differentiate(layoutCon.expenses.get(i).getID(),layoutCon.expenses.get(i).getDate(),layoutCon.expenses.get(j).getExpense()));
					}
			}
		}*/
	}
	
	public void setMain(LayoutController layoutCon) {
		this.layoutCon = layoutCon;
	}
	@FXML
	private void inputButton() {
		setInputData();
	}
	@FXML
	private void outputButton() {
		setOutputData();
	}
	 public void setInputData() {
	    	try {
	    		FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(Main.class.getResource("../view/Input.fxml"));
	    		AnchorPane page= (AnchorPane) loader.load();	
	    		Stage dialogStage = new Stage();
	    		dialogStage.setTitle("입력하기");
	    	    dialogStage.initModality(Modality.WINDOW_MODAL);
	    	    dialogStage.initOwner(primaryStage);
	    	    Scene scene = new Scene(page);
	    	    dialogStage.setScene(scene);  
	    	    InputButtonController controller = loader.getController();
	    	    controller.setDialogStage(dialogStage);
	    	    controller.setInput(this);
	    	    dialogStage.showAndWait();
	    	    dialogStage.setResizable(false);
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	 public void setOutputData() {
	    	try {
	    		FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(Main.class.getResource("../view/Output.fxml"));
	    		SplitPane page= (SplitPane) loader.load();	
	    		Stage dialogStage = new Stage();
	    		dialogStage.setTitle("내역확인");
	    	    dialogStage.initModality(Modality.WINDOW_MODAL);
	    	    dialogStage.initOwner(primaryStage);
	    	    Scene scene = new Scene(page);
	    	    dialogStage.setScene(scene);  
	    	    OutputButtonController controller = loader.getController();
	    	    controller.setOutput(this);
	    	    controller.setDialogStage(dialogStage);
	    	    dialogStage.showAndWait();
	    	    dialogStage.setResizable(false);
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	 }
