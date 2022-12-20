package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Income {
	private String ID;
	private ObjectProperty<LocalDate> date;
	private final StringProperty name;
	private final IntegerProperty income;
	public Income(String ID,LocalDate date,String name,int income) {
		this.ID = ID;
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.name = new SimpleStringProperty(name);
		this.income = new SimpleIntegerProperty(income);
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
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty getNameProperty() {
		return name;
	}
	public int getIncome() {
		return income.get();
	}
	public void setIncome(int income) {
		this.income.set(income);
	}
	public IntegerProperty getIncomeProperty() {
		return income;
	}
}
