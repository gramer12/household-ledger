package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FindController {
	private Stage dialogStage;
	@FXML private TextField txtName1;
	@FXML private TextField txtFirstNumber1;
	@FXML private PasswordField txtLastNumber1;
	@FXML private TextField txtName2;
	@FXML private TextField txtID;
	@FXML private TextField txtFirstNumber2;
	@FXML private PasswordField txtLastNumber2;
	private LoginController loginCon;
	private int searchNumber;
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public void setLogin(LoginController loginCon) {
		this.loginCon = loginCon;
	}
	@FXML
	public void IDSearchButton() {
		boolean IDSearch = false;
		for(int i =0;i<loginCon.users.size();i++) {
			if(txtName1.getText().equals(loginCon.users.get(i).getName()) && txtFirstNumber1.getText().equals(loginCon.users.get(i).getFirstNumber()) &&  txtLastNumber1.getText().equals(loginCon.users.get(i).getLastNumber())) {
                   IDSearch = true;
                   searchNumber = i;
		}
		}
		if(IDSearch == true) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("아이디 찾기");
			alert.setHeaderText("아이디를 찾았습니다.");
			alert.setContentText("아이디 : "+loginCon.users.get(searchNumber).getID());
			alert.show();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("오류");
			alert.setHeaderText("계정이 존재하지 않습니다.");
			alert.setContentText(" 확인후 다시  시도해주세요.");
			alert.show();
		}
		txtName1.setText(null);
		txtFirstNumber1.setText(null);
		txtLastNumber1.setText(null);
	}
	@FXML
	public void PasswordSearchButton() {
		boolean PasswordSearch = false;
		for(int i =0;i<loginCon.users.size();i++) {
			if(txtName2.getText().equals(loginCon.users.get(i).getName()) && txtFirstNumber2.getText().equals(loginCon.users.get(i).getFirstNumber()) &&  txtLastNumber2.getText().equals(loginCon.users.get(i).getLastNumber()) && txtID.getText().equals(loginCon.users.get(i).getID())) {
                   PasswordSearch = true;
                   searchNumber = i;
		}
		}
			if(PasswordSearch == true) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("비밀번호 찾기");
				alert.setHeaderText("비밀번호를 찾았습니다.");
				alert.setContentText("비밀번호 : "+loginCon.users.get(searchNumber).getPassword());
				alert.show();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("오류");
				alert.setHeaderText("계정이 존재하지 않습니다.");
				alert.setContentText(" 확인후 다시  시도해주세요.");
				alert.show();
			}
			txtName2.setText(null);
			txtFirstNumber2.setText(null);
			txtLastNumber2.setText(null);
			txtID.setText(null);
		}
	}

