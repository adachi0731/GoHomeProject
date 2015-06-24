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


public class PulldownBean implements  FullneflowerBean{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		// TODO 自動生成されたメソッド・スタブ
		ConnectionManager cm = null;
		cm=new ConnectionManager();

		Connection connection = cm.getConnection();
		ItemDao itemDao= new ItemDao(connection);

		//assortmentメソッドをassortmentListにつめる
		List<ItemAssortmentVo> assortmentList= itemDao.assortment();
		request.setAttribute("assortmentList", assortmentList);
		//categoryメソッドをcategoryListにつめる
		List<ItemCategoryVo> categoryList= itemDao.category();
		request.setAttribute("categoryList", categoryList);

		return "success";

	}
}
