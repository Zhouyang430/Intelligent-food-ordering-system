package boundary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entity.Add_on;
import entity.Customer;
import entity.Dish;
import entity.DishOption;
import entity.Manager;
import entity.Order;

/**
 * @author Du Ruinian
 *
 */
public class Initialize {
	private static ArrayList<Add_on> add_on= new ArrayList<Add_on>();
	private static ArrayList<Customer> customer= new ArrayList<Customer>();
	private static ArrayList<Dish> dish=new ArrayList<Dish>();
	private static ArrayList<DishOption> dishOption= new ArrayList<DishOption>();
	private static ArrayList<Order> order= new ArrayList<Order>();
	private static ArrayList<Manager> manager= new ArrayList<Manager>();
	
	/**
	 * @return
	 */
	public static ArrayList<Add_on> initializeAdd_on(){
		Add_on add_on_0= new Add_on("00000001", "Extra Nori", 1.00, true);
		Add_on add_on_1= new Add_on("00000002", "Extra boiled egg", 1.00, true);
		Add_on add_on_2= new Add_on("00000003", "Bamboo shoots", 1.00, true);
		Add_on add_on_3= new Add_on("00000004", "Extra Chashu", 2.00, true);
		
		add_on.add(add_on_0);
		add_on.add(add_on_1);
		add_on.add(add_on_2);
		add_on.add(add_on_3);
		return add_on;
	}
	
	/**
	 * @return
	 */
	public static ArrayList<Customer> initializeCustomer(){
		Customer customer_0= new Customer("00000001", "Eminem", "18d78383cc10d86d58e9f692a9db5159f80818b177c5bf0c", false, null, null, null, null, null, 0);
		Customer customer_1= new Customer("00000002", "WTF", "99c14975c22e91c82601b23be0945f62f215231b88a4470b", true,"Ruinian", "Du", "996017608@qq.com", "15331737017", "00000001", 10);	
		
		customer.add(customer_0);
		customer.add(customer_1);
		return customer;
	}
	
	/**
	 * @return
	 */
	public static ArrayList<Dish> initializeDish(){
		Dish dish_0= new Dish ("00000001", "Soup", true);
		Dish dish_1= new Dish ("00000002", "Noodles", true);
		Dish dish_2= new Dish ("00000003", "Spring onion", true);
		Dish dish_3= new Dish ("00000004", "Nori", true);
		Dish dish_4= new Dish ("00000005", "Chashu", true);
		Dish dish_5= new Dish ("00000006", "Boiled egg", true);
		
		dish.add(dish_0);
		dish.add(dish_1);
		dish.add(dish_2);
		dish.add(dish_3);
		dish.add(dish_4);
		dish.add(dish_5);
		return dish;
	}
	
	/**
	 * @return
	 */
	public static ArrayList<DishOption> initializeDishOption(){
		DishOption dishOption_0= new DishOption ("00000001", "00000001", "Tonkotsu", 0.00, true);
		DishOption dishOption_1= new DishOption ("00000001", "00000002", "Shoyu", 0.00, true);
		DishOption dishOption_2= new DishOption ("00000001", "00000003", "Shio", 0.00, true);
		DishOption dishOption_3= new DishOption ("00000002", "00000004", "Soft", 0.00, true);
		DishOption dishOption_4= new DishOption ("00000002", "00000005", "Medium", 0.00, true);
		DishOption dishOption_5= new DishOption ("00000002", "00000006", "Firm", 0.00, true);
		DishOption dishOption_6= new DishOption ("00000003", "00000007", "No please", 0.00, true);
		DishOption dishOption_7= new DishOption ("00000003", "00000008", "Just a little", 0.00, true);
		DishOption dishOption_8= new DishOption ("00000003", "00000009", "A lot!", 0.00, true);
		DishOption dishOption_9= new DishOption ("00000004", "00000010", "Yes", 0.00, true);
		DishOption dishOption_10= new DishOption ("00000004", "00000011", "No", 0.00, true);
		DishOption dishOption_11= new DishOption ("00000005", "00000012", "Yes", 0.00, true);
		DishOption dishOption_12= new DishOption ("00000005", "00000013", "No", 0.00, true);
		DishOption dishOption_13= new DishOption ("00000006", "00000014", "Yes", 0.00, true);
		DishOption dishOption_14= new DishOption ("00000006", "00000015", "No", 0.00, true);
		
		dishOption.add(dishOption_0);
		dishOption.add(dishOption_1);
		dishOption.add(dishOption_2);
		dishOption.add(dishOption_3);
		dishOption.add(dishOption_4);
		dishOption.add(dishOption_5);
		dishOption.add(dishOption_6);
		dishOption.add(dishOption_7);
		dishOption.add(dishOption_8);
		dishOption.add(dishOption_9);
		dishOption.add(dishOption_10);
		dishOption.add(dishOption_11);
		dishOption.add(dishOption_12);
		dishOption.add(dishOption_13);
		dishOption.add(dishOption_14);
		return dishOption;
	}
	
	/**
	 * @return
	 */
	public static ArrayList<Order> initializeOrder(){
		Calendar calendar = Calendar.getInstance();  
	    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);  
		Date date=calendar.getTime();
		Order order_0= new Order("00000001", "00000002", "00000001,00000001,1.0;00000006,00000015,0.0;", "00000003,1.0,10;", "3", "Take away", 11.00,date);
		
		order.add(order_0);
		return order;
	}
	
	public static ArrayList<Manager> initializeManager(){
		
		Manager manager_0= new Manager("00000001", "Miyazaki", "99c14975c22e91c82601b23be0945f62f215231b88a4470b", "996017608@qq.com", true);
		
		manager.add(manager_0);
		return manager;
	}
}
