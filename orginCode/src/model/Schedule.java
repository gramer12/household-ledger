package model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Schedule {
	private String ID;
	private ObjectProperty<LocalDate> date;
	private final StringProperty name;
	private final StringProperty content;
	public Schedule(String ID,LocalDate date,String name,String content) {
		this.ID = ID;
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.name = new SimpleStringProperty(name);
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
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty getNameProperty() {
		return name;
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

}
