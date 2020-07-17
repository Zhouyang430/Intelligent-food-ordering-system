package control;

import java.io.IOException;

import java.util.Random;

import entity.Customer;

/**
 * This class implements customer register in the order system for the first
 * time
 * 
 * @author Zhouyang Wang
 * @version 2.0
 */
public class CustomerRegister {

	public static void registerCus(String customerName, String password) throws IOException {
		Customer customer = new Customer(null, null, null, false, null, null, null, null, null, 0);
		Customer.getCustomer().add(customer);
		customer.setCustomerName(customerName);
		String cryptedPassword = MD5Utils.getSaltMd5AndSha(password);// encryption
		customer.setPassword(cryptedPassword);// write back to the file
		String customerID = genRandomNum();
		customer.setCustomerID(customerID);
		System.out.println("Your customerID is:" + customerID);

	}

	/**
	 * @return generate non-repetitive 10-digit CustomerID CutomerID is longer than
	 *         VIPid, assumption: the number of customer is larger than VIP customer
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
		}while(!Query.checkCustomerID(pwd.toString()));

		
		return pwd.toString();
	}

	
	/**
	   * This method is used to send message to a certain phone
	   * @param phone from GUI input.
	   * @return nothing
	   */
	public static void sendStampMessage(String phone) {
		
	}
	
	/**
	   * This method is used to send message to a certain e-mail
	   * @param e_mail from GUI input.
	   * @return nothing
	   */
	public static void sendStampEmail(String e_mail) {
		
	}
}
