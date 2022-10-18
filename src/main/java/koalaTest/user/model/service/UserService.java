package koalaTest.user.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import koalaTest.order.model.vo.Order;
import koalaTest.order.model.vo.OrderDetail;
import koalaTest.order.model.vo.OrderDetailData;
import koalaTest.order.model.vo.OrderPageData;
import koalaTest.user.model.dao.UserDao;
import koalaTest.user.model.vo.User;
import koalaTest.user.model.vo.UserPageData;

public class UserService {
	private UserDao dao;
	
	public UserService() {
		super();
		dao = new UserDao();
	}

	public int insertUser(User u) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertUser(conn, u);
		if (result != 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public User selectOneUser(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		User u = dao.selectOneUser(conn, userId);
		JDBCTemplate.close(conn);
		return u;
	}
	
	public User checkPhone(String userPhone) {
		Connection conn = JDBCTemplate.getConnection();
		User u = dao.checkPhone(conn, userPhone);
		JDBCTemplate.close(conn);
		return u;
	}

	public User checkEmail(String userEmail) {
		Connection conn = JDBCTemplate.getConnection();
		User u = dao.checkEmail(conn, userEmail);
		JDBCTemplate.close(conn);
		return u;
	}
	
	//유저로그인용
	public User selectOneUser(User user) {
		Connection conn = JDBCTemplate.getConnection();
		User u = dao.selectOneUser(conn, user);
		JDBCTemplate.close(conn);
		return u;
	}

	//회원 수정
	public int updateUser(User u) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateUser(conn, u);
		if (result != 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<User> selectAllUser() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<User> list = dao.selectAllUser(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	//관리자페이지에서 개별 레벨변경
	public int changeLevel(int userNo, int userLevel) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changeLevel(conn, userNo,userLevel);
		if (result != 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public boolean checkedChangeLevel(String num, String level) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		StringTokenizer sT2 = new StringTokenizer(level,"/");
		boolean result = true;
		while(sT1.hasMoreTokens()) {
			int userNo = Integer.parseInt(sT1.nextToken());
			int userLevel = Integer.parseInt(sT2.nextToken());
			int changeResult = dao.changeLevel(conn, userNo, userLevel);
			if(changeResult==0) {
				result = false;
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public UserPageData selectUserList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection(); 
		//1.한페이지당 게시물 수 지정 ->10개
		int numPerPage = 10;
		// 요청페이지 1 -> 가장 최신 글 1~10
		// 요청페이지 2 -> 가장 최신 글 11~20
		int end = numPerPage*reqPage;
		int start = end - numPerPage + 1;//20 - 10 +1
		ArrayList<User> list = dao.selectUserList(conn,start,end);
		//페이징처리
		//전체페이지 수 계산 ->전체 게시물 수 조회
		int totalCount = dao.selectUserCount(conn);
		int totalPage =0;
		 if(totalCount%numPerPage ==0) {
			 totalPage = totalCount/numPerPage;
		 }else {
			 totalPage = totalCount/numPerPage+1;
		 }
		 
		 //지정해줘야할 값 페이지 네비게이션 사이즈
		 int pageNaviSize =5;
		 
		 //페이지 네비게이션 시작번호지정
		 //reqPage 1~5 ->1부터 시작해서 5개
		 //reqPage 6~10 -> 6,7,8,9,10
		 int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		 
		 //페이지 네비게이션 제작 시작
		 String pageNavi="<ul class='pagination circle-style'>";
		 //이전버튼
		 if(pageNo !=1) {
			 pageNavi +="<li>";
			 pageNavi +="<a class='page-item' href='/adminPage.do?reqPage="+(pageNo-1)+"'>";
			 pageNavi +="<span class='material-icons'>chevron_left</span>";
			 pageNavi +="</a></li>";
		 }
		 //페이지숫자
		 for(int i =0;i<pageNaviSize;i++) {
			 if(pageNo == reqPage) {
				 pageNavi +="<li>";
				 pageNavi +="<a class='page-item active-page' href='/adminPage.do?reqPage="+pageNo+"'>";
				 pageNavi += pageNo;
				 //pageNavi +="<span class='material-icons'>chevron_left</span>";
				 pageNavi +="</a></li>";
			 }else {
				 pageNavi +="<li>";
				 pageNavi +="<a class='page-item' href='/adminPage.do?reqPage="+pageNo+"'>";
				 pageNavi += pageNo;
				 //pageNavi +="<span class='material-icons'>chevron_left</span>";
				 pageNavi +="</a></li>";
			 }
			 pageNo++;
			 if(pageNo>totalPage) {
				break; 
			 }
		 }
		 //다음버튼
		 if(pageNo<=totalPage) {
			 pageNavi +="<li>";
			 pageNavi +="<a class='page-item' href='/adminPage.do?reqPage="+pageNo+"'>";
			 pageNavi +="<span class='material-icons'>chevron_right</span>";
			 pageNavi +="</a></li>";
		 }
		 pageNavi += "</ul>";
		 UserPageData npd = new UserPageData(list,pageNavi);
		 JDBCTemplate.close(conn);
		 
		 return npd;
	}

	public int deleteUser(User u) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteUser(conn, u);
		if (result != 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}	
	
	/*
	public ArrayList<Order> selectOrder(int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Order> order = dao.selectOrder(conn, userNo);
		JDBCTemplate.close(conn);
		return order;
	}
	*/
	
	

	public OrderDetailData selectOrderDetail(int orderNo) {
		Connection conn = JDBCTemplate.getConnection();
		Order o = dao.selectOrderInfo(conn, orderNo);
		ArrayList<OrderDetail> orderDetail = dao.selectOrderDetail(conn, orderNo);
		OrderDetailData odd = new OrderDetailData(o, orderDetail);
		JDBCTemplate.close(conn);
		return odd;
	}

	public int requestCancelOrder(int orderNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.requestCancelOrder(conn, orderNo);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public OrderPageData selectMyOrderList(int reqPage, int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = numPerPage * reqPage;
		int start = end - numPerPage + 1;
		ArrayList<Order> order = dao.selectMyOrderList(conn, userNo, start, end);
		
		int totalCount = dao.selectMyOrderCount(conn, userNo);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage + 1;			
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination circle-style'>";
		if(pageNo != 1) { // pageNo가 1이 아닐 때만 이전 버튼을 붙임
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/myOrderPage.do?userNo="+userNo+"&reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) { // 페이지 번호가 내가 선택한 페이지인 경우 색깔을 칠함
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/myOrderPage.do?userNo="+userNo+"&reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else { // 페이지 번호가 내가 선택한 페이지가 아닌 경우 색깔을 칠하지 않음
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/myOrderPage.do?userNo="+userNo+"&reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++; // 다음 페이지 번호 진행하기 위해 페이지 번호 올림
			if(pageNo > totalPage) { // 만약 페이지 번호가 총 페이지 수보다 커지면
				break;
			}
		}
		// 다음 버튼 표시
		if(pageNo <= totalPage) { // 총 페이지 수가 지금 pageNo보다 크거나 같으면 (= 아직 페이징 처리 해야할 것들이 남아있으면) 다음 버튼을 붙임
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/myOrderPage.do?userNo="+userNo+"&reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		OrderPageData opd = new OrderPageData(order, pageNavi);
		JDBCTemplate.close(conn);
		return opd;
	}
	

	public User findId(String userEmail) {
		Connection conn = JDBCTemplate.getConnection();
		User u = dao.findId(conn, userEmail);
		JDBCTemplate.close(conn);
		return u;
	}

	public User findPw(String userIdVal, String userEmail) {
		Connection conn = JDBCTemplate.getConnection();
		User u = dao.findPw(conn,userIdVal, userEmail);
		JDBCTemplate.close(conn);
		return u;
	}
}
