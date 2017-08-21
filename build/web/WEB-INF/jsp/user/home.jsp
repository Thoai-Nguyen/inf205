<%-- 
    Document   : home
    Created on : Jun 11, 2017, 7:54:03 PM
    Author     : Thoai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
    <li><a data-toggle="tab" href="${pageContext.servletContext.contextPath}/user/index.htm">Quản Lý User</a></li>
    <li><a data-toggle="tab" href="${pageContext.servletContext.contextPath}/staff/index.htm">Quản Lý Staff</a></li>
    <li><a data-toggle="tab" href="${pageContext.servletContext.contextPath}/depart/index.htm">Quản Lý Depart</a></li>
    <li><a data-toggle="tab" href="${pageContext.servletContext.contextPath}/record/index.htm">Quản Lý Report</a></li>
  </ul>

   <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      
      
    </div>
    <div id="menu1" class="tab-pane fade">
      
     
    </div>
    <div id="menu2" class="tab-pane fade">
      
     
    </div>
    <div id="menu3" class="tab-pane fade">
      
     
    </div>
  </div>
</div>
</body>
</html>

