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
			alert.setTitle("���̵� ã��");
			alert.setHeaderText("���̵� ã�ҽ��ϴ�.");
			alert.setContentText("���̵� : "+loginCon.users.get(searchNumber).getID());
			alert.show();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����");
			alert.setHeaderText("������ �������� �ʽ��ϴ�.");
			alert.setContentText(" Ȯ���� �ٽ�  �õ����ּ���.");
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
				alert.setTitle("��й�ȣ ã��");
				alert.setHeaderText("��й�ȣ�� ã�ҽ��ϴ�.");
				alert.setContentText("��й�ȣ : "+loginCon.users.get(searchNumber).getPassword());
				alert.show();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("����");
				alert.setHeaderText("������ �������� �ʽ��ϴ�.");
				alert.setContentText(" Ȯ���� �ٽ�  �õ����ּ���.");
				alert.show();
			}
			txtName2.setText(null);
			txtFirstNumber2.setText(null);
			txtLastNumber2.setText(null);
			txtID.setText(null);
		}
	}

