<%-- 
    Document   : login
    Created on : Mar 6, 2020, 9:57:45 AM
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
        <h1>Login Page</h1>
        <form action="login" method="POST">
            UserName <input type="text" name="username" value="" /><br/>
            PassWord <input type="password" name="password" value="" />
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        
        <h3 style="color: red"><s:property value="%{error}"/></h3>
    </body>
</html>
