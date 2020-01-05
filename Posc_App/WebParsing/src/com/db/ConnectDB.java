package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }
    public ConnectDB() {  }

    // oracle 계정
    String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
    String userId = "admin_user";
    String userPw = "root";

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;

    String sql = "";
    String sql2 = "";
    String returns = "a";
    
    public String ConnectOracle() {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
    		
    		returns = "연동완료!";
    		
    	} catch (Exception e) {
    		returns = "오라클 오류!";
    	}
    	
    	return returns;
    }
    
    public String OracleSelectMenu() {
		String menu = null;   	
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery("select * from menuinfo");
    		
    		while(rs.next()) {
    			menu = rs.getString(3);
    			System.out.println("<html>" + menu + "</html>");
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return menu;
    }
    
    public void OracleMenuInsert(String u, String m, String mn, String mp) {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
    		stmt = conn.createStatement();
    		stmt.executeUpdate("insert into userinfo values('" + u + "', '" + m + "', '" + mn + "', '" + mp + "')");
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}