package control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import boundary.Read_excel;
import entity.Add_on;

/**
 * @author Zhouyang Wang
 * @version 2.0
 */
class ModifyMenuTest {

	@Test
	public void test() throws IOException {
		Read_excel.readAdd_onExcel();
//	Read_excel.readDishOptionExcel();

		Add_on add_on_0 = new Add_on("00000001", "Extra Nori", 1.00, true);

		Add_on.getAdd_on().add(add_on_0);

		ModifyMenu.modifyAddonPrice("00000001", 2.00);
		assertEquals(Add_on.getAdd_on().get(0).getPrice(), 2.00, 0.001);
		ModifyMenu.modifyAddonAvaliable("00000001", false);
		assertEquals(Add_on.getAdd_on().get(0).getAvailable(), false);

		ModifyMenu.addNewAddon("00000004", "fired tomato", 5.00, true);
		assertEquals(Add_on.getAdd_on().get(1).getPrice(), 5.00, 0.001);
		// fail("Not yet implemented");
	}

}
