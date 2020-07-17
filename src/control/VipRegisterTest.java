package control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import boundary.Read_excel;
import entity.Customer;

/**
 * @author Zhouyang Wang
 * @version 2.0
 */
class VipRegisterTest {

	@Test
	public void test() throws IOException {
		Read_excel.readCustomerExcel();
		// Read_excel.readDishExcel();
		// Read_excel.readDishOptionExcel();
		// Read_excel.readOrderExcel();
		VipRegister.register("025O48G0P7", "Jean", "Hart", "safas1511S@qmul.ac.uk", "156151951");
		assertEquals(Customer.getCustomer().get(0).getE_mail(), "safas1511S@qmul.ac.uk");
	}

}
