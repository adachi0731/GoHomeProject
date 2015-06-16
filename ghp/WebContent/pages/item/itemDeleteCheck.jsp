<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var url = new Array("item_maint.html", "item_maint.html");
	function exec(num) {
		document.myForm.action = url[num];
	}
</script>
</head>
<body>
	<center>
		<h2>商品情報メンテナンス</h2>

		下記の商品情報を削除します。<br> 内容を確認の上、「削除」ボタンを押してください。<br> <br>

		<form name="myForm" >

			<table border="1">
				<tr>
					<th>商品番号</th>
					<th>商品名</th>
					<th>単価</th>
					<th>寸法</th>
					<th>種別</th>
					<th>カテゴリー</th>
				</tr>

				<tr>
					<td>0001</td>
					<td>カーネーション</td>
					<td>\9,800-</td>
					<td>50x70x90cm</td>
					<td>花束</td>
					<td>Red</td>
				</tr>
			</table>

			<br> <input type="submit" name="add" value="削除"
				onclick="exec(0);"> <input type="submit" name="delete"
				value="戻る" onclick="exec(1);">

		</form>

	</center>
</body>

</html>