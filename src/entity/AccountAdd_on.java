package entity;

import control.Query;

/**
 * @author Du Ruinian
 * It is used to temporarily store the data of add-ons chosen by customers
 */
public class AccountAdd_on {
	private String add_onID;
	private int amount;
	private Double price;
	
	public AccountAdd_on (String add_onID, int amount) {
		this.add_onID=add_onID;
		this.amount=amount;
		this.price=Query.getAdd_onPrice(add_onID);
	}
	
	public String getAdd_onID() {
		return add_onID;
	}

	public void setAdd_onID(String add_onID) {
		this.add_onID = add_onID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Double getPrice() {
		return this.price;
	}
	
	public String toString () {
		return this.add_onID+"\n"+this.amount+"\n"+this.price;
	}
}
