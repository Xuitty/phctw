<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>學生資料管理系統</title>
</head>
<body>

	<center>
	<h2 style="color: red">${message}</h2>
		<div>
			<form action="login" method="post">
				帳號: <input type="text" name="sno"><br> <br> E-mail: <input
					type="text" name="smail"><br> <br> <input
					type="hidden" name="buttonAction" value="forgetPassword"> <input
					type="submit" value="送出"><br> <br>
			</form>

		</div>
	</center>
</body>
</html>