<%-- 
    Document   : cartPage
    Created on : Mar 9, 2020, 9:52:51 PM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="./bootstrap/css/mycss.css" rel="stylesheet">
        <title>Cart Page</title>
    </head>
    <body>
        <s:if test="%{session.USER.role != 'client'}">
            <jsp:forward page="login.jsp"/>
        </s:if>
        <script src="./js/myjs.js"></script>
        <s:include value="header.jsp"/>
        <div class="container" style="text-align: center">
            <h1>Car Retal Cart</h1>
            <s:if test="%{#session.CART == null || #session.CART.items.isEmpty()}">
                <h2 style="text-align: center">Your cart is empty !</h2>
            </s:if>
            <s:if test="%{#session.CART.items.isEmpty()==false}">
                <table border="1" class="table-bordered">
                    <thead>
                        <tr>
                            <th>Car Name</th>
                            <th>Category</th>
                            <th>Color</th>
                            <th>Price</th>
                            <th>Amount</th>
                            <th>Rental date</th>
                            <th>Return date</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="%{#session.CART.items}" status="count">

                            <tr>
                                <td><s:property value="key.carName"/></td>
                                <td><s:property value="key.category"/></td>
                                <td><s:property value="key.color"/></td>
                                <td><s:property value="key.price"/></td>
                                <td>
                                    <div class="btn btn-group">
                                        <form action="changeNumberOfCard" onsubmit="return cannotDecrease()" name="changeNumberOfCardForm" method="POST">
                                            <input type="hidden" name="carIdChange" value="<s:property value="key.carId"/>" />
                                            <input type="hidden" name="dateFromChange" value="<s:property value="key.dateFrom"/>" />
                                            <input type="hidden" name="dateToChange" value="<s:property value="key.dateTo"/>" />
                                            <input type="hidden" name="curNumber" value="<s:property value="value"/>" />
                                            <input type="hidden" name="changeStatus" value="down" />
                                            <input type="submit" value="-" />
                                        </form>
                                            <input type="submit" value="<s:property value="value"/>" />
                                        <form action="changeNumberOfCard" method="POST">
                                            <input type="hidden" name="carIdChange" value="<s:property value="key.carId"/>" />
                                            <input type="hidden" name="dateFromChange" value="<s:property value="key.dateFrom"/>" />
                                            <input type="hidden" name="dateToChange" value="<s:property value="key.dateTo"/>" />
                                            <input type="hidden" name="changeStatus" value="up" />
                                            <input type="submit" value="+" />
                                        </form>
                                    </div>

                                </td>
                                <td><input id="<s:property value="%{#count.index*2}"/>" type="date" name="dateFrom" value="<s:property value="key.dateFrom"/>" 
                                           onchange="location = '/J3.L.P0006_CarRental/updateCartDateFromAndDateTo?oldDateFrom=<s:property value="key.dateFrom"/>&newDateFrom=' + this.value + '&oldDateTo=' + document.getElementById('<s:property value="%{#count.index*2+1}"/>').value + '&carId=<s:property value="key.carId"/>'"/></td>
                                <td><input id="<s:property value="%{#count.index*2+1}"/>" type="date" name="dateFrom" value="<s:property value="key.dateTo"/>" 
                                           onchange="location = '/J3.L.P0006_CarRental/updateCartDateFromAndDateTo?oldDateTo=<s:property value="key.dateTo"/>&newDateTo=' + this.value + '&oldDateFrom=' + document.getElementById('<s:property value="%{#count.index*2}"/>').value + '&carId=<s:property value="key.carId"/>'"/></td>
                                <td>
                                    <form action="deleteFromCart">
                                        <input type="hidden" name="carIdDelete" value="<s:property value="key.carId"/>" />
                                        <input type="hidden" name="dateFromDelete" value="<s:property value="key.dateFrom"/>" />
                                        <input type="hidden" name="dateToDelete" value="<s:property value="key.dateTo"/>" />
                                        <input type="submit" value="Delete" class="btn btn-danger" onclick="return confirm('Are you sure to delete !')"/>
                                    </form>
                                </td>
                            </tr>

                        </s:iterator>
                    </tbody>
                </table>
                <h3>Amount of money : <s:property value="%{#session.AMOUNT_OF_MONEY}"/></h3>
                <h4 style="color: red; text-align: center"><s:property value="%{errorDate}"/></h4>
                <h4 style="color: red; text-align: center"><s:property value="%{errorDiscount}"/></h4>
                <s:if test="%{listItemOutOfStock != null}">
                    <s:iterator value="%{listItemOutOfStock}">
                        <h3 style="color: red"><s:property/></h3></br>
                    </s:iterator>
                </s:if>
                <s:if test="%{listItemNotAvailable != null}">
                    <s:iterator value="%{listItemNotAvailable}">
                        <h3 style="color: red"><s:property/></h3></br>
                    </s:iterator>
                </s:if>
                </br>
                <form action="checkOut" method="POST">
                    <s:hidden name="email" value="%{#session.USER.email}"/>
                    Discount Code: <input type="text" name="discountCode" value="" />
                    <input type="submit" value="Check Out"  class="btn btn-info"/>
                </form>
            </s:if>
        </div>
    </body>
</html>
