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

public class DeleteBean implements  FullneflowerBean{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws GhpException {

		ConnectionManager cm = new ConnectionManager();
		/*入力された値を受け取る */
		String[] delete = request.getParameterValues("check");
		if(delete==null){
			Connection connection=cm.getConnection();
			ItemDao itemDao = new ItemDao(connection);
			List<ItemVo> itemList= itemDao.selectAll();
			request.setAttribute("itemList", itemList);

			String param = "error.ItemDeleteSelect";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			String erro = msgresult.getString(param); //errorメッセージ
			request.setAttribute("erro", erro);


			return "failure";

		}



		return "success";
	}
}




/*String delete = request.getParameter("check");
		if(delete == null){
			String param = "error.ItemDeleteSelect";
			ResourceBundle msgresult = ResourceBundle.getBundle("Message");
			String erro = msgresult.getString(param); //errorメッセージ
			request.setAttribute("erro", erro);
			return "failure";
		}else{
			return"success";
		}

	}
}*/