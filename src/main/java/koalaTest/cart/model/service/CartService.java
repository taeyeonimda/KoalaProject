package koalaTest.cart.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import koalaTest.cart.model.dao.CartDao;
import koalaTest.cart.model.vo.Cart;

public class CartService {
	
	private CartDao dao;

	public CartService() {
		super();
		dao = new CartDao();
	}

	public ArrayList<Cart> selectCart(int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Cart> cart = dao.selectCart(conn, userNo);
		JDBCTemplate.close(conn);
		return cart;
	}

	public boolean deleteCart(String delCartArr) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(delCartArr, "/"); // 배열을 [/]를 기준으로 나눠 토큰(문자열)로 저장
		boolean result = true; // 레벨 변경이 성공했는지 저장할 변수
		while(sT1.hasMoreTokens()) { // sT1에 토큰이 더 있으면 true
			String cartNo = sT1.nextToken(); // sT1의 토큰 하나(문자열)을 꺼냄
			int changeResult = dao.deleteCart(conn, cartNo);
			if(changeResult == 0) { // dao에서 deleteCart가 실패했다면
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

	public int updateCart(int cartNo, int updateQuan) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateCart(conn, cartNo, updateQuan);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
}
