<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FlowerShop Login</title>
</head>
    <body>
        <div style="text-align: center">
        <h2>FlowerShop</h2>
        <h1>User Login</h1>
        <form action="login" method="post">
            <label for="loginl">Login:</label>
            <input name="username" id ="username" size="30" value="Admin"/>
            <br>
            <br>
            <label for="passwordl">Password:</label>
            <input name="password" size="30" value="Admin"/>
            <br>
            <label for="message"></label>
            <br>
            <button type="submit" name="loginsubmit">Login</button><button type="submit" id="regsubmit" name="regsubmit">Register</button>
        </form>
        </div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript">
$("#username").change(function(){
    $.ajax({
        url: "/rest/check_login/"+$("$username").val(),
        success: function(data){
            if(data == "false")
            {
            $("#regsubmit").removeAttr("disabled");
            alert("re");
            }
            else
            {
            $("#regsubmit").attr("disabled", "true");
            }
        }
    });
    });
</script>
</body>
</html>