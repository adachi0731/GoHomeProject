<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var url = new Array("/ghp/pages/item/itemAdd.jsp","/ghp/controller");
	function exec(num) {
		document.myForm.action = url[num];
	}
</script>
</head>
<body>
	<center>
		<h2>商品情報メンテナンス</h2>

  		<br>${erro }<br>
		商品情報のメンテナンスを行います。<br> 新規に取扱商品を追加する場合は、「商品追加」ボタンを押してください。<br>
		登録済み商品の情報を変更するには、商品名のリンクをクリックしてください。<br>
		商品を削除する場合は、リストの右にある「削除」をチェックし、「削除」ボタンを押してください。<br>
		<br>

 <form name="myForm">
		<table border=1 style="color: black">
			<tr>
				<td>商品番号</td>
				<td>商品名</td>
				<td>単価</td>
				<td>寸法</td>
				<td>種別</td>
				<td>カテゴリ</td>
				<td>削除</td>
			</tr>
			<c:forEach var="itemVo" items="${itemList}">
				<tr>
					<td>${itemVo.itemNo}</td>
					<td><a
						href="/ghp/controller?action=item.Select&itemNo=${itemVo.itemNo}">
							${itemVo.itemName}</a></td>
					<td>${itemVo.unitPrice}</td>
					<td>${itemVo.size}</td>
					<td>${itemVo.assortmentCode}</td>
					<td>${itemVo.categoryCode }</td>
					<td align="center"><input type="checkbox"  name="check" value="${itemVo.itemNo}"></td>
				</tr>
			</c:forEach>

		</table>

		<br>
		<input type="submit" value="商品追加" onClick="exec(0);">

		 <input type="submit" name="delete" value="削除" onClick="exec(1);">
		 <input type="hidden" name="action" value="item.DeleteSelect" >
		</form>


	</center>
</body>

</html>