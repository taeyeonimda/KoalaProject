package koalaTest.order.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import koalaTest.cart.model.vo.Cart;
import koalaTest.order.model.vo.Order;

public class OrderDao {

	public int insertOrder(Connection conn, Order o) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into orders values(ORDERS_NO_SEQ.NEXTVAL, ?, to_char(sysdate,'yyyy-mm-dd'), ?, ?, '상품준비중', ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, o.getUserNo());
			pstmt.setInt(2, o.getOrderQuan());
			pstmt.setInt(3, o.getOrderPrice());
			pstmt.setString(4, o.getShippingName());
			pstmt.setString(5, o.getShippingAddr());
			pstmt.setString(6, o.getShippingPhone());
			pstmt.setString(7, o.getImpUid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int getOrderNo(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int orderNo = 0;
		String query = "select max(order_no) from orders where user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				orderNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return orderNo;
	}
	
	public int insertOrderDetail(Connection conn, int orderNo, Cart c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into ORDERS_DETAIL values(ORDERS_DETAIL_NO_SEQ.nextval, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, c.getBookNo());
			pstmt.setInt(3, c.getCartQuan());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteCart(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from cart where user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Order> selectOrderList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Order> list = new ArrayList<Order>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM (SELECT ORDER_NO, ORDER_DATE, IMP_UID, USER_NAME, TO_CHAR(order_PRICE, '999,999,999') AS WONPRICE, ORDER_STATUS FROM ORDERS JOIN USERS USING (USER_NO) ORDER BY DECODE(ORDER_STATUS, '취소요청', 1), ORDER_NO DESC)A) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Order o = new Order();
				o.setOrderNo(rset.getInt("order_no"));
				o.setOrderDate(rset.getString("order_date"));
				o.setImpUid(rset.getString("imp_uid"));
				o.setUserName(rset.getString("user_name"));
				o.setOrderWonPrice(rset.getString("wonprice"));
				o.setOrderStatus(rset.getString("order_status"));
				list.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public int selectOrderCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) as cnt from orders";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return totalCount;
	}

	public int insertOrderDetail(Connection conn, Order o, int bookNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into ORDERS_DETAIL values(ORDERS_DETAIL_NO_SEQ.nextval, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, o.getOrderNo());
			pstmt.setInt(2, bookNo);
			pstmt.setInt(3, o.getOrderQuan());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateOrderStatus(Connection conn, int orderNo, String orderStatus) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update orders set order_status = ? where order_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, orderStatus);
			pstmt.setInt(2, orderNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
