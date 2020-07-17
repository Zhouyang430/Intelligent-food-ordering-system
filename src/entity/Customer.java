package entity;

import java.io.IOException;
import java.util.ArrayList;

import boundary.Write_excel;

/**
 * @author Du Ruinina
 * Store the data flow in customers
 */
public class Customer {
	private String customerID;
	private String customerName;
	private String password;
	private Boolean isVip;
	private String firstname;
	private String surname;
	private String e_mail;
	private String phone;
	private String vipID;
	private int stamp;
	private static ArrayList<Customer> customer;
	
	public Customer(String customerID,String customerName,String password,Boolean isVip, String firstname,String surname,String e_mail,String phone, String vipID, int stamp) {
		this.customerID=customerID;
		this.customerName=customerName;
		this.password=password;
		this.isVip=isVip;
		this.firstname=firstname;
		this.surname=surname;
		this.e_mail=e_mail;
		this.phone=phone;
		this.vipID=vipID;
		this.stamp=stamp;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(String customerID) throws IOException {
		this.customerID = customerID;
		Write_excel.writeCustomerExcel(customer);
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) throws IOException {
		this.customerName = customerName;
		Write_excel.writeCustomerExcel(customer);
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) throws IOException {
		this.password = password;
		Write_excel.writeCustomerExcel(customer);
	}
	
	public Boolean getIsVip() {
		return isVip;
	}
	
	public void setIsVip(Boolean isVip) throws IOException {
		this.isVip = isVip;
		Write_excel.writeCustomerExcel(customer);
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) throws IOException {
		this.firstname = firstname;
		Write_excel.writeCustomerExcel(customer);
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) throws IOException {
		this.surname = surname;
		Write_excel.writeCustomerExcel(customer);
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) throws IOException {
		this.phone = phone;
		Write_excel.writeCustomerExcel(customer);
	}
	
	public String getE_mail() {
		return e_mail;
	}
	
	public void setE_mail(String e_mail) throws IOException {
		this.e_mail = e_mail;
		Write_excel.writeCustomerExcel(customer);
	}

	public String getVipID() {
		return vipID;
	}

	public void setVipID(String vipID) throws IOException {
		this.vipID = vipID;
		Write_excel.writeCustomerExcel(customer);
	}

	public int getStamp() {
		return stamp;
	}

	public void setStamp(int stamp) throws IOException {
		this.stamp = stamp;
		Write_excel.writeCustomerExcel(customer);
	}

	public static ArrayList<Customer> getCustomer() {
		return Customer.customer;
	}

	public static void setCustomer(ArrayList<Customer> customer) {
		Customer.customer = customer;
	} 
}
