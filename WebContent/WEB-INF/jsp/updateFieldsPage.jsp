<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div class="container-fluid">
        <div class="panel panel-success">
            <div class="panel-heading" align="center">
             <h4><b><font color="Orange" style="font-family: fantasy;">Update File</font> </b></h4>
            </div>
             <div class="panel-body"align="center">
                  
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
    
                    <div class="panel panel-success" style="max-width: 35%;" align="left">
<form action="updateFileS3.do" method="post">
<div class=”form-group”>
<label for="fileToUpdate"><font color="Orange">Update changes for the file</font></label>
<input type="text" size="50px" name = "fileName" value="${fileName}" class=”form-control”/>
</div>
<div class=”form-group”>
<label for="firstName"><font color="Orange">Enter First Name</font></label>
<input type="text" name="firstName" class=”form-control” placeholder=”EnterName">
</div>
<div class=”form-group”>
<label for="firstName"><font color="Orange">Enter Last Name</font></label>
<input type="text" name="lastName" class=”form-control” placeholder=”EnterName">
</div>
<div class=”form-group”>
<label for="firstName"><font color="Orange">Description</font></label>
<input type="text" name="desc" class=”form-control” placeholder=”EnterName">
</div>
<br>
<button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" ><b><font color="Orange">Update</font></b></button>
</form>
</div>
</div>
</div>
</div>
</div>
</body>
</html>