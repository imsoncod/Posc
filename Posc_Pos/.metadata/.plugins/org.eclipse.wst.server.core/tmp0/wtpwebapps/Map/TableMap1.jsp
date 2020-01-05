<%@page import="Oracle.Oracle_DAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>

<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	margin-left:0px;
}

td {
	background-color: green;
	color: white;
	font-size: 35px;
}
</style>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		Oracle_DAO oracle = new Oracle_DAO();
		oracle.OracleLoading();
		oracle.OracleGetTable();
	%>
	<div style = "height:100%">
		<div align="center">
			<div style="border: 1px solid black; height:60vh;">
				<table
					style="width: 100%; height: 100%; text-align: center; border-spacing: 30px">
					<tr height="45%">
					<% 
						for(int i = 1; i <= 4; i++){
							boolean check = false;
							for(int j = 0; j < Oracle_DAO.tableinfo.length; j++){
								if(Oracle_DAO.tableinfo[j] == 2*i-1){
									check = true;
								}
							}
							if(check){
								out.println("<td style = \"background-color:red\">");
								out.println("<b>");
								out.println(2*i-1);
								out.println("</b>");
								out.println("</td>");
							}else{
								out.println("<td>");
								out.println("<b>");
								out.println(2*i-1);
								out.println("</b>");
								out.println("</td>");
							}
						}
					%>
					</tr>
					<tr height="55%">
					<% 
						for(int i = 1; i <= 4; i++){
							boolean check = false;
							for(int j = 0; j < Oracle_DAO.tableinfo.length; j++){
								if(Oracle_DAO.tableinfo[j] == 2*i){
									check = true;
								}
							}
							if(check){
								out.println("<td style = \"background-color:red\">");
								out.println("<b>");
								out.println(2*i);
								out.println("</b>");
								out.println("</td>");
							}else{
								out.println("<td>");
								out.println("<b>");
								out.println(2*i);
								out.println("</b>");
								out.println("</td>");
							}
						}
					%>
					</tr>
				</table>
			</div>
			<div style = "margin-top:5px; margin-right:10px" align ="right">
				<span style = "color:green">■</span> : 자리 있음  &nbsp; <span style = "color:red">■</span> : 자리 없음
			</div>
		</div>
	</div>
</body>
</html>