package cn.edu.hpu.utils;

import java.util.Comparator;

import cn.edu.hpu.model.Product;

public class Sort2 implements Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {
		int r = -1;
		double q1 = o1.getSim()*0.6+o1.getPrice()*0.4;
		double q2 = o2.getSim()*0.6+o2.getPrice()*0.4;
		if(q1 > q2) {
			r = 1;
		}
		return r;
	}

}
