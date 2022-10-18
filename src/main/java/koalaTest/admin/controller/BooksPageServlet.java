package koalaTest.admin.controller;

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
@WebServlet(name = "BooksPage", urlPatterns = { "/adminPage/booksPage.do" })
public class BooksPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BooksPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 값추출
		// 3. 비즈니스로직
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		BookService service = new BookService();
		BookPageData bpd = service.selectBookList(reqPage);

		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/book/booksPage.jsp");
		// 4-1. 결과처리할 페이지를 지정
		request.setAttribute("list", bpd.getList());
		request.setAttribute("pageNavi", bpd.getPageNavi());
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
