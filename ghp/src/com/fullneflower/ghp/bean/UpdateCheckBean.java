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
import com.fullneflower.ghp.vo.ItemAssortmentVo;
import com.fullneflower.ghp.vo.ItemCategoryVo;
import com.fullneflower.ghp.vo.ItemVo;

public class UpdateCheckBean implements  FullneflowerBean{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		// TODO 自動生成されたメソッド・スタブ
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		String result = "failure";
		String itemNo = request.getParameter("itemNo");
		String itemName = request.getParameter("itemName");
		String URL = request.getParameter("URL");
		String unitPrice = request.getParameter("unitPrice");
		String assortment = request.getParameter("assortmentCode");
		String category = request.getParameter("categoryCode");
		ItemDao itemDao = new ItemDao(connection);
		ItemVo itemVo = new ItemVo();
		List<ItemVo> itemList = itemDao.selectPoint(itemNo);
		List<ItemAssortmentVo> assortmentList= itemDao.assortment();
		List<ItemCategoryVo> categoryList= itemDao.category();


		String size = request.getParameter("size");

		//商品名のチェック
		Pattern namePattern = Pattern.compile("^[ぁ-んァ-ヶー]+$");
		Matcher ItemNameCorrect = namePattern.matcher(itemName);
		//単価のチェック
		Pattern numPattern = Pattern.compile("^[0-9]+$");
		Matcher ItemUnitPrice = numPattern.matcher(unitPrice);
		//URLのチェック
		Pattern urlPattern = Pattern.compile("^(.*)(\\.jpg)+$");
		Matcher ItemUrl = urlPattern.matcher(URL);
		//寸法のチェック
		Pattern sizePattern = Pattern.compile("[0-99]+x+[0-99]+x+[0-99]+$");
		Matcher ItemSize = sizePattern.matcher(size);
		boolean inputFlg = true;
		String error = "";
		System.out.println("判断："+itemDao.nameCheck(itemNo,itemName));

		if(!itemDao.nameCheck(itemNo,itemName)){
			String param = "ItemDouble";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			error += "<br>" + msgresult.getString(param); //errorメッセージ
			inputFlg = false;
		}

		if("".equals(itemName)){
			String param = "ItemName";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			error += "<br>" + msgresult.getString(param); //errorメッセージ
			inputFlg = false;
		}else if(!ItemNameCorrect.matches()){
			String param = "ItemNameCorrect";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			error += "<br>" + msgresult.getString(param); //errorメッセージ
			inputFlg = false;
		}

		if("".equals(size)){
			String param = "ItemSize";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			error += "<br>" + msgresult.getString(param); //errorメッセージ
			inputFlg = false;
		}else if(!ItemSize.matches()){
			String param = "ItemSizeCorrect";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			error += "<br>" + msgresult.getString(param); //errorメッセージ
			inputFlg = false;
		}


		if("".equals(URL)){

		}else if(!ItemUrl.matches()){
			String param = "ItemUrl";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			error += "<br>" + msgresult.getString(param); //errorメッセージ
			inputFlg = false;
		}

		if(!ItemUnitPrice.matches()){
			String param = "ItemUnitPrice";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			error += "<br>" + msgresult.getString(param); //errorメッセージ
			inputFlg = false;
		}

		if(inputFlg){
			itemVo.setItemNo(itemNo);
			itemVo.setItemName(itemName);
			itemVo.setItemURL(URL);
			itemVo.setUnitPrice(Integer.parseInt(unitPrice));
			itemVo.setSize(size);
			itemVo.setAssortmentCode(assortment);
			itemVo.setCategoryCode(category);

			request.setAttribute("itemVo", itemVo);
			request.setAttribute("itemList", itemList);
			request.setAttribute("assortmentList", assortmentList);
			request.setAttribute("categoryList", categoryList);
			result = "success";
		} else {
			result = "failure";
			List<ItemVo> itemSelect=itemDao.selectPoint(itemNo);
			ItemVo tmpVo = itemSelect.get(0);
			itemVo.setItemNo(itemNo);
			request.setAttribute("itemVo", itemVo);
			request.setAttribute("selectVo", tmpVo);
			request.setAttribute("itemList", itemList);
			request.setAttribute("assortmentList", assortmentList);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("error", error);
		}
		System.out.println(result);
		return result;
	}
}
