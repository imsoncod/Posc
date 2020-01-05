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
<%
	String phone = request.getParameter("phone");
	String name = request.getParameter("name");
	String price = request.getParameter("price");
	String count = request.getParameter("count");
%>

	<input type ="text" value="<%=phone %>">
	<input type ="text" value="<%=name %>">
	<input type ="text" value="<%=price %>">
	<input type ="text" value="<%=count %>">

<%
try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "admin_user", "root");
	Statement stmt = conn.createStatement();
	stmt.executeUpdate("insert into androidselect values('"+ phone + "', '" + name + "', '" + price + "', '" + count + "')" );
	
} catch (Exception e) {
	e.printStackTrace();
}
	
%>
</body>
</html>