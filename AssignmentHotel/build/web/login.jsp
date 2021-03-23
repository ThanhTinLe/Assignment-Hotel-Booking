<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Login Form</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet"><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css'><link rel="stylesheet" href="./style.css">
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="459916350722-tdrki1jtr8s0ou7mj8694dak61e0uhjo.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </head>
    <body>
        <!-- partial:index.partial.html -->
        <!-- Design belong to: https://dribbble.com/chouaibblgn45 -->
        <div class="signup">
            <h1 class="signup-heading">ABC Hotel</h1>
            <h1 class="signup-heading">Login</h1>
            <c:set var="loi" value="${requestScope.ERROR}"/>
            <form action="MainController" method="POST" class="signup-form" autocomplete="off">
                <div class="form-group">
                    <label for="name" class="form-label">Email</label>
                    <input type="text" class="form-input" id="name" placeholder="email" name="txtemail" value="${param.txtemail}">
                </div>
                <c:if test="${not empty loi}">
                    <font color ="red" size="5%">${loi.emailError} </font>
                </c:if>
                <div class="form-group">
                    <label class="form-label">password</label>
                    <input type="password" class="form-input" id="email" placeholder="password" name="txtpassword">
                </div>
                <c:if test="${not empty loi}">
                    <font color ="red" size="5%">${loi.passwordError} </font>
                </c:if>
                <div class="g-recaptcha" data-sitekey="6LcekeAZAAAAAPOPJPZOIOrhjjokjZM23B_1UjCl"></div>
                <button type="submit" class="form-submit" name="btnAction" value="Login">Login</button>
            </form>
            <p class="signup-already">don't have an account ? <a href="MainController?btnAction=Create Page" class="signup-already-link">Create</a></p><br/>
            <div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
        </div>
        <!-- partial -->
    </body>
    <script>
        function onSignIn(googleUser) {
            // Useful data for your client-side scripts:
            var auth2 = gapi.auth2.getAuthInstance();
            auth2.signOut().then(function () {
                console.log('User signed out.');
            });
            var profile = googleUser.getBasicProfile();
            console.log("ID: " + profile.getId()); // Don't send this directly to your server!
            console.log('Full Name: ' + profile.getName());
            console.log('Given Name: ' + profile.getGivenName());
            console.log('Family Name: ' + profile.getFamilyName());
            console.log("Image URL: " + profile.getImageUrl());
            console.log("Email: " + profile.getEmail());
            window.location.href = 'MainController?btnAction=Gmail&email=' + profile.getEmail() + '&fullName=' + profile.getName() + '&gmailID=' + profile.getId();

            // The ID token you need to pass to your backend:
            var id_token = googleUser.getAuthResponse().id_token;
            console.log("ID Token: " + id_token);
        }
    </script>
</html>