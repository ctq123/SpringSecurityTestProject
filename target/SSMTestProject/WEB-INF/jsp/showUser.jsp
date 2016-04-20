<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>测试</title>
	</head>
	<body>
	   <table>
	   <thead>
		   <tr>
			   <th colspan="2">个人信息</th>
		   </tr>
	   </thead>
	   <tbody>
		   <tr>
			   <td>姓名：</td>
			   <td>${user.username}</td>
		   </tr>
		   <tr>
			   <td>年龄：</td>
			   <td>${user.age}</td>
		   </tr>
		   <tr>
			   <td>url：</td>
			   <td>http://10.45.25.166/SSMTestProject/user/showUser?id=2</td>
		   </tr>
	   </tbody>
	   </table>
	</body>
</html>