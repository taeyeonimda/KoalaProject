package koalaTest.book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.book.model.service.BookService;



/**
 * Servlet implementation class DeleteBookCommentServlet
 */
@WebServlet(name = "DeleteBookComment", urlPatterns = { "/deleteBookComment.do" })
public class DeleteBookCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		//3.비즈니스로직
		BookService service = new BookService();
		int result = service.deleteBookComment(bookNo,reviewNo);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "삭제 성공");
			request.setAttribute("msg", "댓글이 삭제되었습니다.");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "삭제 실패");
			request.setAttribute("msg", "댓글 삭제 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/bookIntro.do?bookNo="+bookNo);
		view.forward(request, response);
		
		
		/*
		
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "삭제 성공");
			request.setAttribute("msg", "댓글이 삭제되었습니다.");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "삭제 실패");
			request.setAttribute("msg", "댓글 삭제 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/noticeView.do?noticeNo="+noticeNo);
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
