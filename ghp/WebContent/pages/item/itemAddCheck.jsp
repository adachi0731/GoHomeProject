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

			document.forms[0].action.value = "item.AllSelect";
		} else {
			document.forms[0].action.value = "item.Insert";
		}
		//		alert(url[num]);
		//		document.myForm.action = url[num];
	}
</script>
</head>
<body>
	<center>
		<h2>商品情報メンテナンス</h2>

		以下の内容で商品情報を登録します。<br> 登録内容を確認の上、「登録」ボタンを押してください。<br> <br>

	<form name="myForm" action="/ghp/controller" method="post">


			<table border="1">
				<tr>
					<th>商品番号</th>
					<td><input type="hidden" name="itemNo" value="${itemList.itemNo}">${itemList.itemNo}</td>
				</tr>
				<tr>
					<th>商品名</th>
					<td><input type="hidden" name="itemName" value="${itemList.itemName}">${itemList.itemName}</td>
				</tr>
				<tr>
					<th>商品画像</th>
					<td><input type="hidden" name="URL" value="${itemList.Url}">${itemList.url}</td>
				</tr>
				<tr>
					<th>単価</th>
					<td><input type="hidden" name="unitPrice" value="${itemList.unitPrice}">${Price}</td>
				</tr>
				<tr>
					<th>寸法</th>
					<td><input type="hidden" name="size" value="${itemList.size}">${itemList.size}</td>
				</tr>
				<tr>
					<th>種別</th>
					<td><input type="hidden" name="assortment" value="${itemList.assortmentCode}">${itemList.assortmentCode}</td>
				</tr>
				<tr>
					<th>カテゴリー</th>
					<td><input type="hidden" name="category" value="${itemList.categoryCode}">${itemList.categoryCode}</td>
				</tr>
			</table>
 <input type="submit" value="登録" onClick="exec(0);">
			<input type="hidden" name="action" value="">
			<input type="submit" value="戻る" onClick="exec(1);">

		</form>

	</center>
</body>

</html>