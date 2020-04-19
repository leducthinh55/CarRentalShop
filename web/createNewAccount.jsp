<%-- 
    Document   : createNewAccount
    Created on : Mar 8, 2020, 8:53:23 PM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Create New Account</title>
    </head>
    <body>
        <s:include value="header.jsp"/>
        <script src="./js/myjs.js"></script>
        <div class="container border" style="width: 35%">
            
            <form action="createNewAccount" method="POST" onsubmit="return onSubmitCreateAccountForm();" name="signUpForm">
                <h1 style="text-align: center">Create New Account</h1>
                <div class="form-group">
                    <span>Username </span>
                    <input type="text" name="email" value="${param.email}" class="form-control"/> (6-50 char)
                    <p id="emailId" style="color: red"></p>
                    <p style="color: red"><s:property value="error"/></p>
                </div>

                <div class="form-group">
                    <span>Password</span>
                    <input type="password" name="password" value="" class="form-control"/> (6-20 char)
                    <p id="passwordId" style="color: red"></p>
                </div>


                <div class="form-group">
                    <span>Confirm </span>
                    <input type="password" name="confirm" value="" class="form-control"/>
                    <p id="confirmId" style="color: red" ></p>
                </div>
                
                <div class="form-group">
                    <span>Full Name :</span>
                    <input type="text" name="name" value="${param.name}" class="form-control"/> (2-50 char)
                    <p id="nameId" style="color: red"></p>
                </div>
                
                <div class="form-group">
                    <span>Phone </span>
                    <input type="text" name="phone" value="${param.phone}" class="form-control"/>
                    <p id="phoneId" style="color: red"></p>
                </div>
                
                <div class="form-group">
                    <span>Address </span>
                    <input type="text" name="address" value="${param.address}" class="form-control"/>
                    <p id="addressId" style="color: red"></p>
                </div>
                <div class="row justify-content-center">
                    <input type="submit" value="Create New Account" class="btn btn-success mr-5"/>
                    <input type="reset" value="Reset" class="btn btn-warning"/>
                </div>
            </form>
        </div>
    </body>
</html>
