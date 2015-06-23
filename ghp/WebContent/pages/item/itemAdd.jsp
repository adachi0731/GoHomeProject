<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//	var url = new Array("/ghp/controller", "/ghp/pages/item/itemAllSelect.jsp");
	//これはテキスト実装編のP43のまま
	//submitとbuttonの違いだけ
	function exec(num) {
		if (num == 0) {

			document.forms[0].action.value="item.InsertCheck";
		}else {
			document.forms[0].action.value="item.AllSelect";
		}
//		alert(url[num]);
//		document.myForm.action = url[num];
	}
</script>
</head>
<body>
	<center>
		<h2>商品情報メンテナンス</h2>

		商品情報を新規登録します。<br> 登録内容を入力して、「送信」ボタンを押してください。<br>
		${errorItemNo}<br> ${errorItemName} ${errorItemNameCorrect}<br>
		${errorItemUrl}<br> ${errorItemUnitPrice}<br>
		${errorItemSize} ${errorItemSizeCorrect}<br>
		${errorItemRegistered }

		<form name="myForm" action="/ghp/controller" method="post">

			<table border="1">
				<tr>
					<th>商品番号</th>
					<td><input type="text" name="itemNo" value="1111"></td>
				</tr>
				<tr>
					<th>商品名</th>
					<td><input type="text" name="itemName" value="テスト"></td>
				</tr>
				<tr>
					<th>商品画像(URL)</th>
					<td><input type="text" name="URL" size="40" value="test.jpg"></td>
				</tr>
				<tr>
					<th>単価</th>
					<td><input type="text" name="unitPrice" value="1000"></td>
				</tr>
				<tr>
					<th>寸法</th>
					<td><input type="text" name="size" value="10x10x10"></td>
				</tr>
				<tr>
					<th>種別</th>
					<td><select name="assortment">
							<option value="1">花束</option>
							<option value="2">鉢植</option>
							<option value="3">アレンジメント</option>
					</select></td>
				</tr>
				<tr>
					<th>カテゴリー</th>
					<td><select name="category">
							<option value="1">Red</option>
							<option value="2">Yellow</option>
							<option value="3">Blue</option>
					</select></td>
				</tr>
			</table>

			<br><input type="submit" value="送信" onClick="exec(0);">
			<input type="hidden" name="action" value="">

			<input type="submit" name="submit" value="戻る" onClick="exec(1);">
		</form>
	</center>
</body>

</html>