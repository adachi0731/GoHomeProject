package com.fullneflower.ghp.bean;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.dao.ConnectionManager;
import com.fullneflower.ghp.dao.ItemDao;
import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.ItemVo;

public class SelectBean implements  FullneflowerBean{
	/**
	 * Selectに関する処理をします。
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		// TODO 自動生成されたメソッド・スタブ
		/**
		 * ・selectAll.jspからの値を受け取る
		 * ・Daoの準備
		 *
		 */
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
			//itemVoという名で同期した値を飛ばす
			request.setAttribute("itemVo", tmpVo);
			request.setAttribute("itemSelect", itemSelect);


			return "success";
		}catch(GhpException e){
			return "failure";
		}
		}

	}

