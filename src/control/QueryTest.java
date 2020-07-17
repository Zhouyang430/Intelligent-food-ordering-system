package control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import boundary.Read_excel;

/**
 * @author Du Ruinian
 *
 */
class QueryTest {

	@Test
	void testGetAdd_onPrice() {
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		assertEquals(1.0,Query.getAdd_onPrice("00000001"));
	}
	
	@Test
	void testGetDishOptionPrice() {
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		assertEquals(1.0,Query.getDishOptionPrice("00000001", "00000001"));
	}
	
	@Test
	void testGetAdd_onID() {
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		assertEquals("00000003",Query.getAdd_onID("Bamboo shoots"));
	}
	
	@Test
	void testGetCustomerIndex() {
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		assertEquals(0,Query.getCustomerIndex("00000001"));
	}
	
	@Test
	void testGetDishName() {
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		assertEquals("Soup",Query.getDishName("00000001"));
	}
	
	@Test
	void testGetDishOptionName() {
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		assertEquals("Tonkotsu",Query.getDishOptionName("00000001", "00000001"));
	}
}
