<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Game.Shop.dao.*"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Home</title>
  	<style><%@include file="../../resources/style.css"%></style>
    </head>
    <body class="body">
    <form>

    <p align="right" style="color:#CE9CE1"><input type="submit" class="button" formaction="client"  method="get" value="Home">&nbsp;&nbsp<input type="submit" class="button" formaction="myAccount" method="get" value="My Account"> &nbsp;&nbsp; Welcome, <%= LogAspect.logBefore()%>!
    &nbsp;&nbsp;&nbsp;&nbsp
    <input type="submit" formaction="logout"  method="get" class="button" value= "Log Out">
    &nbsp;&nbsp;&nbsp;&nbsp
    </p>
    </form>
    	<div align="center">
	        <h1>Cart</h1>
	        <c:set var="total" value="0"/>
	        <table border="1">





                <th>ID</th>
	        	<th>Name</th>
	        	<th>Quantity</th>
                <th>Price</th>
                <th>Action</th>

				<c:forEach var="item" items="${listitem}" varStatus="status">
	        	<tr>
					<td>${item.orderId}</td>
					<td>${item.productName}</td>
					<td>${item.quantity}</td>
					<td>${item.listPrice}</td>

                <td>
                	<a href="deleteCart?id=${item.orderId}">Delete</a>
                </td>

	        	</tr>

	           <c:set var="total" value="${total + item.listPrice}"/>
				</c:forEach>
			</table>
			   <h3>Total:<c:out value="${total}"/></h1>
			   <form>
			   <input type="submit" class="button" formaction="buyAll" method="get" value="Buy All">
			   </form>
    	</div>

    </body>
</html>