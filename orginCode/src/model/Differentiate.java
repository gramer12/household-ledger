package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Differentiate {
	private String ID;
	private ObjectProperty<LocalDate> date;
	private final IntegerProperty dayExpense;
	
	public Differentiate(String ID,LocalDate date,int dayExpense) {
		    this.ID = ID;
			this.date = new SimpleObjectProperty<LocalDate>(date);
		    this.dayExpense = new SimpleIntegerProperty(dayExpense);
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
	public int getDayExpense() {
		return dayExpense.get();
	}
	public void setDayExpense(int dayExpense) {
		this.dayExpense.set(dayExpense);
	}
	public void plusDayExpense(int dayExpense) {
		int a =this.dayExpense.intValue();
		a += dayExpense;
		this.dayExpense.set(a);
	}
	public void minusDayExpense(int dayExpense) {
		int a = this.dayExpense.intValue();
		a -= dayExpense;
		this.dayExpense.set(a);
	}
	public IntegerProperty getDayExpenseProperty() {
		return dayExpense;
	}
}
