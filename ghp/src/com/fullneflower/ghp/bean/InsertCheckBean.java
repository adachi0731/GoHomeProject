package com.fullneflower.ghp.bean;

import java.sql.Connection;
import java.sql.ResultSet;
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

public class InsertCheckBean implements  FullneflowerBean{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		ConnectionManager connectionManager = new ConnectionManager();
		try{
			Connection connection = connectionManager.getConnection();
			// TODO 自動生成されたメソッド・スタブ

			String result = "failure";
			String itemNo = request.getParameter("itemNo");
			String itemName = request.getParameter("itemName");
			String URL = request.getParameter("URL");
			System.out.println("URLURL::::"+request.getParameter("URL"));
			String unitPrice = request.getParameter("unitPrice");
			String assortment = request.getParameter("assortmentCode");
			String category = request.getParameter("categoryCode");
			String size = request.getParameter("size");
			ItemDao itemDao = new ItemDao(connection);
			List<ItemVo> itemAllList= itemDao.selectAll();
			ResultSet doubleCheck = itemDao.Double(itemNo, itemName);

			List<ItemAssortmentVo> assortmentList= itemDao.assortment();
			List<ItemCategoryVo> categoryList= itemDao.category();
			//商品番号のチェック
			Pattern noPattern = Pattern.compile("^[0-9]{4}");
			Matcher ItemNo = noPattern.matcher(itemNo);
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
			if("".equals(itemNo) || !ItemNo.matches()){
				String param = "ItemNo";
				ResourceBundle msgresult = ResourceBundle.getBundle("Message");
				error += "<br>" + msgresult.getString(param); //errorメッセージ
				inputFlg = false;
			}


			if(doubleCheck != null){
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
				ItemVo itemVo = new ItemVo();
				itemVo.setItemNo(itemNo);
				itemVo.setItemName(itemName);
				itemVo.setItemURL(URL);
				itemVo.setUnitPrice(Integer.parseInt(unitPrice));
				itemVo.setSize(size);
				itemVo.setAssortmentCode(assortment);
				itemVo.setCategoryCode(category);

				request.setAttribute("itemVo", itemVo);
				request.setAttribute("assortmentList", assortmentList);
				request.setAttribute("categoryList", categoryList);

				result = "success";
			} else {
				result = "failure";
				request.setAttribute("itemList", itemAllList);
				request.setAttribute("assortmentList", assortmentList);
				request.setAttribute("categoryList", categoryList);
				request.setAttribute("error", error);
			}
			System.out.println(result);
			return result;
		}catch(GhpException e){
			throw new GhpException("InsertCheckBeanでエラー",e);
		}
	}
}
