package control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import boundary.Read_excel;

class PaymentTest {

	@Test
	void testCheckFreePayment() throws IOException {
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();

		assertEquals(true, Payment.checkFreePayment("00000001"));
	}

}
