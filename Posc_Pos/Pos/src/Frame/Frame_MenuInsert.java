package Frame;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Oracle.Oracle_DAO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Frame_MenuInsert extends JFrame {

	private JPanel contentPane;
	public JTextField txt_menuname, txt_price;
	public JButton btn_insert, btn_cancel; 
	public JLabel label_insertmenu, label_menunum, label_menuname, label_price, label_won;
	public static JComboBox combo_menunum;
	public static boolean arr_emptynum[];
	Oracle_DAO oracle = new Oracle_DAO();
	
	public Frame_MenuInsert() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(420, 301);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(53,135,145), 1, true));
		setLocationRelativeTo(null);
		setUndecorated(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_insertmenu = new JLabel("¸Þ´º Ãß°¡");
		label_insertmenu.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		label_insertmenu.setBounds(14, 8, 104, 39);
		contentPane.add(label_insertmenu);
		
		combo_menunum = new JComboBox();
		combo_menunum.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 15));
		combo_menunum.setBounds(181, 61, 60, 24);
		arr_emptynum = new boolean[31];
		oracle.OracleGetMenuNum1();
		for(int i = 1; i <= 30; i++) {
			if(arr_emptynum[i]!=true) {
				combo_menunum.addItem(i);
			}
		}
		contentPane.add(combo_menunum);
		
		label_menunum = new JLabel("¸Þ´º ¹øÈ£ : ");
		label_menunum.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 15));
		label_menunum.setBounds(85, 64, 87, 18);
		contentPane.add(label_menunum);
		
		label_menuname = new JLabel("¸Þ´º ÀÌ¸§ : ");
		label_menuname.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 15));
		label_menuname.setBounds(85, 121, 87, 18);
		contentPane.add(label_menuname);
		
		label_price = new JLabel("°¡°Ý : ");
		label_price.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 15));
		label_price.setBounds(118, 177, 49, 18);
		contentPane.add(label_price);
		
		txt_menuname = new JTextField();
		txt_menuname.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 15));
		txt_menuname.setBounds(181, 119, 162, 24);
		contentPane.add(txt_menuname);
		txt_menuname.setColumns(10);
		
		txt_price = new JTextField();
		txt_price.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 15));
		txt_price.setBounds(181, 175, 129, 24);
		contentPane.add(txt_price);
		txt_price.setColumns(10);
		
		label_won = new JLabel("¿ø");
		label_won.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 15));
		label_won.setBounds(315, 177, 28, 18);
		contentPane.add(label_won);
		
		btn_insert = new JButton("Ãß°¡");
		btn_insert.setForeground(Color.WHITE);
		btn_insert.setBackground(new Color(53,135,145));
		btn_insert.setFocusable(false);
		btn_insert.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 16));
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleInsertMenu(Integer.parseInt(combo_menunum.getSelectedItem().toString()), txt_menuname.getText(), Integer.parseInt(txt_price.getText()));
				dispose();
				Frame_MenuManagement.tablemodel.setNumRows(0);
				oracle.OracleGetMenu();
				oracle.OracleSetMenuBtn();	
			}
		});
		btn_insert.setBounds(73, 247, 105, 35);
		contentPane.add(btn_insert);
		
		btn_cancel = new JButton("Ãë¼Ò");
		btn_cancel.setForeground(Color.WHITE);
		btn_cancel.setFocusable(false);
		btn_cancel.setBackground(new Color(53,135,145));
		btn_cancel.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 16));
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btn_cancel.setBounds(248, 247, 105, 35);
		contentPane.add(btn_cancel);
		
		setVisible(true);
	}
}
