package control;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import boundary.Write_excel;

import java.util.ArrayList;

import entity.*;

/**
* This class is mainly to implement:
* the functions of Payments
* @author Du Ruinian
* @version 2.0
*/

public class Payment {
	private final static Double fixedPrice=9.9;
	private final static String file="";
	
	/**
	 * Should be used before plusStamps
	 * @param customerID
	 * @return if the customer is vip, return true
	 * if not enough stamps, return false
	 * if not vip, return false
	 * if no such customer
	 * @throws IOException
	 */
	public static Boolean checkFreePayment (String vipID) throws IOException {
		for (int i=0; i<Customer.getCustomer().size(); i++) {
			if(Customer.getCustomer().get(i).getVipID().contentEquals(vipID)) {
				if (Customer.getCustomer().get(i).getIsVip()) {
					if (Customer.getCustomer().get(i).getStamp()==10) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		return null;
	}
	
	/**
	 * Calculate total price of the bill
	 * @param accountDish
	 * @param accountAdd_on
	 * @return sumDish+sumAdd_on
	 */
	public static Double calculateBillAmount (ArrayList<AccountDish> accountDish, ArrayList<AccountAdd_on> accountAdd_on) {
		Double sumDish=0.00;
		Double sumAdd_on=0.00;
		
		for(int i=0; i<accountDish.size(); i++) {
			sumDish=accountDish.get(i).getPrice()+sumDish;
		}
		
		for (int i=0; i<accountAdd_on.size();i++) {
			sumAdd_on=accountAdd_on.get(i).getPrice()*accountAdd_on.get(i).getAmount()+sumAdd_on;
		}
		
		return sumDish+sumAdd_on+fixedPrice;
	}
	
	/**
	 * To print the current order and write new orders into order.xlxs
	 * @param accountDish
	 * @param accountAdd_on
	 * @param customer
	 * @param spiciness
	 * @param dining
	 * @param payOption
	 */
	public static void printBill (ArrayList<AccountDish> accountDish, ArrayList<AccountAdd_on> accountAdd_on, Customer customer, String spiciness, String dining, String payOption) {
		FileWriter fileWriter = null;
		String orderID=genRandomNum();
		String dishes=null;
		String add_ons=null;
		Date date = new Date();
		Double bill=calculateBillAmount(accountDish,accountAdd_on);
		try {
			fileWriter = new FileWriter(file+orderID+".txt");// Create file
			fileWriter.write("OrderID"+orderID+"\n");
			fileWriter.write("CustomerID:"+customer.getCustomerID()+"\n");
			fileWriter.write("CustomerName:"+customer.getCustomerName()+"\n");
			fileWriter.write("Is Vip?:"+customer.getIsVip()+"\n");
			fileWriter.write("Dishes ordered:"+"\n");
			
			for (int i=0; i<accountDish.size(); i++) {
				fileWriter.write("Dish name: "+Query.getDishName(accountDish.get(i).getDishID())+", ");
				fileWriter.write("Option name: "+Query.getDishOptionName(accountDish.get(i).getDishID(), accountDish.get(i).getOptionID())+", ");
				fileWriter.write("Price: "+accountDish.get(i).getPrice()+"\n");
				dishes=dishes+accountDish.get(i).getDishID()+","+accountDish.get(i).getOptionID()+","+accountDish.get(i).getPrice()+";";
			}
			
			for (int i=0; i<accountAdd_on.size(); i++) {
				fileWriter.write("Add_on name: "+Query.getAdd_onName(accountAdd_on.get(i).getAdd_onID())+", ");
				fileWriter.write("Price: "+accountAdd_on.get(i).getPrice()+", ");
				fileWriter.write("Amount: "+accountAdd_on.get(i).getAmount()+"\n");
				add_ons=add_ons+accountAdd_on.get(i).getAdd_onID()+","+accountAdd_on.get(i).getPrice()+","+accountAdd_on.get(i).getAmount()+";";
			}
			
			fileWriter.write("Spiciness: "+spiciness+"\n");
			fileWriter.write("Dining: "+dining+"\n");
			fileWriter.write("Payment option: "+payOption+"\n");
			fileWriter.write("Total price: "+bill+"\n");
			
			Order order= new Order(orderID, customer.getCustomerID(), dishes, add_ons, spiciness, dining, bill, date);
			Order.getOrder().add(order);
			Write_excel.writeOrderExcel(Order.getOrder());
			fileWriter.write("\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * To return a totally new ID
	 * @return new order ID
	 */
	public static String genRandomNum() {
		int maxNum = 36;
		int i;
		int count = 0;
		StringBuffer pwd;
		do {
				char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
				pwd = new StringBuffer("");
				Random r = new Random();
				while (count < 10) {
					i = Math.abs(r.nextInt(maxNum));
					if (i >= 0 && i < str.length) {
						pwd.append(str[i]);
						count++;
					}
				}	
		}while(!Query.checkOrderID(pwd.toString()));

		
		return pwd.toString();
	}
}
