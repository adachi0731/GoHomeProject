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
//	var url = new Array("/ghp/controller", "/ghp/pages/item/itemAllSelect.jsp");
	//これはテキスト実装編のP43のまま
	//submitとbuttonの違いだけ
	function exec(num) {
		if (num == 0) {

			document.forms[0].action.value="item.UpdateCheck";
		}else {
			document.forms[0].action.value="item.AllSelect";
		}
//		alert(url[num]);
//		document.myForm.action = url[num];
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

		商品情報を更新します。<br> 更新内容を入力して、「送信」ボタンを押してください。<br>
		${errorItemName}
		${errorItemNameCorrect}<br>
		${errorItemUrl}<br>
		${errorItemUnitPrice}<br>
		${errorItemSize}
		${errorItemSizeCorrect}<br>
		${ItemDeleted}



		<form name="myForm" action="/ghp/controller" method="post">
			<table border="1">
				<tr>
					<th>商品番号</th>
					<td><input type="hidden" name="itemNo" value="${itemVo.itemNo}" >${itemVo.itemNo}</td>
				</tr>
				<tr>
					<th>商品名</th>
					<td><input type="text" name="itemName"
						value="${selectVo.itemName }"></td>
				</tr>
				<tr>
					<th>商品画像(URL)</th>
					<td><input type="text" name="URL" size="40"
						value="${selectVo.itemURL }"></td>
				</tr>
				<tr>
					<th>単価</th>
					<td><input type="text" name="unitPrice"
						value="${selectVo.unitPrice }"></td>
				</tr>
				<tr>
					<th>寸法</th>
					<td><input type="text" name="size" value="${selectVo.size }"></td>
				</tr>
				<tr>
					<th>種別</th>

					<td><select name="assortmentCode">
							<c:forEach var="assortment" items="${assortmentList}">
								<option value="${assortment.assortmentCode}"
								<c:if test="${assortment.assortmentCode==selectVo.assortmentCode }"> selected</c:if>
								>
								${assortment.assortmentName}</option>
							</c:forEach>
					</select></td>

				</tr>
				<tr>
					<th>カテゴリー</th>
					<td><select name="categoryCode">
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.categoryCode}"
									<c:if test="${category.categoryCode==selectVo.categoryCode }"> selected</c:if>
								>
								${category.categoryName}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>

			<br> <input type="submit" name="update" value="送信" onClick="exec(0);">
			<input type="submit"  name ="back"value="戻る" onClick="exec(1);">
			<%-- まずここね。valueにあらかじめ値を入れてしまうとJSで制御できない（固定値 --%>
			<input type="hidden" name="action" value="">
		</form>


	</center>
	</td>
	</tr>
	</table>
</body>

</html>