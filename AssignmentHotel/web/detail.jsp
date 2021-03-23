<%-- 
    Document   : detail
    Created on : Nov 4, 2020, 4:09:52 PM
    Author     : Ray Khum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>detail Page</title>
    </head>

    <body>
        <div class="grid">
            <div class="col-2-3">
                <section class="post-content">

                    <form action="MainController">
                        <c:set var="room" value="${requestScope.DETAIL}" />
                        <h1>Detail Room: ${room.roomID}</h1>
                        <h2>Name:${room.roomName}</h2>
                        <h2>price:${room.price}</h2>
                        <h2>Max people :${room.maxPeople}</h2>
                        <h2>type:${requestScope.TYPE}</h2>
                        <h2>Description:${room.description}</h2>
                        <br>
                        <input type="submit" value="Home" name="btnAction" />
                        <input type="hidden" name="txtcheckin" value="${sessionScope.CHECKIN}" />
                        <input type="hidden" name="txtcheckout" value="${sessionScope.CHECKOUT}" />
                        <input type="submit" value="Add To Cart" name="btnAction" />
                        <input type="hidden" name="roomID" value="${room.roomID}" />
                    </form>
                </section>
            </div>
            <form action="MainController">
                <div class="col-1-3">
                    <figure>
                        <img src="${room.photoLink}" alt>
                    </figure>
                </div>
            </form>
        </div>

    </body>

    <style>
        * {
            margin: 0;
            padding: 0;
            font-size: 1.05em;
            line-height: 1.25em;
        }

        body {
            font-family: Calibri;
            font-size: 0.8em;

        }

        h1 {
            font-size: 2.5em;
            color: black;
        }


        [class*='col-'] {
            float: right;
        }

        h2{
            padding-top: 1%
        }
        .col-2-3 {
            width: 66.66%;
        }

        .col-1-3 {
            width: 33.33%;
        }

        figure {
            padding: 1em;
            max-width: 33em;
        }

        img {
            display: block;
            width: 80%;
            margin-left: 3%;

        }
    </style>
