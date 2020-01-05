package Oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;

public class Oracle_DAO {
	final private String commit = "COMMIT";
	final private String driver = "oracle.jdbc.driver.OracleDriver";
	final private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	final private String user = "posc";
	final private String password = "posc";

	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String sql = null;
	private int check = -1;

	public int OracleLoading() {
		try {
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
	
	public static int tableinfo[];
	//테이블 가져오기
	public void OracleGetTable() {
		try {
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
