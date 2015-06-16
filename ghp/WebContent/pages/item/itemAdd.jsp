<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var url = new Array("item_maint_add_confirm.html", "item_maint.html");
	function exec(num) {
		document.myForm.action = url[num];
	}
</script>
</head>
<body>
	<center>
		<h2>商品情報メンテナンス</h2>

		商品情報を新規登録します。<br> 登録内容を入力して、「送信」ボタンを押してください。<br> <br>

		<form name="myForm" >

			<table border="1">
				<tr>
					<th>商品番号</th>
					<td><input type="text" value=""></td>
				</tr>
				<tr>
					<th>商品名</th>
					<td><input type="text" value=""></td>
				</tr>
				<tr>
					<th>商品画像(URL)</th>
					<td><input type="text" value="" size="40"></td>
				</tr>
				<tr>
					<th>単価</th>
					<td><input type="text" value=""></td>
				</tr>
				<tr>
					<th>寸法</th>
					<td><input type="text" value=""></td>
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