package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private final StringProperty name;
	private final StringProperty firstNumber;
	private final StringProperty lastNumber;
	private final StringProperty ID;
	private final StringProperty password;
	private final StringProperty Email;
	private final StringProperty phoneNumber;
	
	public User(String name,String firstNumber,String lastNumber,String ID,String password,String Email,String phoneNumber) {
		this.name = new SimpleStringProperty(name);
		this.firstNumber = new SimpleStringProperty(firstNumber);
		this.lastNumber =  new SimpleStringProperty(lastNumber);
		this.ID = new SimpleStringProperty(ID);
		this.password = new SimpleStringProperty(password);
		this.Email = new SimpleStringProperty(Email);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
	}
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty getNameProperty() {
		return name;
	}
	public String getFirstNumber() {
		return firstNumber.get();
	}
	public void setFirstNumber(String firstNumber) {
		this.firstNumber.set(firstNumber);
	}
	public StringProperty getFirstNumberProperty() {
		return firstNumber;
	}
	public String getLastNumber() {
		return lastNumber.get();
	}
	public void setLastNumber(String lastNumber) {
		this.lastNumber.set(lastNumber);
	}
	public StringProperty getLastNumberProperty() {
		return lastNumber;
	}
	public String getID() {
		return ID.get();
	}
	public void setID(String ID) {
		this.ID.set(ID);
	}
	public StringProperty getIDProperty() {
		return ID;
	}
	public String getPassword() {
		return password.get();
	}
	public void setPassword(String password) {
		this.password.set(password);;
	}
	public StringProperty getPasswordProperty() {
		return password;
	}
	public String getEmail() {
		return Email.get();
	}
	public void setEmail(String Email) {
		this.Email.set(Email);
	}
	public StringProperty getEmailProperty() {
		return Email;
	}
	public String getPhoneNumber() {
		return phoneNumber.get();
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	public StringProperty getPhoneProperty() {
		return phoneNumber;
	}

}
