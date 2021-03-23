<%-- 
    Document   : admin
    Created on : Nov 7, 2020, 1:54:28 PM
    Author     : Ray Khum
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <br/><a href="MainController?btnAction=Login Page">Logout</a>
            <font color="blue"><h1>ABC Hotel welcome: ${sessionScope.LOGIN_USER}</h1></font>
            <font color="red"><h1>${sessionScope.DELETEAD}</h1></font>
        </form>
        <c:set var="listad" value="${sessionScope.LISTAD}"/>
        <table border="1">
            <thead>
                <tr>
                    <th>RoomID</th>
                    <th>Room</th>
                    <th>price</th>
                    <th>max people</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ad" items="${listad}">

                <form action="MainController">
                    <tr>
                        <td>${ad.roomID}</td>
                        <td>${ad.roomName}</td>
                        <td>${ad.price}</td>
                        <td>${ad.maxPeople}</td>
                </form>
                <form action="MainController">
                    <td>
                        <input type="hidden" name="txtUpdate" value="${ad.roomID}" />
                        <input type="submit" value="Update Page" name="btnAction" />
                    </td>
                </form>
                <form action="MainController">
                    <td>
                        <input type="submit" value="Delete Room" name="btnAction"/>
                        <input type="hidden" name="txtDelete" value="${ad.roomID}" />
                    </td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
