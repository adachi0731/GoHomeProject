package com.fullneflower.ghp.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheck
 */
public class LoginCheck implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginCheck() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * [機能](フィルター) セッションの保持を確認
	 * @author 本多
	 * @param request response chain
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 *
     */
	 /*hiddenから値を持ってきてjudgeに入れる
	  getServletPath()でフィルターにかかる前の場所をpathに入れる
	 */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//chain.doFilter(request, response);
		System.out.println("フィルター-----------------");
		HttpSession session =((HttpServletRequest)request).getSession(false);
		String judge1 = request.getParameter("action");
		System.out.println("actionの中身"+judge1);
		String path =((HttpServletRequest)request).getServletPath();
		System.out.println("パスだよ：" + path);
		System.out.println("エラー確認"+ ("employee.login".equals(judge1) && "/controller".equals(path)));
		System.out.println("セッションだよ"+((HttpServletRequest)request).getSession(false));

		if(session != null){
			chain.doFilter(request, response);
		}else if(session == null){
			if("/pages/log/emplog.jsp".equals(path)||"/ghp/image/fullneflower_logo.png".equals(path)){
				chain.doFilter(request, response);
			}else if("employee.login".equals(judge1) || "employee.logout".equals(judge1)){
				chain.doFilter(request, response);
			}else{
				((HttpServletResponse)response).sendRedirect("/ghp/pages/log/emplog.jsp");
			}
		}
		System.out.println("--------------------------------");
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
