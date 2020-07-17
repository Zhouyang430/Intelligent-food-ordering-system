package control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * @author Zhouyang Wang
 * @version 2.0
 */
class CustomerLoginTest {

	/**
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		CustomerRegistTest.test();
		assertEquals(CustomerLogin.loginValid("025O48G0P7", "12345"),true);
	}

}
