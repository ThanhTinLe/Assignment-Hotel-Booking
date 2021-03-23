<%-- 
    Document   : updateRoom
    Created on : Nov 7, 2020, 8:42:33 PM
    Author     : Ray Khum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Room Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <c:set var="upd" value="${sessionScope.ROOM}"/>

            room ID:<input type="text" readonly="readonly" name="updateID" value="${upd.roomID}" /><br/>

            room Name:<input type="text" name="updateName" value="${upd.roomName}" /><br/>


            room Price:<input type="text" name="updatePrice" value="${upd.price}" /><br/>


            room Description:<input type="text" name="updateDescription" value="${upd.description}" /><br/>


            room Max People:<input type="text" name="updateMaxPeople" value="${upd.maxPeople}" /><br/>


            photo link:<input type="text" name="updatePhotoRoom" value="${upd.photoLink}"><br/>

            <c:set var="room" value="${sessionScope.ROOM}"/>
            <c:set var="type" value="${sessionScope.TYPE_NAME}"/>

            <select name="updateTypeRoomID" >
                <c:forEach var="updateTypeRoomID" items="${type}">
                    <option value="${updateTypeRoomID.typeID}">${updateTypeRoomID.typeName}</option>
                    <c:if test="${ room.typeRoomID eq updateTypeRoomID.typeID}">
                        <option value="${updateTypeRoomID.typeID}" selected="">${updateTypeRoomID.typeName}</option>
                    </c:if>
                </c:forEach>
            </select><br/>

            <input type="submit" value="Update Room" name="btnAction" />
            <input type="submit" name="btnAction" value="Return" />
        </form>

    </body>
</html>
