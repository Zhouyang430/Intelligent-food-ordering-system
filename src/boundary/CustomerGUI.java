package boundary;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import com.jgoodies.forms.factories.*;

import control.CustomerLogin;
import control.CustomerRegister;
import control.Format;
import control.Payment;
import control.Query;
import control.VipOperation;
import control.VipRegister;
import entity.AccountAdd_on;
import entity.AccountDish;
import entity.Add_on;
import entity.Customer;
import entity.DishOption;

import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

/**
 * The Customer GUI Interface.
 * 
 * @author YILIN WANG
 * @version 5.2
 * 
 */

public class CustomerGUI {
	
	/** The index of current customer in the customer Arraylist. */
	private int index;			
	
	/** The array to store current ordered dish. */
	private ArrayList<AccountDish> accountDishArray = new ArrayList<AccountDish>();				
	
	/** The array to store current add on options. */
	private ArrayList<AccountAdd_on> accountAdd_onArray = new ArrayList<AccountAdd_on>();
	
	/** The string to store current ordered spiciness. */
	private String spiciness = "0";					
	
	/** The string to store current ordered dining way. */
	private String dining = "Eat in";				
	
	/** The string to store current selsected pay option. */
	private String payOption="Cash";				
	
	/**
	 * Instantiates a new customer GUI interface.
	 */
	public CustomerGUI() {
		initComponents();
	}
	
	/**
	 * The LOGIN button in customerLogin frame action performed.
	 * Implements customer login fucntion in GUI interface.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 * @exception IOException
	 */
	private void button13ActionPerformed(ActionEvent e) {
		if ("".equals(userName.getText())) {
			JOptionPane.showMessageDialog(null, "User name and password can not be empty");
		} else if (password1.getPassword().length==0) {
			JOptionPane.showMessageDialog(null, "User name and password can not be empty");
		}else {
			try {
				if(CustomerLogin.loginValid(userName.getText(),password1.getPassword().toString())) {
					
					index = Query.getCustomerIndex(userName.getText());
					customerLogin.setVisible(false);
					selectMeal.setVisible(true);
					this.calculatePrice();
					this.refreshPrice();
					this.refreshAddonAva();
					this.refreshDishAva();
					System.out.println(textField22.getText());
				}else {
					JOptionPane.showMessageDialog(null, "Sorry! Your customer ID or password is not correct.Please check! ");
				}
				}
			 catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * The SIGNUP button in customerLogin frame action performed.
	 * Implements customer signup fucntion in GUI interface
	 *
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void button2ActionPerformed(ActionEvent e) {
		customerSignup.setVisible(true);
	}

	/**
	 * The SIGNUP button in customerSignup frame action performed.
	 * Implements customer signup confirmation fucntion in GUI interface
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 * @throws IOException
	 */
	private void button3ActionPerformed(ActionEvent e){
		
		if ("".equals(nickName.getText())) {
			JOptionPane.showMessageDialog(null, "Nickname and password can not be empty");
		} else if (password2.getPassword().length==0) {
			JOptionPane.showMessageDialog(null, "Nickname and password can not be empty");
		}else {
			try {
				System.out.println(nickName.getText());
				System.out.println(password2.getPassword());
				CustomerRegister.registerCus(nickName.getText(), password2.getPassword().toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Success!Your customer ID is "+Customer.getCustomer().get(index).getCustomerID());
			customerSignup.setVisible(false);
			customerLogin.setVisible(true);
		}
	}

	/**
	 * The NEXT switch button in 1ST selectMeal frame action performed.
	 * To allow customers to switch the menu back and forward conviently.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void button1ActionPerformed(ActionEvent e) {
		mealPane.setSelectedIndex(1);
	}

	/**
	 * The BACK switch button in 2ND selectMeal frame action performed.
	 * To allow customers to switch the menu back and forward conviently.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void button21ActionPerformed(ActionEvent e) {
		mealPane.setSelectedIndex(0);
	}

	/**
	 * The NEXT switch button in 2ND selectMeal frame action performed.
	 * To allow customers to switch the menu back and forward conviently.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void button20ActionPerformed(ActionEvent e) {
		mealPane.setSelectedIndex(2);
	}

	/**
	 * The BACK switch button in 3RD selectMeal frame action performed.
	 * To allow customers to switch the menu back and forward conviently.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void button10ActionPerformed(ActionEvent e) {
		mealPane.setSelectedIndex(1);
	}

	/**
	 * The FINISH button in selectMeal frame action performed.
	 * Implement confirmation of meal, to allow customers to confirm the order after having a look of what have ordered.
	 * And the goes to membership frame.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void button11ActionPerformed(ActionEvent e) {
		selectMeal.setVisible(false);
		vipLogin.setVisible(true);
	}

	/**
	 * The SIGNUP button in vipLogin frame action performed.
	 * Implements VIP signup fucntion in GUI interface
	 *
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void button12ActionPerformed(ActionEvent e) {
		if(!Customer.getCustomer().get(index).getIsVip())
		{
			vipSingup.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "Sorry!You are already a VIP!");
		}
	}

	/**
	 * The SIGNUP button in vipSignup frame action performed.
	 * Implements VIP signup confirmation fucntion in GUI interface
	 * With format checking.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 * @exception IOException
	 */
	private void button4ActionPerformed(ActionEvent e) {
		if ("".equals(fName.getText())) {
			JOptionPane.showMessageDialog(null, "First name can not be empty");
		} else if ("".equals(sName.getText())) {
			JOptionPane.showMessageDialog(null, "Surname can not be empty");
		}else if ("".equals(eMail.getText()) && "".equals(mNumber.getText())) {
			JOptionPane.showMessageDialog(null, "Please select at least one contact method");
		}else if(!Format.isName(fName.getText())){
			JOptionPane.showMessageDialog(null, "First name format is wrong!");
		}else if(!Format.isName(sName.getText())){
			JOptionPane.showMessageDialog(null, "Surname format is wrong!");
		}else if(!"".equals(eMail.getText()) && !Format.isEmail(eMail.getText())) {
			JOptionPane.showMessageDialog(null, "Email format is wrong!");
		}else if(!"".equals(mNumber.getText()) && !Format.isPhone(mNumber.getText())) {
			JOptionPane.showMessageDialog(null, "Mobile number format is wrong!");
		}else {
			try {
				VipRegister.register(Customer.getCustomer().get(index).getCustomerID(), fName.getText(), sName.getText(), eMail.getText(), mNumber.getText());
				VipRegister.printRegisterMessage(fName.getText(), sName.getText(), eMail.getText(), mNumber.getText());
				if(eMail.getText()!=null) {VipRegister.sendMessage(eMail.getText());}
				if(mNumber.getText()!=null) {VipRegister.sendEmail(mNumber.getText());}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Welcome! Your VipID is:"+Customer.getCustomer().get(index).getVipID());
			vipSingup.setVisible(false);
			vipLogin.setVisible(true);
		}
	}

	/**
	 * The LOGIN button in vipLogin frame action performed.
	 * Implements VIP login fucntion in GUI interface.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 * @exception IOException
	 */
	private void button8ActionPerformed(ActionEvent e) {
		
		if ("".equals(membership.getText())) {
			JOptionPane.showMessageDialog(null, "VIP id is empty!");
		}else {
			try {
				if(Customer.getCustomer().get(index).getVipID().equals(membership.getText())) {
					try {
							if(Payment.checkFreePayment(Customer.getCustomer().get(index).getVipID())) {
								stamp.setText(Integer.toString(VipOperation.queryStampByVipID(Customer.getCustomer().get(index).getVipID())));
								cash.setEnabled(false);
								card.setEnabled(false);
								vipLogin.setVisible(false);
								payment.setVisible(true);
							}else {
								stamp.setText(Integer.toString(VipOperation.queryStampByVipID(Customer.getCustomer().get(index).getVipID())));
								vipLogin.setVisible(false);
								label11.setVisible(false);
								payment.setVisible(true);
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					
				}else {
					JOptionPane.showMessageDialog(null, "Sorry! Your VIP id is not correct.Please check!");
				}
				}
			 catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}

	/**
	 * The NO THANKS button in vipLogin frame action performed.
	 * Goes to payment interface when customer choose not to have a membership.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void button9ActionPerformed(ActionEvent e) {
		panel8.setVisible(false);
		label11.setVisible(false);
		vipLogin.setVisible(false);
		payment.setVisible(true);
		panel9.setBounds(115, 100, 245, 125);
	}

	/**
	 * The PAY button in payment frame action performed.
	 * Implements the payment functction with payment way choosing and VIP check.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 * @exception IOException
	 */
	private void button5ActionPerformed(ActionEvent e) {
		try {
			if(Payment.checkFreePayment(Customer.getCustomer().get(index).getVipID())){
				Customer.getCustomer().get(index).setStamp(0);
				Payment.printBill(accountDishArray, accountAdd_onArray, Customer.getCustomer().get(index), spiciness, dining, "Free");
				JOptionPane.showMessageDialog(null, "FREE! Thanks for your purchasing! Have a nice day!");
			
			}else if(Customer.getCustomer().get(index).getIsVip()){
				VipOperation.plusStamps(Customer.getCustomer().get(index).getVipID());
				if(Customer.getCustomer().get(index).getE_mail()!=null)
				{
					VipOperation.sendStampEmail(Customer.getCustomer().get(index).getE_mail());
				}
				if(Customer.getCustomer().get(index).getPhone()!=null)
				{
					VipOperation.sendStampMessage(Customer.getCustomer().get(index).getPhone());
				}
				Payment.printBill(accountDishArray, accountAdd_onArray, Customer.getCustomer().get(index), spiciness, dining, payOption);
				JOptionPane.showMessageDialog(null, "Thanks for your purchasing! Have a nice day!");
			
			}else {
				Payment.printBill(accountDishArray, accountAdd_onArray, Customer.getCustomer().get(index), spiciness, dining, payOption);
				JOptionPane.showMessageDialog(null, "Thanks for your purchasing! Have a nice day!");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		payment.setVisible(false);
	}

	/**
	 * The radio button of 0 in spiciness action performed.
	 * To store spiciness option-0 in JTextfield spiceiness and show to customer in GUI interface.
	 * Also store the spiciness selected to generate order later.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void spice0ActionPerformed(ActionEvent e) {
		spiceiness.setText("0");
	}

	/**
	 * The radio button of 1 in spiciness action performed.
	 * To store spiciness option-1 in JTextfield spiceiness and show to customer in GUI interface.
	 * Also store the spiciness selected to generate order later.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void spice1ActionPerformed(ActionEvent e) {
		spiceiness.setText("1");
	}

	/**
	 * The radio button of 2 in spiciness action performed.
	 * To store spiciness option-2 in JTextfield spiceiness and show to customer in GUI interface.
	 * Also store the spiciness selected to generate order later.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void spice2ActionPerformed(ActionEvent e) {
		spiceiness.setText("2");
	}

	/**
	 * The radio button of 3 in spiciness action performed.
	 * To store spiciness option-3 in JTextfield spiceiness and show to customer in GUI interface.
	 * Also store the spiciness selected to generate order later.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void spice3ActionPerformed(ActionEvent e) {
		spiceiness.setText("3");
	}

	/**
	 * The radio button of 4 in spiciness action performed.
	 * To store spiciness option-4 in JTextfield spiceiness and show to customer in GUI interface.
	 * Also store the spiciness selected to generate order later.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void spice4ActionPerformed(ActionEvent e) {
		spiceiness.setText("4");
	}

	/**
	 * The radio button of 5 in spiciness action performed.
	 * To store spiciness option-5 in JTextfield spiceiness and show to customer in GUI interface.
	 * Also store the spiciness selected to generate order later.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void spice5ActionPerformed(ActionEvent e) {
		spiceiness.setText("5");
	}

	/**
	 * The radio button of Eat-in in dining way action performed.
	 * To store dining way option- Eat-in in dining.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void radioButton3ActionPerformed(ActionEvent e) {
		dining = "Eat in";
	}

	/**
	 * The radio button of take-away in dining way action performed.
	 * To store dining way option-take-away in dining.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void radioButton4ActionPerformed(ActionEvent e) {
		dining = "Take away";
	}

	/**
	 * Implementing the price calculation of current selected meal.
	 * 
	 * @author YILIN WANG
	 */
	private void calculatePrice() {
		accountDishArray = new ArrayList<AccountDish>();
		accountAdd_onArray = new ArrayList<AccountAdd_on>();
		
		accountDishArray.add(new AccountDish((Query.getDishID("Soup")),Query.getDishOptionID("Soup", textField22.getText())));
		accountDishArray.add(new AccountDish((Query.getDishID("Noodles")),Query.getDishOptionID("Noodles",textField23.getText())));
		accountDishArray.add(new AccountDish((Query.getDishID("Spring onion")),Query.getDishOptionID("Spring onion",textField24.getText())));
		
		if(checkBox9.isSelected()) {
			accountDishArray.add(new AccountDish(Query.getDishID("Nori"),Query.getDishOptionID("Nori", "Yes")));
		}else {
			accountDishArray.add(new AccountDish(Query.getDishID("Nori"),Query.getDishOptionID("Nori", "No")));
		}
		if(checkBox8.isSelected()) {
			accountDishArray.add(new AccountDish(Query.getDishID("Chashu"),Query.getDishOptionID("Chashu", "Yes")));
		}else {
			accountDishArray.add(new AccountDish(Query.getDishID("Chashu"),Query.getDishOptionID("Chashu", "No")));
		}
		if(checkBox7.isSelected()) {
			accountDishArray.add(new AccountDish(Query.getDishID("Boiled egg"),Query.getDishOptionID("Boiled egg", "Yes")));
		}else {
			accountDishArray.add(new AccountDish(Query.getDishID("Boiled egg"),Query.getDishOptionID("Boiled egg", "No")));
		}
	
		accountAdd_onArray.add(new AccountAdd_on(Query.getAdd_onID("Extra Nori"),Integer.parseInt(textField27.getText())));
		accountAdd_onArray.add(new AccountAdd_on(Query.getAdd_onID("Extra boiled egg"),Integer.parseInt(textField28.getText())));
		accountAdd_onArray.add(new AccountAdd_on(Query.getAdd_onID("Bamboo shoots"),Integer.parseInt(textField29.getText())));
		accountAdd_onArray.add(new AccountAdd_on(Query.getAdd_onID("Extra Chashu"),Integer.parseInt(textField30.getText())));
		
		spiciness = spiceiness.getText(); 
		
		textField31.setText(Double.toString(Payment.calculateBillAmount(accountDishArray, accountAdd_onArray)));
		textField32.setText(Double.toString(Payment.calculateBillAmount(accountDishArray, accountAdd_onArray)));
	}

	/**
	 * Implementing the refreshment of the price according to backstage data.
	 * 
	 * @author YILIN WANG
	 */
	private void refreshPrice() {
		
		textField1.setText(Double.toString(Query.getAdd_onPrice(Query.getAdd_onID("Extra Nori"))));
		textField2.setText(Double.toString(Query.getAdd_onPrice(Query.getAdd_onID("Extra boiled egg"))));
		textField3.setText(Double.toString(Query.getAdd_onPrice(Query.getAdd_onID("Bamboo shoots"))));
		textField4.setText(Double.toString(Query.getAdd_onPrice(Query.getAdd_onID("Extra Chashu"))));
	
		}
	
	/**
	 * Implementing the refreshment of the availability of add on menu according to backstage data.
	 * 
	 * @author YILIN WANG
	 */
	private void refreshAddonAva() {
		if(!Add_on.getAdd_on().get(Query.getAdd_onIndex("Extra Nori")).getAvailable()){
			aNori2.setEnabled(false);
		}
		
		if(!Add_on.getAdd_on().get(Query.getAdd_onIndex("Extra boiled egg")).getAvailable()){
			aEgg2.setEnabled(false);
		}
		
		if(!Add_on.getAdd_on().get(Query.getAdd_onIndex("Bamboo shoots")).getAvailable()){
			aBamboo2.setEnabled(false);
		}
		
		if(!Add_on.getAdd_on().get(Query.getAdd_onIndex("Extra Chashu")).getAvailable()){
			aChashu2.setEnabled(false);
		}

		}

	/**
	 *  Implementing the refreshment of the availability of fixed dish according to backstage data.
	 *
	 * 
	 * @author YILIN WANG
	 */
	private void refreshDishAva() {
		
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Soup", "Tonkotsu")).getAvailable()){
			soupComboBox.removeItem("Tonkotsu");
		}
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Soup", "Shoyu")).getAvailable()){
			soupComboBox.removeItem("Shoyu");
		}
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Soup", "Shio")).getAvailable()){
			soupComboBox.removeItem("Shio");
		}
		
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Noodles", "Soft")).getAvailable()){
			noodlesComboBox.removeItem("Soft");
		}
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Noodles", "Medium")).getAvailable()){
			noodlesComboBox.removeItem("Medium");
		}
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Noodles", "Firm")).getAvailable()){
			noodlesComboBox.removeItem("Firm");
		}
		
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Spring onion", "No please")).getAvailable()){
			onionComboBox.removeItem("No please");
		}
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Spring onion", "Just a little")).getAvailable()){
			onionComboBox.removeItem("Just a little");
		}
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Spring onion", "A lot!")).getAvailable()){
			onionComboBox.removeItem("A lot!");
		}
		
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Nori", "Yes")).getAvailable()){
			nori.setEnabled(false);
		}
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Chashu", "Yes")).getAvailable()){
			chashu.setEnabled(false);
		}
		if(!DishOption.getDishOption().get(Query.getDishOptionIndex("Boiled egg", "Yes")).getAvailable()){
			egg.setEnabled(false);
		}

	}
	
	/**
	 * Calculate price when Select Meal pane state changed.
	 * Display the newest price of customer's choice.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void mealPaneStateChanged(ChangeEvent e) {
		if(selectMeal.isVisible())
		{	
			this.calculatePrice();
		}
	}

	/**
	 * The radio button CASH action performed.
	 * To store pay option-cash in varaible payOption.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 * 
	 */
	private void radioButton6ActionPerformed(ActionEvent e) {
		payOption = "Cash";
	}

	/**
	 * The radio button CARD action performed.
	 * To store pay option-card in varaible payOption.
	 * 
	 * @param e the e
	 * @author YILIN WANG
	 */
	private void radioButton5ActionPerformed(ActionEvent e) {
		payOption = "Card";
	}

	/**
	 * Inits the components of customer GUI.
	 * 
	 * @author YILIN WANG
	 */
	private void initComponents() {
		
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - YILIN WANG
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		selectMeal = new JFrame();
		mealPane = new JTabbedPane();
		panel1 = new JPanel();
		soupComboBox = new JComboBox<>();
		nori = new JCheckBox();
		button1 = new JButton();
		label1 = new JLabel();
		textField5 = new JLabel();
		noodlesComboBox = new JComboBox<>();
		onionComboBox = new JComboBox<>();
		chashu = new JCheckBox();
		egg = new JCheckBox();
		spice0 = new JRadioButton();
		spice1 = new JRadioButton();
		spice2 = new JRadioButton();
		spice3 = new JRadioButton();
		spice4 = new JRadioButton();
		spice5 = new JRadioButton();
		panel5 = new JPanel();
		aNori2 = new JSpinner();
		aEgg2 = new JSpinner();
		aChashu2 = new JSpinner();
		aBamboo2 = new JSpinner();
		textField13 = new JLabel();
		button20 = new JButton();
		button21 = new JButton();
		label16 = new JLabel();
		label17 = new JLabel();
		label18 = new JLabel();
		label19 = new JLabel();
		textField1 = new JTextField();
		textField2 = new JTextField();
		textField3 = new JTextField();
		textField4 = new JTextField();
		panel6 = new JPanel();
		textField21 = new JLabel();
		label24 = new JLabel();
		label25 = new JLabel();
		label26 = new JLabel();
		label27 = new JLabel();
		checkBox7 = new JCheckBox();
		checkBox8 = new JCheckBox();
		checkBox9 = new JCheckBox();
		button10 = new JButton();
		button11 = new JButton();
		textField22 = new JTextField();
		textField23 = new JTextField();
		textField24 = new JTextField();
		spiceiness = new JTextField();
		label28 = new JLabel();
		label29 = new JLabel();
		label30 = new JLabel();
		label31 = new JLabel();
		label32 = new JLabel();
		label34 = new JLabel();
		textField27 = new JTextField();
		textField28 = new JTextField();
		textField29 = new JTextField();
		textField30 = new JTextField();
		label35 = new JLabel();
		textField31 = new JTextField();
		label33 = new JLabel();
		radioButton3 = new JRadioButton();
		radioButton4 = new JRadioButton();
		customerLogin = new JFrame();
		password1 = new JPasswordField();
		userName = new JTextField();
		label2 = new JLabel();
		label3 = new JLabel();
		button13 = new JButton();
		title1 = compFactory.createTitle("Totoro Ramen");
		button2 = new JButton();
		label4 = new JLabel();
		vipLogin = new JFrame();
		textField11 = new JLabel();
		button12 = new JButton();
		button8 = new JButton();
		button9 = new JButton();
		membership = new JTextField();
		label8 = new JLabel();
		panel2 = new JPanel();
		customerSignup = new JFrame();
		panel11 = new JPanel();
		label5 = new JLabel();
		nickName = new JTextField();
		password2 = new JPasswordField();
		label6 = new JLabel();
		button3 = new JButton();
		vipSingup = new JFrame();
		label12 = new JLabel();
		label13 = new JLabel();
		label14 = new JLabel();
		label15 = new JLabel();
		fName = new JTextField();
		sName = new JTextField();
		eMail = new JTextField();
		mNumber = new JTextField();
		button4 = new JButton();
		label9 = new JLabel();
		textField14 = new JLabel();
		panel3 = new JPanel();
		payment = new JFrame();
		panel8 = new JPanel();
		label10 = new JLabel();
		stamp = new JTextField();
		panel9 = new JPanel();
		textField32 = new JTextField();
		label36 = new JLabel();
		card = new JRadioButton();
		cash = new JRadioButton();
		label37 = new JLabel();
		button5 = new JButton();
		label11 = new JLabel();
		panel4 = new JPanel();

		//======== selectMeal ========
		{
			selectMeal.setBackground(new Color(153, 0, 51));
			selectMeal.setForeground(Color.black);
			selectMeal.setFocusableWindowState(false);
			selectMeal.setIconImage(new ImageIcon("C:\\Users\\38573\\Desktop\\CW\\pic\\c.jpg").getImage());
			selectMeal.setTitle("Totoro Ramen");
			var selectMealContentPane = selectMeal.getContentPane();
			selectMealContentPane.setLayout(new BoxLayout(selectMealContentPane, BoxLayout.X_AXIS));

			//======== mealPane ========
			{
				mealPane.setBackground(Color.orange);
				mealPane.setForeground(new Color(204, 0, 51));
				mealPane.setFont(mealPane.getFont().deriveFont(Font.BOLD));
				mealPane.setBorder(Borders.TABBED_DIALOG);
				mealPane.addChangeListener(e -> mealPaneStateChanged(e));

				//======== panel1 ========
				{
					panel1.setBackground(Color.white);
					panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
					.border.EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax.swing.border.TitledBorder
					.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dialo\u0067",java.
					awt.Font.BOLD,12),java.awt.Color.red),panel1. getBorder()))
					;panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
					){if("borde\u0072".equals(e.getPropertyName()))throw new RuntimeException();}})
					;
					panel1.setLayout(null);

					//---- soupComboBox ----
					soupComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
						"Tonkotsu",
						"Shoyu",
						"Shio"
					}));
					soupComboBox.setFont(new Font("Bubblegum Sans", Font.BOLD, 18));
					soupComboBox.setAction(null);
					soupComboBox.setMaximumRowCount(3);
					panel1.add(soupComboBox);
					soupComboBox.setBounds(125, 70, 105, 25);

					//---- nori ----
					nori.setText("Nori");
					nori.setFont(new Font("Bubblegum Sans", Font.BOLD, 18));
					panel1.add(nori);
					nori.setBounds(335, 70, 60, 30);

					//---- button1 ----
					button1.setText("Next");
					button1.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					button1.addActionListener(e -> button1ActionPerformed(e));
					panel1.add(button1);
					button1.setBounds(490, 310, 65, 25);

					//---- label1 ----
					label1.setText("Spiciness");
					label1.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					panel1.add(label1);
					label1.setBounds(50, 225, 60, 25);

					//---- textField5 ----
					textField5.setText("Welcome and Select your own meal (Fixed price \uffe19.9)");
					textField5.setFont(new Font("Bubblegum Sans", Font.BOLD | Font.ITALIC, 20));
					panel1.add(textField5);
					textField5.setBounds(55, 10, 435, 35);

					//---- noodlesComboBox ----
					noodlesComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
						"Soft",
						"Medium",
						"Firm"
					}));
					noodlesComboBox.setFont(new Font("Bubblegum Sans", Font.BOLD, 18));
					noodlesComboBox.setAction(null);
					noodlesComboBox.setMaximumRowCount(3);
					panel1.add(noodlesComboBox);
					noodlesComboBox.setBounds(125, 120, 105, 25);

					//---- onionComboBox ----
					onionComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
						"No please",
						"Just a little",
						"A lot!"
					}));
					onionComboBox.setFont(new Font("Bubblegum Sans", Font.BOLD, 18));
					onionComboBox.setAction(null);
					onionComboBox.setMaximumRowCount(3);
					panel1.add(onionComboBox);
					onionComboBox.setBounds(125, 165, 105, 25);

					//---- chashu ----
					chashu.setText("Chashu");
					chashu.setFont(new Font("Bubblegum Sans", Font.BOLD, 18));
					panel1.add(chashu);
					chashu.setBounds(325, 115, 85, 30);

					//---- egg ----
					egg.setText("Boiled egg");
					egg.setFont(new Font("Bubblegum Sans", Font.BOLD, 18));
					panel1.add(egg);
					egg.setBounds(315, 165, 115, 30);

					//---- spice0 ----
					spice0.setText("0(No)");
					spice0.setMnemonic('0');
					spice0.setSelected(true);
					spice0.setFont(new Font("Bubblegum Sans", Font.BOLD, 12));
					spice0.addActionListener(e -> {
			spice0ActionPerformed(e);
			spice0ActionPerformed(e);
		});
					panel1.add(spice0);
					spice0.setBounds(130, 225, spice0.getPreferredSize().width, 23);

					//---- spice1 ----
					spice1.setText("1");
					spice1.setMnemonic('1');
					spice1.addActionListener(e -> spice1ActionPerformed(e));
					panel1.add(spice1);
					spice1.setBounds(206, 223, 55, 23);

					//---- spice2 ----
					spice2.setText("2");
					spice2.setMnemonic('2');
					spice2.addActionListener(e -> spice2ActionPerformed(e));
					panel1.add(spice2);
					spice2.setBounds(273, 224, 55, 23);

					//---- spice3 ----
					spice3.setText("3");
					spice3.setMnemonic('3');
					spice3.addActionListener(e -> spice3ActionPerformed(e));
					panel1.add(spice3);
					spice3.setBounds(340, 225, 55, 23);

					//---- spice4 ----
					spice4.setText("4");
					spice4.setMnemonic('4');
					spice4.addActionListener(e -> spice4ActionPerformed(e));
					panel1.add(spice4);
					spice4.setBounds(407, 226, 55, 23);

					//---- spice5 ----
					spice5.setText("5(Max)");
					spice5.setMnemonic('5');
					spice5.addActionListener(e -> spice5ActionPerformed(e));
					panel1.add(spice5);
					spice5.setBounds(474, 227, 66, 23);

					{
						// compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel1.getComponentCount(); i++) {
							Rectangle bounds = panel1.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel1.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel1.setMinimumSize(preferredSize);
						panel1.setPreferredSize(preferredSize);
					}
				}
				mealPane.addTab("Fixed", panel1);

				//======== panel5 ========
				{
					panel5.setBackground(Color.white);
					panel5.setLayout(null);

					//---- aNori2 ----
					aNori2.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					aNori2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					panel5.add(aNori2);
					aNori2.setBounds(380, 78, 45, 36);

					//---- aEgg2 ----
					aEgg2.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					aEgg2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					panel5.add(aEgg2);
					aEgg2.setBounds(380, 125, 45, 40);

					//---- aChashu2 ----
					aChashu2.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					aChashu2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					panel5.add(aChashu2);
					aChashu2.setBounds(380, 225, 45, 35);

					//---- aBamboo2 ----
					aBamboo2.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					aBamboo2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					panel5.add(aBamboo2);
					aBamboo2.setBounds(380, 175, 45, 35);

					//---- textField13 ----
					textField13.setText("Select your adds-ons");
					textField13.setFont(new Font("Bubblegum Sans", Font.BOLD | Font.ITALIC, 20));
					panel5.add(textField13);
					textField13.setBounds(180, 20, 200, 45);

					//---- button20 ----
					button20.setText("Next");
					button20.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					button20.addActionListener(e -> button20ActionPerformed(e));
					panel5.add(button20);
					button20.setBounds(485, 310, 80, 25);

					//---- button21 ----
					button21.setText("Back");
					button21.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					button21.addActionListener(e -> button21ActionPerformed(e));
					panel5.add(button21);
					button21.setBounds(5, 315, 80, 25);

					//---- label16 ----
					label16.setText("Extra Nori                      \u00a3");
					label16.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel5.add(label16);
					label16.setBounds(155, 80, 150, 36);

					//---- label17 ----
					label17.setText("Extra boiled egg          \u00a3");
					label17.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel5.add(label17);
					label17.setBounds(155, 127, 150, 40);

					//---- label18 ----
					label18.setText("Bamboo shoots            \u00a3");
					label18.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel5.add(label18);
					label18.setBounds(155, 178, 150, 35);

					//---- label19 ----
					label19.setText("Extra Chashu                \u00a3");
					label19.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel5.add(label19);
					label19.setBounds(155, 224, 150, 35);

					//---- textField1 ----
					textField1.setEditable(false);
					textField1.setText("1");
					textField1.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					textField1.setBackground(Color.white);
					panel5.add(textField1);
					textField1.setBounds(305, 87, 55, textField1.getPreferredSize().height);

					//---- textField2 ----
					textField2.setEditable(false);
					textField2.setText("1");
					textField2.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					textField2.setBackground(Color.white);
					panel5.add(textField2);
					textField2.setBounds(305, 136, 55, 22);

					//---- textField3 ----
					textField3.setEditable(false);
					textField3.setText("1");
					textField3.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					textField3.setBackground(Color.white);
					panel5.add(textField3);
					textField3.setBounds(305, 184, 55, 22);

					//---- textField4 ----
					textField4.setEditable(false);
					textField4.setText("2");
					textField4.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					textField4.setBackground(Color.white);
					panel5.add(textField4);
					textField4.setBounds(305, 230, 55, 22);

					{
						// compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel5.getComponentCount(); i++) {
							Rectangle bounds = panel5.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel5.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel5.setMinimumSize(preferredSize);
						panel5.setPreferredSize(preferredSize);
					}
				}
				mealPane.addTab("Add-on", panel5);

				//======== panel6 ========
				{
					panel6.setBackground(Color.white);
					panel6.setLayout(null);

					//---- textField21 ----
					textField21.setText("Please confirm what you have choosen");
					textField21.setFont(new Font("Bubblegum Sans", Font.BOLD | Font.ITALIC, 20));
					panel6.add(textField21);
					textField21.setBounds(115, 0, 335, 45);

					//---- label24 ----
					label24.setText("SOUP");
					label24.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel6.add(label24);
					label24.setBounds(135, 50, 55, 25);

					//---- label25 ----
					label25.setText("Noodles");
					label25.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel6.add(label25);
					label25.setBounds(125, 80, 65, 25);

					//---- label26 ----
					label26.setText("Spring onion");
					label26.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel6.add(label26);
					label26.setBounds(100, 105, 90, 25);

					//---- label27 ----
					label27.setText("Spiciness");
					label27.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel6.add(label27);
					label27.setBounds(120, 125, 70, 35);

					//---- checkBox7 ----
					checkBox7.setText("Boiled egg");
					checkBox7.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					checkBox7.setEnabled(false);
					panel6.add(checkBox7);
					checkBox7.setBounds(355, 125, 125, 35);

					//---- checkBox8 ----
					checkBox8.setText("Chashu");
					checkBox8.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					checkBox8.setEnabled(false);
					panel6.add(checkBox8);
					checkBox8.setBounds(355, 85, 100, 35);

					//---- checkBox9 ----
					checkBox9.setText("Nori");
					checkBox9.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					checkBox9.setEnabled(false);
					panel6.add(checkBox9);
					checkBox9.setBounds(355, 45, 80, 35);

					//---- button10 ----
					button10.setText("Back");
					button10.setFont(new Font("Bubblegum Sans", Font.PLAIN, 12));
					button10.addActionListener(e -> button10ActionPerformed(e));
					panel6.add(button10);
					button10.setBounds(5, 310, 72, 30);

					//---- button11 ----
					button11.setText("Finish");
					button11.setForeground(Color.red);
					button11.setFont(new Font("Bubblegum Sans", Font.PLAIN, 12));
					button11.addActionListener(e -> button11ActionPerformed(e));
					panel6.add(button11);
					button11.setBounds(495, 310, 73, 30);

					//---- textField22 ----
					textField22.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					textField22.setEditable(false);
					panel6.add(textField22);
					textField22.setBounds(195, 50, 75, 21);

					//---- textField23 ----
					textField23.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					textField23.setEditable(false);
					panel6.add(textField23);
					textField23.setBounds(195, 80, 75, 21);

					//---- textField24 ----
					textField24.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					textField24.setEditable(false);
					panel6.add(textField24);
					textField24.setBounds(195, 105, 75, 21);

					//---- spiceiness ----
					spiceiness.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					spiceiness.setEditable(false);
					spiceiness.setText("0");
					panel6.add(spiceiness);
					spiceiness.setBounds(195, 135, 75, 21);

					//---- label28 ----
					label28.setText("-------------------------------------------------------------------------------------------------------------------------------------");
					panel6.add(label28);
					label28.setBounds(20, 165, 535, label28.getPreferredSize().height);

					//---- label29 ----
					label29.setText("Extra Nori");
					label29.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel6.add(label29);
					label29.setBounds(115, 190, 80, 25);

					//---- label30 ----
					label30.setText("Extra boiled egg");
					label30.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel6.add(label30);
					label30.setBounds(330, 185, 115, 25);

					//---- label31 ----
					label31.setText("Bamboo shoots");
					label31.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel6.add(label31);
					label31.setBounds(85, 220, 110, 25);

					//---- label32 ----
					label32.setText("Extra Chashu");
					label32.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel6.add(label32);
					label32.setBounds(345, 215, 100, 25);

					//---- label34 ----
					label34.setText("Select your dining way:");
					label34.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel6.add(label34);
					label34.setBounds(35, 270, 165, 35);

					//---- textField27 ----
					textField27.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					textField27.setEditable(false);
					panel6.add(textField27);
					textField27.setBounds(195, 190, 55, 21);

					//---- textField28 ----
					textField28.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					textField28.setEditable(false);
					panel6.add(textField28);
					textField28.setBounds(450, 190, 55, 21);

					//---- textField29 ----
					textField29.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					textField29.setEditable(false);
					panel6.add(textField29);
					textField29.setBounds(195, 225, 55, 21);

					//---- textField30 ----
					textField30.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					textField30.setEditable(false);
					panel6.add(textField30);
					textField30.setBounds(450, 220, 55, 21);

					//---- label35 ----
					label35.setText("Total Price");
					label35.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					panel6.add(label35);
					label35.setBounds(355, 275, 90, 35);

					//---- textField31 ----
					textField31.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
					textField31.setEditable(false);
					panel6.add(textField31);
					textField31.setBounds(450, 280, 55, 25);

					//---- label33 ----
					label33.setText("-------------------------------------------------------------------------------------------------------------------------------------");
					panel6.add(label33);
					label33.setBounds(20, 255, 535, 18);

					//---- radioButton3 ----
					radioButton3.setText("Eat in");
					radioButton3.setSelected(true);
					radioButton3.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					radioButton3.addActionListener(e -> radioButton3ActionPerformed(e));
					panel6.add(radioButton3);
					radioButton3.setBounds(215, 275, 75, 20);

					//---- radioButton4 ----
					radioButton4.setText("Take-away");
					radioButton4.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
					radioButton4.addActionListener(e -> radioButton4ActionPerformed(e));
					panel6.add(radioButton4);
					radioButton4.setBounds(200, 300, 110, 20);

					{
						// compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel6.getComponentCount(); i++) {
							Rectangle bounds = panel6.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel6.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel6.setMinimumSize(preferredSize);
						panel6.setPreferredSize(preferredSize);
					}
				}
				mealPane.addTab("Confirmation", panel6);
			}
			selectMealContentPane.add(mealPane);
			selectMeal.setSize(600, 420);
			selectMeal.setLocationRelativeTo(selectMeal.getOwner());
		}

		//======== customerLogin ========
		{
			customerLogin.setAutoRequestFocus(false);
			customerLogin.setIconImage(new ImageIcon("C:\\Users\\38573\\Desktop\\CW\\pic\\c.jpg").getImage());
			customerLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			customerLogin.setTitle("Totoro Ramen");
			var customerLoginContentPane = customerLogin.getContentPane();
			customerLoginContentPane.setLayout(null);

			//---- password1 ----
			password1.setBackground(Color.white);
			customerLoginContentPane.add(password1);
			password1.setBounds(205, 185, 110, 30);

			//---- userName ----
			userName.setBackground(Color.white);
			userName.setForeground(Color.black);
			customerLoginContentPane.add(userName);
			userName.setBounds(205, 125, 110, 30);

			//---- label2 ----
			label2.setText("CUSTOMER ID");
			label2.setFont(new Font("Consolas", Font.BOLD, 14));
			label2.setForeground(Color.white);
			customerLoginContentPane.add(label2);
			label2.setBounds(75, 130, 90, 20);

			//---- label3 ----
			label3.setText("PASSWORD");
			label3.setFont(new Font("Consolas", Font.BOLD, 14));
			label3.setForeground(Color.white);
			customerLoginContentPane.add(label3);
			label3.setBounds(new Rectangle(new Point(80, 195), label3.getPreferredSize()));

			//---- button13 ----
			button13.setText("LOGIN");
			button13.setFont(new Font("Consolas", Font.PLAIN, 14));
			button13.addActionListener(e -> button13ActionPerformed(e));
			customerLoginContentPane.add(button13);
			button13.setBounds(new Rectangle(new Point(95, 245), button13.getPreferredSize()));

			//---- title1 ----
			title1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 45));
			title1.setForeground(Color.white);
			customerLoginContentPane.add(title1);
			title1.setBounds(45, 35, 298, 54);

			//---- button2 ----
			button2.setText("SIGN UP");
			button2.setFont(new Font("Consolas", Font.PLAIN, 14));
			button2.addActionListener(e -> button2ActionPerformed(e));
			customerLoginContentPane.add(button2);
			button2.setBounds(260, 245, 90, button2.getPreferredSize().height);

			//---- label4 ----
			label4.setIcon(new ImageIcon("C:\\Users\\38573\\Desktop\\CW\\pic\\c.jpg"));
			customerLoginContentPane.add(label4);
			label4.setBounds(0, 0, 440, 325);

			{
				// compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < customerLoginContentPane.getComponentCount(); i++) {
					Rectangle bounds = customerLoginContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = customerLoginContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				customerLoginContentPane.setMinimumSize(preferredSize);
				customerLoginContentPane.setPreferredSize(preferredSize);
			}
			customerLogin.pack();
			customerLogin.setLocationRelativeTo(customerLogin.getOwner());
		}

		//======== vipLogin ========
		{
			vipLogin.setBackground(Color.white);
			vipLogin.setIconImage(new ImageIcon("C:\\Users\\38573\\Desktop\\CW\\pic\\c.jpg").getImage());
			vipLogin.setTitle("Totoro Ramen");
			var vipLoginContentPane = vipLogin.getContentPane();
			vipLoginContentPane.setLayout(null);

			//---- textField11 ----
			textField11.setText("Do you have a membership /Would you like to join us?");
			textField11.setFont(new Font("Bubblegum Sans", Font.BOLD | Font.ITALIC, 20));
			vipLoginContentPane.add(textField11);
			textField11.setBounds(25, 15, 455, 35);

			//---- button12 ----
			button12.setText("SIGN UP");
			button12.setFont(new Font("Bubblegum Sans", Font.PLAIN, 18));
			button12.addActionListener(e -> button12ActionPerformed(e));
			vipLoginContentPane.add(button12);
			button12.setBounds(260, 125, 100, 31);

			//---- button8 ----
			button8.setText("LOGIN");
			button8.setFont(new Font("Bubblegum Sans", Font.PLAIN, 18));
			button8.addActionListener(e -> button8ActionPerformed(e));
			vipLoginContentPane.add(button8);
			button8.setBounds(new Rectangle(new Point(130, 125), button8.getPreferredSize()));

			//---- button9 ----
			button9.setText("No, thanks");
			button9.setFont(new Font("Bubblegum Sans", Font.PLAIN, 18));
			button9.addActionListener(e -> button9ActionPerformed(e));
			vipLoginContentPane.add(button9);
			button9.setBounds(new Rectangle(new Point(190, 190), button9.getPreferredSize()));
			vipLoginContentPane.add(membership);
			membership.setBounds(205, 70, 170, membership.getPreferredSize().height);

			//---- label8 ----
			label8.setText("Membership:");
			label8.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
			vipLoginContentPane.add(label8);
			label8.setBounds(new Rectangle(new Point(115, 70), label8.getPreferredSize()));

			//======== panel2 ========
			{
				panel2.setBackground(Color.white);
				panel2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
				swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border
				. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
				,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel2. getBorder
				( )) ); panel2. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
				.beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
				( ); }} );
				panel2.setLayout(new FlowLayout());
			}
			vipLoginContentPane.add(panel2);
			panel2.setBounds(0, -30, 500, 360);

			vipLoginContentPane.setPreferredSize(new Dimension(500, 275));
			vipLogin.pack();
			vipLogin.setLocationRelativeTo(vipLogin.getOwner());
		}

		//======== customerSignup ========
		{
			customerSignup.setIconImage(new ImageIcon("C:\\Users\\38573\\Desktop\\CW\\pic\\c.jpg").getImage());
			customerSignup.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			customerSignup.setTitle("Totoro Ramen");
			var customerSignupContentPane = customerSignup.getContentPane();
			customerSignupContentPane.setLayout(null);

			//======== panel11 ========
			{
				panel11.setBackground(Color.white);
				panel11.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
				( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
				.TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069al\u006fg", java .awt . Font. BOLD ,12 ) ,java . awt
				. Color .red ) ,panel11. getBorder () ) ); panel11. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
				propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
				;} } );
				panel11.setLayout(null);

				//---- label5 ----
				label5.setText("NICK NAME");
				label5.setFont(new Font("Consolas", Font.BOLD, 16));
				label5.setForeground(Color.pink);
				panel11.add(label5);
				label5.setBounds(55, 30, 85, 20);

				//---- nickName ----
				nickName.setBackground(Color.white);
				nickName.setForeground(Color.black);
				panel11.add(nickName);
				nickName.setBounds(180, 25, 110, 30);

				//---- password2 ----
				password2.setBackground(Color.white);
				panel11.add(password2);
				password2.setBounds(180, 85, 110, 30);

				//---- label6 ----
				label6.setText("PASSWORD");
				label6.setFont(new Font("Consolas", Font.BOLD, 16));
				label6.setForeground(Color.pink);
				panel11.add(label6);
				label6.setBounds(new Rectangle(new Point(55, 95), label6.getPreferredSize()));

				//---- button3 ----
				button3.setText("SIGN UP!");
				button3.setFont(new Font("Consolas", Font.PLAIN, 14));
				button3.addActionListener(e -> button3ActionPerformed(e));
				panel11.add(button3);
				button3.setBounds(120, 140, 105, button3.getPreferredSize().height);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel11.getComponentCount(); i++) {
						Rectangle bounds = panel11.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel11.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel11.setMinimumSize(preferredSize);
					panel11.setPreferredSize(preferredSize);
				}
			}
			customerSignupContentPane.add(panel11);
			panel11.setBounds(-5, 0, 340, 210);

			{
				// compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < customerSignupContentPane.getComponentCount(); i++) {
					Rectangle bounds = customerSignupContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = customerSignupContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				customerSignupContentPane.setMinimumSize(preferredSize);
				customerSignupContentPane.setPreferredSize(preferredSize);
			}
			customerSignup.pack();
			customerSignup.setLocationRelativeTo(customerSignup.getOwner());
		}

		//======== vipSingup ========
		{
			vipSingup.setIconImage(new ImageIcon("C:\\Users\\38573\\Desktop\\CW\\pic\\c.jpg").getImage());
			vipSingup.setBackground(Color.white);
			vipSingup.setTitle("Totoro Ramen");
			vipSingup.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			var vipSingupContentPane = vipSingup.getContentPane();
			vipSingupContentPane.setLayout(null);

			//---- label12 ----
			label12.setText("*First Name:");
			label12.setFont(new Font("Bubblegum Sans", Font.PLAIN, 15));
			vipSingupContentPane.add(label12);
			label12.setBounds(70, 60, 75, 21);

			//---- label13 ----
			label13.setText("*Surname:");
			label13.setFont(new Font("Bubblegum Sans", Font.PLAIN, 15));
			vipSingupContentPane.add(label13);
			label13.setBounds(85, 90, 60, 21);

			//---- label14 ----
			label14.setText("Email:");
			label14.setFont(new Font("Bubblegum Sans", Font.PLAIN, 15));
			vipSingupContentPane.add(label14);
			label14.setBounds(105, 125, 40, 21);

			//---- label15 ----
			label15.setText("Mobile number:");
			label15.setFont(new Font("Bubblegum Sans", Font.PLAIN, 15));
			vipSingupContentPane.add(label15);
			label15.setBounds(50, 160, 100, 21);
			vipSingupContentPane.add(fName);
			fName.setBounds(155, 60, 65, 21);
			vipSingupContentPane.add(sName);
			sName.setBounds(155, 95, 65, 21);
			vipSingupContentPane.add(eMail);
			eMail.setBounds(155, 125, 65, 21);
			vipSingupContentPane.add(mNumber);
			mNumber.setBounds(155, 160, 65, 21);

			//---- button4 ----
			button4.setText("Confirm");
			button4.addActionListener(e -> button4ActionPerformed(e));
			vipSingupContentPane.add(button4);
			button4.setBounds(new Rectangle(new Point(110, 225), button4.getPreferredSize()));

			//---- label9 ----
			label9.setText("*Please select at least one contact method");
			vipSingupContentPane.add(label9);
			label9.setBounds(new Rectangle(new Point(25, 195), label9.getPreferredSize()));

			//---- textField14 ----
			textField14.setText("Join the loyalty scheme now!");
			textField14.setFont(new Font("Bubblegum Sans", Font.BOLD | Font.ITALIC, 20));
			vipSingupContentPane.add(textField14);
			textField14.setBounds(20, 10, 245, 35);

			//======== panel3 ========
			{
				panel3.setBackground(Color.white);
				panel3.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .
				EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax . swing
				. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,
				java . awt. Color .red ) ,panel3. getBorder () ) ); panel3. addPropertyChangeListener( new java. beans .PropertyChangeListener ( )
				{ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )
				throw new RuntimeException( ) ;} } );
				panel3.setLayout(new FlowLayout());
			}
			vipSingupContentPane.add(panel3);
			panel3.setBounds(0, -5, 290, 270);

			{
				// compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < vipSingupContentPane.getComponentCount(); i++) {
					Rectangle bounds = vipSingupContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = vipSingupContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				vipSingupContentPane.setMinimumSize(preferredSize);
				vipSingupContentPane.setPreferredSize(preferredSize);
			}
			vipSingup.pack();
			vipSingup.setLocationRelativeTo(vipSingup.getOwner());
		}

		//======== payment ========
		{
			payment.setIconImage(new ImageIcon("C:\\Users\\38573\\Desktop\\CW\\pic\\c.jpg").getImage());
			payment.setTitle("Totoro Ramen");
			var paymentContentPane = payment.getContentPane();
			paymentContentPane.setLayout(null);

			//======== panel8 ========
			{
				panel8.setBackground(Color.white);
				panel8.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder (
				0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder
				. BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .
				red ) ,panel8. getBorder () ) ); panel8. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java .
				beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
				panel8.setLayout(null);

				//---- label10 ----
				label10.setText("Your stamps:");
				label10.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
				panel8.add(label10);
				label10.setBounds(new Rectangle(new Point(90, 10), label10.getPreferredSize()));

				//---- stamp ----
				stamp.setEditable(false);
				panel8.add(stamp);
				stamp.setBounds(200, 10, 50, stamp.getPreferredSize().height);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel8.getComponentCount(); i++) {
						Rectangle bounds = panel8.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel8.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel8.setMinimumSize(preferredSize);
					panel8.setPreferredSize(preferredSize);
				}
			}
			paymentContentPane.add(panel8);
			panel8.setBounds(50, 20, 415, 45);

			//======== panel9 ========
			{
				panel9.setBackground(Color.white);
				panel9.setLayout(null);

				//---- textField32 ----
				textField32.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
				textField32.setEditable(false);
				panel9.add(textField32);
				textField32.setBounds(125, 5, 81, textField32.getPreferredSize().height);

				//---- label36 ----
				label36.setText("Total Price:");
				label36.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
				panel9.add(label36);
				label36.setBounds(40, 5, 76, label36.getPreferredSize().height);

				//---- card ----
				card.setText("Card");
				card.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
				card.addActionListener(e -> radioButton5ActionPerformed(e));
				panel9.add(card);
				card.setBounds(125, 65, 75, 20);

				//---- cash ----
				cash.setText("Cash");
				cash.setSelected(true);
				cash.setFont(new Font("Bubblegum Sans", Font.PLAIN, 16));
				cash.addActionListener(e -> radioButton6ActionPerformed(e));
				panel9.add(cash);
				cash.setBounds(125, 40, 75, 20);

				//---- label37 ----
				label37.setText("Payment:");
				label37.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
				panel9.add(label37);
				label37.setBounds(50, 40, 66, 35);

				//---- button5 ----
				button5.setText("Pay");
				button5.setFont(new Font("Bubblegum Sans", Font.BOLD, 12));
				button5.addActionListener(e -> button5ActionPerformed(e));
				panel9.add(button5);
				button5.setBounds(new Rectangle(new Point(100, 95), button5.getPreferredSize()));

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel9.getComponentCount(); i++) {
						Rectangle bounds = panel9.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel9.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel9.setMinimumSize(preferredSize);
					panel9.setPreferredSize(preferredSize);
				}
			}
			paymentContentPane.add(panel9);
			panel9.setBounds(115, 130, 245, 125);

			//---- label11 ----
			label11.setText("<html><body><p align=center>You have collected 10 stamps! <br/>And this meal will be free!!!<br/>Congratulations!</p></body></html>");
			label11.setFont(new Font("Bubblegum Sans", Font.BOLD, 16));
			paymentContentPane.add(label11);
			label11.setBounds(new Rectangle(new Point(140, 70), label11.getPreferredSize()));

			//======== panel4 ========
			{
				panel4.setBackground(Color.white);
				panel4.setLayout(new FlowLayout());
			}
			paymentContentPane.add(panel4);
			panel4.setBounds(0, 0, 490, 290);

			{
				// compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < paymentContentPane.getComponentCount(); i++) {
					Rectangle bounds = paymentContentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = paymentContentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				paymentContentPane.setMinimumSize(preferredSize);
				paymentContentPane.setPreferredSize(preferredSize);
			}
			payment.pack();
			payment.setLocationRelativeTo(payment.getOwner());
		}

		//---- buttonGroup4 ----
		var buttonGroup4 = new ButtonGroup();
		buttonGroup4.add(spice0);
		buttonGroup4.add(spice1);
		buttonGroup4.add(spice2);
		buttonGroup4.add(spice3);
		buttonGroup4.add(spice4);
		buttonGroup4.add(spice5);

		//---- buttonGroup1 ----
		var buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(radioButton3);
		buttonGroup1.add(radioButton4);

		//---- buttonGroup3 ----
		var buttonGroup3 = new ButtonGroup();
		buttonGroup3.add(card);
		buttonGroup3.add(cash);

		//---- bindings ----
		bindingGroup = new BindingGroup();
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			noodlesComboBox, BeanProperty.create("selectedItem"),
			textField23, BeanProperty.create("text")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			onionComboBox, BeanProperty.create("selectedItem"),
			textField24, BeanProperty.create("text")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			nori, BeanProperty.create("selected"),
			checkBox9, BeanProperty.create("selected")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			chashu, BeanProperty.create("selected"),
			checkBox8, BeanProperty.create("selected")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			egg, BeanProperty.create("selected"),
			checkBox7, BeanProperty.create("selected")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			aNori2, BeanProperty.create("value"),
			textField27, BeanProperty.create("text")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			aBamboo2, BeanProperty.create("value"),
			textField29, BeanProperty.create("text")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			aEgg2, BeanProperty.create("value"),
			textField28, BeanProperty.create("text")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			aChashu2, BeanProperty.create("value"),
			textField30, BeanProperty.create("text")));
		bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			soupComboBox, BeanProperty.create("selectedItem"),
			textField22, BeanProperty.create("text")));
		bindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	/**
	 * Gets the customer login.
	 *
	 * @return the customer login
	 * @author YILIN WANG
	 */
	public JFrame getCustomerLogin() {
		return customerLogin;
	}

	/**
	 * Sets the customer login.
	 *
	 * @param customerLogin the new customer login
	 * @author YILIN WANG
	 */
	public void setCustomerLogin(JFrame customerLogin) {
		this.customerLogin = customerLogin;
	}
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - YILIN WANG
	private JFrame selectMeal;
	private JTabbedPane mealPane;
	private JPanel panel1;
	private JComboBox<String> soupComboBox;
	private JCheckBox nori;
	private JButton button1;
	private JLabel label1;
	private JLabel textField5;
	private JComboBox<String> noodlesComboBox;
	private JComboBox<String> onionComboBox;
	private JCheckBox chashu;
	private JCheckBox egg;
	private JRadioButton spice0;
	private JRadioButton spice1;
	private JRadioButton spice2;
	private JRadioButton spice3;
	private JRadioButton spice4;
	private JRadioButton spice5;
	private JPanel panel5;
	private JSpinner aNori2;
	private JSpinner aEgg2;
	private JSpinner aChashu2;
	private JSpinner aBamboo2;
	private JLabel textField13;
	private JButton button20;
	private JButton button21;
	private JLabel label16;
	private JLabel label17;
	private JLabel label18;
	private JLabel label19;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JPanel panel6;
	private JLabel textField21;
	private JLabel label24;
	private JLabel label25;
	private JLabel label26;
	private JLabel label27;
	private JCheckBox checkBox7;
	private JCheckBox checkBox8;
	private JCheckBox checkBox9;
	private JButton button10;
	private JButton button11;
	private JTextField textField22;
	private JTextField textField23;
	private JTextField textField24;
	private JTextField spiceiness;
	private JLabel label28;
	private JLabel label29;
	private JLabel label30;
	private JLabel label31;
	private JLabel label32;
	private JLabel label34;
	private JTextField textField27;
	private JTextField textField28;
	private JTextField textField29;
	private JTextField textField30;
	private JLabel label35;
	private JTextField textField31;
	private JLabel label33;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JFrame customerLogin;
	private JPasswordField password1;
	private JTextField userName;
	private JLabel label2;
	private JLabel label3;
	private JButton button13;
	private JLabel title1;
	private JButton button2;
	private JLabel label4;
	private JFrame vipLogin;
	private JLabel textField11;
	private JButton button12;
	private JButton button8;
	private JButton button9;
	private JTextField membership;
	private JLabel label8;
	private JPanel panel2;
	private JFrame customerSignup;
	private JPanel panel11;
	private JLabel label5;
	private JTextField nickName;
	private JPasswordField password2;
	private JLabel label6;
	private JButton button3;
	private JFrame vipSingup;
	private JLabel label12;
	private JLabel label13;
	private JLabel label14;
	private JLabel label15;
	private JTextField fName;
	private JTextField sName;
	private JTextField eMail;
	private JTextField mNumber;
	private JButton button4;
	private JLabel label9;
	private JLabel textField14;
	private JPanel panel3;
	private JFrame payment;
	private JPanel panel8;
	private JLabel label10;
	private JTextField stamp;
	private JPanel panel9;
	private JTextField textField32;
	private JLabel label36;
	private JRadioButton card;
	private JRadioButton cash;
	private JLabel label37;
	private JButton button5;
	private JLabel label11;
	private JPanel panel4;
	private BindingGroup bindingGroup;
			// JFormDesigner - End of variables declaration  //GEN-END:variables

	
}
