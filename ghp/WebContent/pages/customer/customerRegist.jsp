<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<img src="/ghp/image/fullneflower_logo.png"><br>
		<h2>FullneFlower 商品発注システム</h2>
		<hr>
		<p>
			システム利用登録を行います。<br> 下記の情報を入力して、送信ボタンを押してください。
		</p>
		<form action="regist_confirm.html" target="_top">
			<table>
				<tr>
					<th colspan="2">会社情報</th>
				</tr>
				<tr>
					<th>会社名</th>
					<td><input type="text" name="companyName" size="30"></td>
				</tr>
				<tr>
					<th>住所</th>
					<td><input type="text" name="companyAddress" size="50"></td>
				</tr>
				<tr>
					<th>電話番号</th>
					<td><input type="text" name="companyTelNo" size="20"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th colspan="2">ご担当者様情報</th>
				</tr>
				<tr>
					<th>氏名（漢字）</th>
					<td><input type="text" name="tantoshaName" size="30"></td>
				</tr>
				<tr>
				<tr>
					<th>氏名（かな）</th>
					<td><input type="text" name="tantoshaNameKana" size="30"></td>
				</tr>
				<tr>
					<th>部署</th>
					<td><input type="text" name="tantoshaDept" size="20"></td>
				</tr>
				<tr>
					<th>メールアドレス</th>
					<td><input type="text" name="tantoshaEmail" size="50"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th colspan="2">認証用情報</th>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="password" size="20"></td>
				</tr>
				<tr>
					<th>パスワード（確認）</th>
					<td><input type="password" name="password_confirm" size="20"></td>
				</tr>
			</table>

			<br> <input type="submit" value="送信"> <input
				type="reset" value="クリア">
		</form>

	</center>

</body>
</html>