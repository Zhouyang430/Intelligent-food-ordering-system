package control;

import java.io.IOException;

import boundary.Write_excel;
import entity.Add_on;
import entity.Dish;
import entity.DishOption;

/**
 * This class is mainly to implement: The function to modify the price of the
 * options/add_ons and to indicate if an option/ an add_on is not available. And
 * add options/add_ons
 * 
 * @author Zhouyang Wang
 * @version 3.0 change the information of an existing customer directly
 */
public class ModifyMenu {

	/**
	 * this method is for manager to modify price of add_ons
	 * 
	 * @param add_onID from GUI input
	 * @param price    from GUI input
	 * @throws IOException
	 */
	public static void modifyAddonPrice(String add_onID, double price) throws IOException {

		int i;
		for (i = 0; i < Add_on.getAdd_on().size(); i++) {
			if (Add_on.getAdd_on().get(i).getAdd_onID().contentEquals(add_onID)) {
				Add_on.getAdd_on().get(i).setPrice(price);

			}
		}
	}

	/**
	 * this method is for manager to modify avaliability of add_ons
	 * 
	 * @param add_onID  from GUI input
	 * @param available from GUI input
	 * @throws IOException
	 */
	public static void modifyAddonAvaliable(String add_onID, boolean available) throws IOException {

		int i;
		for (i = 0; i < Add_on.getAdd_on().size(); i++) {
			if (Add_on.getAdd_on().get(i).getAdd_onID().contentEquals(add_onID)) {
				Add_on.getAdd_on().get(i).setAvailable(available);
			}
		}

	}

	/**
	 * this method is for manager to modify availability of dish options
	 * 
	 * @param optionID  from GUI input
	 * @param available from GUI input
	 * @throws IOException
	 */
	public static void modifyOptionAvaliable(String optionID, boolean available) throws IOException {

		int i;
		for (i = 0; i < DishOption.getDishOption().size(); i++) {
			if (DishOption.getDishOption().get(i).getOptionID().contentEquals(optionID)) {
				DishOption.getDishOption().get(i).setAvailable(available);
			}
		}

	}
	
	/**
	 * @param dishID
	 * @param available
	 * @throws IOException
	 */
	public static void modifyDishAvaliable(String dishID, boolean available) throws IOException {

		int i;
		for (i = 0; i < DishOption.getDishOption().size(); i++) {
			if (DishOption.getDishOption().get(i).getDishID().contentEquals(dishID)) {
				DishOption.getDishOption().get(i).setAvailable(available);
			}
		}
		
		for (i = 0; i < Dish.getDish().size(); i++) {
			if (Dish.getDish().get(i).getDishID().contentEquals(dishID)) {
				Dish.getDish().get(i).setAvailable(available);
			}
		}

	}

	/**
	 * this method is for manager to modify menu of dish options
	 * 
	 * @param optionID from GUI input
	 * @param price    from GUI input
	 * @throws IOException
	 */
	public static void modifyOptionPrice(String dishID, String optionID, double price) throws IOException {

		int i;
		for (i = 0; i < DishOption.getDishOption().size(); i++) {
			if (DishOption.getDishOption().get(i).getDishID().contentEquals(dishID) && DishOption.getDishOption().get(i).getOptionID().contentEquals(optionID)) {
				DishOption.getDishOption().get(i).setPrice(price);
			}
		}
	}

	/**
	 * this method is for manager to add new add_ons on the menu
	 * 
	 * @param add_onID
	 * @param itemName
	 * @param price
	 * @param available
	 * @throws IOException
	 */
	public static void addNewAddon(String add_onID, String itemName, Double price, Boolean available)
			throws IOException {

		Add_on newAddon = new Add_on(add_onID, itemName, price, available);
		Add_on.getAdd_on().add(newAddon);
		Write_excel.writeAdd_onExcel(Add_on.getAdd_on());

	}

	/**
	 * this method is for manager to add new dish options on the menu
	 * 
	 * @param dishID
	 * @param optionID
	 * @param optionName
	 * @param price
	 * @param available
	 * @throws IOException
	 */
	public static void addNewOption(String dishID, String optionID, String optionName, Double price, Boolean available)
			throws IOException {

		DishOption newDishOption = new DishOption(dishID, optionID, optionName, price, available);
		DishOption.getDishOption().add(newDishOption);
		Write_excel.writeDishOptionExcel(DishOption.getDishOption());

	}
}
