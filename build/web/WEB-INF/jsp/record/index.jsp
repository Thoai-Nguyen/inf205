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
            <a href="${pageContext.servletContext.contextPath}/record/reportNV.htm">Quản Lý Report</a>
            |
         <a href="${pageContext.servletContext.contextPath}/user/login.htm">Quản Lý Đăng Nhập</a>
        
        <h1>Danh Sách User</h1>
        <h2><a href="${pageContext.servletContext.contextPath}/record/insert.htm">Thêm Record</a></h2>
        <table class="table table-hover" width="100%" cellpadding="10" border="0" style="text-align: center;">
            <tr>
                <th>RecID</th>
                <th>Type</th>
                <th>Reason</th>
                <th>Date</th>
            </tr>
            <c:forEach var="user" items="${arrays}">
                <tr class="border-bottom">
                    <td>${arrays.reID}</td>
                    <td>${arrays.type}</td>
                    <td>${arrays.reason}</td>
                    <td>${arrays.date}</td>
                    
                    <td colspan="2">
                        <a href="${pageContext.servletContext.contextPath}/record/edit/${arrays.username}.htm">Edit</a> - 
                        <a href="${pageContext.servletContext.contextPath}/record/insert/${arrays.username}.htm">Insert</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
         <jsp:include page="foder.jsp"></jsp:include>
    </body>
</html>
