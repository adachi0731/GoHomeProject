<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/ghp/css/menu.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//	var url = new Array("/ghp/controller", "/ghp/pages/item/itemAllSelect.jsp");
	//これはテキスト実装編のP43のまま
	//submitとbuttonの違いだけ
	function exec(num) {
		if (num == 0) {
			document.myForm.action.value="item.Pulldown";
		}else {
			document.myForm.action.value="item.DeleteSelect";
		}
	}
</script>
</head>
<body>
	<table class="style">
		<tr>
			<td class="header" colspan="2" align="center"><jsp:include
					page="/pages/log/header.jsp" /></td>
		</tr>
		<tr>
			<td class="menu" valign=top><jsp:include
					page="/pages/log/menu.jsp" /></td>
			<td class="main" valign="top">
				<center>
					<h2>商品情報メンテナンス</h2>
					<br>
					<font color="BLUE" class="completeMessage">${complete } </font>
					<font color="Red" class="errorMessage">${erro}</font>
					<br> 商品情報のメンテナンスを行います。<br>
					新規に取扱商品を追加する場合は、「商品追加」ボタンを押してください。<br>
					登録済み商品の情報を変更するには、商品名のリンクをクリックしてください。<br>
					商品を削除する場合は、リストの右にある「削除」をチェックし、「削除」ボタンを押してください。<br> <br>


					<form name="myForm" action="/ghp/controller" method="post">
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
									<td align="center"><input type="checkbox" name="check"
										value="${itemVo.itemNo}"></td>
								</tr>
							</c:forEach>

						</table>

						<br><input type="submit" value="商品追加" onClick="exec(0);"/>
			<input type="submit" value="削除" onClick="exec(1);"/>
			<input type="hidden" name="action" value="">
		</form>
				</center>
			</td>
		</tr>
	</table>
</body>

</html>