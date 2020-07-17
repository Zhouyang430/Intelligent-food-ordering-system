package boundary;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import com.jgoodies.forms.factories.*;

import control.*;
import entity.*;
/*
 * Created by JFormDesigner on Wed May 27 19:35:56 CST 2020
 */


/**
 * @author Xinyi Luo
 */
public class ManagerGUI extends JFrame {
    public ManagerGUI() {
        initComponents();
    }

    private void button18MouseClicked(MouseEvent e) {
    	menuMod.setVisible(false);
    	stateView.setVisible(false);
    	manaSumFunc.setVisible(true);
    }

    private void button4MouseClicked(MouseEvent e) {
    	manaSumFunc.setVisible(false);
    	menuMod.setVisible(false);
    	stateView.setVisible(false);
    	manaLogin.setVisible(true);
    }

    private void passwordField1ActionPerformed(ActionEvent e){
    	if (ManagerLogin.loginValid(textField2.getText(),new String(passwordField1.getPassword()))) {
    	    box = Manager.getManager().get(index).getInvoice();
            index=Query.getManagerIndex(textField2.getText());
            checkBox1.setSelected(box);
    		manaLogin.setVisible(false);
    		manaSumFunc.setVisible(true);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "wrong password");
    	}

        passwordField1.setText(""); // Clear the text in passwordField
    }

    private void button13MouseClicked(MouseEvent e) {
        if (ManagerLogin.loginValid(new String(textField2.getText()),new String(passwordField1.getPassword()))) {
            box = Manager.getManager().get(index).getInvoice();
            index=Query.getManagerIndex(textField2.getText());
            checkBox1.setSelected(box);
            manaLogin.setVisible(false);
            manaSumFunc.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "wrong password");
        }

        passwordField1.setText(""); // Clear the text in passwordField
    }

    private void button1MouseClicked(MouseEvent e) {
    	manaSumFunc.setVisible(false);
    	menuMod.setVisible(true);
    }

    private void button17MouseClicked(MouseEvent e) {
    	menuModSub1.setVisible(false);
    	menuModSub2.setVisible(false);
    }

    private void button19MouseClicked(MouseEvent e) throws IOException {
        Add_on item1;
        Dish item2;
        double price = 0;
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(textField4.getText());
        if (itemName == "Nori") {
            item1 = Add_on.getAdd_on().get(Query.getAdd_onIndex("Extra Nori"));
            item2 = Dish.getDish().get(Query.getDishIndex(itemName));
            ModifyMenu.modifyOptionAvaliable(item2.getDishID(), radio);
        } else if (itemName == "Boiled egg") {
            item1 = Add_on.getAdd_on().get(Query.getAdd_onIndex("Extra boiled egg"));
            item2 = Dish.getDish().get(Query.getDishIndex(itemName));
            ModifyMenu.modifyOptionAvaliable(item2.getDishID(), radio);
        } else if (itemName == "Bamboo shoot") {
            item1 = Add_on.getAdd_on().get(Query.getAdd_onIndex("Bamboo shoots"));
        } else {
            item1 = Add_on.getAdd_on().get(Query.getAdd_onIndex("Extra Chashu"));
            item2 = Dish.getDish().get(Query.getDishIndex(itemName));
            ModifyMenu.modifyOptionAvaliable(item2.getDishID(), radio);
        }
        if (isNum.matches()) {
            price = Double.parseDouble(textField4.getText());
            ModifyMenu.modifyAddonPrice(item1.getAdd_onID(), price);
            ModifyMenu.modifyAddonAvaliable(item1.getAdd_onID(), radio);
            menuModSub1.setVisible(false);
        }else
            JOptionPane.showMessageDialog(null, "Invalid input");
        textField4.setText("");
    }

    private void button22MouseClicked(MouseEvent e) throws IOException {
        if (itemName == "Tonkotsu" || itemName == "Shoyu" || itemName == "Shio") {
            DishOption item = DishOption.getDishOption().get(Query.getDishOptionIndex("Soup", itemName));
            ModifyMenu.modifyOptionAvaliable(item.getOptionID(), radio);
        } else if (itemName == "Soft" || itemName == "Medium" || itemName == "Firm") {
            DishOption item = DishOption.getDishOption().get(Query.getDishOptionIndex("Noodles", itemName));
            ModifyMenu.modifyOptionAvaliable(item.getOptionID(),radio);
        } else {
            Dish item = Dish.getDish().get(Query.getDishIndex(itemName));
            ModifyMenu.modifyDishAvaliable(item.getDishID(), radio);

        }
        menuModSub2.setVisible(false);
    }

    private void button10MouseClicked(MouseEvent e) {
        Boolean available = Boolean.FALSE;
        itemName = ((JButton) (e.getSource())).getText();
        if(itemName == "Tonkotsu"||itemName == "Shoyu"||itemName == "Shio"){
            DishOption item = DishOption.getDishOption().get(Query.getDishOptionIndex("Soup", itemName));
            available = item.getAvailable();
        }
        else if(itemName == "Soft"||itemName == "Medium"||itemName == "Firm"){
            DishOption item = DishOption.getDishOption().get(Query.getDishOptionIndex("Noodles", itemName));
            available = item.getAvailable();
        }
        else {
            Dish item = Dish.getDish().get(Query.getDishIndex(((JButton) (e.getSource())).getText()));
            available = item.getAvailable();
        }
        radio = available;
        radioButton2.setSelected(available);
        menuModSub2.setVisible(true);
    }

    private void button15MouseClicked(MouseEvent e) {
        Boolean available = Boolean.FALSE;
        Double price;
        itemName = ((JButton) (e.getSource())).getText();
        if(itemName == "Nori"){
            Add_on item = Add_on.getAdd_on().get(Query.getAdd_onIndex("Extra Nori"));
            available = item.getAvailable();
            price = item.getPrice();
        }
        else if(itemName == "Boiled egg"){
            Add_on item = Add_on.getAdd_on().get(Query.getAdd_onIndex("Extra boiled egg"));
            available = item.getAvailable();
            price = item.getPrice();
        }
        else if(itemName == "Bamboo shoot"){
            Add_on item = Add_on.getAdd_on().get(Query.getAdd_onIndex("Bamboo shoots"));
            available = item.getAvailable();
            price = item.getPrice();
        }
        else{
            Add_on item = Add_on.getAdd_on().get(Query.getAdd_onIndex("Extra Chashu"));
            available = item.getAvailable();
            price = item.getPrice();
        }
        radio = available;
        radioButton1.setSelected(available);
        label2.setText(String.format("Price:%.2f -->",price));
        menuModSub1.setVisible(true);
    }

    private void button2MouseClicked(MouseEvent e) {
    	manaSumFunc.setVisible(false);

        ArrayList<StatisticsDishOption> statisticsDishOption =Statistics.getLastWeekDishOptionStatistics();

        textField18.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Soup", "Tonkotsu")).getAmount()));
        textField19.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Soup", "Shoyu")).getAmount()));
        textField20.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Soup", "Tonkotsu")).getAmount()));

        textField24.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Noodles", "Soft")).getAmount()));
        textField25.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Noodles", "Medium")).getAmount()));
        textField26.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Noodles", "Firm")).getAmount()));


        textField30.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Spring onion", "No please")).getAmount()));
        textField31.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Spring onion", "Just a little")).getAmount()));
        textField32.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Spring onion", "A lot!")).getAmount()));

        int[] spicinessInOrder=Statistics.getSpicinessStatistics();
        textField36.setText(Integer.toString(spicinessInOrder[0]));
        textField37.setText(Integer.toString(spicinessInOrder[1]));
        textField38.setText(Integer.toString(spicinessInOrder[2]));
        textField41.setText(Integer.toString(spicinessInOrder[3]));
        textField42.setText(Integer.toString(spicinessInOrder[4]));
        textField44.setText(Integer.toString(spicinessInOrder[5]));

        textField48.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Nori", "Yes")).getAmount()));
        textField49.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Chashu", "Yes")).getAmount()));
        textField50.setText(Integer.toString(statisticsDishOption.get(Query.getStatisticsDishOptionIndex(statisticsDishOption, "Boiled egg", "Yes")).getAmount()));


        ArrayList<StatisticsAdd_on> statisticsAdd_on=Statistics.getLastWeekAdd_onStatistics();
        textField54.setText(Integer.toString(statisticsAdd_on.get(Query.getStatisticsAdd_onIndex(statisticsAdd_on, "Extra Nori")).getAmount()));
        textField55.setText(Integer.toString(statisticsAdd_on.get(Query.getStatisticsAdd_onIndex(statisticsAdd_on, "Extra boiled egg")).getAmount()));
        textField56.setText(Integer.toString(statisticsAdd_on.get(Query.getStatisticsAdd_onIndex(statisticsAdd_on, "Bamboo shoots")).getAmount()));
        textField57.setText(Integer.toString(statisticsAdd_on.get(Query.getStatisticsAdd_onIndex(statisticsAdd_on, "Extra Chashu")).getAmount()));


        stateView.setVisible(true);
    }

    private void radioButton1StateChanged(ChangeEvent e) {
        if (((JRadioButton)e.getSource()).isSelected() != radio)
            radio = !radio;
        System.out.println(radio);
    }

    private void radioButton2StateChanged(ChangeEvent e) {
        if (((JRadioButton)e.getSource()).isSelected() != radio)
            radio = !radio;
        System.out.println(radio);
    }

    private void checkBox1StateChanged(ChangeEvent e) throws IOException {
        if(box != ((JCheckBox)e.getSource()).isSelected()){
            box = !box;
            Manager.getManager().get(index).setInvoice(box);
        }
        if(box){
            Statistics.sendReport(Manager.getManager().get(index));
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Xinyi Luo
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        manaLogin = new JFrame();
        passwordField1 = new JPasswordField();
        textField2 = new JTextField();
        label4 = new JLabel();
        label3 = new JLabel();
        button13 = new JButton();
        title1 = compFactory.createTitle("Totoro Ramen");
        label1 = new JLabel();
        manaSumFunc = new JFrame();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        checkBox1 = new JCheckBox();
        menuModSub1 = new JFrame();
        label2 = new JLabel();
        button17 = new JButton();
        radioButton1 = new JRadioButton();
        button19 = new JButton();
        textField4 = new JTextField();
        menuModSub2 = new JFrame();
        button21 = new JButton();
        radioButton2 = new JRadioButton();
        button22 = new JButton();
        menuMod = new JFrame();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        button14 = new JButton();
        button15 = new JButton();
        button16 = new JButton();
        textField1 = new JTextField();
        button18 = new JButton();
        stateView = new JFrame();
        viewState = new JTabbedPane();
        panel5 = new JPanel();
        label18 = new JLabel();
        label19 = new JLabel();
        label20 = new JLabel();
        textField18 = new JTextField();
        textField19 = new JTextField();
        textField20 = new JTextField();
        button33 = new JButton();
        button34 = new JButton();
        panel7 = new JPanel();
        panel9 = new JPanel();
        label24 = new JLabel();
        label25 = new JLabel();
        label26 = new JLabel();
        textField24 = new JTextField();
        textField25 = new JTextField();
        textField26 = new JTextField();
        button31 = new JButton();
        button32 = new JButton();
        panel6 = new JPanel();
        panel10 = new JPanel();
        label30 = new JLabel();
        label31 = new JLabel();
        label32 = new JLabel();
        textField30 = new JTextField();
        textField31 = new JTextField();
        textField32 = new JTextField();
        button29 = new JButton();
        button30 = new JButton();
        panel8 = new JPanel();
        panel11 = new JPanel();
        label36 = new JLabel();
        label37 = new JLabel();
        label38 = new JLabel();
        textField36 = new JTextField();
        textField37 = new JTextField();
        textField38 = new JTextField();
        label41 = new JLabel();
        label42 = new JLabel();
        textField41 = new JTextField();
        textField42 = new JTextField();
        label44 = new JLabel();
        textField44 = new JTextField();
        button27 = new JButton();
        button28 = new JButton();
        panel1 = new JPanel();
        panel12 = new JPanel();
        label48 = new JLabel();
        label49 = new JLabel();
        label50 = new JLabel();
        textField48 = new JTextField();
        textField49 = new JTextField();
        textField50 = new JTextField();
        button25 = new JButton();
        button26 = new JButton();
        panel2 = new JPanel();
        panel13 = new JPanel();
        label54 = new JLabel();
        label55 = new JLabel();
        label56 = new JLabel();
        textField54 = new JTextField();
        textField55 = new JTextField();
        textField56 = new JTextField();
        textField57 = new JTextField();
        label57 = new JLabel();
        button23 = new JButton();
        button24 = new JButton();

        //======== manaLogin ========
        {
            manaLogin.setAutoRequestFocus(false);
            manaLogin.setTitle("LOGIN");
            var manaLoginContentPane = manaLogin.getContentPane();
            manaLoginContentPane.setLayout(null);

            //---- passwordField1 ----
            passwordField1.setBackground(Color.white);
            passwordField1.addActionListener(e -> passwordField1ActionPerformed(e));
            manaLoginContentPane.add(passwordField1);
            passwordField1.setBounds(210, 165, 110, 30);
            manaLoginContentPane.add(textField2);
            textField2.setBounds(210, 105, 110, 30);

            //---- label4 ----
            label4.setText("ID");
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 6f));
            label4.setForeground(Color.white);
            manaLoginContentPane.add(label4);
            label4.setBounds(125, 105, 66, label4.getPreferredSize().height);

            //---- label3 ----
            label3.setText("PASSWORD");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 6f));
            label3.setForeground(Color.white);
            manaLoginContentPane.add(label3);
            label3.setBounds(85, 165, label3.getPreferredSize().width, 20);

            //---- button13 ----
            button13.setText("LOGIN");
            button13.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button13MouseClicked(e);
                }
            });
            manaLoginContentPane.add(button13);
            button13.setBounds(new Rectangle(new Point(155, 210), button13.getPreferredSize()));

            //---- title1 ----
            title1.setFont(new Font("Consolas", Font.BOLD, 45));
            title1.setForeground(Color.gray);
            manaLoginContentPane.add(title1);
            title1.setBounds(40, 35, 310, 65);

            //---- label1 ----
            label1.setIcon(new ImageIcon("C:\\Users\\Administrator.DESKTOP-V1BU105\\Desktop\\ramen.jpg"));
            label1.setForeground(Color.white);
            manaLoginContentPane.add(label1);
            label1.setBounds(0, 0, 380, 275);

            manaLoginContentPane.setPreferredSize(new Dimension(380, 305));
            manaLogin.pack();
            manaLogin.setLocationRelativeTo(manaLogin.getOwner());
        }

        //======== manaSumFunc ========
        {
            manaSumFunc.setForeground(Color.white);
            manaSumFunc.setAutoRequestFocus(false);
            manaSumFunc.setTitle("OPTION");
            var manaSumFuncContentPane = manaSumFunc.getContentPane();
            manaSumFuncContentPane.setLayout(null);

            //---- button1 ----
            button1.setText("Modify menu");
            button1.setBackground(new Color(255, 153, 0));
            button1.setForeground(Color.white);
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            manaSumFuncContentPane.add(button1);
            button1.setBounds(new Rectangle(new Point(130, 105), button1.getPreferredSize()));

            //---- button2 ----
            button2.setText("View State");
            button2.setBackground(new Color(255, 153, 0));
            button2.setForeground(Color.white);
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            manaSumFuncContentPane.add(button2);
            button2.setBounds(new Rectangle(new Point(135, 170), button2.getPreferredSize()));

            //---- button3 ----
            button3.setText("X");
            button3.setBackground(new Color(255, 0, 51));
            button3.setForeground(Color.white);
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button4MouseClicked(e);
                }
            });
            manaSumFuncContentPane.add(button3);
            button3.setBounds(320, 20, 25, 20);

            //---- checkBox1 ----
            checkBox1.setText("Automatically report");
            checkBox1.setFont(checkBox1.getFont().deriveFont(checkBox1.getFont().getStyle() | Font.ITALIC));
            checkBox1.addChangeListener(e -> {
                try {
                    checkBox1StateChanged(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            manaSumFuncContentPane.add(checkBox1);
            checkBox1.setBounds(new Rectangle(new Point(230, 245), checkBox1.getPreferredSize()));

            manaSumFuncContentPane.setPreferredSize(new Dimension(380, 305));
            manaSumFunc.pack();
            manaSumFunc.setLocationRelativeTo(manaSumFunc.getOwner());
        }

        //======== menuModSub1 ========
        {
            menuModSub1.setForeground(Color.white);
            menuModSub1.setAutoRequestFocus(false);
            var menuModSub1ContentPane = menuModSub1.getContentPane();
            menuModSub1ContentPane.setLayout(null);

            //---- label2 ----
            label2.setText("Price:  \u00a31   -->");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD));
            menuModSub1ContentPane.add(label2);
            label2.setBounds(new Rectangle(new Point(55, 40), label2.getPreferredSize()));

            //---- button17 ----
            button17.setText("X");
            button17.setBackground(new Color(255, 0, 51));
            button17.setForeground(Color.white);
            button17.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button17MouseClicked(e);
                }
            });
            menuModSub1ContentPane.add(button17);
            button17.setBounds(190, 5, 25, 20);

            //---- radioButton1 ----
            radioButton1.setText("Available");
            radioButton1.setFont(radioButton1.getFont().deriveFont(radioButton1.getFont().getStyle() | Font.BOLD));
            radioButton1.addChangeListener(e -> radioButton1StateChanged(e));
            menuModSub1ContentPane.add(radioButton1);
            radioButton1.setBounds(new Rectangle(new Point(70, 65), radioButton1.getPreferredSize()));

            //---- button19 ----
            button19.setText("Confirm");
            button19.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        button19MouseClicked(e);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            menuModSub1ContentPane.add(button19);
            button19.setBounds(new Rectangle(new Point(70, 100), button19.getPreferredSize()));
            menuModSub1ContentPane.add(textField4);
            textField4.setBounds(140, 35, 30, 25);

            menuModSub1ContentPane.setPreferredSize(new Dimension(230, 160));
            menuModSub1.pack();
            menuModSub1.setLocationRelativeTo(menuModSub1.getOwner());
        }

        //======== menuModSub2 ========
        {
            menuModSub2.setForeground(Color.white);
            menuModSub2.setAutoRequestFocus(false);
            var menuModSub2ContentPane = menuModSub2.getContentPane();
            menuModSub2ContentPane.setLayout(null);

            //---- button21 ----
            button21.setText("X");
            button21.setBackground(new Color(255, 0, 51));
            button21.setForeground(Color.white);
            button21.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button17MouseClicked(e);
                }
            });
            menuModSub2ContentPane.add(button21);
            button21.setBounds(190, 5, 25, 20);

            //---- radioButton2 ----
            radioButton2.setText("Available");
            radioButton2.setFont(radioButton2.getFont().deriveFont(radioButton2.getFont().getStyle() | Font.BOLD));
            radioButton2.addChangeListener(e -> radioButton2StateChanged(e));
            menuModSub2ContentPane.add(radioButton2);
            radioButton2.setBounds(new Rectangle(new Point(70, 50), radioButton2.getPreferredSize()));

            //---- button22 ----
            button22.setText("Confirm");
            button22.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        button22MouseClicked(e);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            menuModSub2ContentPane.add(button22);
            button22.setBounds(new Rectangle(new Point(70, 85), button22.getPreferredSize()));

            menuModSub2ContentPane.setPreferredSize(new Dimension(230, 145));
            menuModSub2.pack();
            menuModSub2.setLocationRelativeTo(menuModSub2.getOwner());
        }

        //======== menuMod ========
        {
            menuMod.setForeground(Color.white);
            menuMod.setTitle("MODIFY");
            menuMod.setAutoRequestFocus(false);
            var menuModContentPane = menuMod.getContentPane();
            menuModContentPane.setLayout(null);

            //---- button4 ----
            button4.setText("X");
            button4.setBackground(new Color(255, 0, 51));
            button4.setForeground(Color.white);
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button4MouseClicked(e);
                }
            });
            menuModContentPane.add(button4);
            button4.setBounds(320, 20, 25, 20);

            //---- button5 ----
            button5.setText("Tonkotsu");
            button5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button10MouseClicked(e);
                }
            });
            menuModContentPane.add(button5);
            button5.setBounds(new Rectangle(new Point(45, 75), button5.getPreferredSize()));

            //---- button6 ----
            button6.setText("Shoyu");
            button6.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button10MouseClicked(e);
                }
            });
            menuModContentPane.add(button6);
            button6.setBounds(new Rectangle(new Point(155, 75), button6.getPreferredSize()));

            //---- button7 ----
            button7.setText("Shio");
            button7.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button10MouseClicked(e);
                }
            });
            menuModContentPane.add(button7);
            button7.setBounds(new Rectangle(new Point(265, 75), button7.getPreferredSize()));

            //---- button8 ----
            button8.setText("Soft");
            button8.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button10MouseClicked(e);
                }
            });
            menuModContentPane.add(button8);
            button8.setBounds(new Rectangle(new Point(45, 120), button8.getPreferredSize()));

            //---- button9 ----
            button9.setText("Medium");
            button9.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button10MouseClicked(e);
                }
            });
            menuModContentPane.add(button9);
            button9.setBounds(new Rectangle(new Point(155, 120), button9.getPreferredSize()));

            //---- button10 ----
            button10.setText("Firm");
            button10.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button10MouseClicked(e);
                }
            });
            menuModContentPane.add(button10);
            button10.setBounds(new Rectangle(new Point(265, 120), button10.getPreferredSize()));

            //---- button11 ----
            button11.setText("Spring onion");
            button11.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button10MouseClicked(e);
                }
            });
            menuModContentPane.add(button11);
            button11.setBounds(new Rectangle(new Point(45, 165), button11.getPreferredSize()));

            //---- button12 ----
            button12.setText("Nori");
            button12.setBackground(new Color(255, 153, 0));
            button12.setForeground(Color.white);
            button12.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button15MouseClicked(e);
                }
            });
            menuModContentPane.add(button12);
            button12.setBounds(new Rectangle(new Point(160, 165), button12.getPreferredSize()));

            //---- button14 ----
            button14.setText("Chashu");
            button14.setBackground(new Color(255, 153, 0));
            button14.setForeground(Color.white);
            button14.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button15MouseClicked(e);
                }
            });
            menuModContentPane.add(button14);
            button14.setBounds(new Rectangle(new Point(265, 165), button14.getPreferredSize()));

            //---- button15 ----
            button15.setText("Boiled egg");
            button15.setBackground(new Color(255, 153, 0));
            button15.setForeground(Color.white);
            button15.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button15MouseClicked(e);
                }
            });
            menuModContentPane.add(button15);
            button15.setBounds(new Rectangle(new Point(45, 210), button15.getPreferredSize()));

            //---- button16 ----
            button16.setText("Bamboo shoot");
            button16.setBackground(new Color(255, 153, 0));
            button16.setForeground(Color.white);
            button16.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button15MouseClicked(e);
                }
            });
            menuModContentPane.add(button16);
            button16.setBounds(new Rectangle(new Point(155, 210), button16.getPreferredSize()));

            //---- textField1 ----
            textField1.setText("Only the orange one(may add-on) can change the price");
            textField1.setForeground(new Color(204, 0, 0));
            textField1.setFont(textField1.getFont().deriveFont(textField1.getFont().getStyle() | Font.ITALIC));
            textField1.setEditable(false);
            menuModContentPane.add(textField1);
            textField1.setBounds(new Rectangle(new Point(75, 250), textField1.getPreferredSize()));

            //---- button18 ----
            button18.setText("<--");
            button18.setBackground(new Color(0, 204, 51));
            button18.setForeground(Color.white);
            button18.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button18MouseClicked(e);
                }
            });
            menuModContentPane.add(button18);
            button18.setBounds(285, 20, 25, 20);

            menuModContentPane.setPreferredSize(new Dimension(390, 323));
            menuMod.pack();
            menuMod.setLocationRelativeTo(menuMod.getOwner());
        }

        //======== stateView ========
        {
            stateView.setTitle("VIEW");
            stateView.setAutoRequestFocus(false);
            var stateViewContentPane = stateView.getContentPane();
            stateViewContentPane.setLayout(null);

            //======== viewState ========
            {
                viewState.setBackground(Color.orange);
                viewState.setForeground(new Color(204, 0, 51));
                viewState.setFont(viewState.getFont().deriveFont(Font.BOLD));
                viewState.setBorder(null);
                viewState.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                viewState.setTabPlacement(SwingConstants.LEFT);

                //======== panel5 ========
                {
                    panel5.setBackground(Color.white);
                    panel5.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
                    0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
                    .BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt.Color.
                    red),panel5. getBorder()));panel5. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
                    beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}});
                    panel5.setLayout(null);

                    //---- label18 ----
                    label18.setText("Tonkotsu");
                    label18.setForeground(new Color(204, 0, 0));
                    panel5.add(label18);
                    label18.setBounds(15, 75, 105, 36);

                    //---- label19 ----
                    label19.setText("Shoyu");
                    label19.setForeground(new Color(204, 0, 0));
                    panel5.add(label19);
                    label19.setBounds(15, 130, 105, 36);

                    //---- label20 ----
                    label20.setText("Shio");
                    label20.setForeground(new Color(204, 0, 0));
                    panel5.add(label20);
                    label20.setBounds(15, 185, 105, 36);

                    //---- textField18 ----
                    textField18.setText("Tonkotsu");
                    textField18.setEditable(false);
                    panel5.add(textField18);
                    textField18.setBounds(160, 80, 105, 36);

                    //---- textField19 ----
                    textField19.setText("Shoyu");
                    textField19.setEditable(false);
                    panel5.add(textField19);
                    textField19.setBounds(160, 130, 105, 36);

                    //---- textField20 ----
                    textField20.setText("Shio");
                    textField20.setEditable(false);
                    panel5.add(textField20);
                    textField20.setBounds(160, 185, 105, 36);

                    //---- button33 ----
                    button33.setText("<--");
                    button33.setBackground(new Color(0, 204, 51));
                    button33.setForeground(Color.white);
                    button33.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            button18MouseClicked(e);
                        }
                    });
                    panel5.add(button33);
                    button33.setBounds(195, 10, 25, 20);

                    //---- button34 ----
                    button34.setText("X");
                    button34.setBackground(new Color(255, 0, 51));
                    button34.setForeground(Color.white);
                    button34.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            button4MouseClicked(e);
                        }
                    });
                    panel5.add(button34);
                    button34.setBounds(230, 10, 25, 20);

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
                viewState.addTab("Soup", panel5);

                //======== panel7 ========
                {
                    panel7.setLayout(null);

                    //======== panel9 ========
                    {
                        panel9.setBackground(Color.white);
                        panel9.setLayout(null);

                        //---- label24 ----
                        label24.setText("Soft");
                        label24.setForeground(new Color(204, 0, 0));
                        panel9.add(label24);
                        label24.setBounds(10, 85, 95, 35);

                        //---- label25 ----
                        label25.setText("Medium");
                        label25.setForeground(new Color(204, 0, 0));
                        panel9.add(label25);
                        label25.setBounds(10, 135, 95, 35);

                        //---- label26 ----
                        label26.setText("Firm");
                        label26.setForeground(new Color(204, 0, 0));
                        panel9.add(label26);
                        label26.setBounds(10, 185, 95, 35);

                        //---- textField24 ----
                        textField24.setText("Soft");
                        textField24.setEditable(false);
                        panel9.add(textField24);
                        textField24.setBounds(170, 85, 95, 35);

                        //---- textField25 ----
                        textField25.setText("Medium");
                        textField25.setEditable(false);
                        panel9.add(textField25);
                        textField25.setBounds(170, 135, 95, 35);

                        //---- textField26 ----
                        textField26.setText("Firm");
                        textField26.setEditable(false);
                        panel9.add(textField26);
                        textField26.setBounds(170, 185, 95, 35);

                        //---- button31 ----
                        button31.setText("<--");
                        button31.setBackground(new Color(0, 204, 51));
                        button31.setForeground(Color.white);
                        button31.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button18MouseClicked(e);
                            }
                        });
                        panel9.add(button31);
                        button31.setBounds(195, 10, 25, 20);

                        //---- button32 ----
                        button32.setText("X");
                        button32.setBackground(new Color(255, 0, 51));
                        button32.setForeground(Color.white);
                        button32.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button4MouseClicked(e);
                            }
                        });
                        panel9.add(button32);
                        button32.setBounds(230, 10, 25, 20);

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
                    panel7.add(panel9);
                    panel9.setBounds(0, 0, 280, 290);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel7.getComponentCount(); i++) {
                            Rectangle bounds = panel7.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel7.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel7.setMinimumSize(preferredSize);
                        panel7.setPreferredSize(preferredSize);
                    }
                }
                viewState.addTab("Noodles", panel7);

                //======== panel6 ========
                {
                    panel6.setLayout(null);

                    //======== panel10 ========
                    {
                        panel10.setBackground(Color.white);
                        panel10.setLayout(null);

                        //---- label30 ----
                        label30.setText("No please");
                        label30.setForeground(new Color(204, 0, 0));
                        panel10.add(label30);
                        label30.setBounds(25, 90, 105, 35);

                        //---- label31 ----
                        label31.setText("Just a little");
                        label31.setForeground(new Color(204, 0, 0));
                        panel10.add(label31);
                        label31.setBounds(25, 140, 105, 35);

                        //---- label32 ----
                        label32.setText("A lot!");
                        label32.setForeground(new Color(204, 0, 0));
                        panel10.add(label32);
                        label32.setBounds(25, 195, 105, 35);

                        //---- textField30 ----
                        textField30.setText("No please");
                        textField30.setEditable(false);
                        panel10.add(textField30);
                        textField30.setBounds(160, 90, 105, 35);

                        //---- textField31 ----
                        textField31.setText("Just a little");
                        textField31.setEditable(false);
                        panel10.add(textField31);
                        textField31.setBounds(160, 140, 105, 35);

                        //---- textField32 ----
                        textField32.setText("A lot!");
                        textField32.setEditable(false);
                        panel10.add(textField32);
                        textField32.setBounds(160, 195, 105, 35);

                        //---- button29 ----
                        button29.setText("<--");
                        button29.setBackground(new Color(0, 204, 51));
                        button29.setForeground(Color.white);
                        button29.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button18MouseClicked(e);
                            }
                        });
                        panel10.add(button29);
                        button29.setBounds(195, 10, 25, 20);

                        //---- button30 ----
                        button30.setText("X");
                        button30.setBackground(new Color(255, 0, 51));
                        button30.setForeground(Color.white);
                        button30.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button4MouseClicked(e);
                            }
                        });
                        panel10.add(button30);
                        button30.setBounds(230, 10, 25, 20);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel10.getComponentCount(); i++) {
                                Rectangle bounds = panel10.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel10.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel10.setMinimumSize(preferredSize);
                            panel10.setPreferredSize(preferredSize);
                        }
                    }
                    panel6.add(panel10);
                    panel10.setBounds(0, 0, 280, 290);

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
                viewState.addTab("Spring onion", panel6);

                //======== panel8 ========
                {
                    panel8.setLayout(null);

                    //======== panel11 ========
                    {
                        panel11.setBackground(Color.white);
                        panel11.setLayout(null);

                        //---- label36 ----
                        label36.setText("0(No)");
                        label36.setForeground(new Color(204, 0, 0));
                        panel11.add(label36);
                        label36.setBounds(15, 55, 95, 25);

                        //---- label37 ----
                        label37.setText("1");
                        label37.setForeground(new Color(204, 0, 0));
                        panel11.add(label37);
                        label37.setBounds(15, 90, 95, 25);

                        //---- label38 ----
                        label38.setText("2");
                        label38.setForeground(new Color(204, 0, 0));
                        panel11.add(label38);
                        label38.setBounds(15, 125, 95, 25);

                        //---- textField36 ----
                        textField36.setText("0(No)");
                        textField36.setEditable(false);
                        panel11.add(textField36);
                        textField36.setBounds(165, 55, 95, 25);

                        //---- textField37 ----
                        textField37.setText("1");
                        textField37.setEditable(false);
                        panel11.add(textField37);
                        textField37.setBounds(165, 90, 95, 25);

                        //---- textField38 ----
                        textField38.setText("2");
                        textField38.setEditable(false);
                        panel11.add(textField38);
                        textField38.setBounds(165, 125, 95, 25);

                        //---- label41 ----
                        label41.setText("3");
                        label41.setForeground(new Color(204, 0, 0));
                        panel11.add(label41);
                        label41.setBounds(15, 160, 95, 25);

                        //---- label42 ----
                        label42.setText("4");
                        label42.setForeground(new Color(204, 0, 0));
                        panel11.add(label42);
                        label42.setBounds(15, 195, 95, 25);

                        //---- textField41 ----
                        textField41.setText("3");
                        textField41.setEditable(false);
                        panel11.add(textField41);
                        textField41.setBounds(165, 160, 95, 25);

                        //---- textField42 ----
                        textField42.setText("4");
                        textField42.setEditable(false);
                        panel11.add(textField42);
                        textField42.setBounds(165, 195, 95, 25);

                        //---- label44 ----
                        label44.setText("5(Max)");
                        label44.setForeground(new Color(204, 0, 0));
                        panel11.add(label44);
                        label44.setBounds(15, 230, 95, 25);

                        //---- textField44 ----
                        textField44.setText("5(Max)");
                        textField44.setEditable(false);
                        panel11.add(textField44);
                        textField44.setBounds(165, 230, 95, 25);

                        //---- button27 ----
                        button27.setText("<--");
                        button27.setBackground(new Color(0, 204, 51));
                        button27.setForeground(Color.white);
                        button27.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button18MouseClicked(e);
                            }
                        });
                        panel11.add(button27);
                        button27.setBounds(195, 10, 25, 20);

                        //---- button28 ----
                        button28.setText("X");
                        button28.setBackground(new Color(255, 0, 51));
                        button28.setForeground(Color.white);
                        button28.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button4MouseClicked(e);
                            }
                        });
                        panel11.add(button28);
                        button28.setBounds(230, 10, 25, 20);

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
                    panel8.add(panel11);
                    panel11.setBounds(0, 0, 280, 290);

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
                viewState.addTab("Spiciness", panel8);

                //======== panel1 ========
                {
                    panel1.setLayout(null);

                    //======== panel12 ========
                    {
                        panel12.setBackground(Color.white);
                        panel12.setLayout(null);

                        //---- label48 ----
                        label48.setText("Nori");
                        label48.setForeground(new Color(204, 0, 0));
                        panel12.add(label48);
                        label48.setBounds(15, 85, 95, 36);

                        //---- label49 ----
                        label49.setText("Chashu");
                        label49.setForeground(new Color(204, 0, 0));
                        panel12.add(label49);
                        label49.setBounds(15, 135, 95, 36);

                        //---- label50 ----
                        label50.setText("Boiled egg");
                        label50.setForeground(new Color(204, 0, 0));
                        panel12.add(label50);
                        label50.setBounds(15, 185, 95, 36);

                        //---- textField48 ----
                        textField48.setText("Nori");
                        textField48.setEditable(false);
                        panel12.add(textField48);
                        textField48.setBounds(170, 85, 95, 36);

                        //---- textField49 ----
                        textField49.setText("Chashu");
                        textField49.setEditable(false);
                        panel12.add(textField49);
                        textField49.setBounds(170, 135, 95, 36);

                        //---- textField50 ----
                        textField50.setText("Boiled egg");
                        textField50.setEditable(false);
                        panel12.add(textField50);
                        textField50.setBounds(170, 185, 95, 36);

                        //---- button25 ----
                        button25.setText("<--");
                        button25.setBackground(new Color(0, 204, 51));
                        button25.setForeground(Color.white);
                        button25.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button18MouseClicked(e);
                            }
                        });
                        panel12.add(button25);
                        button25.setBounds(195, 10, 25, 20);

                        //---- button26 ----
                        button26.setText("X");
                        button26.setBackground(new Color(255, 0, 51));
                        button26.setForeground(Color.white);
                        button26.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button4MouseClicked(e);
                            }
                        });
                        panel12.add(button26);
                        button26.setBounds(230, 10, 25, 20);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel12.getComponentCount(); i++) {
                                Rectangle bounds = panel12.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel12.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel12.setMinimumSize(preferredSize);
                            panel12.setPreferredSize(preferredSize);
                        }
                    }
                    panel1.add(panel12);
                    panel12.setBounds(0, 0, 280, 290);

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
                viewState.addTab("Fix ingredient", panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(null);

                    //======== panel13 ========
                    {
                        panel13.setBackground(Color.white);
                        panel13.setLayout(null);

                        //---- label54 ----
                        label54.setText("Extra Nori");
                        label54.setForeground(new Color(204, 0, 0));
                        panel13.add(label54);
                        label54.setBounds(10, 60, 105, 35);

                        //---- label55 ----
                        label55.setText("Extra boiled egg");
                        label55.setForeground(new Color(204, 0, 0));
                        panel13.add(label55);
                        label55.setBounds(10, 110, 105, 35);

                        //---- label56 ----
                        label56.setText("Bamboo shoots");
                        label56.setForeground(new Color(204, 0, 0));
                        panel13.add(label56);
                        label56.setBounds(10, 160, 105, 35);

                        //---- textField54 ----
                        textField54.setText("Extra Nori");
                        textField54.setEditable(false);
                        panel13.add(textField54);
                        textField54.setBounds(160, 60, 105, 35);

                        //---- textField55 ----
                        textField55.setText("Extra boiled egg");
                        textField55.setEditable(false);
                        panel13.add(textField55);
                        textField55.setBounds(160, 110, 105, 35);

                        //---- textField56 ----
                        textField56.setText("Bamboo shoots");
                        textField56.setEditable(false);
                        panel13.add(textField56);
                        textField56.setBounds(160, 160, 105, 35);

                        //---- textField57 ----
                        textField57.setText("Extra Chashu");
                        textField57.setEditable(false);
                        panel13.add(textField57);
                        textField57.setBounds(160, 210, 105, 35);

                        //---- label57 ----
                        label57.setText("Extra Chashu");
                        label57.setForeground(new Color(204, 0, 0));
                        panel13.add(label57);
                        label57.setBounds(10, 210, 105, 35);

                        //---- button23 ----
                        button23.setText("<--");
                        button23.setBackground(new Color(0, 204, 51));
                        button23.setForeground(Color.white);
                        button23.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button18MouseClicked(e);
                            }
                        });
                        panel13.add(button23);
                        button23.setBounds(195, 10, 25, 20);

                        //---- button24 ----
                        button24.setText("X");
                        button24.setBackground(new Color(255, 0, 51));
                        button24.setForeground(Color.white);
                        button24.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button4MouseClicked(e);
                            }
                        });
                        panel13.add(button24);
                        button24.setBounds(230, 10, 25, 20);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel13.getComponentCount(); i++) {
                                Rectangle bounds = panel13.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel13.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel13.setMinimumSize(preferredSize);
                            panel13.setPreferredSize(preferredSize);
                        }
                    }
                    panel2.add(panel13);
                    panel13.setBounds(0, 0, 280, 290);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel2.getComponentCount(); i++) {
                            Rectangle bounds = panel2.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel2.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel2.setMinimumSize(preferredSize);
                        panel2.setPreferredSize(preferredSize);
                    }
                }
                viewState.addTab("Add-on", panel2);
            }
            stateViewContentPane.add(viewState);
            viewState.setBounds(0, 0, 380, 290);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < stateViewContentPane.getComponentCount(); i++) {
                    Rectangle bounds = stateViewContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = stateViewContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                stateViewContentPane.setMinimumSize(preferredSize);
                stateViewContentPane.setPreferredSize(preferredSize);
            }
            stateView.pack();
            stateView.setLocationRelativeTo(stateView.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Xinyi Luo
    public JFrame getManaLogin() {
        return manaLogin;
    }

    public void setManaLogin(JFrame manaLogin) {
        this.manaLogin = manaLogin;
    }
    private String itemName;
    private Boolean radio;
    private  Boolean box;
    private int index;
    private JFrame manaLogin;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JLabel label4;
    private JLabel label3;
    private JButton button13;
    private JLabel title1;
    private JLabel label1;
    private JFrame manaSumFunc;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JCheckBox checkBox1;
    private JFrame menuModSub1;
    private JLabel label2;
    private JButton button17;
    private JRadioButton radioButton1;
    private JButton button19;
    private JTextField textField4;
    private JFrame menuModSub2;
    private JButton button21;
    private JRadioButton radioButton2;
    private JButton button22;
    private JFrame menuMod;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JTextField textField1;
    private JButton button18;
    private JFrame stateView;
    private JTabbedPane viewState;
    private JPanel panel5;
    private JLabel label18;
    private JLabel label19;
    private JLabel label20;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField textField20;
    private JButton button33;
    private JButton button34;
    private JPanel panel7;
    private JPanel panel9;
    private JLabel label24;
    private JLabel label25;
    private JLabel label26;
    private JTextField textField24;
    private JTextField textField25;
    private JTextField textField26;
    private JButton button31;
    private JButton button32;
    private JPanel panel6;
    private JPanel panel10;
    private JLabel label30;
    private JLabel label31;
    private JLabel label32;
    private JTextField textField30;
    private JTextField textField31;
    private JTextField textField32;
    private JButton button29;
    private JButton button30;
    private JPanel panel8;
    private JPanel panel11;
    private JLabel label36;
    private JLabel label37;
    private JLabel label38;
    private JTextField textField36;
    private JTextField textField37;
    private JTextField textField38;
    private JLabel label41;
    private JLabel label42;
    private JTextField textField41;
    private JTextField textField42;
    private JLabel label44;
    private JTextField textField44;
    private JButton button27;
    private JButton button28;
    private JPanel panel1;
    private JPanel panel12;
    private JLabel label48;
    private JLabel label49;
    private JLabel label50;
    private JTextField textField48;
    private JTextField textField49;
    private JTextField textField50;
    private JButton button25;
    private JButton button26;
    private JPanel panel2;
    private JPanel panel13;
    private JLabel label54;
    private JLabel label55;
    private JLabel label56;
    private JTextField textField54;
    private JTextField textField55;
    private JTextField textField56;
    private JTextField textField57;
    private JLabel label57;
    private JButton button23;
    private JButton button24;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
