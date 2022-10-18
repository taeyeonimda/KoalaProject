package koalaTest.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.book.model.service.BookService;
import koalaTest.book.model.vo.BookReview;
import koalaTest.qna.model.service.QnaService;
import koalaTest.qna.model.vo.QnaComment;

/**
 * Servlet implementation class QnaUpdateCommentServlet
 */
@WebServlet(name = "QnaUpdateComment", urlPatterns = { "/qnaUpdateComment.do" })
public class QnaUpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		int qnaCommentNo = Integer.parseInt(request.getParameter("qnaCommentNo"));
		String qnaCommentContent = request.getParameter("qnaCommentContent");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		QnaComment qc = new QnaComment();
		qc.setQnaCommentNo(qnaCommentNo);
		qc.setQnaCommentContent(qnaCommentContent);
		//3.비즈니스로직
		QnaService service = new QnaService();
		int result = service.qnaUpdateComment(qc);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글이 수정되었습니다.");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글 수정 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("reqPage", reqPage);
		request.setAttribute("loc", "/qnaView.do?qnaNo="+qnaNo+"&"+"reqPage="+reqPage);
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
