package control;

import entity.Customer;

/**
 * This class implements logic login validation (if customer id matches the
 * password)
 * 
 * @author Zhouyang Wang
 * @version 2.0
 */
public class CustomerLogin {

	/**
	 * @param customerID
	 * @param password
	 * @return if the customer id matches the password
	 */
	public static boolean loginValid(String customerID, String password) {

		int i;
		//System.out.println("Customer.getCustomer().size()=" + Customer.getCustomer().size());
		for (i = 0; i < Customer.getCustomer().size(); i++) {
			System.out.println("find customer in file");
			if (Customer.getCustomer().get(i).getCustomerID().contentEquals(customerID)) {
				String cusEcryPass = Customer.getCustomer().get(i).getPassword();// ecrypted password stored in files
				if (MD5Utils.getSaltverifyMd5AndSha(password, cusEcryPass)) {
					System.out.println("password" + password + "cusEcryPass" + cusEcryPass);
				return true;
					
				}
					
			}
		}
		System.out.println("find no customer in file");
		return false;

	}
}
