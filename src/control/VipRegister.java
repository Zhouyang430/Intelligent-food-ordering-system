package control;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Random;

import entity.Customer;

/**
 * This class is mainly to implement: The function of registering VIP for the
 * user
 * 
 * @author Zhouyang Wang
 * @version 3.0 
 */

public class VipRegister {
	private final static String file="";
	// customerArray=Customer.getCustomer();
	/**
	 * @param firstName
	 * @param surName
	 * @param email
	 * @param phone
	 * @throws IOException
	 */
	public static void register(String customerID, String firstName, String surName, String email, String phone)
			throws IOException {
		// email and phone is optional,at least one of them, if one is null just store
		// as null

		int i;
		for (i = 0; i < Customer.getCustomer().size(); i++) {
			if (Customer.getCustomer().get(i).getCustomerID().contentEquals(customerID)) {
				if (Customer.getCustomer().get(i).getIsVip() == false) {
					Customer.getCustomer().get(i).setFirstname(firstName);
					Customer.getCustomer().get(i).setSurname(surName);
					Customer.getCustomer().get(i).setE_mail(email);
					Customer.getCustomer().get(i).setPhone(phone);
				}
				Customer.getCustomer().get(i).setIsVip(true);
				Customer.getCustomer().get(i).setVipID(genRandomNum());

				System.out.println("Welcome! Your VipID is: " + Customer.getCustomer().get(i).getVipID());
				// sendMessage
				sendMessage(phone);
				sendEmail(email);

				printRegisterMessage(Customer.getCustomer().get(i).getFirstname(),
						Customer.getCustomer().get(i).getSurname(), Customer.getCustomer().get(i).getE_mail(),
						Customer.getCustomer().get(i).getPhone());// print a ticket with the registration details

			}
		}
		// Customer customer = new Customer(null, null, null, false, null, null, null,
		// null, null, 0);
		// Customer.getCustomer().add(customer);// add new Customer to Customer
		// arraylist

	}

	/**
	 * @return generate non-repetitive 8-digit VipNumber
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
		}while(!Query.checkVipID(pwd.toString()));

		
		return pwd.toString();
	}

	/**
	 * @return generate register information ticket
	 */
	public static void printRegisterMessage(String firstName, String surName, String email, String phone) {

		getDate();

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file + ".txt");// Create file
			fileWriter.write("Register information" + "\n");
			if (firstName != null)
				fileWriter.write("FistName:" + firstName + "\n");
			if (surName != null)
				fileWriter.write("SurName:" + surName + "\n");
			if (email != null)
				fileWriter.write("Email:" + email + "\n");
			else
				fileWriter.write("No Email is used to register yet" + "\n");
			if (phone != null)
				fileWriter.write("Phone:" + phone + "\n");
			else
				fileWriter.write("No Phone number is used to register yet" + "\n");

			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return current register date and time
	 */
	public static String getDate() {
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Register date:" + df.format(day));
		return df.format(day);
	}

	/**
	   * This method is used to send message to a certain phone
	   * @param phone from GUI input.
	   * @return nothing
	   */
	public static void sendMessage(String phone) {
		
	}
	
	/**
	   * This method is used to send message to a certain e-mail
	   * @param e_mail from GUI input.
	   * @return nothing
	   */
	public static void sendEmail(String e_mail) {
		
	}
	
}
