package koalaTest.book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.book.model.service.BookService;
import koalaTest.book.model.vo.BookPageData;

/**
 * Servlet implementation class UserRegServlet
 */
@WebServlet(name = "BookSearch", urlPatterns = { "/search.do" })
public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 값추출
		String keyword = request.getParameter("searchKeyword");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		// 3. 비즈니스로직
		BookService service = new BookService();
		BookPageData bpd = service.searchBookList2(reqPage,keyword);
		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/bookSearchResult.jsp");
		request.setAttribute("books", bpd.getList());
		request.setAttribute("pageNavi", bpd.getPageNavi());
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
