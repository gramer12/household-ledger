package controller;


import javafx.collections.ObservableList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import model.UserDAO;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class LoginController  {
	@FXML private TextField txtID;
	@FXML private PasswordField txtPassword;
	@FXML private Button loginButton;
	private Stage primaryStage;
	private LayoutController layoutController =new LayoutController();
	public static ObservableList<User> users = FXCollections.observableArrayList();
	public static int userNumber;
	public LoginController() {
		users.add(new User("양희태","950601","1419016","test","123123","123123","01083819600"));
	}

	public void LoginButton(ActionEvent event) throws Exception {
		login();
	}
	public void loginButton() {
		loginButton.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				login();
			}
		});
	}
	private void login() {
		try {
			boolean UserFind =false;
			UserFind = userFind();
			
			UserDAO userDAO = new UserDAO();
			ObservableList<User> tempList3 = userDAO.getUser();
			for(int a = 0; a<tempList3.size();a++){
			users.add(tempList3.get(a));
			}
			
			if(UserFind==true) {
		    layoutController.setRootLayout();
			layoutController.setHome();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				//alert.initOwner(main.getPrimaryStage());
				alert.setTitle("로그인 실패");
				alert.setHeaderText("아이디 및 비밀번호 오류");
				alert.setContentText("확인한 후 다시 입력해주시오.");
				alert.showAndWait();	
				txtPassword.setText(null);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void signUpButton() {
		setSignUpButton();
	}
	public void setSignUpButton() {
		try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../view/Signup.fxml"));
		AnchorPane signUP= (AnchorPane) loader.load();	
		Stage dialogStage = new Stage();
		dialogStage.setTitle("회원가입");
	    dialogStage.initModality(Modality.WINDOW_MODAL);
	    dialogStage.initOwner(primaryStage);
	    Scene scene = new Scene(signUP);
	    dialogStage.setScene(scene);
	    SignUpController controller = loader.getController();
	    controller.setLogin(this);
	    controller.setDialogStage(dialogStage);
	    dialogStage.showAndWait();
	    dialogStage.setResizable(false);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void findButton() {
		setFindButton();
	}
	public void setFindButton() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/Find.fxml"));
			VBox find= (VBox) loader.load();	
			Stage dialogStage = new Stage();
			dialogStage.setTitle("아이디/비밀번호 찾기");
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    dialogStage.initOwner(primaryStage);
		    Scene scene = new Scene(find);
		    dialogStage.setScene(scene);
		    FindController controller = loader.getController();
		    controller.setLogin(this);
		    controller.setDialogStage(dialogStage);
		    dialogStage.showAndWait();
		    dialogStage.setResizable(false);
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	public boolean userFind() {
		boolean userFind = false;
		for(int i=0;i<users.size();i++) {
			if(txtID.getText().equals(users.get(i).getID()) && txtPassword.getText().equals(users.get(i).getPassword())){
					userFind=true;
					userNumber = i;
		}
	}
		return userFind;
}
}
