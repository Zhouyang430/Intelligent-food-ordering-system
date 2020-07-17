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
class CustomerRegistTest {

	@Test
	public static void test() throws IOException {

		// Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		// Read_excel.readDishExcel();
		// Read_excel.readDishOptionExcel();
		// Read_excel.readOrderExcel();
		CustomerRegister.registerCus("Jean", "12345");
		assertEquals(Customer.getCustomer().get(0).getCustomerName(), "Jean");

	}

}
