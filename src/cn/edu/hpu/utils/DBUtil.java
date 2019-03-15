package cn.edu.hpu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

public class DBUtil {

	private DBUtil() {}
	
	private static Properties p = new Properties();
	private static DataSource ds = null;
	private static QueryRunner qr = null;
	
	static {
		InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
		try {
			p.load(in);
			ds = BasicDataSourceFactory.createDataSource(p);
			qr = new QueryRunner(ds);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static QueryRunner getQr() {
		return qr;
	}
	
}
