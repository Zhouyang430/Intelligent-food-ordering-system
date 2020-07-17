package control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import boundary.Read_excel;

/**
 * @author Du Ruinian
 *
 */
class StatisticsTest {

	/**
	 * 
	 */
	@Test
	public void testGetSpicinessStatistics() {		
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		int result[]=Statistics.getSpicinessStatistics();
		assertEquals(3, result[0]);
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetLastWeekAdd_onStatistics(){
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		assertEquals(10,Statistics.getLastWeekAdd_onStatistics().get(2).getAmount());
	}
	
	/**
	 * 
	 */
	@Test
	public void getLastWeekDishOptionStatistics() {
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		assertEquals(1,Statistics.getLastWeekDishOptionStatistics().get(0).getAmount());
		assertEquals(1,Statistics.getLastWeekDishOptionStatistics().get(14).getAmount());
	}
}
