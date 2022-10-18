package koalaTest.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.JDBCTemplate;
import koalaTest.book.model.vo.Book;
import koalaTest.book.model.vo.BookRank;
import koalaTest.book.model.vo.BookReview;
import koalaTest.cart.model.vo.Cart;
import koalaTest.order.model.vo.OrderDetail;

public class BookDao {

	public ArrayList<Book> seleteBook(Connection conn, String bookCate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> book = new ArrayList<Book>();
		String query = "SELECT BOOK_NO, BOOK_IMG, BOOK_NAME, BOOK_PRICE, TO_CHAR(BOOK_PRICE, '999,999,999') AS WONPRICE FROM BOOK WHERE BOOK_CATE = ? and book_state ='A' order by book_no desc";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookCate);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setBookImg(rset.getString("book_img"));
				b.setBookName(rset.getString("book_name"));
				b.setBookPrice(rset.getInt("book_price"));
				b.setBookWonPrice(rset.getString("wonprice"));
				//b.setBookCate(rset.getString("book_cate"));
				book.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return book;
	}

	public Book selectOneBook(Connection conn, int bookNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Book bo = null;
		String query = "select TO_CHAR(book_PRICE, '999,999,999') AS WONPRICE, b.* from book b where book_no = ?";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				bo = new Book();
				bo.setBookNo(rset.getInt("book_no"));
				bo.setBookName(rset.getString("book_name"));
				bo.setBookWriter(rset.getString("book_writer"));
				bo.setBookPub(rset.getString("book_pub"));
				bo.setBookPubdate(rset.getString("book_pubdate"));
				bo.setBookImg(rset.getString("book_img"));
				bo.setBookPrice(rset.getInt("book_price"));
				bo.setBookWonPrice(rset.getString("wonprice"));
				bo.setBookCate(rset.getString("book_cate"));
				bo.setBookInfo(rset.getString("book_info"));
				bo.setBookInfo2(rset.getString("book_info2"));
				bo.setBookInfoMokcha(rset.getString("book_info_mokcha"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return bo;
	}

	public double starAvg(Connection conn, int bookNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		double avg = 0;
		String query = "select ROUND(avg(br_star), 1) as review_avg from book_review where book_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				avg = rset.getDouble("review_avg");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return avg;
	}

	public int insertBookComment(Connection conn, BookReview br) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into book_review values(?, review_no_seq.nextval, ?, to_char(sysdate,'yyyy-mm-dd'),? ,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, br.getBookNo());
			pstmt.setString(2, br.getBrContent());
			pstmt.setInt(3, br.getBrStar());
			pstmt.setString(4, br.getReviewWriter());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<BookReview> selectBookCommentList(Connection conn, int bookNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookReview> list = new ArrayList<BookReview>();
		String query = "select * from book_review where book_no=? order by review_no";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BookReview br = new BookReview();
				br.setBookNo(rset.getInt("book_no"));
				br.setReviewNo(rset.getInt("review_no"));
				br.setReviewWriter(rset.getString("review_writer"));
				br.setBrContent(rset.getString("br_content"));
				br.setBrDate(rset.getString("br_date"));
				br.setBrStar(rset.getInt("br_star"));
				list.add(br);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	
	
	
	
	
	public int updateBookComment(Connection conn, BookReview br) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update book_review set br_content=? where book_no=? and review_no =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, br.getBrContent());
			pstmt.setInt(2, br.getBookNo());
			pstmt.setInt(3, br.getReviewNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteBookComment(Connection conn, int bookNo,int reviewNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from book_review where book_no=? and review_no =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			pstmt.setInt(2, reviewNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Book> selectBookList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> book = new ArrayList<Book>();
		String query = "select * from (select rownum as rnum,  "
				+ " n.* from (SELECT*FROM book order by book_no desc)n) "
				+ " where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setBookName(rset.getString("book_name"));
				b.setBookWriter(rset.getString("book_writer"));
				b.setBookPub(rset.getString("book_pub"));
				b.setBookPubdate(rset.getString("book_pubdate"));
				b.setBookImg(rset.getString("book_img"));
				b.setBookPrice(rset.getInt("book_price"));
				b.setBookCate(rset.getString("book_cate"));
				b.setBookInfo(rset.getString("book_info"));
				b.setBookState(rset.getString("book_state"));
				book.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return book;
	}

	public int selectBookCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) as cnt from book";
		
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

	public int insertBook(Connection conn, Book book) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into book values(book_no_seq.nextval, ?,?,?,?,?,?,?,?,?,?,'A')";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getBookWriter());
			pstmt.setString(3, book.getBookPub());
			pstmt.setString(4, book.getBookPubdate());
			pstmt.setString(5, book.getBookImg());
			pstmt.setInt(6, book.getBookPrice());
			pstmt.setString(7, book.getBookCate());
			pstmt.setString(8, book.getBookInfo());
			pstmt.setString(9, book.getBookInfo2());
			pstmt.setString(10, book.getBookInfoMokcha());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public boolean searchSameBook(Connection conn, Cart c) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean sameCheck = false; // 같은 책이 있으면 true, 없으면 false
		String query = "select * from cart where book_no = ? and user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, c.getBookNo());
			pstmt.setInt(2, c.getUserNo());
			rset = pstmt.executeQuery();
			if(rset.next()) { // rset 결과가 있으면
				sameCheck = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return sameCheck;
	}
	
	public int insertCart(Connection conn, Cart c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into cart values(cart_no_seq.nextval, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, c.getUserNo());
			pstmt.setInt(2, c.getBookNo());
			pstmt.setInt(3, c.getCartQuan());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateCartQuan(Connection conn, Cart c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update cart set cart_quan = cart_quan+? where book_no = ? and user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, c.getCartQuan());
			pstmt.setInt(2, c.getBookNo());
			pstmt.setInt(3, c.getUserNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<BookRank> getSalesRank(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookRank> salesRank = new ArrayList<BookRank>();
		String query = "select * from (select book_no, book_name, book_img, 판매량, DENSE_RANK() OVER (ORDER BY 판매량 DESC) AS 판매순위 from book b join (select book_no, sum(order_detail_quan) as 판매량 from orders_detail group by book_no)o using (book_no) where book_state = 'A') where 판매순위 between 1 and 3";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BookRank br = new BookRank();
				br.setBookNo(rset.getInt("book_no"));
				br.setBookName(rset.getString("book_name"));
				br.setBookImg(rset.getString("book_img"));
				br.setSalesQuan(rset.getInt("판매량"));
				br.setBookRank(rset.getInt("판매순위"));
				salesRank.add(br);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}		
		return salesRank;
	}

	public ArrayList<BookRank> getStarRank(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookRank> starRank = new ArrayList<BookRank>();
		String query = "select * from (select book_no, book_name, book_img, 평점, DENSE_RANK() OVER (ORDER BY 평점 DESC) AS 평점순위 from book b join (select book_no, ROUND(avg(br_star), 1) as 평점 from book_review group by book_no)br using (book_no) where book_state = 'A') where 평점순위 between 1 and 3";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BookRank br = new BookRank();
				br.setBookNo(rset.getInt("book_no"));
				br.setBookName(rset.getString("book_name"));
				br.setBookImg(rset.getString("book_img"));
				br.setStarAvg(rset.getDouble("평점"));
				br.setBookRank(rset.getInt("평점순위"));
				starRank.add(br);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}		
		return starRank;
	}

	public ArrayList<BookRank> getReviewRank(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookRank> reviewRank = new ArrayList<BookRank>();
		String query = "select * from (select book_no, book_name, book_img, 리뷰수, DENSE_RANK() OVER (ORDER BY 리뷰수 DESC) AS 리뷰수순위 from book b join (select book_no, count(*) as 리뷰수 from book_review group by book_no)br using (book_no) where book_state = 'A') where 리뷰수순위 between 1 and 3";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BookRank br = new BookRank();
				br.setBookNo(rset.getInt("book_no"));
				br.setBookName(rset.getString("book_name"));
				br.setBookImg(rset.getString("book_img"));
				br.setReviewQuan(rset.getInt("리뷰수"));
				br.setBookRank(rset.getInt("리뷰수순위"));
				reviewRank.add(br);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}		
		return reviewRank;
	}


	public ArrayList<Book> searchBookList(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> book = new ArrayList<Book>();
		String query = "select * from book where book_name like '%'||?||'%'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setBookImg(rset.getString("book_img"));
				b.setBookName(rset.getString("book_name"));
				b.setBookPrice(rset.getInt("book_price"));
				b.setBookCate(rset.getString("book_cate"));
				book.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return book;
	}

	public ArrayList<Book> searchBookList2(Connection conn, int start, int end, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> book = new ArrayList<Book>();
		String query = "select * from (select rownum as rnum,  "
				+ " n.* from (SELECT*FROM book where book_name like '%'||?||'%' and book_state = 'A' order by book_name desc)n) "
				+ " where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			DecimalFormat decFormat = new DecimalFormat("###,###");

			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setBookName(rset.getString("book_name"));
				b.setBookWriter(rset.getString("book_writer"));
				b.setBookPub(rset.getString("book_pub"));
				b.setBookPubdate(rset.getString("book_pubdate"));
				b.setBookImg(rset.getString("book_img"));
				b.setBookPrice(rset.getInt("book_price"));
				b.setBookWonPrice(decFormat.format(rset.getInt("book_price")));
				b.setBookCate(rset.getString("book_cate"));
				b.setBookInfo(rset.getString("book_info"));
				book.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return book;
	}

	public int searchBookCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) as cnt from book where book_name like '%'||?||'%' ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
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

	public int changeBookState(Connection conn, int bookNo, String bookState) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update book set book_state=? where book_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,bookState);
			pstmt.setInt(2,bookNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateBook(Connection conn, Book book) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update book set book_name = ?, book_writer = ?, book_pub = ?, book_pubdate = ?, book_img = ?, book_price = ?, book_cate = ?, book_info = ?, book_info2 = ?, book_info_mokcha = ? where book_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getBookWriter());
			pstmt.setString(3, book.getBookPub());
			pstmt.setString(4, book.getBookPubdate());
			pstmt.setString(5, book.getBookImg());
			pstmt.setInt(6, book.getBookPrice());
			pstmt.setString(7, book.getBookCate());
			pstmt.setString(8, book.getBookInfo());
			pstmt.setString(9, book.getBookInfo2());
			pstmt.setString(10, book.getBookInfoMokcha());
			pstmt.setInt(11, book.getBookNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
