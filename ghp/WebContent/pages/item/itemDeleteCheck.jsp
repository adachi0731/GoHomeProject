<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/ghp/css/menu.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//	var url = new Array("/ghp/controller", "/ghp/pages/item/itemAllSelect.jsp");
	//これはテキスト実装編のP43のまま
	//submitとbuttonの違いだけ
	function exec(num) {
		if (num == 0) {

			document.forms[0].action.value="item.AllSelect";
		}else {
			document.forms[0].action.value="item.AllSelect";
		}
	}
</script>
</head>
<body>
<table class="style" >
	<tr>
		<td class="header" colspan="2" align="center"><jsp:include page="/pages/log/header.jsp" /></td>
	</tr>
	<tr>
		<td class="menu" valign=top><jsp:include page="/pages/log/menu.jsp" /></td>
		<td class="main" valign="top">
	<center>
		<h2>商品情報メンテナンス</h2>

		下記の商品情報を削除します。<br> 内容を確認の上、「削除」ボタンを押してください。<br> <br>


		<form name="myForm" action="/ghp/controller" method="post">
			<table border="1">
				<tr>
					<th>商品番号</th>
					<th>商品名</th>
					<th>単価</th>
					<th>寸法</th>
					<th>種別</th>
					<th>カテゴリー</th>
				</tr>
				<c:forEach var="itemVo" items="${dataList}">
					<tr>
						<td>${itemVo.itemNo}
							<input type="hidden" name="delItemNo" value="${itemVo.itemNo}">
						</td>
						<td>${itemVo.itemName}</td>
						<td>${itemVo.unitPrice}</td>
						<td>${itemVo.size}</td>
						<td>${itemVo.cateVo.categoryName}</td>
						<td>${itemVo.assortVo.assortmentName}</td>
					</tr>
				</c:forEach>
			</table>
			<br><input type="submit" name="add" value="削除" onClick="exec(0);"/>
			<input type="submit" name="delete" value="戻る" onClick="exec(1);"/>
			<input type="hidden" name="action" value="">
		</form>
	</center>
	</td>
	</tr>
	</table>
</body>
</html>