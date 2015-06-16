package com.fullneflower.ghp.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullneflower.ghp.bean.FullneflowerBean;
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
			String param = request.getParameter("action");
			ResourceBundle rb = ResourceBundle.getBundle("Resource");
			String clazz = rb.getString(param);
			Class cls = Class.forName(clazz);
			Object obj = cls.newInstance();
			FullneflowerBean bean = (FullneflowerBean)obj;

			String ret = bean.execute(request, response);
			String path = rb.getString(param + "." + ret);
			System.out.println("遷移先のパス:" + path);

			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} catch (GhpException e) {
			//システムエラー
			e.printStackTrace();
			RequestDispatcher deispatcher = request.getRequestDispatcher("/pages/log/error.jsp");
			deispatcher.forward(request, response);
		}catch (Exception e) {
			RequestDispatcher deispatcher = request.getRequestDispatcher("/pages/log/error.jsp");
			deispatcher.forward(request, response);
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
