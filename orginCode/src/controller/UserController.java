package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class UserController {
	@FXML private Label userName;
	@FXML private Label userID;
	@FXML private Label userEmail;
	@FXML private Label userPhoneNumber;
	@FXML private PasswordField txtPassword;
	@FXML private PasswordField txtPasswordTest;
	private LoginController loginCon;
	public void setUser(int userNumber) {
	     String userName = loginCon.users.get(userNumber).getName();
	     this.userName.setText(userName);
	     String userId = loginCon.users.get(userNumber).getID();
	     this.userID.setText(userId);
	     String userEmail = loginCon.users.get(userNumber).getEmail();
	     this.userEmail.setText(userEmail);
	     String userPhoneNumber = loginCon.users.get(userNumber).getPhoneNumber();
	     this.userPhoneNumber.setText(userPhoneNumber);
	}
	@FXML
	private void editButton() {
			if(txtPassword.getText() == null || txtPassword.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("오류");
				alert.setHeaderText("비밀번호가 입력되지 않았습니다.");
				alert.setContentText("비밀번호를 입력해주세요.");
				alert.show();
			}else {
				if(!txtPassword.getText().equals(txtPasswordTest.getText())){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("비밀번호 오류");
					alert.setHeaderText("비밀번호가 일치하지 않습니다.");
					alert.setContentText("다시 입력해주세요.");
					alert.show();
					txtPassword.setText(null);
					txtPasswordTest.setText(null);
				}else {
					if(txtPassword.getText().equals(loginCon.users.get(loginCon.userNumber).getPassword())) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("비밀번호 중복");
						alert.setHeaderText("비밀번호가 똑같습니다.");
						alert.setContentText("다른 비밀번호로 수정해주세요.");
						alert.show();
						txtPassword.setText(null);
						txtPasswordTest.setText(null);
					}else {
					loginCon.users.get(loginCon.userNumber).setPassword(txtPassword.getText());
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("변경 완료");
					alert.setHeaderText("비밀번호 변경.");
					alert.setContentText("비밀번호가 변경되었습니다.");
					alert.show();
				    txtPassword.setText(null);
				    txtPasswordTest.setText(null);
					}
				}
			}	
	}
	@FXML
	private void logoutButton() {
		try {
		Parent login = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Stage loginStage;
		Scene loginscene = new Scene(login);
		loginStage = Main.parentWindow;
		loginStage.setScene(loginscene);
		loginStage.centerOnScreen();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
