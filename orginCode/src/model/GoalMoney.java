package model;

import java.time.YearMonth;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class GoalMoney {
	private String ID;
	private ObjectProperty<YearMonth> date;
	private final IntegerProperty traffic;	
	private final IntegerProperty food;	
	private final IntegerProperty life;
	private final IntegerProperty medical;	
	private final IntegerProperty pleasure;
	private final IntegerProperty guitar;
	private final IntegerProperty total;
	
	public GoalMoney(String ID,YearMonth date,int traffic,int food,int life,int medical,int pleasure,int guitar,int total) {
		this.ID = ID;
		this.date = new SimpleObjectProperty<YearMonth>(date);
		this.traffic = new SimpleIntegerProperty(traffic);
		this.food = new SimpleIntegerProperty(food);
		this.life = new SimpleIntegerProperty(life);
		this.medical = new SimpleIntegerProperty(medical);
		this.pleasure = new SimpleIntegerProperty(pleasure);
		this.guitar = new SimpleIntegerProperty(guitar);
		this.total = new SimpleIntegerProperty(total);
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
	public int getTraffic() {
		return traffic.get();
	}
	public void setTraffic(int traffic) {
		this.traffic.set(traffic);
	}
	public IntegerProperty getTrafficProperty() {
		return traffic;
	}
	public int getFood() {
		return food.get();
	}
	public void setFood(int food) {
		this.food.set(food);
	}
	public IntegerProperty getFoodProperty() {
		return food;
	}
	public int getLife() {
		return life.get();
	}
	public void setLife(int life) {
		this.life.set(life);
	}
	public IntegerProperty getLifeProperty() {
		return life;
	}
	public int getMedical() {
		return medical.get();
	}
	public void setMedical(int medical) {
		this.medical.set(medical);
	}
	public IntegerProperty getMedicalProperty() {
		return medical;
	}
	public int getPleasure() {
		return pleasure.get();
	}
	public void setPleasure(int pleasure) {
		this.pleasure.set(pleasure);
	}
	public IntegerProperty getPleasureProperty() {
		return pleasure;
	}
	public int getGuitar() {
		return guitar.get();
	}
	public void setGuitar(int guitar) {
		this.guitar.set(guitar);
	}
	public IntegerProperty getGuitarProperty() {
		return guitar;
	}
	public int getTotal() {
		return total.get();
	}
	public void setTotal(int total) {
		this.total.set(total);
	}
	public IntegerProperty getTotalProperty() {
		return total;
	}
}
