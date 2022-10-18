package koalaTest.order.model.vo;

import java.util.ArrayList;

public class OrderPageData {
	private ArrayList<Order> list;
	private String pageNavi;
	
	public OrderPageData() {
		super();
	}
	
	public OrderPageData(ArrayList<Order> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	
	public ArrayList<Order> getList() {
		return list;
	}
	public void setList(ArrayList<Order> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
