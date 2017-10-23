<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href=”bootstrap/css/bootstrap.min.css” rel=”stylesheet” type=”text/css” />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta/css/bootstrap.css" rel="stylesheet">
<link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<script type=”text/javascript” src=”bootstrap/js/bootstrap.min.js”></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
  <h2>All Files in S3</h2>
  <div class="table-responsive">
<form action="deleteFiles.do" method="post">
<table  id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
<thead>
   <tr>
        <th>FileName</th>
        <th>Size</th>
        <th>Last_Modified_Date</th>
        <th>Delete Link</th>
      </tr>
      </thead>
       <tbody>
<c:forEach var="s3" items="${fileDetailscontrl}">
            <tr>
                <td>${s3.fileName}</td>
                <td>${s3.size}</td>
                <td>${s3.lastModifiedDate}</td>
              <td><button type="submit" name="fileName" value="${s3.fileName}">Delete</button></td>
            </tr> 
            <tr>
            </tr>      
        </c:forEach>
</tbody>
</table>
</form>
<form action="index.do" method="post">
<table>
<tr></tr>
<tr></tr>
<tr><td></td><td><button type="submit">Go to Index page</button></td> 
</tr>

</table>
</form>
</div></div>
</body>
</html>