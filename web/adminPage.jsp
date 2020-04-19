<%-- 
    Document   : adminPage
    Created on : Mar 12, 2020, 7:27:49 PM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <s:if test="%{session.USER.role != 'admin'}">
            <jsp:forward page="login.jsp"/>
        </s:if>
        <script src="./js/myjs.js"></script>
        <s:include value="header.jsp"/>
        <div class="container">
            <form action="findMax">
                <input type="submit" value="Submit" />
            </form>
            <form action="findCannotSell">
                <input type="date" name="dateFrom" value="" />
                <input type="date" name="dateTo" value="" />
                <input type="submit" value="Find" />
            </form>
            <div class="col-12 mt-3 ml-5">
                <form action="searchAdmin" class="form-inline">
                    <input class="form-control ml-5 mr-3 w-30" type="text" placeholder="Car Name"
                           aria-label="Search" name="carName" value="">
                   <select name="status" class="form-control mr-3">
                        <option value="all" <c:if test="${param.status =='all'}">selected</c:if>>All</option>
                        <option value="active"<c:if test="${param.status =='active'}">selected</c:if>>Active</option>
                        <option value="inactive" <c:if test="${param.status =='inactive'}">selected</c:if>>In Active</option>
                        </select>
                        <input type="submit" class="btn btn-info" value="Search" />
                    </form>
                </div>
                <div class="row">
                <s:set var="listCar" value="%{#application.LIST_CAR_ADMIN}"/>
                <s:if test="%{listSearchAdmin != null}">
                    <s:set var="listCar" value="%{listSearchAdmin}"/>
                </s:if>
                <s:iterator var="car" value="%{listCar}">
                    <div class="col-4 mt-2">
                        <div class="card p-1">
                            <img src="<s:property value="image"/>" class="card-img-top" height="240"/>
                            <div class="card-body">
                                <div class="card-title ct">
                                    <s:property value="carName"/>
                                </div>
                                <p class="card-text ct">Category : <s:property value="category"/></p>
                                <p class="card-text ct">Status : <s:property value="status"/></p>
                                <p class="card-text ct">Price : <s:property value="price"/></p>
                                <div class="row justify-content-center">
                                    <form action="deleteCar" method="POST">
                                        <s:hidden name="carId" value="%{carId}" />
                                        <input type="submit" value="Delete" class="btn btn-danger mr-3" style="width: 100px"
                                               onclick="return confirm('Are you sure to delete')"/>
                                    </form>
                                        <form action="detailToUpdate" method="POST">
                                        <s:hidden name="carId" value="%{carId}" />
                                        <s:hidden name="carName" value="%{carName}"/>
                                        <s:hidden name="category" value="%{category}"/>
                                        <s:hidden name="image" value="%{image}"/>
                                        <s:hidden name="price" value="%{price}" />
                                        <s:hidden name="color" value="%{color}" />
                                        <s:hidden name="category" value="%{category}"/>
                                        <s:hidden name="quantity" value="%{quantity}"/>
                                        <s:hidden name="status" value="%{status}"/>
                                        <input type="submit" value="Update" class="btn btn-warning mr-3" style="width: 100px"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:iterator>
            </div>
        </div>
    </body>
</html>
