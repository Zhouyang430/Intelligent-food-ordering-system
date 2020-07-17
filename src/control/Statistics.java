package control;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entity.Add_on;
import entity.DishOption;
import entity.Manager;
import entity.Order;
import entity.StatisticsAdd_on;
import entity.StatisticsDishOption;

/**
 * @author Du ruinian
 * This class is mainly to implement the function of visualizing statistics
 */
public class Statistics {
	private static final int days=7;
	private static final String file="";
	private static ArrayList<StatisticsDishOption> statisticsDishOption;
	private static ArrayList<StatisticsAdd_on> statisticsAdd_on;
	
	/**
	 * Get Last Week DateBoundary
	 * @return an ArrayList contain the boundary
	 */
	public static ArrayList<Date> getLastWeekDateBoundary (){
		ArrayList<Date> date=new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();  
	    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);  
	    while (calendar.get(Calendar.DAY_OF_WEEK)!=1){
	    	//System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
	    	//System.out.println(calendar.getTime());
	    	calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1); 
	    }
	    //System.out.println("Date to begin "+calendar.getTime());
	    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)+1);
	    date.add(calendar.getTime());
	    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)+6);
	    date.add(calendar.getTime());
	    return date;
	}	
	
	/**
	 * Get Spiciness Statistics
	 * @return int[] spicinessInOrder
	 */
	public static int[] getSpicinessStatistics (){
		int [] spicinessInOrder= new int[6];
		int[]data= new int[6];
		int spiciness=0;
		int max;
		for (int i=0; i<6; i++) {
			data[i]=0;
		}
		
		for (int i=0; i<6; i++) {
			spicinessInOrder[i]=i;
		}
		
		ArrayList<Order> order=Order.getOrder();
		for (int i=0; i<order.size(); i++) {
			data[Integer.parseInt(order.get(i).getSpiciness())]=data[Integer.parseInt(order.get(i).getSpiciness())]+1;
		}
		
		max=data[0];
		for (int i=0; i<6; i++) {
			//System.out.println("data "+i+" : "+data[i]);
			if (max<data[i]) {
				spiciness=i;
				max=data[i];
			}
		}		
		
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				if (data[spicinessInOrder[j]]<data[spicinessInOrder[j+1]]) {
					//System.out.println("******");
					int temp=spicinessInOrder[j+1];
					spicinessInOrder[j+1]=spicinessInOrder[j];
					spicinessInOrder[j]=temp;
				}
			}
		}
		
		return spicinessInOrder;
	}
	
	/**
	 * Get Last Week DishOption Statistics
	 * @return ArrayList<StatisticsDishOption>
	 */
	public static ArrayList<StatisticsDishOption> getLastWeekDishOptionStatistics (){
		statisticsDishOption = new ArrayList<StatisticsDishOption>();
		ArrayList<Order> order=Order.getOrder();
		ArrayList<DishOption> dishOption=DishOption.getDishOption();
		
		ArrayList<Date> dateBoundary= Statistics.getLastWeekDateBoundary();
		
		for (int i=0; i<dishOption.size(); i++) {
			StatisticsDishOption iterator= new StatisticsDishOption(Query.getDishName(dishOption.get(i).getDishID()),dishOption.get(i).getOptionName(),0);
			statisticsDishOption.add(iterator);
		}
		
		for (int i=0; i<order.size(); i++) {
			if (Query.checkOrderDate(order.get(i).getDate(), dateBoundary)) {
				String dishList[]=order.get(i).getDishes().split(";");
				for (int j=0; j<dishList.length; j++) {
					String optionList[]=dishList[j].split(",");
					int index=Query.getStatisticsDishOptionIndex(
							statisticsDishOption,Query.getDishName(optionList[0]), Query.getDishOptionName(optionList[0], optionList[1]));
					statisticsDishOption.get(index).setAmount(statisticsDishOption.get(index).getAmount()+1); // Amount++
				}
			}

		}
		return statisticsDishOption;
	}
	
	/**
	 * Get Last Week Add_on Statistics
	 * @return ArrayList<StatisticsAdd_on> 
	 */
	public static ArrayList<StatisticsAdd_on> getLastWeekAdd_onStatistics (){
		statisticsAdd_on = new ArrayList<StatisticsAdd_on>() ;
		ArrayList<Order> order=Order.getOrder();
		ArrayList<Add_on> add_on=Add_on.getAdd_on();
		
		ArrayList<Date> dateBoundary= Statistics.getLastWeekDateBoundary();
		
		for (int i=0; i<add_on.size(); i++) {
			StatisticsAdd_on iterator= new StatisticsAdd_on(add_on.get(i).getItemName(), 0);
			statisticsAdd_on.add(iterator);
		}
		
		for (int i=0; i<order.size(); i++) {
			//System.out.println(order.get(i).getDate());
			if (Query.checkOrderDate(order.get(i).getDate(), dateBoundary)) {
				//System.out.println("This is it");
				String add_onList[]=order.get(i).getAdd_ons().split(";");
				for (int j=0; j<add_onList.length; j++) {
					//System.out.println(add_onList[0]);
					String optionList[]=add_onList[j].split(",");
					int index=Query.getStatisticsAdd_onIndex(
							statisticsAdd_on,Query.getAdd_onName(optionList[0]));
					statisticsAdd_on.get(index).setAmount(statisticsAdd_on.get(index).getAmount()+Integer.parseInt(optionList[2])); // Amount++
				}
			}

		}
		
		return statisticsAdd_on;
	}	
	
	/**
	 * Automatically send reports to manager
	 * @param manager
	 */
	public static void sendReport (Manager manager) {
		FileWriter fileWriter = null;
		Date date = new Date();
		ArrayList<StatisticsAdd_on> statisticsAdd_on= getLastWeekAdd_onStatistics();
		ArrayList<StatisticsDishOption> statisticsDishOption= getLastWeekDishOptionStatistics();

		try {
			fileWriter = new FileWriter(file+date.getMinutes()+".txt");// Create file
			fileWriter.write("Date"+date+"\n");
			fileWriter.write("ManagerID: "+manager.getManagerID()+"\n");
			fileWriter.write("ManagerName: "+manager.getManagerName()+"\n");
			
			for (int i=0; i<statisticsAdd_on.size(); i++) {
				fileWriter.write("Add-on Name: "+statisticsAdd_on.get(i).getAdd_onName()+", ");
				fileWriter.write("Amount: "+statisticsAdd_on.get(i).getAmount()+"\n");
			}
			
			fileWriter.write("********************************************"+"\n");
			
			for (int i=0; i<statisticsDishOption.size(); i++) {
				fileWriter.write("Dish Name: "+statisticsDishOption.get(i).getDishName()+", ");
				fileWriter.write("Option Name: "+statisticsDishOption.get(i).getOptionName()+", ");
				fileWriter.write("Amount: "+statisticsDishOption.get(i).getAmount()+"\n");
			}
			
			fileWriter.write("\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
