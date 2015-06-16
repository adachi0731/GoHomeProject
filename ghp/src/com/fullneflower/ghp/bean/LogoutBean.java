package com.fullneflower.ghp.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullneflower.ghp.exception.GhpException;
/**
 * ログアウトリンクを押された場合の処理
 * @return "error","failure"
 * @param request,response
 * @throws GhpException
 */
public class LogoutBean  implements  FullneflowerBean{
	/**
	 * セッションが保持している場合はセッションを切断し、”failure”ログイン画面へ遷移
	 * セッションが切断できなかった場合は”error”システムエラー画面へ遷移。
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException{
		try{
			 HttpSession session = request.getSession(false);
			 if(session != null){
				 session.invalidate();
			 }
			 return "failure";
		 }catch(Exception e){
			 /*エラーが起きたのでシステムエラー画面へ遷移*/
			 e.printStackTrace();
			 return "error";
		 }
	}
}
