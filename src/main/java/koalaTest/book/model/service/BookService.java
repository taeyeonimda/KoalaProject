package koalaTest.book.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import koalaTest.book.model.dao.BookDao;
import koalaTest.book.model.vo.Book;
import koalaTest.book.model.vo.BookPageData;
import koalaTest.book.model.vo.BookRank;
import koalaTest.book.model.vo.BookRankData;
import koalaTest.book.model.vo.BookReview;
import koalaTest.book.model.vo.BookReviewPlus;
import koalaTest.cart.model.vo.Cart;
import koalaTest.order.model.vo.OrderDetail;

public class BookService {
	
	private BookDao dao;
	
	public BookService() {
		super();
		dao = new BookDao();
	}

	public ArrayList<Book> selectBook(String bookCate) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> book = dao.seleteBook(conn, bookCate);
		JDBCTemplate.close(conn);
		return book;
	}

	public BookReviewPlus selectOneBook(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		Book result = dao.selectOneBook(conn,bookNo);
		double avg = dao.starAvg(conn, bookNo);
		
		ArrayList<BookReview> sbc = dao.selectBookCommentList(conn, bookNo);
		BookReviewPlus brp = new BookReviewPlus(result, avg, sbc);
		JDBCTemplate.close(conn);
		return brp;
	}

	public int insertBookComment(BookReview br) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertBookComment(conn,br);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateBookComment(BookReview br) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateBookComment(conn,br);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteBookComment(int bookNo,int reviewNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteBookComment(conn,bookNo,reviewNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public BookPageData selectBookList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection(); 
		//1.한페이지당 게시물 수 지정 ->10개
		int numPerPage = 10;
		// 요청페이지 1 -> 가장 최신 글 1~10
		// 요청페이지 2 -> 가장 최신 글 11~20
		int end = numPerPage*reqPage;
		int start = end - numPerPage + 1;//20 - 10 +1
		ArrayList<Book> list = dao.selectBookList(conn,start,end);
		//페이징처리
		//전체페이지 수 계산 ->전체 게시물 수 조회
		int totalCount = dao.selectBookCount(conn);
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
			 pageNavi +="<a class='page-item' href='/adminPage/booksPage.do?reqPage="+(pageNo-1)+"'>";
			 pageNavi +="<span class='material-icons'>chevron_left</span>";
			 pageNavi +="</a></li>";
		 }
		 //페이지숫자
		 for(int i =0;i<pageNaviSize;i++) {
			 if(pageNo == reqPage) {
				 pageNavi +="<li>";
				 pageNavi +="<a class='page-item active-page' href='/adminPage/booksPage.do?reqPage="+pageNo+"'>";
				 pageNavi += pageNo;
				 //pageNavi +="<span class='material-icons'>chevron_left</span>";
				 pageNavi +="</a></li>";
			 }else {
				 pageNavi +="<li>";
				 pageNavi +="<a class='page-item' href='/adminPage/booksPage.do?reqPage="+pageNo+"'>";
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
			 pageNavi +="<a class='page-item' href='/adminPage/booksPage.do?reqPage="+pageNo+"'>";
			 pageNavi +="<span class='material-icons'>chevron_right</span>";
			 pageNavi +="</a></li>";
		 }
		 pageNavi += "</ul>";
		 BookPageData bpd = new BookPageData(list,pageNavi);
		 JDBCTemplate.close(conn);
		 
		 return bpd;
	}

	//북 추가
	public int insertBook(Book book) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertBook(conn,book);
		if(result!=0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertCart(Cart c) {
		Connection conn = JDBCTemplate.getConnection();
		boolean sameCheck = dao.searchSameBook(conn,c);
		int result = 0;
		if(!sameCheck) { // sameCheck이 false면 (카트에 있지않은 책이면)
			result = dao.insertCart(conn, c);			
		} else { // sameCheck이 true면 (이미 카트에 있는 책이면) -> 수량만 추가
			result = dao.updateCartQuan(conn, c);
		}
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Book getBookInfo(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		Book b = dao.selectOneBook(conn, bookNo);
		JDBCTemplate.close(conn);
		return b;
	}

	public BookRankData getBookRanking() {
		Connection conn = JDBCTemplate.getConnection();
		// 판매량순위
		ArrayList<BookRank> salesRank = dao.getSalesRank(conn);
		// 평점순위
		ArrayList<BookRank> starRank = dao.getStarRank(conn);
		// 리뷰수
		ArrayList<BookRank> reviewRank = dao.getReviewRank(conn);
		BookRankData brd = new BookRankData(salesRank, starRank, reviewRank);
		JDBCTemplate.close(conn);
		return brd;
	}


	public ArrayList<Book> searchBookList(String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> book = dao.searchBookList(conn, keyword);
		JDBCTemplate.close(conn);
		return book;
	}

	//검색결과 페이징
	public BookPageData searchBookList2(int reqPage, String keyword) {
		Connection conn = JDBCTemplate.getConnection(); 
		//1.한페이지당 게시물 수 지정 ->10개
		int numPerPage = 10;
		// 요청페이지 1 -> 가장 최신 글 1~10
		// 요청페이지 2 -> 가장 최신 글 11~20
		int end = numPerPage*reqPage;
		int start = end - numPerPage + 1;//20 - 10 +1
		ArrayList<Book> list = dao.searchBookList2(conn,start,end,keyword);
		//페이징처리
		//전체페이지 수 계산 ->전체 게시물 수 조회
		int totalCount = dao.searchBookCount(conn,keyword);
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
			 pageNavi +="<a class='page-item' href='/search.do?searchKeword="+keyword+"&reqPage="+(pageNo-1)+"'>";
			 pageNavi +="<span class='material-icons'>chevron_left</span>";
			 pageNavi +="</a></li>";
		 }
		 //페이지숫자
		 for(int i =0;i<pageNaviSize;i++) {
			 if(pageNo == reqPage) {
				 pageNavi +="<li>";
				 pageNavi +="<a class='page-item active-page' href='/search.do?searchKeyword="+keyword+"&reqPage="+pageNo+"'>";
				 pageNavi += pageNo;
				 //pageNavi +="<span class='material-icons'>chevron_left</span>";
				 pageNavi +="</a></li>";
			 }else {
				 pageNavi +="<li>";
				 pageNavi +="<a class='page-item' href='/search.do?searchKeyword="+keyword+"&reqPage="+pageNo+"'>";
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
			 pageNavi +="<a class='page-item' href='/search.do?searchKeyword="+keyword+"reqPage="+pageNo+"'>";
			 pageNavi +="<span class='material-icons'>chevron_right</span>";
			 pageNavi +="</a></li>";
		 }
		 pageNavi += "</ul>";
		 BookPageData bpd = new BookPageData(list,pageNavi);
		 JDBCTemplate.close(conn);
		 
		 return bpd;
	}

	public boolean changeBookState(String num, String state) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		StringTokenizer sT2 = new StringTokenizer(state,"/");
		boolean result = true;
		while(sT1.hasMoreTokens()) {
			int bookNo = Integer.parseInt(sT1.nextToken());
			String bookState = sT2.nextToken();
			int changeResult = dao.changeBookState(conn, bookNo,bookState);
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

	public int changeBookStatus(int bookNo, String bookStatus) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changeBookState(conn, bookNo, bookStatus);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateBook(Book book) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateBook(conn, book);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
