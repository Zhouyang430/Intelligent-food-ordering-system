package control;

import entity.Manager;

/**
 * @author Du Ruinian
 *	Implement functions for managers to login
 */
public class ManagerLogin {
	/**
	 * Check ID and password
	 * @param managerID
	 * @param password
	 * @return true when correct
	 * false when wrong password or ID
	 */
	public static boolean loginValid(String managerID, String password) {

		int i;
		for (i = 0; i < Manager.getManager().size(); i++) {
			//System.out.println("find manager in file");
			if (Manager.getManager().get(i).getManagerID().contentEquals(managerID)) {
				String cusEcryPass = Manager.getManager().get(i).getPassword();// ecrypted password stored in files
				if (MD5Utils.getSaltverifyMd5AndSha(password, cusEcryPass)) {
					return true;
				}
					//System.out.println("password" + password + "cusEcryPass" + cusEcryPass);	
			}
		}
		System.out.println("find no manager in file");
		return false;

	}
}
