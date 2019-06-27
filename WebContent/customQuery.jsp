<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Custom Query Execution</title>
</head>
<body>
<h2>Enter your query to execute</h2>
<form action="/CoffeeConsumption/CustomQuery" method="post">
<textarea rows="3" cols="100" id="query" name="query"></textarea>
<br>
<br>
<button type="submit">Submit</button>
</form>

<%
ArrayList<ArrayList<String>> list = (ArrayList<ArrayList<String>>)request.getAttribute("result");
HashMap<String,String> meta = (HashMap<String,String>)request.getAttribute("meta");
if(meta != null && meta.size()>0)
{
%>
<p>Query MetaData</p>
<table border = "1">
<tr><th>Column Name</th><th>Column Type</th></tr>
<% for(Map.Entry<String,String> e: meta.entrySet()) 
{%>
<tr><td><%=e.getKey() %></td><td><%=e.getValue() %></td></tr>
<%} %>
</table>

<p>Query Results</p>
<table border = "1">
<% for(ArrayList<String> internal: list) 
{%>
<tr>
<td>
	<%for(String s: internal) {%>
	<td><%=s %></td>
	<%} %>
</td>
</tr>
<%} %>
</table>

<%} %>

</body>
</html>