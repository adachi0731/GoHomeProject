<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var url = new Array("item_maint.html", "item_maint_update.html");
	function exec(num) {
		document.myForm.action = url[num];
	}
</script>
</head>
<body>
	<center>
		<h2>商品情報メンテナンス</h2>

		以下の内容で商品情報を更新します。<br> 更新内容を確認の上、「更新」ボタンを押してください。<br> <br>

		<form name="myForm">

			<table border="1">
				<tr>
					<th>商品番号</th>
					<td>0001</td>
				</tr>
				<tr>
					<th>商品名</th>
					<td>カーネーション</td>
				</tr>
				<tr>
					<th>商品画像</th>
					<td><img src="../image/r07_1.jpg"></td>
				</tr>
				<tr>
					<th>単価</th>
					<td>\9,800-</td>
				</tr>
				<tr>
					<th>寸法</th>
					<td>90x60x50cm</td>
				</tr>
				<tr>
					<th>種別</th>
					<td>花束</td>
				</tr>
				<tr>
					<th>カテゴリー</th>
					<td>Red</td>
				</tr>
			</table>

			<br> <input type="submit" name="submit" value="更新"
				onclick="exec(0);"> <input type="submit" name="submit"
				value="戻る" onclick="exec(1);">

		</form>

	</center>
</body>

</html>