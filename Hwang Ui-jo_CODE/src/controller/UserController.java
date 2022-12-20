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
				alert.setTitle("����");
				alert.setHeaderText("��й�ȣ�� �Էµ��� �ʾҽ��ϴ�.");
				alert.setContentText("��й�ȣ�� �Է����ּ���.");
				alert.show();
			}else {
				if(!txtPassword.getText().equals(txtPasswordTest.getText())){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("��й�ȣ ����");
					alert.setHeaderText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					alert.setContentText("�ٽ� �Է����ּ���.");
					alert.show();
					txtPassword.setText(null);
					txtPasswordTest.setText(null);
				}else {
					if(txtPassword.getText().equals(loginCon.users.get(loginCon.userNumber).getPassword())) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("��й�ȣ �ߺ�");
						alert.setHeaderText("��й�ȣ�� �Ȱ����ϴ�.");
						alert.setContentText("�ٸ� ��й�ȣ�� �������ּ���.");
						alert.show();
						txtPassword.setText(null);
						txtPasswordTest.setText(null);
					}else {
					loginCon.users.get(loginCon.userNumber).setPassword(txtPassword.getText());
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("���� �Ϸ�");
					alert.setHeaderText("��й�ȣ ����.");
					alert.setContentText("��й�ȣ�� ����Ǿ����ϴ�.");
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
