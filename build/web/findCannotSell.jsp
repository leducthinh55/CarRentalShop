<%-- 
    Document   : findCannotSell
    Created on : Mar 26, 2020, 10:12:49 AM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <s:iterator var="car" value="%{listCar}">
            <s:property value="carName"/>
            <s:property value="quantity"/>
            </br>
        </s:iterator>
    </body>
</html>
