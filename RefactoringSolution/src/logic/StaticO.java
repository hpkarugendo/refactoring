package logic;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entities.Customer;

public abstract class StaticO {
	public static final List<Customer> customerList = new ArrayList<Customer>();
	//Frames
	public static JFrame f = null;
	public static JFrame f1 = null;
	public static Container content = null;
	
	//Buttons
	public static final JButton deleteAccount = new JButton("Delete Account");
	public static final JButton deleteCustomer = new JButton("Delete Customer");
	public static final JButton bankChargesButton = new JButton("Apply Bank Charges");
	public static final JButton interestButton = new JButton("Apply Interest");
	public static final JButton editCustomerButton = new JButton("Edit existing Customer");
	public static final ButtonGroup userType = new ButtonGroup();
	public static final JButton returnButton = new JButton("Exit Admin Menu");
	public static final JButton accountButton = new JButton("Add an Account to a Customer");
	public static final JButton summaryButton = new JButton("Display Summary Of All Accounts");
	public static final JButton navigateButton = new JButton("Navigate Customer Collection");
	public static final JButton add = new JButton("Add");
	public static final JButton cancel = new JButton("Cancel");
	
	//Labels
	public static final JLabel firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
	public static final JLabel surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
	public static final JLabel pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
	public static final JLabel dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
	
	//TextFields
	public static final JTextField firstNameTextField = new JTextField(20);
	public static final JTextField surnameTextField = new JTextField(20);
	public static final JTextField pPSTextField = new JTextField(20);
	public static final JTextField dOBTextField = new JTextField(20);
	
	//Panels
	public static final JPanel panel2 = new JPanel();
	
	//Strings
	public static String PPS = "";
	public static String firstName = "";
	public static String surname = "";
	public static String DOB = "";
	public static String CustomerID = "";
	public static String password = "";
	public static String id = "";
}
