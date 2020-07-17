package boundary;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.Add_on;
import entity.Customer;
import entity.Dish;
import entity.DishOption;
import entity.Order;
import entity.Manager;


public class Read_excel {
	//Files' absolute name
	private static File customer = new File("customer.xlsx");
	private static File add_on = new File("add_on.xlsx");
	private static File dish = new File("dish.xlsx");
	private static File order = new File("order.xlsx");
	private static File dishOption = new File("dishOption.xlsx");
	private static File manager = new File("manager.xlsx");
	
	private static HSSFWorkbook wb;
	
	public static void readCustomerExcel(){  
        try{  
            ArrayList<Customer> result = new  ArrayList<Customer>();
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(customer));  
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;  
            
            for(int i =0; i < sheet.getPhysicalNumberOfRows(); i++ ){
                row=sheet.getRow(i);//获得行
                XSSFCell cell_0 = row.getCell(0);
                String customerID= cell_0.getStringCellValue();
	                    
	            XSSFCell cell_1 = row.getCell(1);
	            String customerName=cell_1.getStringCellValue();
	            
	            XSSFCell cell_2 = row.getCell(2);
	            String password=cell_2.getStringCellValue();
	                    
	            XSSFCell cell_3 = row.getCell(3);
	            Boolean isVip=cell_3.getBooleanCellValue();
	            
	            XSSFCell cell_4 = row.getCell(4);
	            String firstname=cell_4.getStringCellValue();
	            
	            XSSFCell cell_5 = row.getCell(5);
	            String surname= cell_5.getStringCellValue();
	            
	            XSSFCell cell_6 = row.getCell(6);
	            String e_mail=cell_6.getStringCellValue();
	            
	            XSSFCell cell_7 = row.getCell(7);
	            String phone=cell_7.getStringCellValue();
	            
	            XSSFCell cell_8 = row.getCell(8);
	            String vipID= cell_8.getStringCellValue();
	            //System.out.print("vipID");
	            
	            XSSFCell cell_9 = row.getCell(9);
	            int stamp;
	            //System.out.println("String: "+cell_9.getStringCellValue());
	            if (cell_9.getStringCellValue()=="") {
	            	stamp=0;
	            }
	            else {
	            	stamp= Integer.parseInt(cell_9.getStringCellValue());
	            }            
	            //System.out.println(stamp);
                
                Customer customer= new Customer(customerID, customerName, password, isVip,  firstname, surname, e_mail, phone,  vipID, stamp);
                result.add(customer);
                
            }
            wb.close();
            Customer.setCustomer(result); 
        }catch(Exception e){  
        	System.out.print("Customer");
            e.printStackTrace(); 
        }  
    }  
	
	public static void readOrderExcel(){  
        try{  
            ArrayList<Order> result = new  ArrayList<Order>();
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(order));  
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;  
            
            for(int i =0; i < sheet.getPhysicalNumberOfRows(); i++ ){
                row=sheet.getRow(i);
                XSSFCell cell_0 = row.getCell(0);
                String orderID=cell_0.getStringCellValue();
	                    
	            XSSFCell cell_1 = row.getCell(1);
	            String customerID=cell_1.getStringCellValue();
	            
	            XSSFCell cell_2 = row.getCell(2);
	            String dishes= cell_2.getStringCellValue();
	                    
	            XSSFCell cell_3 = row.getCell(3);
	            String add_ons= cell_3.getStringCellValue();
	            
	            XSSFCell cell_4 = row.getCell(4);
	            String spiciness=cell_4.getStringCellValue();
	            
	            XSSFCell cell_5 = row.getCell(5);
	            String dining=cell_5.getStringCellValue();
	            
	            XSSFCell cell_6 = row.getCell(6);
	            Double bill= Double.parseDouble(cell_6.getStringCellValue());
	            
	            XSSFCell cell_7 = row.getCell(7);
	            Date date= cell_7.getDateCellValue();
                
                Order order= new Order ( orderID,  customerID, dishes, add_ons, spiciness, dining, bill, date);
                result.add(order);
                
            }
            wb.close();
            Order.setOrder(result); 
        }catch(Exception e){  
        	System.out.print("Order");
        	e.printStackTrace();
        }  
    }  
	
	public static void readAdd_onExcel(){  
        try{  
            ArrayList<Add_on> result = new  ArrayList<Add_on>();
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(add_on));  
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;  
            
            for(int i =0; i < sheet.getPhysicalNumberOfRows(); i++ ){
                row=sheet.getRow(i);//获得行
                XSSFCell cell_0 = row.getCell(0);
                String add_onID=cell_0.getStringCellValue();
	                    
	            XSSFCell cell_1 = row.getCell(1);
	            String itemName=cell_1.getStringCellValue();
	            
	            XSSFCell cell_2 = row.getCell(2);
	            Double price=Double.parseDouble(cell_2.getStringCellValue());
	                    
	            XSSFCell cell_3 = row.getCell(3);
	            Boolean available=cell_3.getBooleanCellValue();
	        
                Add_on add_on= new Add_on ( add_onID, itemName, price, available);
                result.add(add_on);
                
            }
            wb.close();
            Add_on.setAdd_on(result); 
        }catch(Exception e){  
        	System.out.print("Add_on");
            e.printStackTrace();  
        }  
    }  
	
	public static void readDishExcel(){  
        try{  
            ArrayList<Dish> result = new  ArrayList<Dish>();
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(dish));
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;  
            
            for(int i =0; i < sheet.getPhysicalNumberOfRows(); i++ ){
                row=sheet.getRow(i);
                XSSFCell cell_0 = row.getCell(0);
                //System.out.println(cell_0);
                String dishID=cell_0.getStringCellValue();
                //System.out.println("dishID="+dishID);
	                    
	            XSSFCell cell_1 = row.getCell(1);
	            String itemName=cell_1.getStringCellValue();
	            //System.out.println("itemName="+itemName);
	                    
	            XSSFCell cell_2 = row.getCell(2);
	            Boolean available=cell_2.getBooleanCellValue();
	            //System.out.println("available="+available);
	        
                Dish dish= new Dish (dishID, itemName, available);
                result.add(dish);
     
            }
            wb.close();
            Dish.setDish(result); 
        }catch(Exception e){  
        	System.out.print("Dish");
            e.printStackTrace();  
        }  
    }  
	
	public static void readDishOptionExcel(){  
        try{  
            ArrayList<DishOption> result = new  ArrayList<DishOption>();
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(dishOption));  
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;  
            
            for(int i =0; i < sheet.getPhysicalNumberOfRows(); i++ ){
                row=sheet.getRow(i);
                XSSFCell cell_0 = row.getCell(0);
                String dishID=cell_0.getStringCellValue();
	                    
	            XSSFCell cell_1 = row.getCell(1);
	            String optionID=cell_1.getStringCellValue();
	            
	            XSSFCell cell_2 = row.getCell(2);
	            String optionName= cell_2.getStringCellValue();
	                    
	            XSSFCell cell_3 = row.getCell(3);
	            Double price= Double.parseDouble(cell_3.getStringCellValue());
	            
	            XSSFCell cell_4 = row.getCell(4);
	            Boolean available=cell_4.getBooleanCellValue();
                
                DishOption dishOption= new DishOption(dishID, optionID, optionName, price, available);
                result.add(dishOption);
            }
            wb.close();
            DishOption.setDishOption(result); 
        }catch(Exception e){ 
        	System.out.print("DishOption");
            e.printStackTrace();  
        }  
    }
	
	public static void readManagerExcel(){  
        try{  
            ArrayList<Manager> result = new  ArrayList<Manager>();
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(manager));  
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;  
            
            for(int i =0; i < sheet.getPhysicalNumberOfRows(); i++ ){
                row=sheet.getRow(i);
                XSSFCell cell_0 = row.getCell(0);
                String managerID=cell_0.getStringCellValue();
	                    
	            XSSFCell cell_1 = row.getCell(1);
	            String managerName=cell_1.getStringCellValue();
	            
	            XSSFCell cell_2 = row.getCell(2);
	            String password= cell_2.getStringCellValue();
	                    
	            XSSFCell cell_3 = row.getCell(3);
	            String e_mail= cell_3.getStringCellValue();
	            
	            XSSFCell cell_4 = row.getCell(4);
	            Boolean invoice=cell_4.getBooleanCellValue();
                
	            Manager manager= new Manager(managerID, managerName, password, e_mail, invoice);
                result.add(manager);
            }
            wb.close();
            Manager.setManager(result); 
        }catch(Exception e){ 
        	System.out.print("Manager");
            e.printStackTrace();  
        }  
    }
}
