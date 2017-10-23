<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href=”bootstrap/css/bootstrap.min.css” rel=”stylesheet” type=”text/css” />
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type=”text/javascript” src=”bootstrap/js/bootstrap.min.js”></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN</title>
</head>
<body>
<div class="container-fluid">
        <div class="panel panel-success">
            <div class="panel-heading" align="center">
             <h4><b><font color="Blue" style="font-family: fantasy;">Login to your Account</font> </b></h4>
            </div>
              <div class="panel-body"align="center">
                  
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
    
                    <div class="panel panel-success" style="max-width: 35%;" align="left">
                        
                        <div class="panel-heading form-group">
                            <b><font color="white">
                                Login Form</font> </b>
                        </div>
                    
                        <div class="panel-body" >
<form action="login.do" method="post" >
<div class=”form-group”>

<label for=”exampleInputEmail1″><font color="Blue">Username</font></label>
<input type="text" name="name" class=”form-control” placeholder=”EnterName">
</div>
<div class=”form-group”>
<label for=”exampleInputPassword1″><font color="Blue">Password</font></label>&nbsp;
<input type="password" class=”form-control”  name="password" placeholder=”Password”>
</div>
 <button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" ><b><font color="Blue">Login</font></b></button>
                                                   


<div class=”form-group”>${message}</div>
</form>
   </div>
                    </div>
                    
                </div>
                
            </div>
</div></div>
</body>
</html>