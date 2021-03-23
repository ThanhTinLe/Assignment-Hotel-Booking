<%-- 
    Document   : create
    Created on : Oct 29, 2020, 3:24:01 PM
    Author     : Ray Khum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Create Form</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet"><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css'><link rel="stylesheet" href="./style.css">

    </head>
    
    <body>
        <!-- partial:index.partial.html -->
        <!-- Design belong to: https://dribbble.com/chouaibblgn45 -->
        <div class="signup">
            <h1 class="signup-heading">Create</h1>
            <a href="#" class="signup-google">
                <i class="fab fa-google signup-google-icon"></i>
                <span class="signup-google-text">Sign up with Google</span>
            </a>
            <div class="signup-or">
                <span class="signup-or-text">Or</span>
            </div>
            <c:set var="loi" value="${requestScope.ERROR}"/>
            <form method="POST"action="MainController">
                <div class="form-group">
                    <label for="name" class="form-label">Email</label>
                    <input type="email" class="form-input" id="name" placeholder="email" name="txtemailc" value="${param.txtemailc}">
                </div>
                <c:if test="${not empty loi}">
                <font color="red" size="5%">${loi.emailError}</font>
            </c:if>
                <div class="form-group">
                    <label for="name" class="form-label">FullName</label>
                    <input type="text" class="form-input" id="name" placeholder="FullName" name="txtfullnamec" value="${param.txtfullnamec}">
                </div>
                <c:if test="${not empty loi}">
                <font color="red" size="5%">${loi.fullnameError}</font>
            </c:if>
                <div class="form-group">
                    <label class="form-label">password</label>
                    <input type="password" class="form-input" id="email" placeholder="password" name="txtpasswordc">
                </div>
                <c:if test="${not empty loi}">
                <font color="red" size="5%">${loi.passwordError}</font>
            </c:if>
                <div class="form-group">
                    <label class="form-label">Confirm</label>
                    <input type="password" class="form-input" id="email" placeholder="Confirm" name="txtconfirm">
                </div>
                <c:if test="${not empty loi}">
                 <font color="red" size="5%">${loi.confirmError}</font>
             </c:if>
                <button type="submit" class="form-submit" name="btnAction" value="Create">Create</button>
            </form>
            <p class="signup-already">Already have an account ? <a href="MainController?btnAction=Login Page" class="signup-already-link">Login</a></p>
        </div>
        <!-- partial -->
    </body>
</html>