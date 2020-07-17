package control;

import java.io.IOException;
import java.util.ArrayList;

import entity.*;

/**
* This class is mainly to implement:
* the functions of queries according to given 
* @author Du Ruinian
* @version 2.0
*/
public class VipOperation {
	private static ArrayList<Customer> customer;
	
	/**
	   * This method returns stamps' count of a certain vip.
	   * If no such vip exists, return -1.
	   * @param vipID from GUI input.
	   * @return index or -1.
	   */
	public static int queryStampByVipID (String vipID) {
		customer=Customer.getCustomer();
		int i;
		for (i=0; i<customer.size(); i++) {
			if(customer.get(i).getVipID().contentEquals(vipID)) {
				return customer.get(i).getStamp();
			}
		}
		return -1;
	}
	
	/**
	   * This method will be called by GUI after one vip paid successfully.
	   * @param vipID from GUI input.
	   * @return 
	 * @throws IOException 
	   */
	public static void plusStamps (String vipID) throws IOException {
		for (int i=0; i<Customer.getCustomer().size(); i++) {
			if (vipID.contentEquals(Customer.getCustomer().get(i).getVipID())) {
				Customer.getCustomer().get(i).setStamp(Customer.getCustomer().get(i).getStamp()+1);
			}
		}
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
