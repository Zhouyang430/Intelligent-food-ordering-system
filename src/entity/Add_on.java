package entity;

import java.io.IOException;
import java.util.ArrayList;

import boundary.Write_excel;

/**
 * @author Du ruinian
 * Store the data flow in add-ons
 */
public class Add_on {
	private String add_onID;
	private String itemName;
	private Double price;
	private Boolean available;
	private static ArrayList<Add_on> add_on;
	
	public Add_on (String add_onID,String itemName,Double price,Boolean available) {
		this.add_onID=add_onID;
		this.itemName=itemName;
		this.price=price;
		this.available=available;
	}
	
	public String getAdd_onID() {
		return add_onID;
	}
	public void setAdd_onID(String add_onID) throws IOException {
		this.add_onID = add_onID;
		Write_excel.writeAdd_onExcel(add_on);
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) throws IOException {
		this.price = price;
		Write_excel.writeAdd_onExcel(add_on);
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) throws IOException {
		this.itemName = itemName;
		Write_excel.writeAdd_onExcel(add_on);
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) throws IOException {
		this.available = available;
		Write_excel.writeAdd_onExcel(add_on);
	}

	public static ArrayList<Add_on> getAdd_on() {
		return Add_on.add_on;
	}

	public static void setAdd_on(ArrayList<Add_on> add_on) {
		Add_on.add_on = add_on;
	}
	
}
