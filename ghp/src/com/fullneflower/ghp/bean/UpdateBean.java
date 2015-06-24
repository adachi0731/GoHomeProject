package com.fullneflower.ghp.bean;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.dao.ConnectionManager;
import com.fullneflower.ghp.dao.ItemDao;
import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.ItemVo;

/**
 * インターフェースの実装
 * @author Owner
 */
public class UpdateBean implements  FullneflowerBean{

	/**
	 *[機能]入力された値をデータベースに更新した結果を受け取るメソッド。
	 *[説明]更新結果が"1件"の場合"success"をreturnで返す。
	 *      更新結果が"1件以外"の場合"failer"returnで返す。
	 *@author 足立
	 *@param HttpServletRequest request, HttpServletResponse response
	 *@throws GhpException
	 *@return 更新件数
	 */

	//戻り値の値
	String re;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		// TODO 自動生成されたメソッド・スタブ
		//コネクション取得
		ConnectionManager cm = new ConnectionManager();
		Connection connection = cm.getConnection();

		//ItemVo呼び出し
		ItemVo itemV = new ItemVo();

		try{
			//ここで、リクエストから更新したい情報を取得する
			//(itemUpdateCheck.jsp)に入力され、(ItemVo)に入れた値を呼びだす。
			System.out.println(request.getParameter("itemNo"));
			System.out.println(request.getParameter("itemName"));
			System.out.println(request.getParameter("URL"));
			System.out.println(request.getParameter("unitPrice"));
			System.out.println(request.getParameter("size"));
			System.out.println(request.getParameter("assortment"));
			System.out.println(request.getParameter("category"));
			itemV.setItemNo(request.getParameter("itemNo"));
			itemV.setItemName(request.getParameter("itemName"));
			itemV.setItemURL(request.getParameter("URL"));
			int untiPrice=  Integer.parseInt(request.getParameter("unitPrice"));
			itemV.setUnitPrice(untiPrice);
			itemV.setSize(request.getParameter("size"));
			itemV.setAssortmentCode(request.getParameter("assortment"));
			itemV.setCategoryCode(request.getParameter("category"));
			String complete ="";
			/*
			 * Voの値が取得できているかの確認用
			System.out.println(itemV.getItemNo());
			System.out.println(itemV.getAssortmentCode());
			System.out.println(itemV.getCategoryCode());
			System.out.println("vo:"+itemV.getItemName());
			System.out.println("param:"+request.getParameter("itemName"));
			System.out.println(itemV.getItemURL());
			System.out.println(itemV.getSize());
			System.out.println(itemV.getUnitPrice());*/

			/**
			 * ItemDaoのupdate()メソッドを実行
			 * 結果(result)を基に"re"に値を入れる。
			 */
			ItemDao itemD = new ItemDao(connection);

			int result = itemD.update(itemV);

			if(result==1){
				cm.commit();
				List<ItemVo> itemList = itemD.selectAll();
            	request.setAttribute("itemList", itemList);
            	String param = "ItemAddCheck";
				ResourceBundle msgresult = ResourceBundle.getBundle("Message");
				complete = msgresult.getString(param);
				request.setAttribute("complete", complete);
				re = "success";
			}else{
				String param = "ItemDeleted";
				ResourceBundle msgresult = ResourceBundle.getBundle("Message");
				String erro = msgresult.getString(param); //errorメッセージ
				request.setAttribute("ItemDeleted", erro);
				re = "failure";
			}

		}catch(GhpException e){
			//ConnectionManager内のrollback
			cm.rollback();
			throw new GhpException("UpdateBeanでエラーが起きました", e);
		}finally{
			//コネクション切断
			cm.closeConnection();
		}
		return re;
	}
}

