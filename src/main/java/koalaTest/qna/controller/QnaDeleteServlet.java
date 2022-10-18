package koalaTest.qna.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.qna.model.service.QnaService;
import koalaTest.qna.model.vo.Qna;

/**
 * Servlet implementation class QnaDeleteServlet
 */
@WebServlet(name = "QnaDelete", urlPatterns = { "/qnaDelete.do" })
public class QnaDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDeleteServlet() {
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
		//3.비즈니스로직
		QnaService service = new QnaService();
		//삭제후 파일을 처리하기 위해 해당 정보를 받음
		Qna q = service.deleteQna(qnaNo);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(q!=null) {
			request.setAttribute("title", "삭제완료");
			request.setAttribute("msg", "삭제가 완료됬습니다");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/qnaList.do?reqPage=1");
		}else {
			request.setAttribute("title", "삭제실패");
			request.setAttribute("msg", "삭제 실패");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/qnaView.do?qnaNo="+qnaNo);
		}
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
