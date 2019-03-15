package cn.edu.hpu.model;

public class Product {
	
	private int id;
	private String name;
	private String property;
	private String source;
	private String descs;
	private String img;
	private int coment1;
	private int coment2;
	private int coment3;
	private int price;
	private double sim;
	
	public double getSim() {
		return sim;
	}
	public void setSim(double sim) {
		this.sim = sim;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDescs() {
		return descs;
	}
	public void setDescs(String desc) {
		this.descs = desc;
	}
	public int getComent1() {
		return coment1;
	}
	public void setComent1(int coment1) {
		this.coment1 = coment1;
	}
	public int getComent2() {
		return coment2;
	}
	public void setComent2(int coment2) {
		this.coment2 = coment2;
	}
	public int getComent3() {
		return coment3;
	}
	public void setComent3(int coment3) {
		this.coment3 = coment3;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", property=" + property + ", source="
				+ source + ", descs=" + descs + ", img=" + img + ", coment1="
				+ coment1 + ", coment2=" + coment2 + ", coment3=" + coment3
				+ ", price=" + price + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
