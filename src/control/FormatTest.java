package control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FormatTest {

	@Test
	void test() {
		//fail("Not yet implemented");
		assertEquals(Format.isEmail("zhouyang@qmul.ac.uk"),true);
		assertEquals(Format.isPhone("13993654965"),true);
		assertEquals(Format.isName("zhouyang"),true);
		assertEquals(Format.isEmail("zhouyangqqqqwd"),false);
		assertEquals(Format.isPhone("139sad"),false);
		assertEquals(Format.isName("zhouyang###"),false);
	
	}

}
