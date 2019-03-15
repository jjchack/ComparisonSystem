package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hpu.dao.UserMgr;
import cn.edu.hpu.model.Product;
import cn.edu.hpu.utils.PageBean;

public class search extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String property = request.getParameter("property");
		String encode = request.getParameter("encode");
		if(encode!=null) {
			property = new String(property.getBytes("ISO8859-1"),"utf-8");
		}
		String pageNumber = request.getParameter("pageNumber");
		PageBean<Product> pb = new PageBean<Product>();
		if(pageNumber != null) {
			int p = Integer.parseInt(pageNumber);
			pb.setCurrentPage(p);
		}
		
		UserMgr.getInstance().getListProducts(property, pb
				, Product.class, "product");
		
		if(pb.getPageData() == null || pb.getPageData().size()==0) {
			if(pageNumber != null) {
				int p = Integer.parseInt(pageNumber);
				pb.setCurrentPage(p);
			}
			UserMgr.getInstance().getListProductsByName(property, pb, Product.class, "product");
		}
		
		request.getSession().setAttribute("pb", pb);
		request.getSession().setAttribute("search", property);
		response.sendRedirect(request.getContextPath()+"/serachrs.jsp");
	}

}
