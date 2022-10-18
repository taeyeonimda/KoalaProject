package koalaTest.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.qna.model.service.QnaService;
import koalaTest.qna.model.vo.QnaComment;

/**
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet(name = "QnaInsertComment", urlPatterns = { "/qnaInsertComment.do" })
public class QnaInsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaInsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		QnaComment qc = new QnaComment();
		qc.setQnaCommentWriter(request.getParameter("qnaCommentWriter"));
		qc.setQnaRef(Integer.parseInt(request.getParameter("qnaRef")));
		qc.setQnaCommentContent(request.getParameter("qnaCommentContent"));
		
		QnaService service = new QnaService();
		int result = service.insertQnaComment(qc);
		
		// 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "답변 작성 완료");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "답변 작성 실패");
			request.setAttribute("icon", "error");
			
		}
		request.setAttribute("reqPage", reqPage);
		request.setAttribute("loc", "/qnaView.do?qnaNo="+qc.getQnaRef()+"&reqPage="+reqPage);
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
