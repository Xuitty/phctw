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
		<div>
			<h3>${message}</h3><br><br><br><br>
			學號:　${sno}　　<br><br>
			姓名:　${sname}<br><br>
			生日:　${sbday}　(yyyy-mm-dd)<br><br>
			性別:　${ssex}<br><br>
			E-mail:　${smail}<br><br>
			身分證字號:　${sid}<br><br><br><br>
			<form action="login"><input name="sno" type="hidden" value="${sno}"><input name="buttonAction" type="hidden" value="mainResetPassword"><input value="重設密碼" type="submit"></form><br><br>
			<input type="button" value="登出" onclick="location.href='/spring/logout';">
		</div>
	</center>
</body>
</html>