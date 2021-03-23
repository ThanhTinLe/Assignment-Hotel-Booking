<%-- 
    Document   : home
    Created on : Oct 28, 2020, 5:08:08 PM
    Author     : Ray Khum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Home</title>
    </head>
    <body>

        <c:set var="loi" value="${sessionScope.CHECKINERROR}"/>

        <form action="MainController" method="POST">
            <br/><a href="MainController?btnAction=Login Page">Logout</a>
            <font color="blue"><h1>ABC Hotel welcome: ${sessionScope.LOGIN_USER}</h1></font>
            <span>
                Check in:<input type="date" name="txtcheckin" value="${sessionScope.CHECKIN}">   
                <font color ="red">${requestScope.CHECKINERROR}</font>
            </span><br/>
            <span>
                Check out:<input type="date" name="txtcheckout" value="${sessionScope.CHECKOUT}">
                <font color ="red">${requestScope.CHECKOUTERROR}</font>
            </span><br/>
            <button type="submit" name="btnAction" value="Check In">Check</button><br/>
        </form>

        <c:set var="list" value="${sessionScope.LIST}"/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>RoomID</th>
                        <th>Room</th>
                        <th>price</th>
                        <th>max people</th>
                        <th>add to cart</th>
                        <th>view detail</th>
                    </tr>
                </thead>

                <c:forEach var="room" varStatus="counter" items="${sessionScope.LIST}">
                    <form action="MainController">
                        <tbody>
                            <tr>
                                <td>${room.roomID}</td>
                                <td>${room.roomName}</td>
                                <td>${room.price}</td>
                                <td>${room.maxPeople}</td>
                                <td>
                                    <input type="hidden" name="txtcheckin" value="${sessionScope.CHECKIN}" />
                                    <input type="hidden" name="txtcheckout" value="${sessionScope.CHECKOUT}" />
                                    <input type="submit" value="Add To Cart" name="btnAction"/>
                                    <input type="hidden" name="txtRoomID" value="${room.roomID}"/>
                                </td>
                                <td>
                                    <input type="hidden" name="txtcheckin" value="${sessionScope.CHECKIN}" />
                                    <input type="hidden" name="txtcheckout" value="${sessionScope.CHECKOUT}" />
                                    <input type="hidden" name="roomID" value="${room.roomID}" />
                                    <input type="submit" value="Detail" name="btnAction"/>
                                </td>
                            </tr>
                        </tbody>
                    </form>
                </c:forEach>
            </c:if>
        </table>
        <form action="MainController">
            <font color="blue"><h1>${requestScope.OK}</h1></font>
            <input type="submit" value="View Cart" name="btnAction" />
        </form>
    </body>
</html>
