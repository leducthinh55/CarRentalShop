<%-- 
    Document   : detailPage
    Created on : Mar 14, 2020, 7:11:03 PM
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
        <script src="./js/myjs.js"></script>
        <s:include value="header.jsp"/>
        <div class="container">
            <h1 style="text-align: center"><s:property value="carDTO.carName"/></h1>
            </br>
            <div class="row">
                <div class="col-6">
                    <img src="<s:property value="carDTO.image"/>" width="500"/>
                </div>
                <div class="col-6">
                    </br>
                    </br>
                    </br>
                    </br>
                    <p class="">Category : <s:property value="carDTO.category"/></p>
                    <p class="">Color : <s:property value="carDTO.color"/></p>
                    <p class="">Price : <s:property value="carDTO.price"/> $</p>
                </div>
            </div>
            <div class="col-12">
                <s:if test="%{#session.USER.role == 'client'}">
                    <form action="sendFeedback" method="POST" class="form-group" onsubmit="return onFeedback()" name="sendFeedback">
                        <s:hidden name="carIdFeedback" value="%{carIdDetail}"/>
                        <s:hidden name="userFeedback" value="%{#session.USER.email}"/>
                        Rating: <input type="number" name="rating" value="" style="width: 100px" class="form-control" min="1" max="10" placeholder="1 to 10" /> </br>
                        Comment : <textarea type="text" name="description" value=""  rows="4" cols="100" class="form-control"></textarea> </br>
                        <input type="submit" value="Send Feedback" />
                    </form>
                </s:if>
                <s:else>
                    <p style="color: red">You need to login like a client to Feed back</p>
                </s:else>
                <s:iterator var="dtoFeedback" value="%{listFeedbacks}">
                    <p><s:property value="email"/> at <s:property value="timeFeedback"/></p><br/>
                    <s:if test="%{rating != 0}">
                        <p>Rating : <s:property value="rating"/>/10</p><br/>
                    </s:if> 
                    <p>Comment: <s:property value="description"/></p><br/>
                    <br>
                </s:iterator>
            </div>
        </div>
    </body>
</html>
