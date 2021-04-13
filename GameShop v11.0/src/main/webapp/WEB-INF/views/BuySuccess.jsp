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
    <form action="buySuccess" method="get">
    <p style="color:#CE9CE1; position: absolute;top:30%;left: 5%">You might also like: </p>
    <img src=${rate1.image} style="position: absolute;top:40%;left: 5%" height="223" width="170">
    <p style="color:#CE9CE1; position: absolute;top:30%;left: 85%">You might also like: </p>
    <img src=${rate2.image} style="position: absolute;top:40%;left: 85%" height="223" width="170">
    <h3 style="position: absolute;top:50%;left: 42%;text-align: center;color: #400040;">Payment successfully made!
        <br/>
        <br/>
            <input type="submit" class="button" value="Home">
        </h3>
    </body>
</html>
