package koalaTest.order.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import koalaTest.cart.model.dao.CartDao;
import koalaTest.cart.model.vo.Cart;
import koalaTest.order.model.dao.OrderDao;
import koalaTest.order.model.vo.Order;
import koalaTest.order.model.vo.OrderPageData;

public class OrderService {
	
	private OrderDao dao;

	public OrderService() {
		super();
		dao = new OrderDao();
	}

	public int insertOrder(Order o) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertOrder(conn, o);
		int detailResult = 0;
		if(result > 0) {
			ArrayList<Cart> cart = new CartDao().selectCart(conn, o.getUserNo());
			o.setOrderNo(dao.getOrderNo(conn, o.getUserNo()));
			for(int i=0; i<cart.size(); i++) {
				detailResult = dao.insertOrderDetail(conn, o.getOrderNo(), cart.get(i));
				if(detailResult == 0) {
					result = 0;
					JDBCTemplate.rollback(conn);
					break;
				}
			}
			if(detailResult > 0) {
				int deleteResult = dao.deleteCart(conn, o.getUserNo());
				if(deleteResult > 0) {
					JDBCTemplate.commit(conn);					
				} else {
					result = 0;
					JDBCTemplate.rollback(conn);					
				}
			}
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public OrderPageData selectOrderList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = numPerPage * reqPage;
		int start = end - numPerPage + 1;
		ArrayList<Order> order = dao.selectOrderList(conn, start, end);
		
		int totalCount = dao.selectOrderCount(conn);
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
			pageNavi += "<a class='page-item' href='/orderPage.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) { // 페이지 번호가 내가 선택한 페이지인 경우 색깔을 칠함
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/orderPage.do?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else { // 페이지 번호가 내가 선택한 페이지가 아닌 경우 색깔을 칠하지 않음
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/orderPage.do?reqPage="+pageNo+"'>";
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
			pageNavi += "<a class='page-item' href='/orderPage.do?reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		OrderPageData opd = new OrderPageData(order, pageNavi);
		JDBCTemplate.close(conn);
		return opd;
	}

	public int insertDirectOrder(Order o, int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertOrder(conn, o);
		int detailResult = 0;
		if(result > 0) {
			o.setOrderNo(dao.getOrderNo(conn, o.getUserNo()));
			detailResult = dao.insertOrderDetail(conn, o, bookNo);
			if(detailResult > 0) {
				JDBCTemplate.commit(conn);
			} else {
				result = 0;
				JDBCTemplate.rollback(conn);
			}
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateOrderStatus(int orderNo, String orderStatus) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateOrderStatus(conn, orderNo, orderStatus);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public boolean checkedChangeStatus(String orderNoArr, String orderStatusArr) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(orderNoArr, "/"); // 배열을 [/]를 기준으로 나눠 토큰(문자열)로 저장
		StringTokenizer sT2 = new StringTokenizer(orderStatusArr, "/"); 
		boolean result = true; // 레벨 변경이 성공했는지 저장할 변수
		while(sT1.hasMoreTokens()) { // sT1에 토큰이 더 있으면 true
			int orderNo = Integer.parseInt(sT1.nextToken()); // sT1의 토큰 하나(문자열)을 꺼냄
			String orderStatus = sT2.nextToken(); // sT1의 토큰 하나(문자열)을 꺼냄
			int changeResult = dao.updateOrderStatus(conn, orderNo, orderStatus);
			if(changeResult == 0) { // dao에서 실패했다면
				result = false;
				break; // 한번이라도 실패하면 break
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
}
