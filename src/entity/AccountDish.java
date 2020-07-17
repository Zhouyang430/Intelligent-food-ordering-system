package entity;

import control.Query;

/**
 * @author Du Ruinian
 * It is used to temporarily store the data of dishes chosen by customers
 */
public class AccountDish {
	private String dishID;
	private String optionID;
	private Double price;
	
	public AccountDish ( String dishID, String optionID) {
		this.dishID=dishID;
		this.optionID=optionID;
		this.price=(Query.getDishOptionPrice(dishID, optionID));
	}
	public String getOptionID() {
		return optionID;
	}
	public void setOptionID(String optionID) {
		this.optionID = optionID;
	}
	public String getDishID() {
		return dishID;
	}
	public void setDishID(String dishID) {
		this.dishID = dishID;
	}
	
	public String toString() {
		return this.dishID+"\n"+this.optionID+"\n";
	}
	public Double getPrice() {
		return price;
	}
}
