<%-- 
    Document   : index
    Created on : Jun 4, 2017, 2:08:25 PM
    Author     : Thoai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <style>
            a {
                text-decoration : none;
            }
            table {
                width : 100%;
                border-collapse: collapse;
            }

            table tr th {
                background-color : #007ba7;
                color : #FFF;
                padding : 10px 0px;
            }

            table tr td {
                text-align : center;
                padding : 10px;
            }

            table tbody tr {
                border-bottom : 1px solid #4ea248;
            }

            table tr td a.btn-edit {
                background-color : #20b2aa;
                color : #FFF;
                padding : 5px 10px;
            }

            table tr td a.btn-del {
                background-color : #d43710;
                color : #FFF;
                padding : 5px 10px;
            }
            a.btn-del:hover,a.btn-edit:hover {
                opacity : 0.7;
            }
        </style>
    </head>
    <body>
         <jsp:include page="header.jsp"></jsp:include>

       <a href="${pageContext.servletContext.contextPath}/user/index.htm">Quản Lý User</a>
            |
            <a href="${pageContext.servletContext.contextPath}/staff/index.htm">Quản Lý Staff</a>
            |
            <a href="${pageContext.servletContext.contextPath}/depart/index.htm">Quản Lý Depart</a>
            |
            <a href="${pageContext.servletContext.contextPath}/record/index.htm">Quản Lý Report</a>
            |
         <a href="${pageContext.servletContext.contextPath}/user/login.htm">Quản Lý Đăng Nhập</a>
        
        <h1>Danh Sách Department</h1>
        <h2><a href="${pageContext.servletContext.contextPath}/depart/insert.htm">Thêm Depart</a></h2>
        <table class="table table-hover" width="100%" cellpadding="10" border="0" style="text-align: center;">
            <tr>
                <th>Department ID</th>
                <th>Department Name</th>
                <th>Option</th>
            </tr>
            <c:forEach var="depart" items="${departs}">
                <tr class="border-bottom">
                    <td>${depart.depId}</td>
                    <td>${depart.name}</td>
                    <td colspan="2">
                        <a href="${pageContext.servletContext.contextPath}/depart/edit/${depart.depId}.htm">Edit</a> - 
                        <a href="${pageContext.servletContext.contextPath}/depart/delete/${depart.depId}.htm">Delete</a>
                    </td>
                </tr>
            </c:forEach>
                
        </table>
         <jsp:include page="foder.jsp"></jsp:include>
    </body>
</html>
