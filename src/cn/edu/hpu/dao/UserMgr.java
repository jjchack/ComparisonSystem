package cn.edu.hpu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.hpu.model.Product;
import cn.edu.hpu.model.User;
import cn.edu.hpu.utils.DBUtil;
import cn.edu.hpu.utils.PageBean;
import cn.edu.hpu.utils.Sort1;
import cn.edu.hpu.utils.Sort2;

public class UserMgr<T> {
	
	private UserMgr(){}
	
	private static UserMgr um = null;
	/**
	 * 使用单例模式获取usermgr实例
	 */
	public static UserMgr getInstance() {
		if(um == null) {
			synchronized(UserMgr.class) {
				if(um == null) {
					um = new UserMgr();
				}
			}
		}
		return um;
	}
	/**
	 * 注册用户
	 */
	public static boolean registerUser(User u) {
		boolean flag = false;
		String sql = "insert into user(id,username,password,account) values(?,?,?,?)";
		Object params[] = {null,u.getUsername(),u.getPassword(),u.getAccount()};
		try {
			int count = DBUtil.getQr().update(sql,params);
			if(count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 用户登录
	 */
	public static User login(String username, String password) {
		User u = null;
		
		String sql = "select * from user where username = ? and password = ?";
		Object params[] = {username, password};
		
		try {
			u = DBUtil.getQr().query(sql, params,new BeanHandler<User>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}
	
	public <T> void getListProducts(String prperty,PageBean<T> pb,Class clazz,String tableName) {
		int totalcount = this.getTotalCount(tableName,prperty);
		pb.setTotalCount(totalcount);
		if(pb.getCurrentPage()<=0){
			pb.setCurrentPage(1);
		}
		else if(pb.getCurrentPage()>pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());
		}
		int currentPage = pb.getCurrentPage();
		int index = (currentPage-1)*pb.getPageSize();
		int pageSize = pb.getPageSize();
		String sql = "select * from "+tableName+" where property= '"+prperty+"' limit ?,?";
		try {
			List<T> pageData = DBUtil.getQr().query(sql, new BeanListHandler<T>(clazz), index,pageSize);
			pb.setPageData(pageData);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public <T> void getListProductsByName(String name,PageBean<T> pb,Class clazz,String tableName) {
		int totalcount = this.getTotalCountByName(tableName,name);
		pb.setTotalCount(totalcount);
		if(pb.getCurrentPage()<=0){
			pb.setCurrentPage(1);
		}
		else if(pb.getCurrentPage()>pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());
		}
		int currentPage = pb.getCurrentPage();
		int index = (currentPage-1)*pb.getPageSize();
		int pageSize = pb.getPageSize();
		String sql = "select * from "+tableName+" where name= '"+name+"' limit ?,?";
		try {
			List<T> pageData = DBUtil.getQr().query(sql, new BeanListHandler<T>(clazz), index,pageSize);
			pb.setPageData(pageData);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	public int getTotalCountByName(String str,String name) {
		String sql = "select count(*) from "+str+" where name='"+name+"'";
		try {
			Long count = DBUtil.getQr().query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public int getTotalCount(String str,String property) {
		String sql = "select count(*) from "+str+" where property='"+property+"'";
		try {
			Long count = DBUtil.getQr().query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public boolean addProduct(Product p) {
		boolean flag = false;
		String sql = "insert into product(id,name,property,source,descs,coment1,coment2,coment3,price,img) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		Object params[] = {null,p.getName(),p.getProperty(),p.getSource(),p.getDescs(),p.getComent1(),p.getComent2(),p.getComent3(),p.getPrice(),p.getImg()};
		try {
			System.out.println();
			int count = DBUtil.getQr().update(sql, params);
			if(count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public static List<Product> getre(List<Product> list){
		List<Product> resultls = new ArrayList<Product>();
		Map<String, Map<String, Integer>> userPerfMap = new HashMap<String, Map<String, Integer>>();
		Map<String, Integer> pref5 = null;
		for(int i=0;i<list.size();i++){
			Map<String, Integer> pref = new HashMap<String, Integer>();
			pref.put("A", list.get(i).getComent1());
			pref.put("B", list.get(i).getComent2());
			pref.put("C", list.get(i).getComent3());
			userPerfMap.put("p"+(i+1), pref);
			if(i==0){
				pref5 = pref;
			}
		}
	    Map<String, Double> simUserSimMap = new HashMap<String, Double>();  
	    String output1 = "皮尔逊相关系数:", output2 = "欧几里得距离:";  
	    for (Entry<String, Map<String, Integer>> userPerfEn : userPerfMap.entrySet()) {  
	        String userName = userPerfEn.getKey();  
	        if (!"p5".equals(userName)) {  
	        double sim = getUserSimilar(pref5, userPerfEn.getValue());  
	        //double distance = getEuclidDistance(pref5, userPerfEn.getValue());  
	        output1 += "p5与" + userName + "之间的相关系数:" + sim + ",";  
	        //output2 += "p5与" + userName + "之间的距离:" + distance + ",";  
	        simUserSimMap.put(userName, sim); 
	        if(sim >= 0.6){
	        	int pp = Integer.parseInt(String.valueOf(userName.toCharArray()[1]));
	        	System.out.println(pp);
	        	Product pw = list.get(pp-1);
	        	pw.setSim(sim);
	        	resultls.add(pw);
		    }
	        }  
	    }  
	    System.out.println(output1);  
	   return resultls;
	   // System.out.println(output2);  
	      
	}
	
	public static double getUserSimilar(Map<String, Integer> pm1, Map<String, Integer> pm2) {  
		    int n = 0;// 数量n  
		    int sxy = 0;// Σxy=x1*y1+x2*y2+....xn*yn  
		    int sx = 0;// Σx=x1+x2+....xn  
		    int sy = 0;// Σy=y1+y2+...yn  
		    int sx2 = 0;// Σx2=(x1)2+(x2)2+....(xn)2  
		    int sy2 = 0;// Σy2=(y1)2+(y2)2+....(yn)2  
		    for (Entry<String, Integer> pme : pm1.entrySet()) {  
		        String key = pme.getKey();  
		        Integer x = pme.getValue();  
		        Integer y = pm2.get(key);  
		        if (x != null && y != null) {  
		        n++;  
		        sxy += x * y;  
		        sx += x;  
		        sy += y;  
		        sx2 += Math.pow(x, 2);  
		        sy2 += Math.pow(y, 2);  
		        }  
		    }  
		    // p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));  
		    double sd = sxy - sx * sy / n;  
		    double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));  
		    return Math.abs(sm == 0 ? 1 : sd / sm);  
		    }  

	public List<Product> getOp1List(List<Product> ls) {
		List<Product> list = ls;
		Collections.sort(list,new Sort1());
		return list;
		
	}
	
	public List<Product> getOp2List(List<Product> ls) {
		List<Product> list = new ArrayList<Product>();
		list.add(ls.get(0));
		return list;
		
	}
	
	public static Product getProductById(String id) {
		Product p = null;
		
		String sql = "select * from product where id = ?";
		Object params[] = {Integer.parseInt(id)};
		
		try {
			p = DBUtil.getQr().query(sql, params,new BeanHandler<Product>(Product.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	
	public List<Product> getRemoveSimilarProduct(List<Product> ls) {
		if(!ls.isEmpty()){
            for(int i=0;i<ls.size();i++){
                for(int j=i+1;j<ls.size();j++){
                    if(ls.get(i).getName().equals(ls.get(j).getName()) &&
                    		ls.get(i).getDescs().equals(ls.get(i).getDescs()) &&
                    		ls.get(i).getPrice()==ls.get(i).getPrice() &&
                    		ls.get(i).getProperty().equals(ls.get(i).getProperty()) &&
                    		ls.get(i).getSource().equals(ls.get(i).getSource()))
                    		{
                        ls.remove(j);
                    }
                }
            }
        }
		return ls;
	}
	public List<Product> getOp3List(List<Product> ls) {
		Collections.sort(ls, new Sort2());
		return ls;
	}
	
	public List<T> getAllListProductsByName(String name,Class clazz,String tableName) {
		String sql = "select * from "+tableName+" where name= '"+name+"'";
		try {
			List<T> list = DBUtil.getQr().query(sql, new BeanListHandler<T>(clazz));
			return list;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
