package Oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;

import Frame.Frame_Main;
import Frame.Frame_MenuInsert;
import Frame.Frame_MenuManagement;
import Frame.Frame_MenuModify;
import Frame.Frame_Order;

public class Oracle_DAO {
	private String ip;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url;
	private String user;
	private String password;
	private String commit = "commit";

	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String sql = null;
	private int check = -1;
	
	//본 서버
	public void DBSetting_Server() {
		ip = "192.168.0.2";
		url = "jdbc:oracle:thin:@"+ip+":1521:xe";
		user = "admin_user";
		password = "root";
	}
	
	//포스기 서버
	public void DBSetting_Me() {
		ip = "localhost";
		url = "jdbc:oracle:thin:@"+ip+":1521:orcl";
		user = "posc";
		password = "posc";
	}

	public int OracleLoading() {
		try {
			DBSetting_Server();
			Class.forName(driver);
			DriverManager.getConnection(url, user, password);
			check = 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	// Check Login Possible
	public int OracleLogin(String usernum, String userpw) {
		try {
			DBSetting_Server();
			con = DriverManager.getConnection(url, user, password);

			sql = "select * from userinfo where usernum ='" + usernum + "'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if ((usernum.isEmpty() == true)) {
				check = -1;
			} else {
				sql = "select * from userinfo where usernum ='" + usernum + "'";
				rs = stmt.executeQuery(sql);
				while (rs.next() == true) {
					if (rs.getString("userpw").equals(userpw)) {
						check = 0;
					} else {
						check = 1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{if(con!=null) con.close();}catch(Exception e){};
			try{if(stmt!=null) stmt.close();}catch(Exception e){};
			try{if(rs!=null) rs.close();}catch(Exception e){};
		}
		return check;
	}

	//회원정보 가져오기
	public String OracleGetData(String usernum, String attribute) {
		try {
			DBSetting_Server();
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			sql = "select " + attribute + " from userinfo where usernum = '" + usernum + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return rs.getString(attribute);
			}
		} catch (Exception e) {
			
		}finally {
			try{if(con!=null) con.close();}catch(Exception e){};
			try{if(stmt!=null) stmt.close();}catch(Exception e){};
			try{if(rs!=null) rs.close();}catch(Exception e){};
		}
		return null;
	}
	
	//메뉴 추가
	public void OracleInsertMenu(int menunum, String name, int price) {
		try {
			DBSetting_Me();
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			sql = "Insert into menuinfo values(" + menunum + ",'" + name + "','" + price + "')";
			rs = stmt.executeQuery(sql);
			rs = stmt.executeQuery("commit");
		} catch (Exception e) {
			
		}finally {
			try{if(con!=null) con.close();}catch(Exception e){};
			try{if(stmt!=null) stmt.close();}catch(Exception e){};
			try{if(rs!=null) rs.close();}catch(Exception e){};
		}
	}
	
	//메뉴 수정
		public void OracleModifyMenu(int menunum1, int menunum2, String name, int price) {
			try {
				DBSetting_Me();
				con = DriverManager.getConnection(url, user, password);
				stmt = con.createStatement();
				sql = "Update menuinfo set menunum ="+menunum2+",menuname='"+name+"',menuprice='"+price+"' where menunum = "+menunum1;
				rs = stmt.executeQuery(sql);
				rs = stmt.executeQuery("commit");
				switch(menunum1) {
				case 1:
					Frame_Order.btn_menu1.setText("1");
					break;
				case 2:
					Frame_Order.btn_menu2.setText("2");
					break;
				case 3:
					Frame_Order.btn_menu3.setText("3");
					break;
				case 4:
					Frame_Order.btn_menu4.setText("4");
					break;
				case 5:
					Frame_Order.btn_menu5.setText("5");
					break;
				case 6:
					Frame_Order.btn_menu6.setText("6");
					break;
				case 7:
					Frame_Order.btn_menu7.setText("7");
					break;
				case 8:
					Frame_Order.btn_menu8.setText("8");
					break;
				case 9:
					Frame_Order.btn_menu9.setText("9");
					break;
				case 10:
					Frame_Order.btn_menu10.setText("10");
					break;
				case 11:
					Frame_Order.btn_menu11.setText("11");
					break;
				case 12:
					Frame_Order.btn_menu12.setText("12");
					break;
				case 13:
					Frame_Order.btn_menu13.setText("13");
					break;
				case 14:
					Frame_Order.btn_menu14.setText("14");
					break;
				case 15:
					Frame_Order.btn_menu15.setText("15");
					break;
				case 16:
					Frame_Order.btn_menu16.setText("16");
					break;
				case 17:
					Frame_Order.btn_menu17.setText("17");
					break;
				case 18:
					Frame_Order.btn_menu18.setText("18");
					break;
				case 19:
					Frame_Order.btn_menu19.setText("19");
					break;
				case 20:
					Frame_Order.btn_menu20.setText("20");
					break;
				case 21:
					Frame_Order.btn_menu21.setText("21");
					break;
				case 22:
					Frame_Order.btn_menu22.setText("22");
					break;
				case 23:
					Frame_Order.btn_menu23.setText("23");
					break;
				case 24:
					Frame_Order.btn_menu24.setText("24");
					break;
				case 25:
					Frame_Order.btn_menu25.setText("25");
					break;
				case 26:
					Frame_Order.btn_menu26.setText("26");
					break;
				case 27:
					Frame_Order.btn_menu27.setText("27");
					break;
				case 28:
					Frame_Order.btn_menu28.setText("28");
					break;
				case 29:
					Frame_Order.btn_menu29.setText("29");
					break;
				case 30:
					Frame_Order.btn_menu30.setText("30");
					break;
				}
			} catch (Exception e) {
				
			}finally {
				try{if(con!=null) con.close();}catch(Exception e){};
				try{if(stmt!=null) stmt.close();}catch(Exception e){};
				try{if(rs!=null) rs.close();}catch(Exception e){};
			}
		}
	
	//메뉴 삭제
		public void OracleRemoveMenu(String menunum) {
			try {
				DBSetting_Me();
				con = DriverManager.getConnection(url, user, password);
				stmt = con.createStatement();
				sql = "delete from menuinfo where menunum = " + menunum;
				rs = stmt.executeQuery(sql);
				rs = stmt.executeQuery("commit");
			} catch (Exception e) {
				
			}finally {
				try{if(con!=null) con.close();}catch(Exception e){};
				try{if(stmt!=null) stmt.close();}catch(Exception e){};
				try{if(rs!=null) rs.close();}catch(Exception e){};
			}
		}
	
	//메뉴 가져오기
	public void OracleGetMenu() {
		try {
			DBSetting_Me();
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			sql = "Select menunum, menuname, menuprice from menuinfo order by menunum";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Frame_MenuManagement.tablemodel.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getInt(3)});
			}
		} catch (Exception e) {
			
		}finally {
			try{if(con!=null) con.close();}catch(Exception e){};
			try{if(stmt!=null) stmt.close();}catch(Exception e){};
			try{if(rs!=null) rs.close();}catch(Exception e){};
		}
	}
	
	//메뉴 주문하기
		public void OracleOrderMenu(int menunum) {
			try {
				DBSetting_Me();
				for(int i = 0; i < Frame_Order.tablemodel.getRowCount(); i++) {
					if(Integer.parseInt(Frame_Order.orderTable.getValueAt(i, 0).toString())==menunum) {
						Frame_Order.orderTable.setValueAt(Integer.parseInt(Frame_Order.orderTable.getValueAt(i, 3).toString())+1, i, 3);
						Frame_Order.orderTable.setValueAt(Integer.parseInt(Frame_Order.orderTable.getValueAt(i, 2).toString())*Integer.parseInt(Frame_Order.orderTable.getValueAt(i, 3).toString()), i, 4);
						Frame_Order.calc_pay();
						return;
					}
				}
				con = DriverManager.getConnection(url, user, password);
				stmt = con.createStatement();
				sql = "Select menunum, menuname, menuprice from menuinfo where menunum = " + menunum;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Frame_Order.tablemodel.addRow(new Object[] {rs.getString(1),rs.getString(2), rs.getInt(3), 1 , rs.getInt(3)});
				}
				Frame_Order.calc_pay();
			} catch (Exception e) {
				
			}finally {
				try{if(con!=null) con.close();}catch(Exception e){};
				try{if(stmt!=null) stmt.close();}catch(Exception e){};
				try{if(rs!=null) rs.close();}catch(Exception e){};
			}
		}
		
		//주문 추가하기
			public void OracleOrderInsert(int tablenum, int menunum, String menuname, int menuprice, int count, int sumprice) {
				try {
					DBSetting_Me();
					con = DriverManager.getConnection(url, user, password);
					stmt = con.createStatement();
					sql = "Insert into orderinfo values(" + tablenum + ","+menunum+",'"+menuname+"'," + menuprice + "," +count+",'"+sumprice+"')";
					rs = stmt.executeQuery(sql);
					rs = stmt.executeQuery("commit");
				} catch (Exception e) {
					
				}finally {
					try{if(con!=null) con.close();}catch(Exception e){};
					try{if(stmt!=null) stmt.close();}catch(Exception e){};
					try{if(rs!=null) rs.close();}catch(Exception e){};
				}
			}
			
			//테이블버튼 -> JTable 주문정보 가져오기
			public void OracleOrderRecvFromTable(int tablenum) {
				try {
					DBSetting_Me();
					con = DriverManager.getConnection(url, user, password);
					stmt = con.createStatement();
					sql = "Select * from orderinfo where tablenum = " + tablenum + "order by menunum";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Frame_Order.tablemodel.addRow(new Object[] {rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)});
					}
					Frame_Order.calc_pay();
				} catch (Exception e) {
					
				}finally {
					try{if(con!=null) con.close();}catch(Exception e){};
					try{if(stmt!=null) stmt.close();}catch(Exception e){};
					try{if(rs!=null) rs.close();}catch(Exception e){};
				}
			}	
			
			//DB -> 테이블버튼
			public void OracleOrderRecvFromDB() {
				try {
					DBSetting_Me();
					String ordermenu = "";
					con = DriverManager.getConnection(url, user, password);
					stmt = con.createStatement();
					sql = "Select * from orderinfo order by menunum";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						switch(rs.getInt(1)) {
						case 1:
							if(Frame_Main.btn_table1.getText().equals("테이블 1")) Frame_Main.btn_table1.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table1.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table1.setText(ordermenu);
							Frame_Main.btn_table1.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table1.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 2:
							if(Frame_Main.btn_table2.getText().equals("테이블 2")) Frame_Main.btn_table2.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table2.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table2.setText(ordermenu);
							Frame_Main.btn_table2.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table2.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 3:
							if(Frame_Main.btn_table3.getText().equals("테이블 3")) Frame_Main.btn_table3.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table3.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table3.setText(ordermenu);
							Frame_Main.btn_table3.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table3.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 4:
							if(Frame_Main.btn_table4.getText().equals("테이블 4")) Frame_Main.btn_table4.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table4.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table4.setText(ordermenu);
							Frame_Main.btn_table4.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table4.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 5:
							if(Frame_Main.btn_table5.getText().equals("테이블 5")) Frame_Main.btn_table5.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table5.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table5.setText(ordermenu);
							Frame_Main.btn_table5.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table5.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 6:
							if(Frame_Main.btn_table6.getText().equals("테이블 6")) Frame_Main.btn_table6.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table6.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table6.setText(ordermenu);
							Frame_Main.btn_table6.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table6.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 7:
							if(Frame_Main.btn_table7.getText().equals("테이블 7")) Frame_Main.btn_table7.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table7.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table7.setText(ordermenu);
							Frame_Main.btn_table7.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table7.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 8:
							if(Frame_Main.btn_table8.getText().equals("테이블 8")) Frame_Main.btn_table8.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table8.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table8.setText(ordermenu);
							Frame_Main.btn_table8.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table8.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 9:
							if(Frame_Main.btn_table9.getText().equals("테이블 9")) Frame_Main.btn_table9.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table9.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table9.setText(ordermenu);
							Frame_Main.btn_table9.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table9.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 10:
							if(Frame_Main.btn_table10.getText().equals("테이블 10")) Frame_Main.btn_table10.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table10.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table10.setText(ordermenu);
							Frame_Main.btn_table10.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table10.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 11:
							if(Frame_Main.btn_table11.getText().equals("테이블 11")) Frame_Main.btn_table11.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table11.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table11.setText(ordermenu);
							Frame_Main.btn_table11.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table11.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 12:
							if(Frame_Main.btn_table12.getText().equals("테이블 12")) Frame_Main.btn_table12.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table12.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table12.setText(ordermenu);
							Frame_Main.btn_table12.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table12.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 13:
							if(Frame_Main.btn_table13.getText().equals("테이블 13")) Frame_Main.btn_table13.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table13.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table13.setText(ordermenu);
							Frame_Main.btn_table13.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table13.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 14:
							if(Frame_Main.btn_table14.getText().equals("테이블 14")) Frame_Main.btn_table14.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table14.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table14.setText(ordermenu);
							Frame_Main.btn_table14.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table14.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 15:
							if(Frame_Main.btn_table15.getText().equals("테이블 15")) Frame_Main.btn_table15.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table15.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table15.setText(ordermenu);
							Frame_Main.btn_table15.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table15.setVerticalAlignment(SwingConstants.TOP);
							break;
						case 16:
							if(Frame_Main.btn_table16.getText().equals("테이블 16")) Frame_Main.btn_table16.setText("<html><br>");
							ordermenu = "<html>" + Frame_Main.btn_table16.getText().toString() +rs.getString(3)+" "+rs.getInt(5)+"<br>";
							Frame_Main.btn_table16.setText(ordermenu);
							Frame_Main.btn_table16.setHorizontalAlignment(SwingConstants.LEFT);
							Frame_Main.btn_table16.setVerticalAlignment(SwingConstants.TOP);
							break;
						}
					}
				} catch (Exception e) {
					
				}finally {
					try{if(con!=null) con.close();}catch(Exception e){};
					try{if(stmt!=null) stmt.close();}catch(Exception e){};
					try{if(rs!=null) rs.close();}catch(Exception e){};
				}
			}
			
			//테이블 주문정보 삭제하기
			public void OracleOrderDeleteAll(int tablenum) {
				try {
					DBSetting_Me();
					con = DriverManager.getConnection(url, user, password);
					stmt = con.createStatement();
					sql = "Delete from orderinfo where tablenum = " + tablenum;
					rs = stmt.executeQuery(sql);
					rs = stmt.executeQuery("commit");
				} catch (Exception e) {
					
				}finally {
					try{if(con!=null) con.close();}catch(Exception e){};
					try{if(stmt!=null) stmt.close();}catch(Exception e){};
					try{if(rs!=null) rs.close();}catch(Exception e){};
				}
			}
			
			//모든 주문정보 삭제하기
			public void OracleOrderDeleteAllExit() {
				try {
					DBSetting_Me();
					con = DriverManager.getConnection(url, user, password);
					stmt = con.createStatement();
					sql = "Delete from orderinfo";
					rs = stmt.executeQuery(sql);
					rs = stmt.executeQuery("commit");
				} catch (Exception e) {
					
				}finally {
					try{if(con!=null) con.close();}catch(Exception e){};
					try{if(stmt!=null) stmt.close();}catch(Exception e){};
					try{if(rs!=null) rs.close();}catch(Exception e){};
				}
			}
	
	//비어있는 메뉴 번호 가져오기(메뉴삽입시)
	public void OracleGetMenuNum1() {
		try {
			DBSetting_Me();
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			sql = "Select menunum from menuinfo";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Frame_MenuInsert.arr_emptynum[rs.getInt(1)] = true;
			}
		} catch (Exception e) {
			
		}finally {
			try{if(con!=null) con.close();}catch(Exception e){};
			try{if(stmt!=null) stmt.close();}catch(Exception e){};
			try{if(rs!=null) rs.close();}catch(Exception e){};
		}
	}
	
	//비어있는 메뉴 변호 가져오기(메뉴수정시)
	public void OracleGetMenuNum2() {
		try {
			DBSetting_Me();
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			sql = "Select menunum from menuinfo";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Frame_MenuModify.arr_emptynum[rs.getInt(1)] = true;
			}
		} catch (Exception e) {
			
		}finally {
			try{if(con!=null) con.close();}catch(Exception e){};
			try{if(stmt!=null) stmt.close();}catch(Exception e){};
			try{if(rs!=null) rs.close();}catch(Exception e){};
		}
	}
	
	//버튼에 메뉴 넣기
			public void OracleSetOrderPay(int tablenum) {
				try {
					DBSetting_Me();
					con = DriverManager.getConnection(url, user, password);
					stmt = con.createStatement();
					sql = "Select sum(sumprice) from orderinfo where tablenum = " + tablenum;
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						switch(tablenum) {
						case 1:
							Frame_Main.table1_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 2:
							Frame_Main.table2_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 3:
							Frame_Main.table3_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 4:
							Frame_Main.table4_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 5:
							Frame_Main.table5_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 6:
							Frame_Main.table6_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 7:
							Frame_Main.table7_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 8:
							Frame_Main.table8_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 9:
							Frame_Main.table9_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 10:
							Frame_Main.table10_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 11:
							Frame_Main.table11_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 12:
							Frame_Main.table12_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 13:
							Frame_Main.table13_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 14:
							Frame_Main.table14_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 15:
							Frame_Main.table15_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						case 16:
							Frame_Main.table16_order.setText("주문금액 : " + rs.getInt(1) + " 원");
							break;
						}
					}
				} catch (Exception e) {
					
				}finally {
					try{if(con!=null) con.close();}catch(Exception e){};
					try{if(stmt!=null) stmt.close();}catch(Exception e){};
					try{if(rs!=null) rs.close();}catch(Exception e){};
				}
			}
		
		//메모 저장
		public void SaveNote(String memo) {
			try {
				DBSetting_Me();
				con = DriverManager.getConnection(url, user, password);
				stmt = con.createStatement();
				sql = "Update note set memo = '"+memo+"' where mnum = 1";
				stmt.executeUpdate(sql);
			} catch (Exception e) {

			}finally {
				try{if(con!=null) con.close();}catch(Exception e){};
				try{if(stmt!=null) stmt.close();}catch(Exception e){};
				try{if(rs!=null) rs.close();}catch(Exception e){};
			}		
		}
		
		//메모 가져오기
		public String GetNote() {
			try {
				DBSetting_Me();
				con = DriverManager.getConnection(url, user, password);
				stmt = con.createStatement();
				sql = "select memo from note";
				rs = stmt.executeQuery(sql);
				if(rs.next()) return rs.getString(1);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try{if(con!=null) con.close();}catch(Exception e){};
				try{if(stmt!=null) stmt.close();}catch(Exception e){};
				try{if(rs!=null) rs.close();}catch(Exception e){};
			}		
			return "123";
		}
			
	
		//버튼에 메뉴 넣기
		public void OracleSetMenuBtn() {
			try {
				DBSetting_Me();
				con = DriverManager.getConnection(url, user, password);
				stmt = con.createStatement();
				sql = "Select * from menuinfo";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					switch(rs.getInt(1)) {
					case 1:
						Frame_Order.btn_menu1.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 2:
						Frame_Order.btn_menu2.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 3:
						Frame_Order.btn_menu3.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 4:
						Frame_Order.btn_menu4.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 5:
						Frame_Order.btn_menu5.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 6:
						Frame_Order.btn_menu6.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 7:
						Frame_Order.btn_menu7.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 8:
						Frame_Order.btn_menu8.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 9:
						Frame_Order.btn_menu9.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 10:
						Frame_Order.btn_menu10.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 11:
						Frame_Order.btn_menu11.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 12:
						Frame_Order.btn_menu12.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 13:
						Frame_Order.btn_menu13.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 14:
						Frame_Order.btn_menu14.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 15:
						Frame_Order.btn_menu15.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 16:
						Frame_Order.btn_menu16.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 17:
						Frame_Order.btn_menu17.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 18:
						Frame_Order.btn_menu18.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 19:
						Frame_Order.btn_menu19.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 20:
						Frame_Order.btn_menu20.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 21:
						Frame_Order.btn_menu21.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 22:
						Frame_Order.btn_menu22.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 23:
						Frame_Order.btn_menu23.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 24:
						Frame_Order.btn_menu24.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 25:
						Frame_Order.btn_menu25.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 26:
						Frame_Order.btn_menu26.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 27:
						Frame_Order.btn_menu27.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 28:
						Frame_Order.btn_menu28.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 29:
						Frame_Order.btn_menu29.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					case 30:
						Frame_Order.btn_menu30.setText("<html><center>"+rs.getString(2)+"<br><br>"+rs.getString(3)+"원"+"</center></html>");
						break;
					}
				}
			} catch (Exception e) {
				
			}finally {
				try{if(con!=null) con.close();}catch(Exception e){};
				try{if(stmt!=null) stmt.close();}catch(Exception e){};
				try{if(rs!=null) rs.close();}catch(Exception e){};
			}
		}
		
		public static int tableinfo[];
		//테이블 가져오기
		public void OracleGetTable() {
			try {
				DBSetting_Me();
				tableinfo = new int[16];
				con = DriverManager.getConnection(url, user, password);
				stmt = con.createStatement();
				sql = "select distinct(tablenum) from orderinfo";
				rs = stmt.executeQuery(sql);
				int i = 0;
				while (rs.next()) {
					tableinfo[i] = rs.getInt(1);
					i++;
				}
			} catch (Exception e) {
				
			}finally {
				try{if(con!=null) con.close();}catch(Exception e){};
				try{if(stmt!=null) stmt.close();}catch(Exception e){};
				try{if(rs!=null) rs.close();}catch(Exception e){};
			}
		}
		
}
