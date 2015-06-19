<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page session = "false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h2>システムエラーが発生しました</h2>
	システムエラーが発生しました。
	<br> 現在の操作を中断して、再度ログインして処理を行ってください。
	<br> 状況が改善されない場合は、大変お手数ですが、弊社情報システム部 までお問い合わせください。
	<br>
	<form action="/ghp/pages/log/error.jsp" method="post">
	<input type="submit" value="ログイン">
	<input type="hidden" name="action" value="employee.login">
	</form>
	</center>
</body>
</html>