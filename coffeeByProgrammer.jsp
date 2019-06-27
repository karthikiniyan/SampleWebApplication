<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Coffee Consumption Metrics</title>
</head>
<body>
<h2>Coffee consumption by Selected user:</h2>
<form action="/CoffeeConsumption/FetchProgrammers" method="post">
<select name="name" id="name">
<% List<String> names = (List<String>)request.getAttribute("names"); 
for(String name: names)
{
%>
<option value = "<%=name%>"><%=name%></option>
<%} %>
</select>
<button type="submit"> Submit </button>
</form>
</body>
</html>