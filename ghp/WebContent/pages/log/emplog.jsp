
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page session = "false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script language="JavaScript">
  <!--

   //-->
  </script>
<title>Insert title here</title>
</head>
<body>
<center>
		<img src="/ghp/image/fullneflower_logo.png"><br>
		<h2>FullneFlower 受発注管理システム</h2>
		<hr>
		<p>社員番号とパスワードを入力してください。</p>
		${erro}
		<form action="/ghp/controller" method="post">
			<table>
				<tr>
					<td>社員番号</td>
					<td><input type="text" name="empno"></td>
				</tr>
				<tr>
					<td>パスワードあああああ</td>
					<td><input type="password" name="passwd"></td>
				</tr>
			</table>

			<br> <input type="submit" value="ログイン" onClick="javascript:double(this)"> <input
				type="reset" value="クリア">
				<input type="hidden" name="action" value="employee.login">
		</form>

		<br>
	</center>
</body>
</html>