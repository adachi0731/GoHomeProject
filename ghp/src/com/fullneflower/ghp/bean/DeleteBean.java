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
 *インターフェース(FullneflowerBean)の実装
 * @author Owner
 */
public class DeleteBean implements  FullneflowerBean{
	/**
	 * [機能]チェックボックスに削除チェックがついた商品を削除し、
	 *         データベースで削除した商品数を受け取るメソッド
	 * [説明]削除チェックがついた商品数 = データベースで削除した商品数  のとき"success"を返す。
	 *         それ以外のとき”failer"を返す。
	 *@author 森
	 *@param HttpServletRequest request, HttpServletResponse response
	 *@return "success" or "failer"
	 *@throws GhpException
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		//コネクションを取る
		Connection connection =null;
		ConnectionManager cm = new ConnectionManager();
		connection= cm.getConnection();
		ItemDao itemDao= new ItemDao(connection);
		/*配列の値を取り出して入れ物に入れる*/
		String[] delItemNo = request.getParameterValues("delItemNo");
		ResourceBundle msgresult = ResourceBundle.getBundle("Message");
		String complete = "";
		try{
			int resultCnt=0;
			for(int i=0; i<delItemNo.length; i++){
				//DAOが取得した結果はリストに
				int itemVo =itemDao.delete(delItemNo[i]);
				//DELETEできた件数をresultCntに加算
				resultCnt += itemVo;
				if(resultCnt==delItemNo.length){
					cm.commit();
					List<ItemVo> itemList= itemDao.selectAll();
					request.setAttribute("itemList", itemList);
					String param = "ItemDeleteCheck";
					msgresult = ResourceBundle.getBundle("Message");
					complete = msgresult.getString(param);
					request.setAttribute("complete", complete);

				}
			}
		}catch(GhpException e){
			cm.rollback();
			throw new GhpException("DeleteBeanで失敗しました", e);
		}finally{
			cm.closeConnection();
		}
		return "success";
	}
}