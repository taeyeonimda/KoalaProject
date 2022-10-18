package koalaTest.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.book.model.service.BookService;
import koalaTest.user.model.service.UserService;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet(name = "CheckedBookDelete", urlPatterns = { "/checkedBookDelete.do" })
public class CheckedBookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckedBookDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		String num = request.getParameter("num");
		String state = request.getParameter("state");
		//3. 비즈니스로직
		BookService service = new BookService();
		boolean result = service.changeBookState(num,state);
		//4. 결과처리
		RequestDispatcher view = 
				request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result) {
			request.setAttribute("title", "변경완료" );
			request.setAttribute("msg","요청이 처리 되었습니다.");
			request.setAttribute("icon","success");
		}else {
			request.setAttribute("title", "변경실패" );
			request.setAttribute("msg","요청이 처리 실패하였습니다.");
			request.setAttribute("icon","warning");
		}
		//4-1. 결과처리할 페이지를 지정
		request.setAttribute("loc", "/adminPage/booksPage.do?reqPage=1");
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
