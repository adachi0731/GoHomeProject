package com.fullneflower.ghp.bean;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.dao.ConnectionManager;
import com.fullneflower.ghp.dao.ItemDao;
import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.ItemVo;

public class InsertBean implements  FullneflowerBean{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		// TODO 自動生成されたメソッド・スタブ
		 ConnectionManager connectionManager = new ConnectionManager();
	        try {
	            Connection connection = connectionManager.getConnection();
	             ItemDao itemDao = new ItemDao(connection);
	             ItemVo itemVo = new ItemVo();

	            itemVo.setItemNo(request.getParameter("itemNo"));
	     		itemVo.setItemName(request.getParameter("itemName"));
	     		itemVo.setItemURL(request.getParameter("URL"));
	     		int untiPrice=  Integer.parseInt(request.getParameter("unitPrice"));
	     		itemVo.setUnitPrice(untiPrice);
	     		itemVo.setSize(request.getParameter("size"));
	     		itemVo.setAssortmentCode(request.getParameter("assortment"));
	     		itemVo.setCategoryCode(request.getParameter("category"));

	            int result = itemDao.insert(itemVo);
	            connectionManager.commit();
	            System.out.println(result);
	            if(result == 1){
	            	return "success";
	            }else{
	            	return "failure";
	            }

	        } catch (RuntimeException e) {
	            connectionManager.rollback();
	            throw e;
	        } finally {
	            connectionManager.closeConnection();
	        }
	}

}
