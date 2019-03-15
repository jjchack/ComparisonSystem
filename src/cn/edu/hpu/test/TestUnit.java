package cn.edu.hpu.test;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import cn.edu.hpu.dao.UserMgr;
import cn.edu.hpu.model.Product;
import cn.edu.hpu.model.User;
import cn.edu.hpu.utils.DBUtil;
import cn.edu.hpu.utils.PageBean;

public class TestUnit {

	@Test
	public void testQR(){
		System.out.println(DBUtil.getQr().toString());
	}
	
	@Test
	public void testRegister(){
		User u = new User();
		u.setAccount("2237222915@qq.com");
		u.setPassword("123.456");
		u.setUsername("to");
		System.out.println(UserMgr.getInstance().registerUser(u));
	}
	
	@Test
	public void testLogin(){
		User u = new User();
		u.setAccount("2237222915@qq.com");
		u.setPassword("1456");
		u.setUsername("tom");
		System.out.println(UserMgr.getInstance().login(u.getUsername(), u.getPassword()));
	}
	
	@Test
	public void testGetListProperties(){
		PageBean<cn.edu.hpu.model.Product> pb = new PageBean<cn.edu.hpu.model.Product>();
		pb.setCurrentPage(1);
		UserMgr.getInstance().getListProducts("手机", pb, Product.class, "product"); 
		System.out.println(pb.getPageData().size());
	}
	@Test
	public void testGetListPropertiesByName(){
		PageBean<cn.edu.hpu.model.Product> pb = new PageBean<cn.edu.hpu.model.Product>();
		UserMgr.getInstance().getListProductsByName("别克", pb, Product.class, "product"); 
		System.out.println(pb.getPageData().size());
	}
	@Test
	public void testAddProduct(){
		Product p = new Product();
		Random r = new Random();
		String sources[] = {"一号店","淘宝","天猫","苏宁","京东","国美在线","聚美优品"};
 		p.setComent1(1);
		p.setComent2(1);
		p.setComent3(1);
		p.setName("1");
		p.setProperty("1");
		p.setSource("1");
		p.setDescs("1");
		p.setPrice(558);
		System.out.println(UserMgr.getInstance().addProduct(p));
	}
	
	/*@Test
	public void testGetNewsList(){
		System.out.println(android.dao.UserMgr.getInstance().getAllNews(3, 3, "校园新闻", null).size());
	}*/
	
}
