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

/**
 * インターフェース実装
 * @author 本多
 */
public class PulldownBean implements  FullneflowerBean{

	/**
	 * [機能]プルダウン「種別」「カテゴリー」内の項目を表示する
	 * [説明]プルダウンの項目である種別(assortment)、カテゴリー(category)内の項目を
	 *       データベースから呼び出し、Listに詰め表示させる
	 * @author 本多
	 * @param HttpServletRequest request, HttpServletResponse response
	 * @return "success"
	 * @throws GhpException
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		// TODO 自動生成されたメソッド・スタブ
		ConnectionManager cm = null;
		cm=new ConnectionManager();
		try{
			Connection connection = cm.getConnection();
			ItemDao itemDao= new ItemDao(connection);

			//assortmentメソッドをassortmentListにつめる
			List<ItemAssortmentVo> assortmentList= itemDao.assortment();
			request.setAttribute("assortmentList", assortmentList);
			//categoryメソッドをcategoryListにつめる
			List<ItemCategoryVo> categoryList= itemDao.category();
			request.setAttribute("categoryList", categoryList);

			return "success";
		}catch(Exception e){
			cm.rollback();
			throw new GhpException("UpdateCheckBeanで失敗しました", e);
		}finally{
			cm.closeConnection();
		}
	}
}
