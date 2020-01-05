package Frame;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Oracle.Oracle_DAO;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame_Order extends JFrame {

	private JPanel panel_title, panel_menu, panel_order;
	public JLabel lblPosc, label_storename, label_frameInfo;
	public static JButton btn_menu1, btn_menu2, btn_menu3, btn_menu4, btn_menu5, btn_menu6, btn_menu7, btn_menu8, btn_menu9, btn_menu10;
	public static JButton btn_menu11, btn_menu12, btn_menu13, btn_menu14, btn_menu15, btn_menu16, btn_menu17, btn_menu18, btn_menu19, btn_menu20;
	public static JButton btn_menu21, btn_menu22, btn_menu23, btn_menu24, btn_menu25, btn_menu26, btn_menu27, btn_menu28, btn_menu29, btn_menu30;
	public static JTable orderTable;
	public static DefaultTableModel tablemodel;
	public DefaultTableCellRenderer tablesort;
	private String attribute[] = {"¸Þ´º¹øÈ£","¸Þ´º","´Ü°¡","¼ö·®","°¡°Ý"};
	private Object tuple[][] = {};
	private JLabel label_sum;
	public static JLabel label_pay;
	private JButton btn_removeAll;
	private JButton btn_remove;
	private JButton btn_complete;
	private JButton btn_cancel;
	private JButton btn_card;
	private JButton btn_cash;
	private JButton btn_receipt;
	private JButton btn_management;
	private JButton btn_exit;
	Oracle_DAO oracle = new Oracle_DAO();
	
	public Frame_Order() {
		getContentPane().setBackground(Color.WHITE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setSize(1920, 1100);
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/WindowIcon.png")));
		getContentPane().setLayout(null);
		
		setTitle();
		setMenu();
		setOrderTable();
		oracle.OracleSetMenuBtn();			
		
		setVisible(true);
	}
	private void setOrderTable() {
		tablemodel = new DefaultTableModel(tuple, attribute) {
			public boolean isCellEditable(int tuple, int attribute) {
				return false;
			}
		};

		orderTable = new JTable(tablemodel);
		orderTable.setBorder(new LineBorder(null, 0, true));
//		orderTable.addMouseListener(this);
		orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderTable.setRowHeight(60);
		orderTable.getTableHeader().setBackground(new Color(53,135,145));
		orderTable.getTableHeader().setReorderingAllowed(false);
		orderTable.getTableHeader().setResizingAllowed(false);
		orderTable.getTableHeader().setForeground(Color.WHITE);
		orderTable.getTableHeader().setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		orderTable.setFocusable(false);
		orderTable.setRowSelectionAllowed(true);
		orderTable.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 16));
		orderTable.setSelectionBackground(new Color(255,255,180));
		
		orderTable.getColumnModel().getColumn(0).setMaxWidth(0);
		orderTable.getColumnModel().getColumn(0).setMinWidth(0);
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		orderTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		orderTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		orderTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		
		tablesort = new DefaultTableCellRenderer();
		tablesort.setHorizontalAlignment(tablesort.CENTER);
		
		orderTable.getColumnModel().getColumn(1).setCellRenderer(tablesort);
		orderTable.getColumnModel().getColumn(2).setCellRenderer(tablesort);
		orderTable.getColumnModel().getColumn(3).setCellRenderer(tablesort);
		orderTable.getColumnModel().getColumn(4).setCellRenderer(tablesort);

		JScrollPane sc = new JScrollPane(orderTable);
		sc.setBounds(0, 0, 629, 609);
		sc.getViewport().setBackground(Color.WHITE);
		sc.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panel_order.add(sc);
		
		label_sum = new JLabel("ÇÕ °è");
		label_sum.setBorder(new LineBorder(new Color(53,135,145)));
		label_sum.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		label_sum.setHorizontalAlignment(SwingConstants.CENTER);
		label_sum.setBounds(0, 696, 311, 71);
		panel_order.add(label_sum);
		
		label_pay = new JLabel("0 ¿ø ");
		label_pay.setBorder(new LineBorder(new Color(53,135,145)));
		label_pay.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		label_pay.setHorizontalAlignment(SwingConstants.RIGHT);
		label_pay.setBounds(318, 696, 311, 71);
		panel_order.add(label_pay);
		
		btn_removeAll = new JButton("ÀüÃ¼ »èÁ¦");
		btn_removeAll.setForeground(Color.WHITE);
		btn_removeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablemodel.setNumRows(0);
				calc_pay();
			}
		});
		btn_removeAll.setBackground(new Color(53,135,145));
		btn_removeAll.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		btn_removeAll.setBounds(0, 614, 311, 71);
		panel_order.add(btn_removeAll);
		
		btn_remove = new JButton("¼±ÅÃ »èÁ¦");
		btn_remove.setForeground(Color.WHITE);
		btn_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablemodel.removeRow(orderTable.getSelectedRow());
					calc_pay();
				} catch (Exception e2) {
					return;
				}
			}
		});
		btn_remove.setBackground(new Color(53,135,145));
		btn_remove.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		btn_remove.setBounds(318, 614, 311, 71);
		panel_order.add(btn_remove);
		
		btn_complete = new JButton("ÁÖ¹® ¿Ï·á");
		btn_complete.setForeground(Color.WHITE);
		btn_complete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderDeleteAll(Frame_Main.num_table);
				for(int i = 0; i < Frame_Order.tablemodel.getRowCount(); i++) {
					oracle.OracleOrderInsert(Frame_Main.num_table, Integer.parseInt(orderTable.getValueAt(i, 0).toString()), orderTable.getValueAt(i, 1).toString(), Integer.parseInt(orderTable.getValueAt(i, 2).toString()),Integer.parseInt(orderTable.getValueAt(i, 3).toString()),Integer.parseInt(orderTable.getValueAt(i, 2).toString())*Integer.parseInt(orderTable.getValueAt(i, 3).toString()));
				}
				new Frame_Main();
				dispose();
			}
		});
		btn_complete.setBackground(new Color(53,135,145));
		btn_complete.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		btn_complete.setBounds(0, 779, 311, 182);
		panel_order.add(btn_complete);
		
		btn_cancel = new JButton("ÁÖ¹® Ãë¼Ò");
		btn_cancel.setForeground(Color.WHITE);
		btn_cancel.setBackground(new Color(53,135,145));
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderDeleteAll(Frame_Main.num_table);
				new Frame_Main();
				dispose();
			}
		});
		btn_cancel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		btn_cancel.setBounds(318, 779, 311, 182);
		panel_order.add(btn_cancel);
		
		switch(Frame_Main.num_table) {
		case 1:
			oracle.OracleOrderRecvFromTable(1);
			break;
		case 2:
			oracle.OracleOrderRecvFromTable(2);
			break;
		case 3:
			oracle.OracleOrderRecvFromTable(3);
			break;
		case 4:
			oracle.OracleOrderRecvFromTable(4);
			break;
		case 5:
			oracle.OracleOrderRecvFromTable(5);
			break;
		case 6:
			oracle.OracleOrderRecvFromTable(6);
			break;
		case 7:
			oracle.OracleOrderRecvFromTable(7);
			break;
		case 8:
			oracle.OracleOrderRecvFromTable(8);
			break;
		case 9:
			oracle.OracleOrderRecvFromTable(9);
			break;
		case 10:
			oracle.OracleOrderRecvFromTable(10);
			break;
		case 11:
			oracle.OracleOrderRecvFromTable(11);
			break;
		case 12:
			oracle.OracleOrderRecvFromTable(12);
			break;
		case 13:
			oracle.OracleOrderRecvFromTable(13);
			break;
		case 14:
			oracle.OracleOrderRecvFromTable(14);
			break;
		case 15:
			oracle.OracleOrderRecvFromTable(15);
			break;
		case 16:
			oracle.OracleOrderRecvFromTable(16);
			break;
		}
		
	}
	
	public static void calc_pay() {
		int pay_sum = 0;
		for(int i = 0; i < tablemodel.getRowCount(); i++) {
			pay_sum += Integer.parseInt(orderTable.getValueAt(i, 2).toString())*Integer.parseInt(orderTable.getValueAt(i, 3).toString());
		}
		label_pay.setText(pay_sum + " ¿ø ");
	}
	
	private void setMenu() {
		panel_order = new JPanel();
		panel_order.setBorder(new LineBorder(null, 0));
		panel_order.setBackground(Color.WHITE);
		panel_order.setBounds(10, 112, 629, 961);
		getContentPane().add(panel_order);
		panel_order.setLayout(null);
		
		panel_menu = new JPanel();
		panel_menu.setBounds(651, 112, 1259, 767);
		getContentPane().add(panel_menu);
		panel_menu.setLayout(new GridLayout(0, 6, 5, 5));
		
		btn_menu1 = new JButton("1");
		btn_menu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(1);
			}
		});
		btn_menu1.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu1.setBackground(Color.WHITE);
		panel_menu.add(btn_menu1);
		
		btn_menu2 = new JButton("2");
		btn_menu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(2);
			}
		});
		btn_menu2.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu2.setBackground(Color.WHITE);
		panel_menu.add(btn_menu2);
		
		btn_menu3 = new JButton("3");
		btn_menu3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(3);
			}
		});
		btn_menu3.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu3.setBackground(Color.WHITE);
		panel_menu.add(btn_menu3);
		
		btn_menu4 = new JButton("4");
		btn_menu4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(4);
			}
		});
		btn_menu4.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu4.setBackground(Color.WHITE);
		panel_menu.add(btn_menu4);
		
		btn_menu5 = new JButton("5");
		btn_menu5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(5);
			}
		});
		btn_menu5.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu5.setBackground(Color.WHITE);
		panel_menu.add(btn_menu5);
		
		btn_menu6 = new JButton("6");
		btn_menu6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(6);
			}
		});
		btn_menu6.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu6.setBackground(Color.WHITE);
		panel_menu.add(btn_menu6);
		
		btn_menu7 = new JButton("7");
		btn_menu7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(7);
			}
		});
		btn_menu7.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu7.setBackground(Color.WHITE);
		panel_menu.add(btn_menu7);
		
		btn_menu8 = new JButton("8");
		btn_menu8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(8);
			}
		});
		btn_menu8.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu8.setBackground(Color.WHITE);
		panel_menu.add(btn_menu8);
		
		btn_menu9 = new JButton("9");
		btn_menu9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(9);
			}
		});
		btn_menu9.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu9.setBackground(Color.WHITE);
		panel_menu.add(btn_menu9);
		
		btn_menu10 = new JButton("10");
		btn_menu10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(10);
			}
		});
		btn_menu10.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu10.setBackground(Color.WHITE);
		panel_menu.add(btn_menu10);
		
		btn_menu11 = new JButton("11");
		btn_menu11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(11);
			}
		});
		btn_menu11.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu11.setBackground(Color.WHITE);
		panel_menu.add(btn_menu11);
		
		btn_menu12 = new JButton("12");
		btn_menu12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(12);
			}
		});
		btn_menu12.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu12.setBackground(Color.WHITE);
		panel_menu.add(btn_menu12);
		
		btn_menu13 = new JButton("13");
		btn_menu13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(13);
			}
		});
		btn_menu13.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu13.setBackground(Color.WHITE);
		panel_menu.add(btn_menu13);
		
		btn_menu14 = new JButton("14");
		btn_menu14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(14);
			}
		});
		btn_menu14.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu14.setBackground(Color.WHITE);
		panel_menu.add(btn_menu14);
		
		btn_menu15 = new JButton("15");
		btn_menu15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(15);
			}
		});
		btn_menu15.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu15.setBackground(Color.WHITE);
		panel_menu.add(btn_menu15);
		
		btn_menu16 = new JButton("16");
		btn_menu16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(16);
			}
		});
		btn_menu16.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu16.setBackground(Color.WHITE);
		panel_menu.add(btn_menu16);
		
		btn_menu17 = new JButton("17");
		btn_menu17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(17);
			}
		});
		btn_menu17.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu17.setBackground(Color.WHITE);
		panel_menu.add(btn_menu17);
		
		btn_menu18 = new JButton("18");
		btn_menu18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(18);
			}
		});
		btn_menu18.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu18.setBackground(Color.WHITE);
		panel_menu.add(btn_menu18);
		
		btn_menu19 = new JButton("19");
		btn_menu19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(19);
			}
		});
		btn_menu19.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu19.setBackground(Color.WHITE);
		panel_menu.add(btn_menu19);
		
		btn_menu20 = new JButton("20");
		btn_menu20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(20);
			}
		});
		btn_menu20.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu20.setBackground(Color.WHITE);
		panel_menu.add(btn_menu20);
		
		btn_menu21 = new JButton("21");
		btn_menu21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(21);
			}
		});
		btn_menu21.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu21.setBackground(Color.WHITE);
		panel_menu.add(btn_menu21);
		
		btn_menu22 = new JButton("22");
		btn_menu22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(22);
			}
		});
		btn_menu22.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu22.setBackground(Color.WHITE);
		panel_menu.add(btn_menu22);
		
		btn_menu23 = new JButton("23");
		btn_menu23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				oracle.OracleOrderMenu(23);
			}
		});
		btn_menu23.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu23.setBackground(Color.WHITE);
		panel_menu.add(btn_menu23);
		
		btn_menu24 = new JButton("24");
		btn_menu24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(24);
			}
		});
		btn_menu24.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu24.setBackground(Color.WHITE);
		panel_menu.add(btn_menu24);
		
		btn_menu25 = new JButton("25");
		btn_menu25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(25);
			}
		});
		btn_menu25.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu25.setBackground(Color.WHITE);
		panel_menu.add(btn_menu25);
		
		btn_menu26 = new JButton("26");
		btn_menu26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				oracle.OracleOrderMenu(26);
			}
		});
		btn_menu26.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu26.setBackground(Color.WHITE);
		panel_menu.add(btn_menu26);
		
		btn_menu27 = new JButton("27");
		btn_menu27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(27);
			}
		});
		btn_menu27.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu27.setBackground(Color.WHITE);
		panel_menu.add(btn_menu27);
		
		btn_menu28 = new JButton("28");
		btn_menu28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(28);
			}
		});
		btn_menu28.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu28.setBackground(Color.WHITE);
		panel_menu.add(btn_menu28);
		
		btn_menu29 = new JButton("29");
		btn_menu29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(29);
			}
		});
		btn_menu29.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu29.setBackground(Color.WHITE);
		panel_menu.add(btn_menu29);
		
		btn_menu30 = new JButton("30");
		btn_menu30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderMenu(30);
			}
		});
		btn_menu30.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btn_menu30.setBackground(Color.WHITE);
		panel_menu.add(btn_menu30);
		
		btn_card = new JButton("Ä«µå");
		btn_card.setForeground(Color.WHITE);
		btn_card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderDeleteAll(Frame_Main.num_table);
				new Frame_Main();
				dispose();
			}
		});
		btn_card.setBackground(new Color(53,135,145));
		btn_card.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		btn_card.setBounds(651, 891, 257, 182);
		getContentPane().add(btn_card);
		
		btn_cash = new JButton("Çö±Ý");
		btn_cash.setForeground(Color.WHITE);
		btn_cash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.OracleOrderDeleteAll(Frame_Main.num_table);
				new Frame_Main();
				dispose();
			}
		});
		btn_cash.setBackground(new Color(53,135,145));
		btn_cash.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		btn_cash.setBounds(920, 891, 257, 182);
		getContentPane().add(btn_cash);
		
		btn_receipt = new JButton("¿µ¼öÁõ");
		btn_receipt.setForeground(Color.WHITE);
		btn_receipt.setBackground(new Color(53,135,145));
		btn_receipt.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		btn_receipt.setBounds(1189, 891, 257, 182);
		getContentPane().add(btn_receipt);
		
		btn_management = new JButton("¸Þ´º °ü¸®");
		btn_management.setForeground(Color.WHITE);
		btn_management.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Frame_MenuManagement();
			}
		});
		btn_management.setBackground(new Color(53,135,145));
		btn_management.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		btn_management.setBounds(1466, 891, 215, 182);
		getContentPane().add(btn_management);
		
		btn_exit = new JButton("µÚ·Î °¡±â");
		btn_exit.setForeground(Color.WHITE);
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Frame_Main();
				dispose();
			}
		});
		btn_exit.setBackground(new Color(53,135,145));
		btn_exit.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		btn_exit.setBounds(1693, 891, 215, 182);
		getContentPane().add(btn_exit);
		
	}
	private void setTitle() {
		panel_title = new JPanel();
		panel_title.setLayout(null);
		panel_title.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_title.setBackground(new Color(53,135,145));
		panel_title.setBounds(10, 7, 1900, 99);
		getContentPane().add(panel_title);
		
		lblPosc = new JLabel("Posc");
		lblPosc.setForeground(Color.WHITE);
		lblPosc.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.BOLD, 50));
		lblPosc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosc.setBounds(1694, 10, 192, 79);
		panel_title.add(lblPosc);
		
		label_storename = new JLabel("¾÷¼Ò¸í : " + oracle.OracleGetData(Frame_Login.usernum, "storename"));
		label_storename.setForeground(Color.WHITE);
		label_storename.setHorizontalAlignment(SwingConstants.CENTER);
		label_storename.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 40));
		label_storename.setBounds(704, 14, 545, 75);
		panel_title.add(label_storename);
		
		label_frameInfo = new JLabel("¤Ó¸ÅÃâ µî·Ï");
		label_frameInfo.setForeground(Color.WHITE);
		label_frameInfo.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.BOLD, 40));
		label_frameInfo.setHorizontalAlignment(SwingConstants.LEFT);
		label_frameInfo.setBounds(12, 10, 462, 78);
		panel_title.add(label_frameInfo);
		
	}
}
