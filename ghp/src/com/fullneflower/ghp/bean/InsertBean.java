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
 * @author 本多
 */
public class InsertBean implements  FullneflowerBean{

	/**
	 * [機能]入力された値をitemVoに詰め、itemDaoのinsert()結果を受け取りreturnでsuccessかfailureを返す
	 * [説明]入力された値をitemVoに詰める
	 *       itemDaoのinsert()結果が"1"の時returnに"success"を返す。
	 *       itemDaoのinsert()結果が"1"以外の時returnに"failure"を返す。
	 * @author 本多
	 * @param HttpServletRequest request, HttpServletResponse response
	 * @return "success" or "failure"
	 * @throws GhpException
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		// TODO 自動生成されたメソッド・スタブ
		ConnectionManager connectionManager = new ConnectionManager();
		try {
			Connection connection = connectionManager.getConnection();
			ItemDao itemDao = new ItemDao(connection);
			ItemVo itemVo = new ItemVo();

			System.out.println(request.getParameter("assortmentCode"));

			itemVo.setItemNo(request.getParameter("itemNo"));
			itemVo.setItemName(request.getParameter("itemName"));
			itemVo.setItemURL(request.getParameter("URL"));
			int untiPrice=  Integer.parseInt(request.getParameter("unitPrice"));
			itemVo.setUnitPrice(untiPrice);
			itemVo.setSize(request.getParameter("size"));
			itemVo.setAssortmentCode(request.getParameter("assortment"));
			itemVo.setCategoryCode(request.getParameter("category"));
			System.out.println("category:"+request.getParameter("category"));
			System.out.println(itemVo.getItemNo());
			System.out.println(itemVo.getItemName());
			System.out.println(itemVo.getItemURL());
			System.out.println(itemVo.getSize());
			System.out.println(itemVo.getAssortmentCode());
			System.out.println(itemVo.getCategoryCode());
			String complete="";
			//List<ItemVo> insertCheck= itemDao.insertCheck(itemVo);

			//if(insertCheck==null){
			int result = itemDao.insert(itemVo);
			connectionManager.commit();
			System.out.println(result);
			if(result == 1){
				List<ItemVo> itemList= itemDao.selectAll();
				request.setAttribute("itemList", itemList);
				String param = "ItemRegisteredCheck";
				ResourceBundle msgresult = ResourceBundle.getBundle("Message");
				complete = msgresult.getString(param);
				request.setAttribute("complete", complete);

				return "success";
			}else{
				return "failure";
			}
			/*}else{
					String param = "ItemRegistered";
					ResourceBundle msgresult = ResourceBundle.getBundle("Message");
					String erro = msgresult.getString(param); //errorメッセージ
					request.setAttribute("errorItemRegistered", erro);
					return "failure";
				}*/


		} catch (RuntimeException e) {
			connectionManager.rollback();
			throw e;
		} finally {
			connectionManager.closeConnection();
		}
	}

}
