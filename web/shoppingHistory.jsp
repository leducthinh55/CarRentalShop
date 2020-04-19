<%-- 
    Document   : shoppingHistory
    Created on : Mar 15, 2020, 11:36:00 AM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping history Page</title>
    </head>
    <body>
        <s:if test="%{session.USER.role != 'client'}">
            <jsp:forward page="login.jsp"/>
        </s:if>
        <script src="./js/myjs.js"></script>
        <s:include value="header.jsp"/>
        <div class="container" style="text-align: center">
            <h1>Shopping History</h1>
            <form action="searchShoppingHistory" class="form-inline">
                <input class="form-control mr-3 w-30" type="text" placeholder="Car Name"
                       aria-label="Search" name="carName" value="${param.carName}">
                <input type="date" name="orderDate" value="${param.orderDate}" class="form-control mr-3 w-30"/>
                <input type="submit" class="btn btn-info" value="Search" />
            </form>

            <s:if test="%{(listRent == null || listRent.isEmpty()) && listSearchShoppingHistory == null}">
                <h2 style="text-align: center">Your shopping history is empty !</h2>
            </s:if>
            <s:if test="%{listRent!=null || listSearchShoppingHistory != null}">
                <s:if test="%{listRent != null}">
                    <s:set var="listRentHistory" value="%{listRent}"/>
                </s:if>
                <s:if test="%{listSearchShoppingHistory != null}">
                    <s:if test="%{listSearchShoppingHistory.isEmpty()}">
                        <h2 style="text-align: center">Your search is empty !</h2>
                    </s:if>
                    <s:else>
                        <s:set var="listRentHistory" value="%{listSearchShoppingHistory}"/>
                    </s:else>
                </s:if>
                <h3 style="color: red"><s:property value="errorDate"/></h3>
                <table border="1" class="table-bordered">
                    <thead>
                        <tr>
                            <th>Car Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Rental date</th>
                            <th>Return date</th>
                            <th>Discount</th>
                            <th>Status</th>
                            <th>Amount of money</th>
                            <th>Amount of money after Discount</th>
                            <th>Cancel</th>
                        </tr>
                    </thead>
                    <tbody>

                        <s:iterator value="%{listRentHistory}" status="count">

                            <tr>
                                <td><s:property value="carName"/></td>
                                <td><s:property value="price"/> $</td>
                                <td><s:property value="amount"/></td>
                                <td><s:property value="dateFrom"/></td>
                                <td><s:property value="dateTo"/></td>
                                <td><p><s:property value="percentDiscount*100"/>%</p></td>
                                <td><s:property value="status"/></td>
                                <td><s:property value="totalMoney"/> $</td>
                                <td><s:property value="totalMoney - totalMoney * percentDiscount"/> $</td>
                                <td>
                                    <form action="deleteShoppingHistory" onsubmit="return onSubmitFormDeleteOrderHistory()" name="formDeleteHistory" method="POST">
                                        <s:hidden name="rentId" value="%{rentId}"/>
                                        <s:hidden name="dateFrom" value="%{dateFrom}"/>
                                        <s:hidden name="status" value="%{status}"/>
                                        <s:hidden name="amountOfMoney" value="%{totalMoney}"/>
                                        <input type="submit" value="Cancel" class="btn btn-warning" onclick=" return confirm('Are you sure to cancel this order ?')"/>
                                    </form>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
        </div>
    </body>
</html>
