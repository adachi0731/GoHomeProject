<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var url = new Array("item_maint_update_confirm.html", "item_maint.html");
	function exec(num) {
		document.myForm.action = url[num];
	}
</script>
</head>
<body>
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



		<form action="/ghp/controller" method="post">

			<table border="1">
				<tr>
					<th>商品番号</th>
					<td>${itemVo.itemNo}</td>
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
							<%-- 今これなければ動く？どこから起動する？　ひとまず固定値ね（ハードコーディング）んじゃ同的にするには？3の部分がどうなる？selectVoのcategoryCodeと一致すればーにしたらいんじゃね？わかる？
							検索結果の値がselectVoに詰まってるんでしょ？商品No1なら、商品名はバラ、カテゴリコードは3、　商品No2なら、（たとえば）商品名はゆり、カテゴリコードは4　とか。動的に星井のは、
														categoryCode  なくね？？？？？DAOでセットした？
														ということばい。
														同じように種別もやる。できるかな？
							 --%>
								<option value="${category.categoryCode}"
									<c:if test="${category.categoryCode==selectVo.categoryCode }"> selected</c:if>
								>
								${category.categoryName}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>

			<br> <input type="submit" value="送信"> <input
				type="hidden" name="action" value="item.UpdateCheck">
		</form>
		<form action="/ghp/pages/item/itemAllSelect.jsp">
			<input type="submit" name="submit" value="戻る">
		</form>

	</center>
</body>

</html>