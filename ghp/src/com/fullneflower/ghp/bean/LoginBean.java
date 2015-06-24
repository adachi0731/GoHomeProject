package com.fullneflower.ghp.bean;

import java.sql.Connection;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullneflower.ghp.dao.ConnectionManager;
import com.fullneflower.ghp.dao.EmployeeDao;
import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.EmployeeVo;

/**
 *ログイン関連の処理をします
 */

public class LoginBean implements  FullneflowerBean{
	//private static final long serialVersionUID = 1L;
	/**
	 * ・入力された値をチェックし、
	 * 不正なデータが入力された場合はfailureを返す。
	 * DAOに送り、返ってきた結果が1であればsuccessを返す。0であれば、failureを返す。
	 * @return 該当件数
	 * @param request,response
	 * @throws GhpException
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException{
		ConnectionManager cm = null;
		try{
			/*入力された値を受け取る */
			String empNo= request.getParameter("empno");
			String passWo = request.getParameter("passwd");

			/*担当者番号入力チェック*/
			if(empNo == null||"".equals(empNo)){
				String param = "logNullError";
				ResourceBundle msgresult = ResourceBundle.getBundle("Message");
				String erro = msgresult.getString(param); //errorメッセージ
				request.setAttribute("erro", erro);

				return "failure";
				/*パスワード入力チェック*/
			}
			if(passWo == null||"".equals(passWo)){
				String param = "logNullError";
				ResourceBundle msgresult = ResourceBundle.getBundle("Message");
				String erro = msgresult.getString(param); //errorメッセージ
				request.setAttribute("erro", erro);

				return "failure";
			}
			/*入力された値をVoに格納する*/
			EmployeeVo empVo =new EmployeeVo();
			empVo.setEmpNo(empNo);
			empVo.setPassword(passWo);

			/*コネクション取得*/
			cm=new ConnectionManager();
			Connection connection = cm.getConnection();

			/* DAOに入力された値を渡し、担当者IDとパスワードの一致するデータを検索*/
			EmployeeDao empDao= new EmployeeDao(connection);
			int result = empDao.search(empVo);

			/* 件数が1件の場合はセッションを付与し、successを返し、メニュー画面への遷移*/
			if (result==1) {
				/*セッション開始
				 * セッションがない場合、付与する*/
				HttpSession session =request.getSession(true);

				/* セッションに受け取った値を付与する*/
				session.setAttribute("empno", empNo);
				session.setAttribute("passwd", passWo);

				return "success";
			}
			/*DBに該当データがない場合 */
			else{
				String param = "logError";
				ResourceBundle msgresult = ResourceBundle.getBundle("Message");
				String erro = msgresult.getString(param); //errorメッセージ
				request.setAttribute("erro", erro);
			}
			/*エラーが起きたのでシステムエラー画面へ遷移 */
		}catch(GhpException e){
			throw new GhpException("LoginBeanでエラーが起きました", e);
		}finally{
			if (cm != null) {	//こんな感じばい
				cm.closeConnection();	//コネクションの切断
			}	//こんな感じばい
		}
		return "failure";
	}
}

