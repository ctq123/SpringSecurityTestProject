<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Login Page</h1>
		<div id="login-error">${error}</div>
		<form action="../j_spring_security_check" method="post">
			<p>
				<label for="j_username">Username</label>
				<input id="j_usrname" name="j_username" type="text"/>
			</p>
			
			<p>
				<label for="j_password">Password</label>
				<input id="j_password" name="j_password" type="password"/>
			</p>
			
			<input type="submit" value="Login"/>
			
		</form>
		
		<div>url：<span>http://10.45.25.166/SpringSecurityTestProject/auth/login</span></div>
	</body>
</html>