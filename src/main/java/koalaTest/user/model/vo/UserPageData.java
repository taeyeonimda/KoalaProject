package koalaTest.user.model.vo;

import java.util.ArrayList;

public class UserPageData {
	private ArrayList<User> list;
	private String pageNavi;
	
	public UserPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserPageData(ArrayList<User> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<User> getList() {
		return list;
	}
	public void setList(ArrayList<User> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
