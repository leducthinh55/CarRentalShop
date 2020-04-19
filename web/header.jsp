<%-- 
    Document   : header
    Created on : Mar 8, 2020, 2:07:12 PM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Header Page</title>
        <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="./bootstrap/css/mycss.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
            <div class="container">
                <a class="navbar-brand" href="#">CAR RENTAL</a>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="homePage">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <s:if test="">

                        </s:if>
                        <s:if test="%{#session.USER == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="createNewAccount.jsp">Sign Up</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="login.jsp">Login</a>
                            </li>
                        </s:if>

                        <s:if test="%{#session.USER.role == 'client'}">
                            <li class="nav-item">
                                <a class="nav-link" href="cartPage.jsp">Cart Page</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="shoppingHistory">Shopping History</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"><s:property value="%{#session.USER.name}"/></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="logOut">Log out</a>
                            </li>
                        </s:if>
                        <s:if test="%{#session.USER.role =='admin'}">
                            <li class="nav-item">
                                <a class="nav-link" href="adminPage">Admin Page</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="createNewCar.jsp">Create New Car</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link">ADMIN</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="logOut">Log out</a>
                            </li>
                        </s:if>
                    </ul>
                </div>
            </div>
        </nav>

        <script src="./jquery/jquery.slim.min.js"></script>
        <script src="./bootstrap/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
