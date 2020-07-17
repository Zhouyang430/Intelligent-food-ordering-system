package control;

import java.util.*;
import java.util.ArrayList;

import entity.Add_on;
import entity.Customer;
import entity.Dish;
import entity.DishOption;
import entity.Manager;
import entity.Order;
import entity.StatisticsAdd_on;
import entity.StatisticsDishOption;


/**
 * @author Du Ruinian
 *
 */
public class Query {
	/**
	 * @param dishID
	 * @return dishName according specific dishID
	 * else return null
	 */
	public static String getDishName(String dishID) {
		ArrayList<Dish> dish=Dish.getDish();
		for (int i = 0; i<dish.size(); i++) {
			if (dishID.contentEquals(dish.get(i).getDishID())) {
				return dish.get(i).getItemName();
			}
		}
		return null;
	}
	
	/**
	 * @param ManagerID
	 * @return
	 */
	public static String getManagerName(String ManagerID) {
		ArrayList<Manager> manager=Manager.getManager();
		for (int i = 0; i<manager.size(); i++) {
			if (ManagerID.contentEquals(manager.get(i).getManagerID())) {
				return manager.get(i).getManagerName();
			}
		}
		return null;
	}
	
	/**
	 * @param dishName
	 * @return
	 */
	public static String getDishID(String dishName) {
		ArrayList<Dish> dish=Dish.getDish();
		for (int i = 0; i<dish.size(); i++) {
			if (dishName.contentEquals(dish.get(i).getItemName())) {
				return dish.get(i).getDishID();
			}
		}
		return null;
	}
	
	/**
	 * @param dishName
	 * @return
	 */
	public static String getDishOptionID(String dishName,String optionName) {
		ArrayList<DishOption> dishOption=DishOption.getDishOption();
		for (int i = 0; i<dishOption.size(); i++) {
			if (optionName.contentEquals(dishOption.get(i).getOptionName())  &&  dishName.contentEquals(Query.getDishName(dishOption.get(i).getDishID()))) {
				return dishOption.get(i).getOptionID();
			}
		}
		return null;
	}
	
	public static String getAdd_onName(String Add_onID) {
		for (int i = 0; i<Add_on.getAdd_on().size(); i++) {
			if (Add_onID.contentEquals(Add_on.getAdd_on().get(i).getAdd_onID())) {
				return Add_on.getAdd_on().get(i).getItemName();
			}
		}
		return null;
	}
	
	/**
	 * @param dishName
	 * @return
	 */
	public static String getAdd_onID(String Add_onName) {
		for (int i = 0; i<Add_on.getAdd_on().size(); i++) {
			if (Add_onName.contentEquals(Add_on.getAdd_on().get(i).getItemName())) {
				return Add_on.getAdd_on().get(i).getAdd_onID();
			}
		}
		return null;
	}
	
	/**
	 * @param customerID
	 * @return
	 */
	public static int getCustomerIndex(String customerID) {
		ArrayList<Customer> customer=Customer.getCustomer();
		for (int i = 0; i<customer.size(); i++) {
			if (customerID.contentEquals(customer.get(i).getCustomerID())) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param dishID
	 * @param optionID
	 * @return
	 */
	public static String getDishOptionName(String dishID, String optionID) {
		ArrayList<DishOption> dishOption= DishOption.getDishOption();
		for (int i=0; i<dishOption.size(); i++) {
			if (dishID.contentEquals(dishOption.get(i).getDishID()) && optionID.contentEquals(dishOption.get(i).getOptionID())) {
				return dishOption.get(i).getOptionName();
			}
		}
		return null;
	}
	
	/**
	 * Use dishID and optionID to search for the total price of certain selection
	 * @param dishID
	 * @param optionID
	 * @return price of this option
	 * no such dishID or optionID return null
	 */
	public static Double getDishOptionPrice (String dishID, String optionID) {
		ArrayList<DishOption> dishOption= DishOption.getDishOption();
		for (int i=0; i<dishOption.size(); i++) {
			if (dishID.contentEquals(dishOption.get(i).getDishID()) && optionID.contentEquals(dishOption.get(i).getOptionID())) {
				return dishOption.get(i).getPrice();
			}
		}
		return null;
	}
	
	
	/**
	 * Use add_onID to search for the total price of certain selection
	 * @param add_onID
	 * @return price of this option
	 * no such dishID or optionID return null
	 */
	public static Double getAdd_onPrice (String add_onID) {
		ArrayList<Add_on> add_on= Add_on.getAdd_on();
		for (int i=0; i<add_on.size(); i++) {
			if (add_onID.contentEquals(add_on.get(i).getAdd_onID())) {
				return add_on.get(i).getPrice();
			}
		}
		return null;
	}
	
	/**
	 * get Statistics DishOption Index in array list
	 * @param statisticsDishOption
	 * @param dishName
	 * @param optionName
	 * @return getStatisticsDishOptionIndex
	 */
	public static int getStatisticsDishOptionIndex(ArrayList<StatisticsDishOption> statisticsDishOption,String dishName, String optionName) {
		for (int i=0; i<statisticsDishOption.size(); i++) {
			if (dishName.contentEquals(statisticsDishOption.get(i).getDishName()) &&  optionName.contentEquals(statisticsDishOption.get(i).getOptionName())) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * get Statistics Add_on Index in array list
	 * @param statisticsAdd_on
	 * @param add_onName
	 * @return getStatisticsAdd_onIndex
	 */
	public static int getStatisticsAdd_onIndex(ArrayList<StatisticsAdd_on> statisticsAdd_on,String add_onName) {
		for (int i=0; i<statisticsAdd_on.size(); i++) {
			if (add_onName.contentEquals(statisticsAdd_on.get(i).getAdd_onName()) ) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Check if the date is between the boundary
	 * @param date
	 * @param dateBoundary
	 * @return true when it is
	 * false when it's not
	 */
	public static Boolean checkOrderDate(Date date, ArrayList<Date> dateBoundary) {
		//System.out.println(dateBoundary.get(0));
		//System.out.println(dateBoundary.get(1));
		if (date.compareTo(dateBoundary.get(0))!=-1 && date.compareTo(dateBoundary.get(1))!=1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @param customerID
	 * @return
	 */
	public static Boolean checkCustomerID(String customerID) {
		ArrayList<Customer> customer=Customer.getCustomer();
		for (int i=0; i<customer.size()-1; i++) {
			//System.out.println(customer.get(i).getCustomerName());
			//System.out.println(customer.size());
			if (customerID.contentEquals(customer.get(i).getCustomerID())) {
				System.out.println(false);
				return false;
			}
			//System.out.println("***");
		}
		//System.out.println(true);
		return true;
	}
	
	/**
	 * @param customerID
	 * @return
	 */
	public static Boolean checkVipID(String customerID) {
		ArrayList<Customer> customer=Customer.getCustomer();
		for (int i=0; i<customer.size()-1; i++) {
			if (customerID.contentEquals(customer.get(i).getVipID())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check duplication of orderID
	 * @param orderID
	 * @return true when ID is new
	 * false when ID has arleady existed
	 */
	public static Boolean checkOrderID(String orderID) {
		ArrayList<Order> order=Order.getOrder();
		for (int i=0; i<order.size(); i++) {
			if (orderID.contentEquals(order.get(i).getOrderID())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Get dish index in the arrayList
	 * @param dishName
	 * @return Get dish index in the arrayList
	 * -1 when nothing found
	 */
	public static int getDishIndex (String dishName) {
		ArrayList<Dish> dish=Dish.getDish();
		for (int i = 0; i<dish.size(); i++) {
			if (dishName.contentEquals(dish.get(i).getItemName())) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Get DishOption index in the arrayList
	 * @param dishName
	 * @param optionName
	 * @return Get DishOption index in the arrayList
	 * -1 when nothing found
	 */
	public static int getDishOptionIndex(String dishName, String optionName) {
		ArrayList<DishOption> dishOption= DishOption.getDishOption();
		for (int i=0; i<dishOption.size(); i++) {
			if (dishOption.get(i).getDishID().contentEquals(Query.getDishID(dishName)) && optionName.contentEquals(dishOption.get(i).getOptionName())) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Get add_on index in the arrayList
	 * @param Add_onName
	 * @return Get add_on index in the arrayList
	 * -1 when nothing found
	 */
	public static int getAdd_onIndex(String Add_onName) {
		ArrayList<Add_on> add_on= Add_on.getAdd_on();
		for (int i=0; i<add_on.size(); i++) {
			if (add_on.get(i).getAdd_onID().contentEquals(Query.getAdd_onID(Add_onName))) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Get manager index in the arrayList
	 * @param ManagerID
	 * @return Get manager index in the arrayList
	 * -1 when nothing found
	 */
	public static int getManagerIndex(String ManagerID) {
		ArrayList<Manager> manager=Manager.getManager();
		for (int i = 0; i<manager.size(); i++) {
			if (ManagerID.contentEquals(manager.get(i).getManagerID())) {
				return i;
			}
		}
		return -1;
	}
}
