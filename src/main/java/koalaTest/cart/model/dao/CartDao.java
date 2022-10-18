package koalaTest.cart.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import koalaTest.cart.model.vo.Cart;

public class CartDao {

	public ArrayList<Cart> selectCart(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Cart> cart = new ArrayList<Cart>();
		String query = "SELECT BOOK_IMG, BOOK_NAME, CART_QUAN, BOOK_PRICE, TO_CHAR(BOOK_PRICE, '999,999,999') AS WONPRICE, CART_NO, USER_NO, BOOK_NO FROM BOOK LEFT JOIN CART USING (BOOK_NO) WHERE USER_NO = ? ORDER BY CART_NO";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Cart c = new Cart();
				c.setCartNo(rset.getInt("cart_no"));
				c.setUserNo(rset.getInt("user_no"));
				c.setBookNo(rset.getInt("book_no"));
				c.setCartQuan(rset.getInt("cart_quan"));
				c.setBookImg(rset.getString("book_img"));
				c.setBookName(rset.getString("book_name"));
				c.setBookPrice(rset.getInt("book_price"));
				c.setBookWonPrice(rset.getString("wonprice"));
				cart.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return cart;
	}

	public int deleteCart(Connection conn, String cartNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from cart where cart_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cartNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateCart(Connection conn, int cartNo, int updateQuan) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update cart set cart_quan = ? where cart_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, updateQuan);
			pstmt.setInt(2, cartNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
