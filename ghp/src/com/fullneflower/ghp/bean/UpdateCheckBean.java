package com.fullneflower.ghp.bean;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.dao.ConnectionManager;
import com.fullneflower.ghp.dao.ItemDao;
import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.ItemVo;

public class UpdateCheckBean implements  FullneflowerBean{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		// TODO 自動生成されたメソッド・スタブ
		ConnectionManager connectionManager = new ConnectionManager();
		int checkFlg=1;
		String result = "failure";
		String itemNo = request.getParameter("itemNo");
		String itemName = request.getParameter("itemName");
		String URL = request.getParameter("URL");
		String unitPrice = request.getParameter("unitPrice");
		String assortment = request.getParameter("assortment");
		String category = request.getParameter("category");



		String size = request.getParameter("size");

		//商品名のチェック
		Pattern namePattern = Pattern.compile("^[ぁ-んァ-ヶー]*$+$");
		Matcher ItemNameCorrect = namePattern.matcher(itemName);
		//単価のチェック
		Pattern numPattern = Pattern.compile("^[0-9].*$");
		Matcher ItemUnitPrice = numPattern.matcher(unitPrice);
		//URLのチェック
		Pattern urlPattern = Pattern.compile("(.*)[^\\.jpg]+$");
		Matcher ItemUrl = urlPattern.matcher(URL);
		//寸法のチェック
		Pattern sizePattern = Pattern.compile("[1-9][0-9]+x+[1-9][0-9]+x+[1-9][0-9]+$");
		Matcher ItemSize = sizePattern.matcher(size);

		if("".equals(itemName)){
			String param = "error.ItemName";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			String erro = msgresult.getString(param); //errorメッセージ
			request.setAttribute("errorItemName", erro);
			checkFlg=0;
			result = "failure";
		}

		if("".equals(size)){
			String param = "error.ItemSize";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			String erro = msgresult.getString(param); //errorメッセージ
			request.setAttribute("errorItemSize", erro);
			checkFlg=0;
			result = "failure";
		}

		if(ItemNameCorrect.matches()){

		}else{
			String param = "error.ItemNameCorrect";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			String erro = msgresult.getString(param); //errorメッセージ
			request.setAttribute("errorItemNameCorrect", erro);
			checkFlg=0;
			result = "failure";
		}

		if(ItemUrl.matches()){
			String param = "error.ItemUrl";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			String erro = msgresult.getString(param); //errorメッセージ
			request.setAttribute("errorItemUrl", erro);
			checkFlg=0;
			result = "failure";
		}else{

		}

		if(ItemUnitPrice.matches()){

		}else{
			String param = "error.ItemUnitPrice";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			String erro = msgresult.getString(param); //errorメッセージ
			request.setAttribute("errorItemUnitPrice", erro);
			checkFlg=0;
			result = "failure";
		}

		if(ItemSize.matches()){

		}else{
			String param = "error.ItemSizeCorrect";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			String erro = msgresult.getString(param); //errorメッセージ
			request.setAttribute("errorItemSizeCorrect", erro);
			checkFlg=0;
			result = "failure";
		}

		if(checkFlg==1){
			ItemVo itemVo = new ItemVo();
			Connection connection = connectionManager.getConnection();
			ItemDao itemDao = new ItemDao(connection);
			itemVo.setItemNo(itemNo);
			itemVo.setItemName(itemName);
			itemVo.setItemURL(URL);
			itemVo.setUnitPrice(Integer.parseInt(unitPrice));
			System.out.println(Integer.parseInt(unitPrice));
			itemVo.setSize(size);
			itemVo.setAssortmentCode(assortment);
			itemVo.setAssortmentCode(category);

			request.setAttribute("No", itemNo);
			request.setAttribute("Name", itemName);
			request.setAttribute("Url", URL);
			request.setAttribute("Price", unitPrice);
			request.setAttribute("Size", size);
			request.setAttribute("Assortment", assortment);
			request.setAttribute("Category", category);
			List<ItemVo> itemList= itemDao.selectAll();
			request.setAttribute("itemList", itemList);
			result = "success";
		}
		System.out.println(result);
		return result;
	}
}
