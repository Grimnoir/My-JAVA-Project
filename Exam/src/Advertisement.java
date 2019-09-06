import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

public class Advertisement extends JFrame implements ActionListener  {
	JLabel l1, l2, l3, l4, l5, l6, l7,l9,l8,l10,l11,l12;
	JPanel panelFields, panelButtons, panelMain;
	JTextField txtslno,txtagno,txtpbdate,txtrepeat;
	JTextArea txanet,txacom,txacost;
	JButton btnNew, btnSave, btnDelete,btnView, btnEdit, btnUpdate, btnFirst, btnNext,
	btnFind, btnLast,btnFinish;
	JPanel btnPaneldim,btnPanelloc;
	JRadioButton dim_buttons[],loc_buttons[];
	ButtonGroup bdim,bloc;
	String[] dim={"5x6","7x8","Half Page","Full Page"};
	String[] loc={"Front","Middle","Back"};
	Container c;
	Connection cn = null;
	Statement sm = null;
	Statement stn1 = null;
	Statement stn2 = null;
	ResultSet rs = null;
	ResultSet res = null;
	PreparedStatement ps = null;
	JComboBox medianame,media;
	Vector<Integer> agentnumber = new Vector<Integer>();
	Vector<String> branch = new Vector<String>();
	Vector<String> type = new Vector<String>();
	String rad,len,ano;
	int x, y, z, temp;
	
	public Advertisement() {
		super("Advertisement Data Entry");
		InterfaceDesign();
		initializeDatabase();
		setSize(900,700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void dispose() {
		super.dispose();
		try {
			rs.close();
			sm.close();
			cn.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void InterfaceDesign()
	{
		
		panelMain = new JPanel();
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		panelMain = new JPanel();
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
		panelFields = new JPanel(new GridLayout(12, 20, 10, 10));
		panelButtons = new JPanel(new GridLayout(2, 4, 10, 10));
		
		l1 = new JLabel("Sl.No", JLabel.RIGHT);
		l2 = new JLabel("Agent No", JLabel.RIGHT);
		l3 = new JLabel("Media", JLabel.RIGHT);
		l4 = new JLabel("Media Name", JLabel.RIGHT);
		l5 = new JLabel("Advt Dimension", JLabel.RIGHT);
		l6 = new JLabel("Location", JLabel.RIGHT);
		l7 = new JLabel("Publish Date ", JLabel.RIGHT);
		l8 = new JLabel("Repeat", JLabel.RIGHT);
		l10= new JLabel("Cost", JLabel.RIGHT);
		l11 = new JLabel("Commission", JLabel.RIGHT);
		l12 = new JLabel("Net Amt", JLabel.RIGHT);

		
		l9=new JLabel("",JLabel.RIGHT);
		
		txtslno = new JTextField("1001");
		txtagno = new JTextField(10);
		
		txtpbdate = new JTextField(10);
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dt = sdf.format(now);
		txtpbdate.setText(dt);
		
		txtrepeat = new JTextField(10);
		txtrepeat.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE
						|| c == KeyEvent.VK_DELETE) {
					ke.consume();
				}

			}

		});
		txanet = new JTextArea(10, 20);
		txacom = new JTextArea(10, 20);
		txacost = new JTextArea(10, 20);
		
		

		media= new JComboBox(type);
		type.add("Newspaper Name");
		type.add("Magazine Name");
		
		
		medianame = new JComboBox(branch);
		branch.add("Decan Herald");
		branch.add("Times Of India");
		branch.add("The Hindu");
		branch.add("Udayavani");
		
		x=dim.length;
		dim_buttons=new JRadioButton[x];
		bdim=new ButtonGroup();
		for(int i=0;i<x;i++)
		{
			dim_buttons[i]=new JRadioButton(dim[i]);
			bdim.add(dim_buttons[i]);	
			dim_buttons[i].addActionListener(this);
		}
		
		y=loc.length;
		loc_buttons=new JRadioButton[y];
		bloc=new ButtonGroup();
		for(int i=0;i<y;i++)
		{
			loc_buttons[i]=new JRadioButton(loc[i]);
			bloc.add(loc_buttons[i]);	
			loc_buttons[i].addActionListener(this);
		}
		
		btnNew = new JButton("Add New");
		btnSave = new JButton("Save");
		btnDelete = new JButton("Delete");
		btnView = new JButton("View Rec");
		btnEdit = new JButton("Edit Rec");
		btnUpdate = new JButton("Update");
		btnFind = new JButton("Find Rec");
		btnFirst = new JButton("First");
		btnLast = new JButton("Last");
		btnNext = new JButton("Next");
		btnFinish=new JButton("Finish");
		
		btnPaneldim=new JPanel();
		btnPaneldim.setLayout(new FlowLayout());
		for(int i=0;i<x;i++)
		{
			btnPaneldim.add(dim_buttons[i]);
		}
		
		btnPanelloc=new JPanel();
		btnPanelloc.setLayout(new FlowLayout());
		for(int i=0;i<y;i++)
		{
			btnPanelloc.add(loc_buttons[i]);
		}
		
		panelFields.add(l1);
		panelFields.add(txtslno );
		panelFields.add(l2);
		panelFields.add(txtagno);
		panelFields.add(l3);
		panelFields.add(media);
		panelFields.add(l4);
		panelFields.add(medianame);
		panelFields.add(l5);
		panelFields.add(btnPaneldim);
		panelFields.add(l6);
		panelFields.add(btnPanelloc);
		panelFields.add(l7);
		panelFields.add(txtpbdate);
		panelFields.add(btnFinish);
		panelFields.add(l8);
		panelFields.add(txtrepeat);
		
		panelFields.add(l9);
		panelFields.add(btnFinish);
	
		panelFields.add(l10);
		panelFields.add(txacost);
		panelFields.add(l11);
		panelFields.add(txacom);
		panelFields.add(l12);
		panelFields.add(txanet);
		txtslno.setEditable(false);
	    txtagno.setEditable(false);
		txacom.setEditable(false);
		txanet.setEditable(false);
		txacost.setEditable(false);
		
		
		
		panelFields.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
	
		panelButtons.add(btnNew);
		panelButtons.add(btnSave);
		panelButtons.add(btnDelete);
		panelButtons.add(btnView);
		panelButtons.add(btnEdit);
		panelButtons.add(btnUpdate);
		panelButtons.add(btnFind);
		panelButtons.add(btnFirst);
		panelButtons.add(btnLast);
		panelButtons.add(btnNext);
		
		
		panelButtons.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		
		panelMain.add(panelFields);
		panelMain.add(panelButtons);
		c.add(panelMain, "Center");
		c.add(new JLabel(""), "South");
		c.add(new JLabel("Created by Sherwin"), "North");
		c.add(new JLabel(""), "East");
		c.add(new JLabel(""), "West");
		
		btnNew.addActionListener(this);
		btnSave.addActionListener(this);
		btnDelete.addActionListener(this);
		btnView.addActionListener(this);
		btnEdit.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnFind.addActionListener(this);
		btnFirst.addActionListener(this);
		btnLast.addActionListener(this);
		btnNext.addActionListener(this);
		
	}
	
	private void initializeDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String cs = "jdbc:mysql://localhost:3306/sherwin";
			cn = DriverManager.getConnection(cs, "Sherwin", "admin");
			sm = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = sm.executeQuery("Select * from advertisementmastertable");
			firstclick();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	private void firstclick() {
		try {
			if (rs.first()) {
				fillFields();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
		
	private void fillFields() {
		try {
			txtagno.setText(rs.getString("AgntNo"));
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public void actionPerformed1(ActionEvent g) {
		for(int i=0;i<x;i++)
				{
			if(dim_buttons[i].isSelected())
				rad=dim_buttons[i].getLabel();
			
				}
	}
	public void actionPerformed2(ActionEvent f) {
		for(int i=0;i<y;i++)
				{
			if(loc_buttons[i].isSelected())
				len=loc_buttons[i].getLabel();
			
				}
	}
	
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equalsIgnoreCase("Add New")) {
			btnNew.setEnabled(false);
			String q = txtslno.getText();
		    int Slnumber = Integer.valueOf(q);
		    q = Integer.toString(++Slnumber);
		    txtslno.setText(q);
		   
			resetFields();
		}
		if (s.equalsIgnoreCase("Save")) {
			saveClick();
		}
		if (s.equalsIgnoreCase("Delete")) {
			deleteClick();
		}
		if (s.equalsIgnoreCase("View Rec")) {

			viewClick();
		}
		if (s.equalsIgnoreCase("Edit Rec")) {
			editClick();
		}
		if (s.equalsIgnoreCase("Update")) {
			updateClick(ano);
		}
		if (s.equalsIgnoreCase("Find Rec")) {
			FindClick();
		}
		if (s.equalsIgnoreCase("First")) {
			firstClick();
		}
		if (s.equalsIgnoreCase("Next")) {
			nextClick();
		}
		if (s.equalsIgnoreCase("Last")) {
			lastClick();
		}
		
		
		
		
	}
	private void updateClick(String ano) {
		// TODO Auto-generated method stub
		
	}

	private void FindClick() {
		// TODO Auto-generated method stub
		
	}

	private void viewClick() {
		// TODO Auto-generated method stub
		
	}

	private void editClick() {
		// TODO Auto-generated method stub
		
	}



	private void lastClick() {
		try {
			if (rs.last()) {
				fillFields();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private void nextClick() {
		try {
			if (rs.next()) {
				fillFields();
			}
			if (rs.isAfterLast()) {
				firstClick();
				return;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		
	}

	private void firstClick() {
		try {
			if (rs.first()) {
				fillFields();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		
	}

	private void deleteClick() {
		// TODO Auto-generated method stub
		
	}

	private void saveClick() {
		// TODO Auto-generated method stub
		
	}

	private void resetFields() {
		
		txtagno.setText("");
		txtpbdate.setText("");
		txtrepeat.setText("");
		txacost.setText("");
		txacom.setText("");
		txanet.setText("");
		bdim.clearSelection();
		bloc.clearSelection();
		medianame.setSelectedItem(null);
		media.setSelectedItem(null);
	
		
	}

	public static void main(String[] args) 
	{
		new Advertisement();

	}
}
