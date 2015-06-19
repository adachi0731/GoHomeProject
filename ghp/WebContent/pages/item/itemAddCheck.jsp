<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>商品情報メンテナンス</h2>

		以下の内容で商品情報を登録します。<br> 登録内容を確認の上、「登録」ボタンを押してください。<br> <br>

	<form action="/ghp/controller" method="post">


			<table border="1">
				<tr>
					<th>商品番号</th>
					<td><input type="hidden" name="itemNo" value="${No}">${No}</td>
				</tr>
				<tr>
					<th>商品名</th>
					<td><input type="hidden" name="itemName" value="${Name}">${Name}</td>
				</tr>
				<tr>
					<th>商品画像</th>
					<td><input type="hidden" name="URL" value="${Url}">${Url}</td>
				</tr>
				<tr>
					<th>単価</th>
					<td><input type="hidden" name="unitPrice" value="${Price}">${Price}</td>
				</tr>
				<tr>
					<th>寸法</th>
					<td><input type="hidden" name="size" value="${Size}">${Size}</td>
				</tr>
				<tr>
					<th>種別</th>
					<td><input type="hidden" name="assortment" value="${Assortment}">${Assortment}</td>
				</tr>
				<tr>
					<th>カテゴリー</th>
					<td><input type="hidden" name="category" value="${Category}">${Category}</td>
				</tr>
			</table>

			<br> <input type="submit" value="登録">
<input type="hidden" name="action" value="item.Insert">
		</form>
		<form action="/ghp/pages/item/itemAdd.jsp">
			<input type="submit"  value="戻る">

		</form>

	</center>
</body>

</html>