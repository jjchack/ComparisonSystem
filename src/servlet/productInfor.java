package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hpu.dao.UserMgr;
import cn.edu.hpu.model.Product;
import cn.edu.hpu.utils.PageBean;

public class productInfor extends HttpServlet {

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
		String property = request.getParameter("property");
		String pageNumber = request.getParameter("pageNumber");
		int value = Integer.parseInt(property);
		PageBean<Product> pb = new PageBean<Product>();
		
		if(pageNumber != null) {
			int p = Integer.parseInt(pageNumber);
			pb.setCurrentPage(p);
		}
		
		switch(value) {
			case 1 :
				property = "手机";break;
			case 2 :
				property = "电脑";break;
			case 3 :
				property = "汽车";break;
			case 4 :
				property = "摩托";break;
			case 5 :
				property = "家具";break;
			case 6 :
				property = "动物";break;
			case 7 :
				property = "书籍";break;
			case 8 :
				property = "时装";break;
			case 9 :
				property = "儿童玩具";break;
			default :
				property = "儿童玩具";break;
		}
		UserMgr.getInstance().getListProducts(property, pb, Product.class, "product");
		request.getSession().setAttribute("pb", pb);
		request.getSession().setAttribute("pname", property);
		request.getSession().setAttribute("property", request.getParameter("property"));
		response.sendRedirect(request.getContextPath()+"/productInfor.jsp");
	
	}

}
