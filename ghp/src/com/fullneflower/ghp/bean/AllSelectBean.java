package com.fullneflower.ghp.bean;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.dao.ConnectionManager;
import com.fullneflower.ghp.dao.ItemDao;
import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.ItemVo;

public class AllSelectBean implements  FullneflowerBean{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {

		ConnectionManager cm = new ConnectionManager();
		try {
			Connection connection = cm.getConnection();
			ItemDao itemDao = new ItemDao(connection);
			List<ItemVo> itemList= itemDao.selectAll();
			request.setAttribute("itemList", itemList);
			System.out.println(itemList);
			//ItemVo tmpVo = itemList.get(0);
			//System.out.println(tmpVo.getItemNo());
			//request.setAttribute("itemVo", tmpVo);

			return "success";
		} finally {
			cm.closeConnection();
		}
	}
}




