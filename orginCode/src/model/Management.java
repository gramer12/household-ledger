package model;

import java.time.YearMonth;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Management {
	private String ID;
	private ObjectProperty<YearMonth> date;
	private IntegerProperty monthExpense;
	private IntegerProperty monthIncome;
	
	public Management(String ID,YearMonth date,int monthExpense,int monthIncome) {
		this.ID = ID;
		this.date = new SimpleObjectProperty<YearMonth>(date);
		this.monthExpense = new SimpleIntegerProperty(monthExpense);
		this.monthIncome = new SimpleIntegerProperty(monthIncome);
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public YearMonth getDate() {
		return date.get();
	}
	public void setDate(YearMonth date) {
		this.date.set(date);
	}
	public ObjectProperty<YearMonth> getDateProperty(){
		return date;
	}
	public int getMonthExpense() {
		return monthExpense.get();
	}
	public void setMonthExpense(int monthExpense) {
		this.monthExpense.set(monthExpense);
	}
	public IntegerProperty getMonthExpenseProperty() {
		return monthExpense;
	}
	public int getMonthIncome() {
		return monthIncome.get();
	}
	public void setMonthIncome(int monthIncome) {
		this.monthIncome.set(monthIncome);
	}
	public IntegerProperty getMonthIncomeProperty() {
		return monthIncome;
	}
	public void plusMonthExpense(int expense) {
		int a = this.monthExpense.get();
		a += expense;
		this.monthExpense.set(a);
	}
	public void minusMonthExpense(int expense) {
		int a = this.monthExpense.get();
		a -= expense;
		this.monthExpense.set(a);
	}
	public void plusMonthIncome(int income) {
		int a = this.monthIncome.get();
		a += income;
		this.monthIncome.set(a);
	}
	public void minusMonthIncome(int income) {
		int a = this.monthIncome.get();
		a -= income;
		this.monthIncome.set(a);
	}

}
