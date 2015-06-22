package com.fullneflower.ghp.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.dao.ConnectionManager;
import com.fullneflower.ghp.dao.ItemDao;
import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.ItemVo;

/**
 * インターフェース(FullneflowerBean)の実装
 * @author Owner
 */
public class DeleteSelectBean implements  FullneflowerBean{
	/**
	 * [機能]削除チェックのついた商品を基に、データベースとVoの「削除フラグ」に値を入れるメソッド
	 * [説明]
	 * @author 森
	 * @param HttpServletRequest request, HttpServletResponse response
	 * @return "success" or "failer" or "error"
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)throws GhpException{
		//コネクションを取る
		Connection connection =null;
		ConnectionManager cm = new ConnectionManager();
		connection= cm.getConnection();
		ItemDao itemDao= new ItemDao(connection);
		/*配列の値を取り出して入れ物に入れる*/
		String[] delItemNo = request.getParameterValues("check");
		ResourceBundle msgresult = ResourceBundle.getBundle("Message");

		try{
			/*削除のチェックボタンにチェックは入っているかどうか確認*/
			if(delItemNo==null){
				List<ItemVo> itemList= itemDao.selectAll();
				request.setAttribute("itemList", itemList);
				String param = "ItemDeleteSelect";

				String error = msgresult.getString(param);
				request.setAttribute("erro", error);
				return "failure";
			}
			/*チェックされた項目に削除済の項目があれば一覧画面で遷移させエラー文を出力する*/
			ArrayList<ItemVo> dataListChk = new ArrayList<ItemVo>();
			for(int i=0; i<delItemNo.length; i++){
				ItemVo itemVo =itemDao.deleteCheck(delItemNo[i]);
				dataListChk.add(itemVo);
			}
			/*未削除の項目の数とチェックされたボタンの数を比較
			 * 一致しなければ、一覧画面にエラー文を出力
			 */
			if(delItemNo.length!=dataListChk.size()){
				String param1= "ItemDeleteSelect";
				String error1 = msgresult.getString(param1);
				request.setAttribute("erro", error1);
				return "failure";
			}

			/*
			 *在庫の有無を確認
			 * ない場合は削除可能なので削除確認画面へ
			 * ある場合は削除できないので一覧画面へ遷移しエラー文を出力
			 */
			ArrayList<ItemVo> dataList = new ArrayList<ItemVo>();
			//配列の中身の数だけDAOにお願いします
			for(int i=0; i<delItemNo.length; i++){
				//DAOが取得した結果はリストに
				ItemVo itemVo =itemDao.deleteSelect(delItemNo[i]);
				dataList.add(itemVo);
				cm.commit();
				System.out.println(itemVo+"deleSele");
			}
			if(delItemNo.length!=dataList.size()){
				String param2 = "emDeleteNot";
				String error2 = msgresult.getString(param2);
				request.setAttribute("erro", error2);
				return "failure";
			}if(delItemNo.length==dataList.size()){
				request.setAttribute("dataList", dataList);
				return "success";
			}
		}catch(GhpException e){
			cm.rollback();
			throw new GhpException("DeleteBeanで失敗しました", e);
		}finally{
			cm.closeConnection();
		}
		return "error";
	}
}

