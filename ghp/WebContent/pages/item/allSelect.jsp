<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var url = new Array("item_maint_add.html", "item_maint_delete.html");
	function exec(num) {
		document.myForm.action = url[num];
	}
</script>
</head>
<body>
	<center>
		<h2>商品情報メンテナンス</h2>

		商品情報のメンテナンスを行います。<br> 新規に取扱商品を追加する場合は、「商品追加」ボタンを押してください。<br>
		登録済み商品の情報を変更するには、商品名のリンクをクリックしてください。<br>
		商品を削除する場合は、リストの右にある「削除」をチェックし、「削除」ボタンを押してください。<br> <br>

		<form name="myForm">

			<table border="1">
				<tr>
					<th>商品番号</th>
					<th>商品名</th>
					<th>単価</th>
					<th>寸法</th>
					<th>種別</th>
					<th>カテゴリー</th>
					<th>削除</th>
				</tr>

				<tr>
					<td>0001</td>
					<td><a href="item_maint_update.html">カーネーション</a></td>
					<td>\9,800-</td>
					<td>50x70x90cm</td>
					<td>花束</td>
					<td>Red</td>
					<td align="center"><input type="checkbox" value="delete"></td>
				</tr>
				<tr>
					<td>0002</td>
					<td><a href="item_maint_update.html">ベゴニア</a></td>
					<td>\9,800-</td>
					<td>50x70x90cm</td>
					<td>鉢植</td>
					<td>Yellow</td>
					<td align="center"><input type="checkbox" value="delete"></td>
				</tr>
				<tr>
					<td>0003</td>
					<td><a href="item_maint_update.html">バラ</a></td>
					<td>\9,800-</td>
					<td>50x70x90cm</td>
					<td>花束</td>
					<td>Blue</td>
					<td align="center"><input type="checkbox" value="delete"></td>
				</tr>

			</table>

			<br> <input type="submit" name="add" value="商品追加"
				onClick="exec(0);"> <input type="submit" name="delete"
				value="削除" onClick="exec(1);" >

		</form>

	</center>
</body>

</html>