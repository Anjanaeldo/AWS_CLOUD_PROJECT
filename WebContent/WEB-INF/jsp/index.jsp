<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href=”bootstrap/css/bootstrap.min.css” rel=”stylesheet” type=”text/css” />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta/css/bootstrap.css" rel="stylesheet">
<link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<script type=”text/javascript” src=”bootstrap/js/bootstrap.min.js”></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AWS S3 File Upload Using Java</title>
</head>
<body>
<div class="container-fluid">
        <div class="panel panel-success">
            <div class="panel-heading" align="center">
             <h4><b><font color="Orange" style="font-family: fantasy;">Welcome ${name}</font> </b></h4>
             <img src="file:///Users/andrew/Downloads/200px-AWS_Simple_Icons_Compute_Amazon_EC2_Instances.svg.png"/>
            </div>
             <div class="panel-body"align="center">
                  
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
    
                    <div class="panel panel-success" style="max-width: 35%;" align="left">

<form action="uploadFilePage.do" method="post" enctype="multipart/form-data">
<div class=”form-group”>
<button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" >
<b><font color="Orange">Upload</font></b></button>
</div>
</form>
<br>
<form action="showFiles.do" method="post" enctype="multipart/form-data">
<div class=”form-group”>
<button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" >
<b><font color="Orange">Read/List Files</font></b></button>
</div>
</form>
<br>
<form action="deleteFileNav.do" method="post" enctype="multipart/form-data">
<div class=”form-group”>
<button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" >
<b><font color="Orange">Delete</font></b></button>
</div>
</form>
<br>
<form action="updatePage.do" method="post" enctype="multipart/form-data">
<div class=”form-group”>
<button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" >
<b><font color="Orange">Update</font></b></button>
</div>
</form>
</div>
</div>
                    
</div>
                
</div>
</div>
</body>
</html>
