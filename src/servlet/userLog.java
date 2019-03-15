package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hpu.dao.UserMgr;
import cn.edu.hpu.model.User;

public class userLog extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		User u = UserMgr.getInstance().login(userName, passWord);
		
		if( u!=null) {
			request.getSession().setAttribute("username", u.getUsername());
			response.sendRedirect(request.getContextPath()+"/productList.jsp");
		} else {
			request.setAttribute("msg", "用户名或密码输入有误。。。");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

}
