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
 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {
		//コネクションを取る
		Connection connection =null;
		ConnectionManager cm = new ConnectionManager();
		connection= cm.getConnection();
		try{
			//配列をJSPを持ってくる
			String[] delItemNo = request.getParameterValues("delItemNo");

			//DAOにコネクションあげる
			ItemDao itemDao= new ItemDao(connection);

			/*入力された値を受け取る */
			String[] delete = request.getParameterValues("check");
			if(delete==null){
				List<ItemVo> itemList= itemDao.selectAll();
				request.setAttribute("itemList", itemList);
				String param = "error.ItemDeleteSelect";
				ResourceBundle msgresult = ResourceBundle.getBundle("Message");
				String erro = msgresult.getString(param); //errorメッセージ
				request.setAttribute("erro", erro);
				return "failure";
			}
/**
 * [機能]削除チェックのついた商品数を求める。
 */
			int resultCnt=0;
			System.out.println("Delete前半戦");
			for(int i=0; i<delItemNo.length; i++){
				//DAOが取得した結果はリストに
				int itemVo =itemDao.delete(delItemNo[i]);
				//DELETEできた件数をresultCntに加算
				resultCnt += itemVo;
				System.out.println(resultCnt);
				cm.commit();
			}
			/**
			 * [機能]削除チェックのついた商品数とデータベースで削除した商品数が”＝”であるかを求める。
			 * [説明]商品数が同じであれば”success”
			 *          異なれば”failer”を返す。
			 * @return "success" or "failer"
			 */
			System.out.println("Delete後半戦");
			if(resultCnt==delItemNo.length){
				System.out.println("Delete後半戦if");
				return "success";
			}else{
				return"failure";
			}
		}catch(GhpException e){
			cm.rollback();
			throw new GhpException("DeleteBeanで失敗しました", e);
		}finally{
			cm.closeConnection();
		}
	}
}
