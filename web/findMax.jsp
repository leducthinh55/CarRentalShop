<%-- 
    Document   : findMax
    Created on : Mar 24, 2020, 11:11:09 AM
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
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <s:iterator var="car" value="%{listRent}">
                    <tr>
                        <td><s:property value="carName"/></td>
                        <td><s:property value="totalMoney"/></td>
                    </tr>
                </s:iterator>

            </tbody>
        </table>


    </body>
</html>
