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
			alert.setTitle("����");
			alert.setHeaderText("�̸��� �Էµ��� �ʾҽ��ϴ�.");
			alert.setContentText("�̸��� �Է����ּ���.");
			alert.show();
		}else {
			if(txtFirstNumber.getText() == null || txtFirstNumber.getText().equals("") || txtLastNumber.getText() == null || txtLastNumber.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("����");
				alert.setHeaderText("�ֹι�ȣ�� �Էµ��� �ʾҽ��ϴ�.");
				alert.setContentText("�ֹι�ȣ�� �Է����ּ���.");
				alert.show();
			}else {
				userCheck = userCheck();
		if(userCheck==false) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����");
			alert.setHeaderText("���� ����.");
			alert.setContentText("�ֹι�ȣ�� �ش��ϴ� ������ �����մϴ�.");
			alert.show();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("���� ����");
			alert.setHeaderText("������ ���� �� �ֽ��ϴ�.");
			alert.setContentText("������ �������� �ʽ��ϴ�.");
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
			alert.setTitle("����");
			alert.setHeaderText("���̵� �Էµ��� �ʾҽ��ϴ�.");
			alert.setContentText("���̵� �Է����ּ���.");
			alert.show();
		}else {
			if(IDTest == true) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���̵� ����");
				alert.setHeaderText("��� ������ ���̵�.");
				alert.setContentText("�ߺ��� ���̵� �����ϴ�.");
				alert.show();
				txtID.setEditable(false);
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���̵� �ߺ�");
				alert.setHeaderText("�ߺ��� ���̵� �ֽ��ϴ�.");
				alert.setContentText("�ٸ� ���̵�� ���弼��.");
				alert.show();
			}
		}
	}
	@FXML
	public void passwordTestButton() {
		if(txtPassword.getText() == null || txtPassword.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����");
			alert.setHeaderText("��й�ȣ�� �Էµ��� �ʾҽ��ϴ�.");
			alert.setContentText("��й�ȣ�� �Է����ּ���.");
			alert.show();
		}else {
			if(txtPassword.getText().equals(txtPasswordTest.getText())) {
				passwordTest = true;
			}
			if(passwordTest==true) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("��й�ȣ Ȯ��");
				alert.setHeaderText("��й�ȣ ���.");
				alert.setContentText("��й�ȣ�� �Ȱ����ϴ�.");
				alert.show();
				txtPassword.setEditable(false);
				txtPasswordTest.setEditable(false);
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("��й�ȣ ����");
				alert.setHeaderText("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				alert.setContentText("�ٽ� �õ����ּ���.");
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
			errorMessage += "����Ȯ���� ���ּ���.\n";
		}if(IDTest == false){
        	errorMessage += "���̵� �ߺ�Ȯ���� ���ּ���.\n";
	    }if(passwordTest == false) {
		    errorMessage += "��й�ȣ Ȯ���� ���ּ���.\n";
		}if(txtEmail.getText() == null || txtEmail.getText().equals("")) {
			errorMessage += "�̸����� �Է��ϼ���.\n";
		}if(txtPhoneNumber.getText() == null || txtPhoneNumber.getText().equals("")) {
			errorMessage += "��ȭ��ȣ�� �Է��ϼ���.\n";
		}
		if(errorMessage==""){
			loginCon.users.add(new User(txtName.getText(),txtFirstNumber.getText(),txtLastNumber.getText(),txtID.getText(),txtPassword.getText(),txtEmail.getText(),txtPhoneNumber.getText()));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ȸ������ ����");
			alert.setHeaderText("ȸ������ ����ϴ�.");
			alert.setContentText("�α��� ���ּ���.");
			alert.show();
			dialogStage.close();
			UserDAO userDAO = new UserDAO();
			int result = userDAO.saveUser(new User(txtName.getText(),txtFirstNumber.getText(),txtLastNumber.getText(),txtID.getText(),txtPassword.getText(),txtEmail.getText(),txtPhoneNumber.getText()));
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����");
			alert.setHeaderText("�ٽ� �Է����ּ���.");
			alert.setContentText(errorMessage);
			alert.show();
		}
	}
}
