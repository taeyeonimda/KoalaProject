package koalaTest.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import koalaTest.order.model.vo.Order;
import koalaTest.order.model.vo.OrderDetail;
import koalaTest.user.model.vo.User;



public class UserDao {
	public int insertUser(Connection conn, User u) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into users values(user_no_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ " 'A', 1 ,to_char(sysdate,'yyyy-mm-dd'))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,u.getUserId());
			pstmt.setString(2,u.getUserPw());
			pstmt.setString(3,u.getUserName());
			pstmt.setString(4,u.getUserBirth());
			pstmt.setString(5,u.getUserPhone());
			pstmt.setString(6,u.getUserEmail());
			pstmt.setString(7,u.getUserAddr1());
			pstmt.setString(8,u.getUserAddr2());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//아이디 중복체크용
	public User selectOneUser(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User u = null;
		String query = "select * from users where user_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				u = new User();
				u.setUserId(userId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return u;
	}

	//폰번호 체크용
	public User checkPhone(Connection conn, String userPhone) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User u = null;
		String query = "select * from users where user_phone=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userPhone);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				u = new User();
				u.setUserPhone(userPhone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return u;
	}
	//이메일 중복체크
	public User checkEmail(Connection conn, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User u = null;
		String query = "select * from users where user_email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userEmail);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				u = new User();
				u.setUserEmail(userEmail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return u;
	}

	//로그인용
	public User selectOneUser(Connection conn, User user) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User u = null;
		String query = "select * from users where user_id=? and user_pw=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				u = new User();
				u.setEnrollDate(rset.getString("enroll_date"));
				u.setUserAddr1(rset.getString("user_addr1"));
				u.setUserAddr2(rset.getString("user_addr2"));
				u.setUserBirth(rset.getString("user_birth"));
				u.setUserEmail(rset.getString("user_email"));
				u.setUserId(rset.getString("user_id"));
				u.setUserLevel(rset.getInt("user_level"));
				u.setUserName(rset.getString("user_name"));
				u.setUserNo(rset.getInt("user_no"));
				u.setUserPhone(rset.getString("user_phone"));
				u.setUserPw(rset.getString("user_pw"));
				u.setUserState(rset.getString("user_state"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return u;
	}
	
	public int updateUser(Connection conn, User u) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update users"
					+" set user_pw = ?,"
					+ "    user_phone = ?,"
					+ "    user_email = ?,"
					+ "    user_addr1 = ?,"
					+ "    user_addr2 = ?"
					+ "where user_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,u.getUserPw());
			pstmt.setString(2,u.getUserPhone());
			pstmt.setString(3,u.getUserEmail());
			pstmt.setString(4,u.getUserAddr1());
			pstmt.setString(5,u.getUserAddr2());
			pstmt.setString(6,u.getUserId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<User> selectAllUser(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<User> list = new ArrayList<User>();
		String query = "select * from users order by 1 ";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				User u = new User();
				u.setEnrollDate(rset.getString("enroll_date"));
				u.setUserAddr1(rset.getString("user_addr1"));
				u.setUserAddr2(rset.getString("user_addr2"));
				u.setUserBirth(rset.getString("user_birth"));
				u.setUserEmail(rset.getString("user_email"));
				u.setUserId(rset.getString("user_id"));
				u.setUserLevel(rset.getInt("user_level"));
				u.setUserName(rset.getString("user_name"));
				u.setUserNo(rset.getInt("user_no"));
				u.setUserPhone(rset.getString("user_phone"));
				u.setUserPw(rset.getString("user_pw"));
				u.setUserState(rset.getString("user_state"));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int changeLevel(Connection conn, int userNo, int userLevel) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update users set user_level=? where user_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,userLevel);
			pstmt.setInt(2,userNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<User> selectUserList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<User> list = new ArrayList<>();
		String query = "select * from (select rownum as rnum,  "
				+ " n.* from (SELECT*FROM users order by user_no desc)n) "
				+ " where rnum between ? and ?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				User u = new User();
				u.setEnrollDate(rset.getString("enroll_date"));
				u.setUserAddr1(rset.getString("user_addr1"));
				u.setUserAddr2(rset.getString("user_addr2"));
				u.setUserBirth(rset.getString("user_birth"));
				u.setUserEmail(rset.getString("user_email"));
				u.setUserId(rset.getString("user_id"));
				u.setUserLevel(rset.getInt("user_level"));
				u.setUserName(rset.getString("user_name"));
				u.setUserNo(rset.getInt("user_no"));
				u.setUserPhone(rset.getString("user_phone"));
				u.setUserPw(rset.getString("user_pw"));
				u.setUserState(rset.getString("user_state"));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int selectUserCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) as cnt from users";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalCount;
	}

	public int deleteUser(Connection conn, User u) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update users"
					+" set user_state = 'D' "
					+ "where user_id = ? and user_pw = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,u.getUserId());
			pstmt.setString(2,u.getUserPw());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}	

	public ArrayList<Order> selectMyOrderList(Connection conn, int userNo, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Order> order = new ArrayList<Order>();
		String query = "SELECT * FROM (select rownum as rnum, TO_CHAR(order_PRICE, '999,999,999') AS WONPRICE, o.* from (select * from orders where user_no = ? order by order_no desc)o) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Order o = new Order();
				o.setOrderWonPrice(rset.getString("wonprice"));
				o.setOrderNo(rset.getInt("order_no"));
				o.setUserNo(rset.getInt("user_no"));
				o.setOrderDate(rset.getString("order_date"));
				o.setOrderQuan(rset.getInt("order_quan"));
				o.setOrderPrice(rset.getInt("order_price"));
				o.setOrderStatus(rset.getString("order_status"));
				o.setShippingName(rset.getString("shipping_name"));
				o.setShippingAddr(rset.getString("shipping_addr"));
				o.setShippingPhone(rset.getString("shipping_phone"));
				o.setImpUid(rset.getString("imp_uid"));
				order.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return order;
	}

	public Order selectOrderInfo(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Order o = null;
		String query = "select TO_CHAR(order_PRICE, '999,999,999') AS WONPRICE, o.* from orders o where order_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				o = new Order();
				o.setOrderNo(rset.getInt("order_no"));
				o.setUserNo(rset.getInt("user_no"));
				o.setOrderDate(rset.getString("order_date"));
				o.setOrderQuan(rset.getInt("order_quan"));
				o.setOrderPrice(rset.getInt("order_price"));
				o.setOrderWonPrice(rset.getString("wonprice"));
				o.setOrderStatus(rset.getString("order_status"));
				o.setShippingName(rset.getString("shipping_name"));
				o.setShippingAddr(rset.getString("shipping_addr"));
				o.setShippingPhone(rset.getString("shipping_phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return o;
	}
	
	public ArrayList<OrderDetail> selectOrderDetail(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
		String query = "select book_img, book_name, order_detail_quan, TO_CHAR(BOOK_PRICE, '999,999,999') AS WONPRICE from orders_detail full join book using (book_no) where order_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OrderDetail od = new OrderDetail();
				od.setBookImg(rset.getString("book_img"));
				od.setBookName(rset.getString("book_name"));
				od.setOrderDetailQuan(rset.getInt("order_detail_quan"));
				od.setBookWonPrice(rset.getString("wonprice"));
				orderDetail.add(od);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return orderDetail;
	}

	public int requestCancelOrder(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update orders set order_status = '취소요청' where order_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectMyOrderCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) as cnt from orders where user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
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

	
	public User findId(Connection conn, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User u = null;
		String query = "select user_id, user_email from users where user_email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userEmail);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				u = new User();
				u.setUserId(rset.getString("user_id"));
				u.setUserEmail(rset.getString("user_email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return u;
	}

	public User findPw(Connection conn, String userIdVal, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User u = null;
		String query = "select user_pw from users where user_id =? and user_email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userIdVal);
			pstmt.setString(2, userEmail);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				u = new User();
				u.setUserPw(rset.getString("user_pw"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return u;
	}
}
