package control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import boundary.Read_excel;

class VipOperationTest {

	@Test
	void testQueryStampByVipID() {
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		
		assertEquals(10,VipOperation.queryStampByVipID("00000001"));
	}

}
