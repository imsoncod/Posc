package Frame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Oracle.Oracle_DAO;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame_MenuManagement extends JFrame {

	public static JPanel contentPane;
	public JButton btn_insert, btn_remove, btn_exit, btn_modify;
	public static JTable menutable;
	public static DefaultTableModel tablemodel;
	public DefaultTableCellRenderer tablesort;
	private String attribute[] = {"¹øÈ£","¸Þ´º","´Ü°¡"};
	private Object tuple[][] = {};
	Oracle_DAO oracle = new Oracle_DAO();
	
	public Frame_MenuManagement() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 502, 639);
		setTitle("¸Þ´º °ü¸®");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTable();
		
		setVisible(true);
	}

	private void setTable() {
			tablemodel = new DefaultTableModel(tuple, attribute) {
				public boolean isCellEditable(int tuple, int attribute) {
					return false;
				}
			};

			menutable = new JTable(tablemodel);
			menutable.setBorder(new LineBorder(null, 0, true));
//			orderTable.addMouseListener(this);
			menutable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			menutable.setRowHeight(50);
			menutable.getTableHeader().setBackground(new Color(53,135,145));
			menutable.getTableHeader().setReorderingAllowed(false);
			menutable.getTableHeader().setResizingAllowed(false);
			menutable.getTableHeader().setForeground(Color.WHITE);
			menutable.getTableHeader().setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			menutable.setFocusable(false);
			menutable.setRowSelectionAllowed(true);
			menutable.setFont(new Font("ÇÑÄÄ À±°íµñ 230", Font.PLAIN, 16));
			menutable.setSelectionBackground(new Color(255,255,180));
			
			menutable.getColumnModel().getColumn(0).setPreferredWidth(20);
			menutable.getColumnModel().getColumn(1).setPreferredWidth(300);
			menutable.getColumnModel().getColumn(2).setPreferredWidth(70);
			
			tablesort = new DefaultTableCellRenderer();
			tablesort.setHorizontalAlignment(tablesort.CENTER);
			
			menutable.getColumnModel().getColumn(0).setCellRenderer(tablesort);
			menutable.getColumnModel().getColumn(1).setCellRenderer(tablesort);
			menutable.getColumnModel().getColumn(2).setCellRenderer(tablesort);

			JScrollPane sc = new JScrollPane(menutable);
			sc.setBounds(2, 0, 480, 543);
			sc.getViewport().setBackground(Color.WHITE);
			sc.setBorder(new LineBorder(new Color(0, 0, 0), 0));
			
			getContentPane().add(sc);
			
			oracle.OracleGetMenu();
						
			btn_insert = new JButton("Ãß°¡");
			btn_insert.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
			btn_insert.setBackground(new Color(53,135,145));
			btn_insert.setForeground(Color.WHITE);
			btn_insert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Frame_MenuInsert();
				}
			});
			btn_insert.setBounds(10, 550, 97, 37);
			contentPane.add(btn_insert);
			
			btn_modify = new JButton("¼öÁ¤");
			btn_modify.setBackground(new Color(53,135,145));
			btn_modify.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
			btn_modify.setForeground(Color.WHITE);
			btn_modify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Frame_MenuModify();
				}
			});
			btn_modify.setBounds(115, 550, 97, 37);
			contentPane.add(btn_modify);
			
			btn_remove = new JButton("»èÁ¦");
			btn_remove.setBackground(new Color(53,135,145));
			btn_remove.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
			btn_remove.setForeground(Color.WHITE);
			btn_remove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					oracle.OracleRemoveMenu(menutable.getValueAt(menutable.getSelectedRow(), 0).toString());
					tablemodel.removeRow(menutable.getSelectedRow());
				}
			});
			btn_remove.setBounds(220, 550, 97, 37);
			contentPane.add(btn_remove);
			
			btn_exit = new JButton("³ª°¡±â");
			btn_exit.setBackground(new Color(53,135,145));
			btn_exit.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
			btn_exit.setForeground(Color.WHITE);
			btn_exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btn_exit.setBounds(378, 550, 97, 37);
			contentPane.add(btn_exit);
	}
}
