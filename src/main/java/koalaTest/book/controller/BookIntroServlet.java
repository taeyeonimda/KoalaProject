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
import koalaTest.book.model.vo.BookReviewPlus;

/**
 * Servlet implementation class BookIntroServlet
 */
@WebServlet(name = "BookIntro", urlPatterns = { "/bookIntro.do" })
public class BookIntroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookIntroServlet() {
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
		// 3. 비즈니스 로직
		BookService service = new BookService();
		BookReviewPlus brp = service.selectOneBook(bookNo);
		// 4. 결과 처리
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/bookIntro.jsp");
		request.setAttribute("bookIntro", brp.getResult());
		request.setAttribute("avg", brp.getAvg());
		request.setAttribute("selectBookCommentList", brp.getSelectBookCommentList());
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
