package refactored;

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
import entities.CustomerAccount;

public abstract class MyStats {
	public static final List<Customer> customerList = new ArrayList<Customer>();
	//Frames
	public static JFrame f = null;
	public static JFrame f1 = null;
	public static JFrame fAdmin = null;
	public static JFrame fCustomer = null;
	public static Container content = null;
	public static Container cAdmin = null;
	
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
	//--------CustomerMenu-------------
	public static final JButton returnButtonC = new JButton("Return");
	public static final JButton continueButtonC = new JButton("Continue");
	public static final JButton statementButtonC = new JButton("Display Bank Statement");
	public static final JButton lodgementButtonC = new JButton("Lodge money into account");
	public static final JButton withdrawButtonC = new JButton("Withdraw money from account");
	public static final JButton exitButtonC = new JButton("Exit Customer Menu");
	//--------Admin Menu---------------
	public static final JButton continueButtonA = new JButton("Apply Interest");
	public static final JButton returnButtonA = new JButton("Return");
	public static final JButton exitButtonA = new JButton("Exit Admin Menu");
	
	//Labels
	//------Customer-----------------
	public static final JLabel firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
	public static final JLabel surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
	public static final JLabel pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
	public static final JLabel dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
	//-----Admin---------------------
	public static final JLabel firstNameLabelA = new JLabel("First Name:", SwingConstants.LEFT);
	public static final JLabel surnameLabelA = new JLabel("Surname:", SwingConstants.LEFT);
	public static final JLabel pPPSLabelA = new JLabel("PPS Number:", SwingConstants.LEFT);
	public static final JLabel dOBLabelA = new JLabel("Date of birth", SwingConstants.LEFT);
	public static final JLabel customerIDLabelA = new JLabel("CustomerID:", SwingConstants.LEFT);
	public static final JLabel passwordLabelA = new JLabel("Password:", SwingConstants.LEFT);
	
	//TextFields
	public static final JTextField firstNameTextField = new JTextField(20);
	public static final JTextField surnameTextField = new JTextField(20);
	public static final JTextField pPSTextField = new JTextField(20);
	public static final JTextField dOBTextField = new JTextField(20);
	//-------Admin-------------------
	public static final JTextField firstNameTextFieldA = new JTextField(20);
	public static final JTextField surnameTextFieldA = new JTextField(20);
	public static final JTextField pPSTextFieldA = new JTextField(20);
	public static final JTextField dOBTextFieldA = new JTextField(20);
	public static final JTextField customerIDTextFieldA = new JTextField(20);
	public static final JTextField passwordTextFieldA = new JTextField(20);
	
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
	public static String adminUsername = "admin";
	public static String adminPassword = "pass";
	
	//Booleans
	public static boolean loop = true;
	public static boolean loop2 = true;
	public static boolean found = false;
	public static boolean cont = false;
	public static boolean on = false;
	
	//Doubles
	public static double balanceC = 0.0;
	public static double withdrawC = 0.0;
	
	//Integers
	public static int position = 0;
	
	//Objects
	public static Customer customer;
	public static CustomerAccount acc;
}
