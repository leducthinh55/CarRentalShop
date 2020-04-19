<%-- 
    Document   : homePage
    Created on : Mar 8, 2020, 1:56:22 PM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script src="./js/myjs.js"></script>
        <s:include value="header.jsp"/>
        <div class="container">
            <div class="col-12 mt-3">
                <form action="search" class="form-inline" onsubmit="return onSubmitSearchForm()" name="searchForm">
                    <input class="form-control form-control mr-3 w-20" type="text" placeholder="Car Name"
                           aria-label="Search" name="carNameSearch" value="${param.carNameSearch}">
                    <select name="categorySearch" class="form-control mr-3">
                        <option value="all">All</option>
                        <s:iterator value="%{#application.LIST_CATEGORY}">
                            <option value="<s:property/>"><s:property/></option>
                        </s:iterator>
                    </select>

                    <input type="date" name="dateFrom" value="${param.dateFrom}" class="form-control mr-3" />
                    <input type="date" name="dateTo" value="${param.dateTo}" class="form-control mr-3"/>
                    <input type="number" name="amountSearch" class="form-control mr-3" 
                           <c:if test="${not empty param.amountSearch}">value="${param.amountSearch}"</c:if>
                           <c:if test="${empty param.amountSearch}">value="1"</c:if>
                               min="1" max="100" style="width: 5em"/>

                           <input type="submit" class="btn btn-info" value="Search" />
                    </form>
                </div>
                <div class="row">
                <s:set var="listCar" value="%{#application.LIST_CAR}"/>
                <s:if test="%{listSearch != null}">
                    <s:set var="listCar" value="%{listSearch}"/>
                </s:if>
                <form action="getCart">
                    <input type="date" name="dateFrom" value="" />
                    <input type="date" name="dateTo" value="" />
                    <input type="submit" value="get" />
                </form>
                <s:iterator var="car" value="%{listCar}">
                    <div class="col-4 mt-4 mb-2">
                        <div class="card p-1">
                            <img src="<s:property value="image"/>" class="card-img-top" height="240"/>
                            <div class="card-body">
                                <div class="card-title ct">
                                    <s:property value="carName"/>
                                </div>
                                <p class="card-text ct">Category : <s:property value="category"/></p>
                                <p class="card-text ct">Price : <s:property value="price"/></p>
                                <div class="row justify-content-center">
                                    <c:if test="${sessionScope.USER.role == 'client'}">
                                        <form action="addToCart" method="GET">
                                            <s:hidden name="carId" value="%{carId}" />
                                            <s:hidden name="carName" value="%{carName}"/>
                                            <s:hidden name="price" value="%{price}" />
                                            <s:hidden name="color" value="%{color}" />
                                            <s:hidden name="category" value="%{category}" />                                                                                
                                            <input type="hidden" name="dateFrom" value="${param.dateFrom}" />
                                            <input type="hidden" name="dateTo" value="${param.dateTo}" />
                                            <input type="hidden" name="carNameSearch" value="${param.carNameSearch}" />
                                            <input type="hidden" name="amountSearch" 
                                                   <c:if test="${not empty param.amountSearch}">value="${param.amountSearch}"</c:if>
                                                   <c:if test="${empty param.amountSearch}">value="1"</c:if>
                                                       />
                                                   <input type="hidden" name="categorySearch" value="${param.categorySearch}" />
                                            <input type="submit" value="Rent" class="btn btn-success mr-3" style="width: 100px"/>
                                        </form>
                                    </c:if>
                                    <form action="detailCar">
                                        <s:hidden name="carIdDetail" value="%{carId}" />
                                        <input type="submit" value="Detail" class="btn btn-info" style="width: 100px"/>
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
