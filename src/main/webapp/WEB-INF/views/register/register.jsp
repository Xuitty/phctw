<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>學生資料管理系統</title>
</head>
<body id="body">
<h2 id="result" style="color: red;size: 20px;"></h2>
	
	<div><br><br>
		<form action="register" method="post">
			學號(帳號):&nbsp;<input type="text" name="sno" id="sno">
			
			<br> <br>
			密碼:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				type="password" name="spwd" id="spwd"><br>
			<br> 姓名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				type="text" name="sname" id="sname"><br>
			<br> 生日:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				name="sbday" id="sbday"> (yyyy-mm-dd)<br>
			<br> 性別:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				type="radio" name="ssex" value="1">男<input type="radio"
				name="ssex" value="0">女<br> <br>
			Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
				name="smail" id="smail"><br> <br>
			身分證字號:&nbsp;<input type="text" name="sid" id="sid"><br> <br> <br> <br>
			<div id="submit" style="display: none">
				<input type="hidden" name="buttonAction" value="register"> <input id="submit"
					type="submit" value="送出">
			</div>


		</form>
		<!--<img src="js/watame-hololive.gif" alt="null"></img>-->
	</div>
	
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="js/script.js"></script>

	

</body>
</html>