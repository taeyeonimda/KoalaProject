package koalaTest.book.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.book.model.service.BookService;
import koalaTest.book.model.vo.Book;
import koalaTest.cart.model.vo.Cart;

/**
 * Servlet implementation class InsertCartServlet
 */
@WebServlet(name = "InsertCart", urlPatterns = { "/insertCart.do" })
public class InsertCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 값 추출
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int bookCount = Integer.parseInt(request.getParameter("bookCount"));
		// 3. 비즈니스 로직
		BookService service = new BookService();
		Cart c = new Cart();
		c.setBookNo(bookNo);
		c.setUserNo(userNo);
		c.setCartQuan(bookCount);
		int result = service.insertCart(c);
		// 4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "장바구니 담기 완료");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/cart.do?userNo="+userNo);
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "장바구니 담기 실패");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/bookIntro.do?bookNo="+bookNo);
		}
	
		view.forward(request, response);
		
		/*
		 // 1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 값 추출
		String bookCate = request.getParameter("bookCate");
		// 3. 비즈니스 로직
		BookService service = new BookService();
		ArrayList<Book> book = service.selectBook(bookCate);
		// 4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/book.jsp");
		request.setAttribute("book", book);
		view.forward(request, response);
		 */
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
