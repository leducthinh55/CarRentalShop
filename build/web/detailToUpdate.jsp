<%-- 
    Document   : detailToUpdate
    Created on : Mar 12, 2020, 9:55:03 PM
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
        <s:if test="%{session.USER.role != 'admin'}">
            <jsp:forward page="login.jsp"/>
        </s:if>
        <s:include value="header.jsp"/>
        <script src="./js/myjs.js"></script>
        <div class="container border" style="width: 35%">
            
            <form action="updateCar" method="POST" onsubmit="return onSubmitUpdateCarForm();" name="updateCar">
                <h1 style="text-align: center">Update Car</h1>
                <s:hidden name="carId" value="%{carId}"/>
                <div class="form-group">
                    <span>Name </span>
                    <input type="text" name="carName" 
                           <s:if test="%{carName != null}">
                               value="<s:property value="carName"/>" 
                           </s:if>
                           <s:if test="%{carName == null}">
                               value="${param.carName}" 
                           </s:if>
                           class="form-control"/> (6-50 char)
                    <p id="carNameId" style="color: red"></p>
                    <p style="color: red"><s:property value="error"/></p>
                </div>

                <div class="form-group">
                    <span>Image </span>
                    <input type="file" name="image" value="" accept="image/x-png,image/gif,image/jpeg" class="form-control"/>
                    <input type="hidden" name="imageHidden" value="<s:property value="%{image}"/>"/>
                    <p id="imageId" style="color: red" ></p>
                </div>
                
                <div class="form-group">
                    <s:select label="Category" list="%{#application.LIST_CATEGORY}" name="category" value="%{category}"/>
                    <p id="categoryId" style="color: red"></p>
                </div>
                    
                <div class="form-group">
                    <span>Color </span>
                    <input type="text" name="color" 
                           <c:if test="${not empty param.color}">value="${param.color}"</c:if>
                           <c:if test="${empty param.color}">value="%{color}"</c:if>
                            class="form-control"/>
                    <p id="colorId" style="color: red"></p>
                </div>
                            
                <div class="form-group">
                    <span>Price </span>
                    <input type="text" name="price" 
                           <c:if test="${not empty param.price}">value="${param.price}"</c:if>
                           <c:if test="${empty param.price}">value="%{price}"</c:if>
                           class="form-control"/>
                    <p id="priceId" style="color: red"></p>
                </div>
                
                <div class="form-group">
                    <span>Quantity </span>
                    <input type="number" name="quantity" 
                           <c:if test="${not empty param.quantity}">value="${param.quantity}"</c:if>
                           <c:if test="${empty param.quantity}">value="%{quantity}"</c:if>
                           class="form-control" min="1" max="1000"/>
                    <p id="quantityId" style="color: red"></p>
                </div>
                           
                <div class="form-group">
                    <label>Status: </label>
                    <select name="status" class="form-control">
                            <option value="active" <c:if test="${not empty param.status}">
                                                        <c:if test="${param.status eq 'active'}">
                                                             selected
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${empty param.status}">
                                                        <s:if test="%{status == 'active'}">
                                                             selected
                                                        </s:if>
                                                    </c:if>>
                                Active
                            </option>
                            <option value="inactive"<c:if test="${not empty param.status}">
                                                        <c:if test="${param.status eq 'inactive'}">
                                                             selected
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${empty param.status}">
                                                        <s:if test="%{status == 'inactive'}">
                                                             selected
                                                        </s:if>
                                                    </c:if>>
                                In Active
                            </option>
                    </select>
                </div>
                           
                <div class="row justify-content-center">
                    <input type="submit" value="Update Car" class="btn btn-success mr-5"/>
                    <input type="reset" value="Reset" class="btn btn-warning"/>
                </div>
            </form>
        </div>
    </body>
</html>
