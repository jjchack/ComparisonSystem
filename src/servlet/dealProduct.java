package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hpu.dao.UserMgr;
import cn.edu.hpu.model.Product;
import cn.edu.hpu.utils.PageBean;

public class dealProduct extends HttpServlet {

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
		
		String op = request.getParameter("op");
		
		if(op != null) {
			int opCode = Integer.parseInt(op);
			List<Product> ls = (List<Product>) request.getSession().getAttribute("ls");
			switch(opCode) {
				case 0: {
					request.getSession().setAttribute("ls", ls);
					response.sendRedirect(request.getContextPath()+"/dealProduct1.jsp");
					break;
				}
				case 1: {
					ls = UserMgr.getInstance().getOp1List(ls);
					request.getSession().setAttribute("ls", ls);
					response.sendRedirect(request.getContextPath()+"/dealProduct2.jsp");
					break;
				}
				case 2: {
					List l1 = UserMgr.getInstance().getOp2List(ls);
					request.getSession().setAttribute("l1", l1);
					request.getSession().setAttribute("ls", ls);
					response.sendRedirect(request.getContextPath()+"/dealProduct3.jsp");
					break;
				}
				case 3: {
					ls = UserMgr.getInstance().getOp3List(ls);
					request.getSession().setAttribute("ls", ls);
					response.sendRedirect(request.getContextPath()+"/dealProduct2.jsp");
					break;
				}
				default: break;
			}
			return;
			
		} else {
			String id = request.getParameter("id");
			PageBean<Product> pb = (PageBean<Product>) request.getSession().getAttribute("pb");
			Product p = UserMgr.getInstance().getProductById(id);
			if(p == null) {
				p = new Product();
				p.setName("贵宾犬");
			}
			
			List<Product> ls = UserMgr.getInstance().getAllListProductsByName(p.getName(),Product.class, "product");
			request.getSession().setAttribute("pre", ls);
			//去重
			ls = UserMgr.getInstance().getre(ls);
			for(int i=0; i<ls.size();i++){
				System.out.println(ls.get(i).toString());
			}
			ls = UserMgr.getInstance().getRemoveSimilarProduct(ls);
			request.getSession().setAttribute("ls", ls);
			response.sendRedirect(request.getContextPath()+"/dealProduct1.jsp");
		}
	
	}

}
