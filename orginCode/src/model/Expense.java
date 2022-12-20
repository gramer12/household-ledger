package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Expense {
	private String ID;
	private ObjectProperty<LocalDate> date;
	private final StringProperty type;
	private final StringProperty name;
	private final IntegerProperty expense;	
	private final StringProperty content;	
	
	public Expense(String ID,LocalDate date,String type,String name,int expense,String content) {
		this.ID = ID;
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.type = new SimpleStringProperty(type);
		this.name = new SimpleStringProperty(name);
		this.expense = new SimpleIntegerProperty(expense);
		this.content = new SimpleStringProperty(content);
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public LocalDate getDate() {
		return date.get();
	}
	public void setDate(LocalDate date) {
		this.date.set(date);
	}
	public ObjectProperty<LocalDate> getDateProperty(){
		return date;
	}
	public String getType() {
		return type.get();
	}
	public void setType(String type) {
		this.type.set(type);
	}
	public StringProperty getTypeProperty() {
		return type;
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
	public int getExpense() {
		return expense.get();
	}
	public void setExpense(int expense) {
		this.expense.set(expense);
	}
	public IntegerProperty getExpenseProperty() {
		return expense;
	}
	public String getContent() {
		return content.get();
	}
	public void setContent(String content) {
		this.content.set(content);
	}
	public StringProperty getContentProperty() {
		return content;
	}
	public static void add(Expense expense2) {
		// TODO Auto-generated method stub
		
	}

}
