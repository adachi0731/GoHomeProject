package com.fullneflower.ghp.bean;

import java.sql.Connection;
import java.util.ArrayList;
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

		/*コネクションの宣言*/
		Connection connection=null;
		/*コネクションマネージャの宣言*/
		ConnectionManager cm = new  ConnectionManager();
		try{
			/*コネクションを取得する*/
			connection = cm.getConnection();
			/*DAOにconnectionを設定する*/
			ItemDao itemDao = new ItemDao(connection);
			/*配列の値を取り出して入れ物に入れる*/
			String[] deleteChk = request.getParameterValues("flower");

			/*
			 * deleteChkの中に削除フラグが0の項目があったら一覧画面で遷移させエラー文を出力する
			 * /
			/*配列の中身の数だけDAOを繰り返し、リストに詰める*/
			ArrayList<ItemVo> dataListChk = new ArrayList<ItemVo>();
			for(int i=0; i<deleteChk.length; i++){
				ItemVo itemVo =itemDao.deleteCheck(deleteChk[i]);
				dataListChk.add(itemVo);
			}
			/*削除された項目の数とチェックされたボタンの数*/
			if(deleteChk.length!=dataListChk.size()){
				String param = "ItemDeleteSelect";
				ResourceBundle msgresult = ResourceBundle.getBundle("Message");
				String erro = msgresult.getString(param);
				request.setAttribute("erro", erro);
				return "success";
			}

			/*
			 * 全部削除できるやつなんで（削除フラグが1のもの）、
			 * 配列の数と帰ってきた件数が一致しますかね？
			 */
			ArrayList<ItemVo> dataList = new ArrayList<ItemVo>();
			//配列の中身の数だけDAOにお願いします
			for(int i=0; i<deleteChk.length; i++){
				//DAOが取得した結果はリストに
				ItemVo itemVo =itemDao.deleteSelect(deleteChk[i]);
				dataList.add(itemVo);
				cm.commit();
				System.out.println(itemVo+"deleSele");
			}
			if(deleteChk.length==dataList.size()){
				request.setAttribute("dataList", dataList);
				return "success";
			}else{
				String param = "emDeleteNot";
				ResourceBundle msgresult = ResourceBundle.getBundle("Message");
				String erro = msgresult.getString(param); //errorメッセージ
				request.setAttribute("erro", erro);
				return "failure";
			}
		}catch(GhpException e){
			throw new GhpException("DeleteSelectBeanでエラー", e);
		}finally{
			cm.closeConnection();
		}
	}
}

