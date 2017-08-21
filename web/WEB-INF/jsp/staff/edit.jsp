<%-- 
    Document   : index
    Created on : Jun 4, 2017, 2:08:25 PM
    Author     : Thoai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
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
            <a href="${pageContext.servletContext.contextPath}/record/reportNV.htm">Quản Lý Report</a>
        
        <h1>Update Staff!</h1>
        ${message}
        <form:form action="${pageContext.servletContext.contextPath}/staff/update/.htm" modelAttribute="staff" method="post">
            <div>
                <label>Mã NV</label>
                <form:input path="staId"/>
            </div>
            <div>
                <label>Họ Tên</label>
                <form:input path="name"/>
            </div>
            <div>
                <label>Giới Tính</label>
                <form:radiobutton path="gender" value="true" label="Nam"/>
                <form:radiobutton path="gender" value="false" label="Nữ"/>
            </div>
            <div>
                <label>Ngày Sinh</label>
                <form:input path="birthday"/>
            </div>
            <div>
                <label>Photo</label>
                <form:input path="photo"/>
            </div>
            <div>
                <label>Email</label>
                <form:input path="email"/>
            </div>
            <div>
                <label>Phone</label>
                <form:input path="phone"/>
            </div>
            <div>
                <label>Salary</label>
                <form:input path="salary"/>
            </div>
            <div>
                <label>Notes</label>
                <form:input path="notes"/>
            </div>
            <div>
                <label>Phòng</label>
                <form:select path="depart.depId" items="${departs}" itemValue="depId" itemLabel="name"/>
              <!-- path = depart.id là đối tượng JoinColumn trong Đối tượng staff -->
            </div>
            <div>
                <button class="btn btn-default">Update</button>
            </div>
        </form:form>
    </body>
</html>
