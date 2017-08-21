<%-- 
    Document   : index
    Created on : Jun 4, 2017, 2:08:25 PM
    Author     : Thoai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       <style type="text/css">
            ul {
                list-style-type: none;
                text-align: center;
            }
            li {
                display: inline-block;
            }
            
            li a {
                text-decoration: none;
                display: block;
                width: 180px;
                padding: 10px;
                background-color: #66cccc;
                color: black;
            }
        </style>
    </head>
    <body>
         <a href="${pageContext.servletContext.contextPath}/user/index.htm">Quản Lý User</a>
            |
            <a href="${pageContext.servletContext.contextPath}/staff/index.htm">Quản Lý Staff</a>
            |
            <a href="${pageContext.servletContext.contextPath}/depart/index.htm">Quản Lý Depart</a>
            |
            <a href="${pageContext.servletContext.contextPath}/record/index.htm">Quản Lý Report</a>
        
        <h1>Thêm mới User</h1>
        <form:form action="${pageContext.servletContext.contextPath}/user/insert/.htm" modelAttribute="user" method="post">
            <div>
                <label>Username : </label>
                <form:input path="username"/>
            </div>
            <div>
                <label>Password : </label>
                <form:input path="password"/>
            </div>
            <div>
                <label>Fullname : </label>
                <form:input path="fullname"/>
            </div>
            <div>
                <button class="btn btn-default">Insert</button>
            </div>
        </form:form>

    </body>
</html>


