<%-- 
    Document   : createNewCar
    Created on : Mar 12, 2020, 2:42:48 PM
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
        <s:if test="%{session.USER.role != 'admin'}">
            <jsp:forward page="login.jsp"/>
        </s:if>
        <s:include value="header.jsp"/>
        <script src="./js/myjs.js"></script>
        <div class="container border" style="width: 35%">
            
            <form action="createNewCar" method="POST" onsubmit="return onSubmitCreateCarForm();" name="creteaNewCar">
                <h1 style="text-align: center">Create New Car</h1>
                <div class="form-group">
                    <span>Name </span>
                    <input type="text" name="carName" value="${param.carName}" class="form-control"/> (6-50 char)
                    <p id="carNameId" style="color: red"></p>
                    <p style="color: red"><s:property value="error"/></p>
                </div>

                <div class="form-group">
                    <span>Image </span>
                    <input type="file" name="image" value="${param.image}" accept="image/x-png,image/gif,image/jpeg" class="form-control"/>
                    <p id="imageId" style="color: red" ></p>
                </div>
                
                <div class="form-group">
                    <span>Category :</span>
                    <select name="category">
                        <s:iterator value="%{#application.LIST_CATEGORY}">
                            <option value="<s:property/>"><s:property/></option>
                        </s:iterator>
                    </select>
                    <p id="categoryId" style="color: red"></p>
                </div>
                    
                <div class="form-group">
                    <span>Color </span>
                    <input type="text" name="color" value="${param.color}" class="form-control"/>
                    <p id="colorId" style="color: red"></p>
                </div>
                    
                <div class="form-group">
                    <span>Price </span>
                    <input type="text" name="price" value="${param.price}" class="form-control" min="0" max="1000"/>
                    <p id="priceId" style="color: red"></p>
                </div>
                
                <div class="form-group">
                    <span>Quantity </span>
                    <input type="number" name="quantity" value="${param.quantity}" class="form-control" min="1" max="1000"/>
                    <p id="quantityId" style="color: red"></p>
                </div>
                <div class="row justify-content-center">
                    <input type="submit" value="Create New Car" class="btn btn-success mr-5"/>
                    <input type="reset" value="Reset" class="btn btn-warning"/>
                </div>
            </form>
        </div>
    </body>
</html>
