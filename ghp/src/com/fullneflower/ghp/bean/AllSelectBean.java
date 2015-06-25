package com.fullneflower.ghp.bean;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.dao.ConnectionManager;
import com.fullneflower.ghp.dao.ItemDao;
import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.ItemVo;
/**
 * インターフェースの実装
 * @author Owner
 *
 */
public class AllSelectBean implements  FullneflowerBean{
	/**
	 * [機能]データベースに入力されている商品を一覧表示する
	 * [説明]ItemDaoでデータベースに登録されている商品を"list"でItemVoに詰める
	 * @param HttpServletRequest request, HttpServletResponse response
	 * @author 坂本
	 * @return "success"
	 */

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
		}catch(Exception e){
			cm.rollback();
			throw new GhpException("UpdateCheckBeanで失敗しました", e);
		}finally{
			cm.closeConnection();
		}
	}
}




