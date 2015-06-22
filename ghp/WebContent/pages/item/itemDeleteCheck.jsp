<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>商品情報メンテナンス</h2>

		下記の商品情報を削除します。<br> 内容を確認の上、「削除」ボタンを押してください。<br> <br>


		<form action="/ghp/controller" method="post">
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
			<br> <input type="submit" name="add" value="削除" />
			<input type="submit" name="delete" value="戻る" />
			<input type="hidden" name="action" value="item.Delete">
		</form>
	</center>
</body>
</html>