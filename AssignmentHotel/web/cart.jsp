<%-- 
    Document   : cart
    Created on : Nov 4, 2020, 8:10:02 PM
    Author     : Ray Khum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <form action="MainController">
            <font color='green'><h1>${sessionScope.LOGIN_USER}'s Cart</h1></font>
            <c:set var="checkNull" value="${sessionScope.MAP}"/>
            <c:if test="${not empty checkNull}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Room</th>
                            <th>price</th>
                            <th>delete</th>
                        </tr>
                    </thead>
                    <tbody><c:set var="total" value="${0}"/>
                        <c:forEach var="map" varStatus="counter"  items="${sessionScope.MAP}">
                            <tr>
                                <td>${map.value.roomName}</td>
                                <td>${map.value.price}</td>
                                <c:set var="total" value="${map.value.price + total}"/>
                                <td>
                                    <input type="hidden" name="txtDelete" value="${map.value.roomID}" />
                                    <input type="submit" value="Delete Room Cart" name="btnAction" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <font color="red"><h1>${requestScope.DELETE}</h1></font>   

            <font color="blue"><h1>total : ${total}</h1></font>
            <input type="hidden" name="totalPrice" value="${total}" />
            <input type="submit" value="Add More" name="btnAction" />
            <input type="submit" value="Booking Now" name="btnAction"/>

            <c:if test="${empty checkNull}">
                <font color ="red"> <h1>cart is empty</h1> </font>
            </c:if>


        </form>
    </body>
</html>
