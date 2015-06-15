package com.fullneflower.ghp.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.bean.fullneflowerBean;
import com.fullneflower.ghp.exception.GhpException;

/**
 * 制御情報取得クラス
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Getリクエストを受け取り遷移
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		try {
			String param = request.getParameter("action");  //employee.logout
			ResourceBundle rb = ResourceBundle.getBundle("Resource");
			String clazz = rb.getString(param); //com.fullneflower.ghp.bean.LogoutBean
			Class cls = Class.forName(clazz); // clsって名前でLogoutBeanというクラスを作る
			Object obj = cls.newInstance();//LogoutBeanをオブジェクト型のobという形でおぎゃー
			fullneflowerBean bean = (fullneflowerBean)obj;//オブジェクト型からfullneflowerBean型（インターフェース型）にする

			String ret = bean.execute(request, response);//success
			String path = rb.getString(param + "." + ret);//employee.login.success
			System.out.println("遷移先のパス:" + path);//遷移先のパス:employee.login.success

			RequestDispatcher rd = request.getRequestDispatcher(path);// /pages/log/menu.jsp
			rd.forward(request, response);
		} catch (GhpException e) {
			//システムエラー
			e.printStackTrace();
			System.out.println("uaaaaaaaaaaaaaaaaaaaaa");
			RequestDispatcher deispatcher = request.getRequestDispatcher("/pages/log/error.jsp");
			deispatcher.forward(request, response);
		}catch (Exception e) {
			//System.out.println("あらら");
		}
	}

	/**
	 * Postリクエストを受け取り遷移
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,  response);
	}
}
