package com.fullneflower.ghp.bean;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.dao.ConnectionManager;
import com.fullneflower.ghp.dao.ItemDao;
import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.ItemAssortmentVo;
import com.fullneflower.ghp.vo.ItemCategoryVo;
import com.fullneflower.ghp.vo.ItemVo;
/**
 * インターフェースの実装
 * @author 木村
 *
 */
public class SelectBean implements  FullneflowerBean{
	/**
	 *[機能] 入力された値(商品番号)をitemVoにつめる
	 *       それを基に行ったitemDaoのselectPoint()の結果を受け取り、Listに詰める
	 *[説明] itemDaoから受け取った結果を基に、"selectVo""itemVo""assortmentList""categoryList"をListに詰める
	 *@author 木村
	 *@param HttpServletRequest request, HttpServletResponse response
	 *@return "success" or "failure"
	 *@throws GhpException
	 *
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		// TODO 自動生成されたメソッド・スタブ

		ConnectionManager cm = null;
		cm=new ConnectionManager();
		try{
			String iNo= request.getParameter("itemNo");
			ItemVo itemVo =new ItemVo();
			itemVo.setItemNo(iNo);
			//コネクション取得
			//ItemVo itmVo =new ItemVo();

			Connection connection = cm.getConnection();
			ItemDao itemDao= new ItemDao(connection);
			//itemDaoのメソッドを呼び出す
			//ここでVOが完成
			List<ItemVo> itemSelect=itemDao.selectPoint(iNo);
			//ListをここでDAOと同期！
			ItemVo tmpVo = itemSelect.get(0);
			//System.out.println(tmpVo.getItemName());
			//System.out.println(tmpVo);
			//itemVoという名で同期した値を飛ばす　種別のプルダウンもうできた？できたよね？
			request.setAttribute("selectVo", tmpVo);
			request.setAttribute("itemVo", itemVo);
			//assortmentメソッドをassortmentListにつめる
			List<ItemAssortmentVo> assortmentList= itemDao.assortment();
			request.setAttribute("assortmentList", assortmentList);
			//categoryメソッドをcategoryListにつめる
			List<ItemCategoryVo> categoryList= itemDao.category();
			request.setAttribute("categoryList", categoryList);

			return "success";
		}catch(GhpException e){
			return "failure";
		}
	}

}

