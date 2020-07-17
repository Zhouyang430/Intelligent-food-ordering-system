package control;

import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import boundary.Initialize;
import boundary.ManagerGUI;
import boundary.Read_excel;
import boundary.Write_excel;
import boundary.CustomerGUI;
import entity.AccountDish;
import entity.Add_on;
import entity.Customer;
import entity.Dish;
import entity.DishOption;
import entity.Manager; 

/**
 * @author Du Ruinian
 *
 */
public class Main {
	public static void main (String[] args) throws IOException
	{	
		Write_excel.writeAdd_onExcel(Initialize.initializeAdd_on());
		Write_excel.writeCustomerExcel(Initialize.initializeCustomer());
		Write_excel.writeDishExcel(Initialize.initializeDish());
		Write_excel.writeDishOptionExcel(Initialize.initializeDishOption());
		Write_excel.writeOrderExcel(Initialize.initializeOrder());
		Write_excel.writeManagerExcel(Initialize.initializeManager());
		
		
		Read_excel.readAdd_onExcel();
		Read_excel.readCustomerExcel();
		Read_excel.readDishExcel();
		Read_excel.readDishOptionExcel();
		Read_excel.readOrderExcel();
		Read_excel.readManagerExcel();
		
		ManagerGUI f= new ManagerGUI();
		f.getManaLogin().setVisible(true);
		
		CustomerGUI f1 = new CustomerGUI();
	    f1.getCustomerLogin().setVisible(true);
	}
	
}
