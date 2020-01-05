package Frame;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import Oracle.Oracle_DAO;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Frame_Main extends JFrame implements ActionListener, MouseListener {
	public JPanel panel_title, panel_table, tab_panel1, tab_panel2, panel_sub, panel_map1, panel_map2;
	public JLabel lblPosc, label_storename, label_date, label_admin, label_exit, label_time, label_frameInfo, label_posNum, label_note;
	public static JLabel table1_order, table3_order, table2_order, table4_order, table5_order, table6_order, table7_order, table8_order;
	public static JLabel table9_order, table10_order, table11_order, table12_order, table13_order, table14_order, table15_order, table16_order;
	public JLabel map_table1, map_table2, map_table3, map_table4, map_table5, map_table6, map_table7, map_table8;
	public JLabel map_table9, map_table10, map_table11, map_table12, map_table13, map_table14, map_table15, map_table16;
	public static JButton btn_table1, btn_table2, btn_table3, btn_table5, btn_table7, btn_table4, btn_table6, btn_table8;
	public static JButton btn_table9, btn_table10, btn_table11, btn_table12, btn_table13, btn_table14, btn_table15, btn_table16;
	public JTabbedPane tabpanel_table;
	private JButton btnNewButton;
	public static int num_table = 0;
	public static MenuItem menu_open, menu_exit;
	public static TrayIcon trayIcon;
	public static SystemTray tray;
	public JTextArea txta_note;
	Oracle_DAO oracle = new Oracle_DAO();

	public Frame_Main() {
		getContentPane().setBackground(Color.WHITE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
		setResizable(false);
		setSize(1920, 1100);
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/WindowIcon.png")));
		
		setTray();
		setTitle();
		setMap1();
		setMap2();
		setTable();
		oracle.OracleOrderRecvFromDB();
		setVisible(true);
	}
	private void setTray() {
        PopupMenu trayMenu = new PopupMenu();
        trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/WindowIcon.png")));
        tray = SystemTray.getSystemTray();
       
        menu_open = new MenuItem("¡¡¿­±â");
        menu_open.setFont(new Font(null, Font.BOLD, 12));
        menu_exit = new MenuItem("¡¡Á¾·á");
        menu_exit.setFont(new Font(null, Font.PLAIN, 12));
        
        menu_open.addActionListener(this);
        menu_exit.addActionListener(this);
       
        trayMenu.add(menu_open);
        trayMenu.addSeparator();
        trayMenu.add(menu_exit);
       
        trayIcon.setPopupMenu(trayMenu);
        trayIcon.addMouseListener(this);
        trayIcon.setToolTip("Posc");
       
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
		
	}
	
	private void setTitle() {
		panel_title = new JPanel();
		panel_title.setBackground(new Color(53,135,145));
		panel_title.setBounds(10, 7, 1900, 99);
		panel_title.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		getContentPane().add(panel_title);
		panel_title.setLayout(null);
		
		lblPosc = new JLabel("Posc");
		lblPosc.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.BOLD, 50));
		lblPosc.setForeground(Color.WHITE);
		lblPosc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosc.setBounds(1694, 10, 192, 79);
		panel_title.add(lblPosc);
		
		label_storename = new JLabel("¾÷¼Ò¸í : " + oracle.OracleGetData(Frame_Login.usernum, "storename"));
		label_storename.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 40));
		label_storename.setForeground(Color.WHITE);
		label_storename.setHorizontalAlignment(SwingConstants.CENTER);
		label_storename.setBounds(709, 14, 545, 75);
		panel_title.add(label_storename);
		
		label_frameInfo = new JLabel("¤ÓÅ×ÀÌºí ÁÖ¹®");
		label_frameInfo.setForeground(Color.WHITE);
		label_frameInfo.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.BOLD, 40));
		label_frameInfo.setHorizontalAlignment(SwingConstants.LEFT);
		label_frameInfo.setBounds(12, 10, 462, 78);
		panel_title.add(label_frameInfo);
	}
	
	private void setMap1() {
		panel_sub = new JPanel();
		panel_sub.setBackground(Color.WHITE);
		panel_sub.setBounds(10, 112, 341, 968);
		getContentPane().add(panel_sub);
		panel_sub.setLayout(null);
		
		panel_map1 = new JPanel();
		panel_map1.setForeground(Color.WHITE);
		panel_map1.setBackground(UIManager.getColor("InternalFrame.inactiveBorderColor"));
		panel_map1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_map1.setBounds(0, 0, 341, 270);
		panel_sub.add(panel_map1);
		panel_map1.setLayout(null);
		
		map_table1 = new JLabel("1");
		map_table1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table1.setForeground(Color.WHITE);
		map_table1.setHorizontalAlignment(SwingConstants.CENTER);
		map_table1.setOpaque(true);
		map_table1.setBackground(new Color(0, 108, 0));
		map_table1.setBounds(14, 22, 54, 78);
		panel_map1.add(map_table1);
		
		map_table2 = new JLabel("2");
		map_table2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table2.setForeground(Color.WHITE);
		map_table2.setHorizontalAlignment(SwingConstants.CENTER);
		map_table2.setOpaque(true);
		map_table2.setBackground(new Color(0, 108, 0));
		map_table2.setBounds(14, 161, 54, 78);
		panel_map1.add(map_table2);
		
		map_table3 = new JLabel("3");
		map_table3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table3.setForeground(Color.WHITE);
		map_table3.setHorizontalAlignment(SwingConstants.CENTER);
		map_table3.setOpaque(true);
		map_table3.setBackground(new Color(0, 108, 0));
		map_table3.setBounds(99, 22, 54, 63);
		panel_map1.add(map_table3);
		
		map_table4 = new JLabel("4");
		map_table4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table4.setForeground(Color.WHITE);
		map_table4.setHorizontalAlignment(SwingConstants.CENTER);
		map_table4.setOpaque(true);
		map_table4.setBackground(new Color(0, 108, 0));
		map_table4.setBounds(99, 176, 54, 63);
		panel_map1.add(map_table4);
		
		map_table5 = new JLabel("5");
		map_table5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table5.setForeground(Color.WHITE);
		map_table5.setHorizontalAlignment(SwingConstants.CENTER);
		map_table5.setOpaque(true);
		map_table5.setBackground(new Color(0, 108, 0));
		map_table5.setBounds(188, 22, 54, 63);
		panel_map1.add(map_table5);
		
		map_table6 = new JLabel("6");
		map_table6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table6.setForeground(Color.WHITE);
		map_table6.setHorizontalAlignment(SwingConstants.CENTER);
		map_table6.setOpaque(true);
		map_table6.setBackground(new Color(0, 108, 0));
		map_table6.setBounds(188, 176, 54, 63);
		panel_map1.add(map_table6);
		
		map_table7 = new JLabel("7");
		map_table7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table7.setForeground(Color.WHITE);
		map_table7.setHorizontalAlignment(SwingConstants.CENTER);
		map_table7.setOpaque(true);
		map_table7.setBackground(new Color(0, 108, 0));
		map_table7.setBounds(275, 22, 54, 63);
		panel_map1.add(map_table7);
		
		map_table8 = new JLabel("8");
		map_table8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table8.setForeground(Color.WHITE);
		map_table8.setHorizontalAlignment(SwingConstants.CENTER);
		map_table8.setOpaque(true);
		map_table8.setBackground(new Color(0, 108, 0));
		map_table8.setBounds(275, 176, 54, 63);
		panel_map1.add(map_table8);
		
		label_posNum = new JLabel(" Æ÷½º¹øÈ£ : " + Frame_Login.usernum);
		label_posNum.setBackground(new Color(53,135,145));
		label_posNum.setForeground(Color.WHITE);
		label_posNum.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		label_posNum.setBounds(0, 283, 341, 60);
		label_posNum.setOpaque(true);
		label_posNum.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panel_sub.add(label_posNum);
		
		label_admin = new JLabel(" ´ã´çÀÚ : " + oracle.OracleGetData(Frame_Login.usernum, "username"));
		label_admin.setBackground(new Color(53,135,145));
		label_admin.setForeground(Color.WHITE);
		label_admin.setBounds(0, 350, 341, 60);
		label_admin.setOpaque(true);
		panel_sub.add(label_admin);
		label_admin.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		label_admin.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
			
		Date date = new Date();
		String today = (date.getYear()+1900) + "³â " + (date.getMonth()+1) + "¿ù " + date.getDate() + "ÀÏ ";
		
		label_date = new JLabel(" ¿µ¾÷ÀÏÀÚ : " + today);
		label_date.setBackground(new Color(53,135,145));
		label_date.setForeground(Color.WHITE);
		label_date.setBounds(0, 417, 341, 60);
		label_date.setOpaque(true);
		label_date.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panel_sub.add(label_date);
		label_date.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		
		label_time = new JLabel();
		label_time.setBackground(new Color(53,135,145));
		label_time.setForeground(Color.WHITE);
		label_time.setOpaque(true);
		label_time.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		label_time.setBounds(0, 483, 341, 60);
		label_time.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panel_sub.add(label_time);
		
		//½Ã°£ °è»ê
		Timer timer = new javax.swing.Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar time = Calendar.getInstance();
				int h = time.get(Calendar.HOUR);
				String hour = h < 10 ? 0+String.valueOf(h) : String.valueOf(h);
				int m = time.get(Calendar.MINUTE);
				String minute = m < 10 ? 0+String.valueOf(m) : String.valueOf(m);
				int s = time.get(Calendar.SECOND);
				String second = s < 10 ? 0+String.valueOf(s) : String.valueOf(s);
				int ap = time.get(Calendar.AM_PM);
				String ampm = ap==0 ? "AM" : "PM"; 
				label_time.setText(" ÇöÀç ½Ã°¢ : " + hour + ":" + minute + ":" + second + " " + ampm);
			}
		});timer.start();	
		
		txta_note = new JTextArea(oracle.GetNote());
		txta_note.setBackground(new Color(224, 255, 255));
		txta_note.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		txta_note.setBounds(0, 605, 341, 298);
		txta_note.setLineWrap(true);
		txta_note.setBorder(new LineBorder(Color.BLACK,1));
		panel_sub.add(txta_note);
		
		label_note = new JLabel();
		label_note.setForeground(new Color(255, 255, 255));
		label_note.setHorizontalAlignment(SwingConstants.CENTER);
		label_note.setText("NOTE");
		label_note.setOpaque(true);
		label_note.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		label_note.setBackground(new Color(53, 135, 145));
		label_note.setBounds(0, 552, 341, 54);
		label_note.setBorder(new LineBorder(Color.BLACK,1));
		panel_sub.add(label_note);
		
		btnNewButton = new JButton("Á¾ ·á");
		btnNewButton.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 18));
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBackground(new Color(53,135,145));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oracle.SaveNote(txta_note.getText());
				oracle.OracleOrderDeleteAllExit();
				System.exit(0);
			}
		});
		btnNewButton.setBounds(0, 911, 341, 45);
		panel_sub.add(btnNewButton);
		
	}
	
	private void setMap2() {
		panel_map2 = new JPanel();
		panel_map2.setForeground(Color.WHITE);
		panel_map2.setBackground(UIManager.getColor("InternalFrame.inactiveBorderColor"));
		panel_map2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_map2.setBounds(0, 0, 341, 270);
		panel_sub.add(panel_map2);
		panel_map2.setLayout(null);
		
		map_table9 = new JLabel("9");
		map_table9.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table9.setForeground(Color.WHITE);
		map_table9.setHorizontalAlignment(SwingConstants.CENTER);
		map_table9.setOpaque(true);
		map_table9.setBackground(new Color(0, 108, 0));
		map_table9.setBounds(14, 22, 54, 78);
		panel_map2.add(map_table9);
		
		map_table10 = new JLabel("10");
		map_table10.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table10.setForeground(Color.WHITE);
		map_table10.setHorizontalAlignment(SwingConstants.CENTER);
		map_table10.setOpaque(true);
		map_table10.setBackground(new Color(0, 108, 0));
		map_table10.setBounds(14, 161, 54, 78);
		panel_map2.add(map_table10);
		
		map_table11 = new JLabel("11");
		map_table11.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table11.setForeground(Color.WHITE);
		map_table11.setHorizontalAlignment(SwingConstants.CENTER);
		map_table11.setOpaque(true);
		map_table11.setBackground(new Color(0, 108, 0));
		map_table11.setBounds(99, 22, 54, 63);
		panel_map2.add(map_table11);
		
		map_table12 = new JLabel("12");
		map_table12.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table12.setForeground(Color.WHITE);
		map_table12.setHorizontalAlignment(SwingConstants.CENTER);
		map_table12.setOpaque(true);
		map_table12.setBackground(new Color(0, 108, 0));
		map_table12.setBounds(99, 176, 54, 63);
		panel_map2.add(map_table12);
		
		map_table13 = new JLabel("13");
		map_table13.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table13.setForeground(Color.WHITE);
		map_table13.setHorizontalAlignment(SwingConstants.CENTER);
		map_table13.setOpaque(true);
		map_table13.setBackground(new Color(0, 108, 0));
		map_table13.setBounds(188, 22, 54, 63);
		panel_map2.add(map_table13);
		
		map_table14 = new JLabel("14");
		map_table14.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table14.setForeground(Color.WHITE);
		map_table14.setHorizontalAlignment(SwingConstants.CENTER);
		map_table14.setOpaque(true);
		map_table14.setBackground(new Color(0, 108, 0));
		map_table14.setBounds(188, 176, 54, 63);
		panel_map2.add(map_table14);
		
		map_table15 = new JLabel("15");
		map_table15.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table15.setForeground(Color.WHITE);
		map_table15.setHorizontalAlignment(SwingConstants.CENTER);
		map_table15.setOpaque(true);
		map_table15.setBackground(new Color(0, 108, 0));
		map_table15.setBounds(275, 22, 54, 63);
		panel_map2.add(map_table15);
		
		map_table16 = new JLabel("16");
		map_table16.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		map_table16.setForeground(Color.WHITE);
		map_table16.setHorizontalAlignment(SwingConstants.CENTER);
		map_table16.setOpaque(true);
		map_table16.setBackground(new Color(0, 108, 0));
		map_table16.setBounds(275, 176, 54, 63);
		panel_map2.add(map_table16);
		
		panel_map2.setVisible(false);
	}
	
	private void setTable() {
		panel_table = new JPanel();
		panel_table.setBackground(Color.WHITE);
		panel_table.setBounds(363, 112, 1547, 960);
		panel_table.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel_table);
		panel_table.setLayout(null);
		
		tabpanel_table = new JTabbedPane(JTabbedPane.TOP);
		tabpanel_table.setBackground(Color.WHITE);
		tabpanel_table.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 15));
		tabpanel_table.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabpanel_table.getSelectedIndex()==0) {
					panel_map1.setVisible(true);
					panel_map2.setVisible(false);
					oracle.OracleGetTable();
					for(int i = 0; i < Oracle_DAO.tableinfo.length; i++) {
						switch(Oracle_DAO.tableinfo[i]) {
						case 1:
							map_table1.setBackground(Color.RED);
							break;
						case 2:
							map_table2.setBackground(Color.RED);
							break;
						case 3:
							map_table3.setBackground(Color.RED);
							break;
						case 4:
							map_table4.setBackground(Color.RED);
							break;
						case 5:
							map_table5.setBackground(Color.RED);
							break;
						case 6:
							map_table6.setBackground(Color.RED);
							break;
						case 7:
							map_table7.setBackground(Color.RED);
							break;
						case 8:
							map_table8.setBackground(Color.RED);
							break;
						}
					}
				}else {
					panel_map2.setVisible(true);
					panel_map1.setVisible(false);
					oracle.OracleGetTable();
					for(int i = 0; i < Oracle_DAO.tableinfo.length; i++) {
						switch(Oracle_DAO.tableinfo[i]) {
						case 9:
							map_table9.setBackground(Color.RED);
							break;
						case 10:
							map_table10.setBackground(Color.RED);
							break;
						case 11:
							map_table11.setBackground(Color.RED);
							break;
						case 12:
							map_table12.setBackground(Color.RED);
							break;
						case 13:
							map_table13.setBackground(Color.RED);
							break;
						case 14:
							map_table14.setBackground(Color.RED);
							break;
						case 15:
							map_table15.setBackground(Color.RED);
							break;
						case 16:
							map_table16.setBackground(Color.RED);
							break;
						}
					}
				}
			}
		});
		tabpanel_table.setBounds(0, 0, 1547, 960);
		panel_table.add(tabpanel_table);
		
		//1Ãþ Å×ÀÌºí ÆÐ³Î
		tab_panel1 = new JPanel();
		tab_panel1.setBackground(Color.WHITE);
		tabpanel_table.addTab("      1Ãþ       ", null, tab_panel1, null);
		tab_panel1.setLayout(null);
		
		//Å×ÀÌºí ¹öÆ°
		btn_table1 = new JButton("Å×ÀÌºí 1");
		btn_table1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 1;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table1.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table1.setBackground(new Color(224, 255, 255));
		btn_table1.setBounds(80, 68, 244, 289);
		tab_panel1.add(btn_table1);
		
		btn_table2 = new JButton("Å×ÀÌºí 2");
		btn_table2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 2;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table2.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table2.setBackground(new Color(224, 255, 255));
		btn_table2.setBounds(80, 550, 244, 289);
		tab_panel1.add(btn_table2);
		
		btn_table3 = new JButton("Å×ÀÌºí 3");
		btn_table3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 3;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table3.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table3.setBackground(new Color(224, 255, 255));
		btn_table3.setBounds(480, 68, 244, 210);
		tab_panel1.add(btn_table3);
		
		btn_table4 = new JButton("Å×ÀÌºí 4");
		btn_table4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 4;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table4.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table4.setBackground(new Color(224, 255, 255));
		btn_table4.setBounds(480, 630, 244, 210);
		tab_panel1.add(btn_table4);
		
		btn_table5 = new JButton("Å×ÀÌºí 5");
		btn_table5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 5;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table5.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table5.setBackground(new Color(224, 255, 255));
		btn_table5.setBounds(880, 68, 244, 210);
		tab_panel1.add(btn_table5);
		
		btn_table6 = new JButton("Å×ÀÌºí 6");
		btn_table6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 6;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table6.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table6.setBackground(new Color(224, 255, 255));
		btn_table6.setBounds(880, 630, 244, 210);
		tab_panel1.add(btn_table6);
		
		btn_table7 = new JButton("Å×ÀÌºí 7");
		btn_table7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 7;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table7.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table7.setBackground(new Color(224, 255, 255));
		btn_table7.setBounds(1280, 68, 244, 210);
		tab_panel1.add(btn_table7);
		
		btn_table8 = new JButton("Å×ÀÌºí 8");
		btn_table8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 8;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table8.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table8.setBackground(new Color(224, 255, 255));
		btn_table8.setBounds(1280, 630, 244, 210);
		tab_panel1.add(btn_table8);
		
		//ÃâÀÔ±¸ ¶óº§
		label_exit = new JLabel("EXIT");
		label_exit.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.BOLD, 15));
		label_exit.setBackground(new Color(0, 128, 0));
		label_exit.setBounds(1503, 376, 39, 97);
		label_exit.setHorizontalAlignment(SwingConstants.CENTER);
		label_exit.setForeground(Color.WHITE);
		label_exit.setOpaque(true);
		tab_panel1.add(label_exit);
		
		//ÁÖ¹® ±Ý¾× ¶óº§
		table1_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table1_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table1_order.setBounds(80, 358, 244, 27);
		tab_panel1.add(table1_order);
		
		table2_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table2_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table2_order.setBounds(80, 840, 244, 27);
		tab_panel1.add(table2_order);
		
		table3_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table3_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table3_order.setBounds(480, 278, 244, 27);
		tab_panel1.add(table3_order);
		
		table4_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table4_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table4_order.setBounds(480, 840, 244, 27);
		tab_panel1.add(table4_order);
		
		table5_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table5_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table5_order.setBounds(880, 278, 244, 27);
		tab_panel1.add(table5_order);
		
		table6_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table6_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table6_order.setBounds(880, 840, 244, 27);
		tab_panel1.add(table6_order);
		
		table7_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table7_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table7_order.setBounds(1280, 278, 244, 27);
		tab_panel1.add(table7_order);
		
		table8_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table8_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table8_order.setBounds(1280, 840, 244, 27);
		tab_panel1.add(table8_order);
		
		//2Ãþ Å×ÀÌºí ÆÐ³Î
		tab_panel2 = new JPanel();
		tab_panel2.setBackground(Color.WHITE);
		tabpanel_table.addTab("      2Ãþ      ", null, tab_panel2, null);
		tab_panel2.setLayout(null);
		
		//Å×ÀÌºí ¹öÆ°
		btn_table9 = new JButton("Å×ÀÌºí 9");
		btn_table9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 9;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table9.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table9.setBackground(new Color(224, 255, 255));
		btn_table9.setBounds(80, 68, 244, 289);
		tab_panel2.add(btn_table9);
		
		btn_table10 = new JButton("Å×ÀÌºí 10");
		btn_table10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 10;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table10.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table10.setBackground(new Color(224, 255, 255));
		btn_table10.setBounds(80, 550, 244, 289);
		tab_panel2.add(btn_table10);
		
		btn_table11 = new JButton("Å×ÀÌºí 11");
		btn_table11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 11;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table11.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table11.setBackground(new Color(224, 255, 255));
		btn_table11.setBounds(480, 68, 244, 289);
		tab_panel2.add(btn_table11);
		
		btn_table12 = new JButton("Å×ÀÌºí 12");
		btn_table12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 12;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table12.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table12.setBackground(new Color(224, 255, 255));
		btn_table12.setBounds(480, 550, 244, 289);
		tab_panel2.add(btn_table12);
		
		btn_table13 = new JButton("Å×ÀÌºí 13");
		btn_table13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 13;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table13.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table13.setBackground(new Color(224, 255, 255));
		btn_table13.setBounds(880, 68, 244, 289);
		tab_panel2.add(btn_table13);
		
		btn_table14 = new JButton("Å×ÀÌºí 14");
		btn_table14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 14;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table14.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table14.setBackground(new Color(224, 255, 255));
		btn_table14.setBounds(880, 550, 244, 289);
		tab_panel2.add(btn_table14);
		
		btn_table15 = new JButton("Å×ÀÌºí 15");
		btn_table15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 15;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table15.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table15.setBackground(new Color(224, 255, 255));
		btn_table15.setBounds(1280, 68, 244, 289);
		tab_panel2.add(btn_table15);
		
		btn_table16 = new JButton("Å×ÀÌºí 16");
		btn_table16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_table = 16;
				oracle.SaveNote(txta_note.getText());
				new Frame_Order();
				dispose();
				tray.remove(trayIcon);
			}
		});
		btn_table16.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 20));
		btn_table16.setBackground(new Color(224, 255, 255));
		btn_table16.setBounds(1280, 550, 244, 289);
		tab_panel2.add(btn_table16);
		
		table9_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table9_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table9_order.setBounds(80, 358, 244, 27);
		tab_panel2.add(table9_order);
		
		table10_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table10_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table10_order.setBounds(80, 840, 244, 27);
		tab_panel2.add(table10_order);
		
		table11_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table11_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table11_order.setBounds(480, 358, 244, 27);
		tab_panel2.add(table11_order);
		
		table12_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table12_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table12_order.setBounds(480, 840, 244, 27);
		tab_panel2.add(table12_order);
		
		table13_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table13_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table13_order.setBounds(880, 358, 244, 27);
		tab_panel2.add(table13_order);
		
		table14_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table14_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table14_order.setBounds(880, 840, 244, 27);
		tab_panel2.add(table14_order);
		
		table15_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table15_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table15_order.setBounds(1280, 358, 244, 27);
		tab_panel2.add(table15_order);
		
		table16_order = new JLabel("ÁÖ¹® ±Ý¾× : 0 ¿ø");
		table16_order.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		table16_order.setBounds(1280, 840, 244, 27);
		tab_panel2.add(table16_order);
		
		for(int i = 1; i <= 16; i++) {
			oracle.OracleSetOrderPay(i);
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if(event == menu_open) {
			setVisible(true);
		}
		if(event == menu_exit) {
			oracle.OracleOrderDeleteAllExit();
			System.exit(0);
		}	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == trayIcon) {
			if(e.getClickCount() == 2) {
				setVisible(true);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}



