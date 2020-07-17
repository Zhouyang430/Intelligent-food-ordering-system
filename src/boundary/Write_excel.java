package boundary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

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

/**
 * @author DU rui nian
 * This class is mainly to implement:
 * write data from front interface to files
 */
public class Write_excel {
	//Files' absolute name
	private static File customer = new File("customer.xlsx");
	private static File add_on = new File("add_on.xlsx");
	private static File dish = new File("dish.xlsx");
	private static File order = new File("order.xlsx");
	private static File dishOption = new File("dishOption.xlsx");
	private static File manager = new File("manager.xlsx");
	
	private static XSSFWorkbook wb;
	
		public static void writeAdd_onExcel(ArrayList<Add_on> result) throws IOException{  
		        if(result == null){  
		            return;  
		        }  
		        
		        if (!add_on.exists()) {
		        	wb = new XSSFWorkbook();   
		            XSSFSheet sheet = wb.createSheet("sheet1");  
		            OutputStream outputStream = new FileOutputStream(add_on);
		            wb.write(outputStream);
		            outputStream.flush();
		            outputStream.close();	            
		            
		        }
		        
		        wb = new XSSFWorkbook();  
		        XSSFSheet sheet = wb.createSheet("sheet1");  
		        for(int i = 0 ;i < result.size() ; i++){  
		            XSSFRow row = sheet.createRow(i);  
		            XSSFCell cell_0 = row.createCell(0);
		            cell_0.setCellValue(result.get(i).getAdd_onID());  
		                    
		            XSSFCell cell_1 = row.createCell(1);
		            cell_1.setCellValue(result.get(i).getItemName());  
		                    
		            XSSFCell cell_2 = row.createCell(2);
		            cell_2.setCellValue(String.valueOf(result.get(i).getPrice())); 
		                    
		            XSSFCell cell_3 = row.createCell(3);
		            cell_3.setCellValue(result.get(i).getAvailable());
				}  
		        OutputStream stream = new FileOutputStream(add_on);  
		        wb.write(stream);  
		        stream.close();  
		    }  
		
		public static void writeCustomerExcel(ArrayList<Customer> result) throws IOException{  
	        if(result == null){  
	            return;  
	        }  
	        
	        if (!customer.exists()) {
	        	wb = new XSSFWorkbook();   
	            XSSFSheet sheet = wb.createSheet("sheet1");  
	            OutputStream outputStream = new FileOutputStream(customer);
	            wb.write(outputStream);
	            outputStream.flush();
	            outputStream.close();
	        }
	        
	        wb = new XSSFWorkbook();  
	        XSSFSheet sheet = wb.createSheet("sheet1");  
	        for(int i = 0 ;i < result.size() ; i++){  
	            XSSFRow row = sheet.createRow(i);  
	            XSSFCell cell_0 = row.createCell(0);
	            cell_0.setCellValue(result.get(i).getCustomerID());  
	                    
	            XSSFCell cell_1 = row.createCell(1);
	            cell_1.setCellValue(result.get(i).getCustomerName());  
	                    
	            XSSFCell cell_2 = row.createCell(2);
	            cell_2.setCellValue(result.get(i).getPassword()); 
	                    
	            XSSFCell cell_3 = row.createCell(3);
	            cell_3.setCellValue(result.get(i).getIsVip());
	            
	            XSSFCell cell_4 = row.createCell(4);
	            cell_4.setCellValue(result.get(i).getFirstname());
	            
	            XSSFCell cell_5 = row.createCell(5);
	            cell_5.setCellValue(result.get(i).getSurname());
	            
	            XSSFCell cell_6 = row.createCell(6);
	            cell_6.setCellValue(result.get(i).getE_mail());
	            
	            XSSFCell cell_7 = row.createCell(7);
	            cell_7.setCellValue(result.get(i).getPhone());
	            
	            XSSFCell cell_8 = row.createCell(8);
	            cell_8.setCellValue(result.get(i).getVipID());
	            
	            XSSFCell cell_9 = row.createCell(9);
	            cell_9.setCellValue(String.valueOf(result.get(i).getStamp()));
			}  
	        OutputStream stream = new FileOutputStream(customer);  
	        wb.write(stream);  
	        stream.close();  
	    } 
		
		public static void writeDishExcel(ArrayList<Dish> result) throws IOException{  
	        if(result == null){  
	            return;  
	        }  
	        
	        if (!dish.exists()) {
	        	wb = new XSSFWorkbook();   
	            XSSFSheet sheet = wb.createSheet("sheet1");  
	            OutputStream outputStream = new FileOutputStream(dish);
	            wb.write(outputStream);
	            outputStream.flush();
	            outputStream.close();
	        }
	        wb = new XSSFWorkbook();  
	        XSSFSheet sheet = wb.createSheet("sheet1");  
	        for(int i = 0 ;i < result.size() ; i++){  
	            XSSFRow row = sheet.createRow(i);  
	            XSSFCell cell_0 = row.createCell(0);
	            cell_0.setCellValue(result.get(i).getDishID());  
	                    
	            XSSFCell cell_1 = row.createCell(1);
	            cell_1.setCellValue(result.get(i).getItemName());  
	                    
	            XSSFCell cell_2 = row.createCell(2);
	            cell_2.setCellValue(result.get(i).getAvailable());
	            
			}    
	        OutputStream stream = new FileOutputStream(dish);  
	        wb.write(stream);  
	        stream.close();  
	    } 
		
		public static void writeOrderExcel(ArrayList<Order> result) throws IOException{  
	        if(result == null){  
	            return;  
	        }  
	        
	        if (!order.exists()) {
	        	wb = new XSSFWorkbook();   
	            XSSFSheet sheet = wb.createSheet("sheet1");  
	            OutputStream outputStream = new FileOutputStream(order);
	            wb.write(outputStream);
	            outputStream.flush();
	            outputStream.close();
	        }
	        
	        wb = new XSSFWorkbook();  
	        XSSFSheet sheet = wb.createSheet("sheet1");  
	        for(int i = 0 ;i < result.size() ; i++){  
	            XSSFRow row = sheet.createRow(i);  
	            XSSFCell cell_0 = row.createCell(0);
	            cell_0.setCellValue(result.get(i).getOrderID());  
	                    
	            XSSFCell cell_1 = row.createCell(1);
	            cell_1.setCellValue(result.get(i).getCustomerID());  
	                    
	            XSSFCell cell_2 = row.createCell(2);
	            cell_2.setCellValue(result.get(i).getDishes()); 
	                    
	            XSSFCell cell_3 = row.createCell(3);
	            cell_3.setCellValue(result.get(i).getAdd_ons());
	            
	            XSSFCell cell_4 = row.createCell(4);
	            cell_4.setCellValue(result.get(i).getSpiciness());
	            
	            XSSFCell cell_5 = row.createCell(5);
	            cell_5.setCellValue(result.get(i).getDining());
	            
	            XSSFCell cell_6 = row.createCell(6);
	            cell_6.setCellValue(String.valueOf(result.get(i).getBill()));
	            
	            XSSFCell cell_7 = row.createCell(7);
	            cell_7.setCellValue(result.get(i).getDate());
	            
			}  
	        OutputStream stream = new FileOutputStream(order);  
	        wb.write(stream);  
	        stream.close();           
	    } 
		
		public static void writeDishOptionExcel(ArrayList<DishOption> result) throws IOException{  
	        if(result == null){  
	            return;  
	        }  
	        
	        if (!dishOption.exists()) {
	        	wb = new XSSFWorkbook();   
	            XSSFSheet sheet = wb.createSheet("sheet1");  
	            OutputStream outputStream = new FileOutputStream(dishOption);
	            wb.write(outputStream);
	            outputStream.flush();
	            outputStream.close();
	        }
	        
	        wb = new XSSFWorkbook();  
	        XSSFSheet sheet = wb.createSheet("sheet1");  
	        for(int i = 0 ;i < result.size() ; i++){  
	            XSSFRow row = sheet.createRow(i);  
	            XSSFCell cell_0 = row.createCell(0);
	            cell_0.setCellValue(result.get(i).getDishID());  
	                    
	            XSSFCell cell_1 = row.createCell(1);
	            cell_1.setCellValue(result.get(i).getOptionID());  
	                    
	            XSSFCell cell_2 = row.createCell(2);
	            cell_2.setCellValue(result.get(i).getOptionName()); 
	                    
	            XSSFCell cell_3 = row.createCell(3);
	            cell_3.setCellValue(String.valueOf(result.get(i).getPrice()));
	            
	            XSSFCell cell_4 = row.createCell(4);
	            cell_4.setCellValue(result.get(i).getAvailable());
			}  
	        OutputStream stream = new FileOutputStream(dishOption);  
	        wb.write(stream);  
	        stream.close();           
	    } 
		
		public static void writeManagerExcel(ArrayList<Manager> result) throws IOException{  
	        if(result == null){  
	            return;  
	        }  
	        
	        if (!manager.exists()) {
	        	wb = new XSSFWorkbook();   
	            XSSFSheet sheet = wb.createSheet("sheet1");  
	            OutputStream outputStream = new FileOutputStream(manager);
	            wb.write(outputStream);
	            outputStream.flush();
	            outputStream.close();
	        }
	        
	        wb = new XSSFWorkbook();  
	        XSSFSheet sheet = wb.createSheet("sheet1");  
	        for(int i = 0 ;i < result.size() ; i++){  
	            XSSFRow row = sheet.createRow(i);  
	            XSSFCell cell_0 = row.createCell(0);
	            cell_0.setCellValue(result.get(i).getManagerID());  
	                    
	            XSSFCell cell_1 = row.createCell(1);
	            cell_1.setCellValue(result.get(i).getManagerName());  
	                    
	            XSSFCell cell_2 = row.createCell(2);
	            cell_2.setCellValue(result.get(i).getPassword()); 
	                    
	            XSSFCell cell_3 = row.createCell(3);
	            cell_3.setCellValue(result.get(i).getE_mail());
	            
	            XSSFCell cell_4 = row.createCell(4);
	            cell_4.setCellValue(result.get(i).getInvoice());
			}  
	        OutputStream stream = new FileOutputStream(manager);  
	        wb.write(stream);  
	        stream.close();           
	    } 
}
