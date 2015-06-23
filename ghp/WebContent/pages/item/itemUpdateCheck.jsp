<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/ghp/css/menu.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//var url = new Array("/ghp/controller", "/ghp/pages/item/itemUpdate.jsp");
	function exec(num) {
		if(num == 0){
			document.forms[0].action.value ="item.Update";
		}else{
			document.forms[0].action.value ="item.Select";
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

		以下の内容で商品情報を更新します。<br> 更新内容を確認の上、「更新」ボタンを押してください。<br> <br>


		<form name="myForm" action="/ghp/controller" method="post">

			<table border="1">
				<tr>
					<th>商品番号</th>
					<td><input type="hidden" name="itemNo" value="${itemNo}">${itemNo}</td>
				</tr>
				<tr>
					<th>商品名</th>
					<td><input type="hidden" name="itemName" value="${itemList[0].itemName}">${itemList[0].itemName}</td>
				</tr>
				<tr>
					<th>商品画像</th>
					<td><input type="hidden" name="URL" value="${itemList[0].itemURL}">${itemList[0].itemURL}</td>
				</tr>
				<tr>
					<th>単価</th>
					<td><input type="hidden" name="unitPrice" value="${itemList[0].unitPrice}">${itemList[0].unitPrice}</td>
				</tr>
				<tr>
					<th>寸法</th>
					<td><input type="hidden" name="size" value="${itemList[0].size}">${itemList[0].size}cm</td>
				</tr>
				<tr>
					<th>種別</th>


					<td><c:forEach var="assortment" items="${assortmentList}">
								<c:if test="${assortment.assortmentCode==itemList[0].assortmentCode }">${assortment.assortmentName}</c:if>
							</c:forEach>
								</td>
				</tr>
				<tr>
					<th>カテゴリー</th>
					<td><c:forEach var="category" items="${categoryList}">
								<c:if test="${category.categoryCode==itemList[0].categoryCode }">${category.categoryName}</c:if>
							</c:forEach></td>
				</tr>
			</table>

			<br> <input type="submit" value="登録" onClick="exec(0);">
			<input type="hidden" name="action" value="">
			<input type="submit" value="戻る" onClick="exec(1);">
	</form>


	</center>
	</td>
	</tr>
	</table>
</body>

</html>