<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>學生資料管理系統</title>
</head>
<body>
	<center>
		<h2>重發驗證信請填帳號信箱<br><br>收信後填入帳號跟驗證碼</h2>
		<br>
		<div>
			<form action="register" method="post">
				<br> 帳號(學號):<input type="text" name="sno">　*必填 <br> <br>
				新的信箱:<input type="text" name="smail">　*重傳必填<br> <br>
				驗證碼:<input type="text" name="verify">　*驗證必填<br> <br> <input
					type="submit" name="buttonAction" value="verify">
					　　 <input
					type="submit" name="buttonAction" value="resend"><br>
				<br> <br> <br> ${message}
			</form>
		</div>
	</center>
</body>
</html>