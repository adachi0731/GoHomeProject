<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>商品情報メンテナンス</h2>

		商品情報を更新します。<br> 更新内容を入力して、「送信」ボタンを押してください。<br> <br>

		<form name="myForm" >

			<table border="1">
				<tr>
					<th>商品番号</th>
					<td>${itemSelect.itemNo }</td>
				</tr>
				<tr>
					<th>商品名</th>
					<td><input type="text" value=></td>
				</tr>
				<tr>
					<th>商品画像(URL)</th>
					<td><input type="text" value="/image/000366.jpg" size="40"></td>
				</tr>
				<tr>
					<th>単価</th>
					<td><input type="text" value="9800"></td>
				</tr>
				<tr>
					<th>寸法</th>
					<td><input type="text" value="90x60x50"></td>
				</tr>
				<tr>
					<th>種別</th>
					<td><select name="shubetsu">
							<option value="0">花束</option>
							<option value="1">鉢植</option>
							<option value="2">アレンジメント</option>
					</select></td>
				</tr>
				<tr>
					<th>カテゴリー</th>
					<td><select name="category">
							<option value="0">Red</option>
							<option value="1">Yellow</option>
							<option value="2">Blue</option>
					</select></td>
				</tr>
			</table>

			<br> <input type="submit" name="submit" value="送信"
				onclick="exec(0);"> <input type="submit" name="submit"
				value="戻る" onclick="exec(1);">

		</form>

	</center>
</body>

</html>