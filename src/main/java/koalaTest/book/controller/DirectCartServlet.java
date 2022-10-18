package koalaTest.book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.book.model.service.BookService;
import koalaTest.book.model.vo.Book;
import koalaTest.order.model.vo.OrderDetail;

/**
 * Servlet implementation class DirectCartServlet
 */
@WebServlet(name = "DirectCart", urlPatterns = { "/directCart.do" })
public class DirectCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectCartServlet() {
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
		int bookQuan = Integer.parseInt(request.getParameter("bookCount"));
		// 3. 비즈니스 로직
		BookService service = new BookService();
		Book b = service.getBookInfo(bookNo);
		// 4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/directOrder.jsp");
		request.setAttribute("b", b);
		request.setAttribute("bookQuan", bookQuan);
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
