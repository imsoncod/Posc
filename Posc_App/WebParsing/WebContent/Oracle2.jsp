<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class ="oracle">
<%
	try{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "admin_user", "root");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select DISTINCT menunum, menuname, menuprice, menuoption, menuoptionname, menuoptionprice from menuinfo order by menunum");
		
		while(rs.next()) {
			out.print("<a>" + rs.getString(2)  + "</a>");
			out.print("<a1>" + rs.getString(3)  + "</a1>");
			out.print("<a2>" + rs.getString(4)  + "</a2>");
			out.print("<a3>" + rs.getString(5)  + "</a3>");
			out.print("<a4>" + rs.getString(6)  + "</a4><p>");
		}
		
		rs.close();
		stmt.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
</div>
</body>
</html>