package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import model.UserDAO;

public class SignUpController {
private Stage dialogStage;
@FXML private TextField txtID;
@FXML private TextField txtName;
@FXML private PasswordField txtPassword;
@FXML private PasswordField txtPasswordTest;
@FXML private TextField txtEmail;
@FXML private TextField txtPhoneNumber;
@FXML private TextField txtFirstNumber;
@FXML private PasswordField txtLastNumber;
private LoginController loginCon;
private boolean userCheck = false;
private boolean IDTest = false;
private boolean passwordTest = false;
	public void setLogin(LoginController loginCon) {
		this.loginCon = loginCon;
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	@FXML
	public void cancleButton() {
		dialogStage.close();
	}
	@FXML
	public void userCheckButton() {
		if(txtName.getText() == null || txtName.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("오류");
			alert.setHeaderText("이름이 입력되지 않았습니다.");
			alert.setContentText("이름를 입력해주세요.");
			alert.show();
		}else {
			if(txtFirstNumber.getText() == null || txtFirstNumber.getText().equals("") || txtLastNumber.getText() == null || txtLastNumber.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("오류");
				alert.setHeaderText("주민번호가 입력되지 않았습니다.");
				alert.setContentText("주민번호를 입력해주세요.");
				alert.show();
			}else {
				userCheck = userCheck();
		if(userCheck==false) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("오류");
			alert.setHeaderText("계정 존재.");
			alert.setContentText("주민번호에 해당하는 계정이 존재합니다.");
			alert.show();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("생성 가능");
			alert.setHeaderText("계정을 만들 수 있습니다.");
			alert.setContentText("계정이 존재하지 않습니다.");
			alert.show();
			txtName.setEditable(false);
			txtFirstNumber.setEditable(false);
			txtLastNumber.setEditable(false);
		}
		}
		}
	}
	public boolean userCheck() {
		boolean userCheck = true;
		for(int i=0;i<loginCon.users.size();i++) {
			if(txtFirstNumber.getText().equals(loginCon.users.get(i).getFirstNumber()) && txtLastNumber.getText().equals(loginCon.users.get(i).getLastNumber())) {
				userCheck = false;
			}
		}
		return userCheck;
	}
	@FXML
	public void IDTestButton() {
		boolean IDTest = IDTest();
		if(txtID.getText() == null || txtID.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("오류");
			alert.setHeaderText("아이디가 입력되지 않았습니다.");
			alert.setContentText("아이디를 입력해주세요.");
			alert.show();
		}else {
			if(IDTest == true) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 가능");
				alert.setHeaderText("사용 가능한 아이디.");
				alert.setContentText("중복된 아이디가 없습니다.");
				alert.show();
				txtID.setEditable(false);
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("아이디 중복");
				alert.setHeaderText("중복된 아이디가 있습니다.");
				alert.setContentText("다른 아이디로 만드세요.");
				alert.show();
			}
		}
	}
	@FXML
	public void passwordTestButton() {
		if(txtPassword.getText() == null || txtPassword.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("오류");
			alert.setHeaderText("비밀번호가 입력되지 않았습니다.");
			alert.setContentText("비밀번호를 입력해주세요.");
			alert.show();
		}else {
			if(txtPassword.getText().equals(txtPasswordTest.getText())) {
				passwordTest = true;
			}
			if(passwordTest==true) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("비밀번호 확인");
				alert.setHeaderText("비밀번호 통과.");
				alert.setContentText("비밀번호가 똑같습니다.");
				alert.show();
				txtPassword.setEditable(false);
				txtPasswordTest.setEditable(false);
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("비밀번호 오류");
				alert.setHeaderText("비밀번호가 틀렸습니다.");
				alert.setContentText("다시 시도해주세요.");
				alert.show();
			}
		}
	}
	
	public boolean IDTest() {
		IDTest=true;
		for(int i=0;i<loginCon.users.size();i++) {
			if(txtID.getText().equals(loginCon.users.get(i).getID())) {
				IDTest=false;
			}
		}
		return IDTest;
	}
	public void CreatButton(ActionEvent event) throws Exception{
		String errorMessage = "";
		if(userCheck==false) {
			errorMessage += "정보확인을 해주세요.\n";
		}if(IDTest == false){
        	errorMessage += "아이디 중복확인을 해주세요.\n";
	    }if(passwordTest == false) {
		    errorMessage += "비밀번호 확인을 해주세요.\n";
		}if(txtEmail.getText() == null || txtEmail.getText().equals("")) {
			errorMessage += "이메일을 입력하세요.\n";
		}if(txtPhoneNumber.getText() == null || txtPhoneNumber.getText().equals("")) {
			errorMessage += "전화번호를 입력하세요.\n";
		}
		if(errorMessage==""){
			loginCon.users.add(new User(txtName.getText(),txtFirstNumber.getText(),txtLastNumber.getText(),txtID.getText(),txtPassword.getText(),txtEmail.getText(),txtPhoneNumber.getText()));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("회원가입 성공");
			alert.setHeaderText("회원가입 됬습니다.");
			alert.setContentText("로그인 해주세요.");
			alert.show();
			dialogStage.close();
			UserDAO userDAO = new UserDAO();
			int result = userDAO.saveUser(new User(txtName.getText(),txtFirstNumber.getText(),txtLastNumber.getText(),txtID.getText(),txtPassword.getText(),txtEmail.getText(),txtPhoneNumber.getText()));
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("오류");
			alert.setHeaderText("다시 입력해주세요.");
			alert.setContentText(errorMessage);
			alert.show();
		}
	}
}
