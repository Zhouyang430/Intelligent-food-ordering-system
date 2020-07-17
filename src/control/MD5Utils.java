package control;

/**This class is to implement MD5 and sha-1 mixed + SALT algorithm 
 * to encrypt user's password
 * This class is to provide safety for user's information
 * @author Zhouyang Wang 
 *@version 1.0
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Utils {

	/**
	 * md5 and sha-1 mixed encryption
	 * 
	 * @param inputText the password which need to be encrypted
	 * @return String md5 and sha-1: the String after encryption
	 */
	public static String md5AndSha(String inputText) {
		return sha(md5(inputText));
	}

	/**
	 * md5 encryption
	 * 
	 * @param inputText the password which need to be encrypted
	 * @return String md5 the String after md5 encryption
	 */
	public static String md5(String inputText) {
		return encrypt(inputText, "md5");
	}

	/**
	 * sha-1 encryption
	 * 
	 * @param inputText the password which need to be encrypted
	 * @return sha-1 The encrypted password
	 */
	public static String sha(String inputText) {
		return encrypt(inputText, "sha-1");
	}

	/**
	 * Md5 or sha-1 encryption
	 *
	 * @param inputText     to encrypt
	 * @param algorithmName encryption algorithmName: md5 or sha-1, case-insensitive
	 *
	 * @return String md5 or sha-1 encrypted results
	 */
	private static String encrypt(String inputText, String algorithmName) {
		if (inputText == null || "".equals(inputText.trim())) {
			throw new IllegalArgumentException("Please enter something to encrypt");
		}
		if (algorithmName == null || "".equals(algorithmName.trim())) {
			algorithmName = "md5";
		}
		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(inputText.getBytes("UTF8"));
			byte s[] = m.digest();
			return hex(s);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptText;
	}

	/**
	 * byte[] byte array converted to hexadecimal string
	 *
	 * @param arr byte[] byte array to convert
	 *
	 * @return String returns a hexadecimal String
	 */
	private static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	/**
	 * Generate a password that contains random salt
	 *
	 * @param password to encrypt
	 * @return String contains the password for the random salt
	 */

	public static String getSaltMd5AndSha(String password) {
// Generate a 16-bit random number
		Random random = new Random();
		StringBuilder sBuilder = new StringBuilder(16);
		sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
		int len = sBuilder.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sBuilder.append("0");
			}
		}
// Generates the final encryption salt
		String salt = sBuilder.toString();
		password = md5AndSha(password + salt);

		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = password.charAt(i / 3 * 2);
			char c = salt.charAt(i / 3);
			cs[i + 1] = c;
			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
		}
		return String.valueOf(cs);
	}

	/**
	 * verify that the salt is the same as the original password
	 *
	 * @param password
	 * @param password
	 * @return Boolean true means that it is identical to the original password.
	 *         False means that it is inconsistent with the original password
	 */
	public static boolean getSaltverifyMd5AndSha(String password, String md5str) {
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5str.charAt(i);
			cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
			cs2[i / 3] = md5str.charAt(i + 1);
		}
		String salt = new String(cs2);
		String encrypPassword = md5AndSha(password + salt);

// The last eight digits of the encrypted password are removed
		encrypPassword = encrypPassword.substring(0, encrypPassword.length() - 8);

		return encrypPassword.contentEquals(String.valueOf(cs1));
	}
//test
	/**
	 * public static void main(String[] args) { // Original password String
	 * plaintext = "123456";
	 * 
	 * // Gets the MD5 value after SALT String ciphertext =
	 * MD5Utils.getSaltMd5AndSha(plaintext); System.out.println("MD5 after SALT锛�" +
	 * ciphertext); System.out.println("Is it the same string:" +
	 * MD5Utils.getSaltverifyMd5AndSha(plaintext, ciphertext)); }
	 */
}
