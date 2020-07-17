package control;

public class Format {
	/**This class uses regular expressions to verify validation of user's
	 * Name,Email,phone number
	 *@author Zhouyang Wang
	 *@verion 2.0 
	 */
	public Format(){
		
	}
/*public static void main (String[] args) {
		System.out.print(isEmail("zhouyang@qmul.ac.uk"));
		System.out.print(isPhone("13993654965"));
		System.out.print(isName("zhouyang"));
		
	}
	*/
	/**
	 * @param string
	 * @return if email format is valid
	 */
	public static boolean isEmail(String string) {
        if (string == null)
            return false;
  
        String reg="[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+";
        if (string.matches(reg)) {
        System.out.println("Email valid!");
        return true;
        }else {
        System.out.println("Email invalid!");
        return false;
        }
    }
	/**support worldwide phone number
	 * @param string
	 * @return if phone number format is valid
	 */
	public static boolean isPhone(String string) {
       if (string == null)
            return false;
  
        String reg="((00){1}[1-9]{1}[0-9]{1,3}|86|\\+86)?1[3458]\\d{9}";
        if (string.matches(reg)) {
        System.out.println("Phone number valid!");
        return true;
        }else {
        System.out.println("Phone number invalid!");
        return false;
        }
    }
	/**support English Name
	 * @param string
	 * @return if name format is valid
	 */
	public static boolean isName(String string) {
        if (string == null)
            return false;
  
        String reg="[\\u4E00-\\u9FA5A-Za-z\\s]+(·[\\u4E00-\\u9FA5A-Za-z]+)*";
        if (string.matches(reg)) {
        System.out.println("Name format valid!");
        return true;
        }else {
        System.out.println("Name format invalid!");
        return false;
        }
    }
	

}
