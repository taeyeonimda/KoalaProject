package koalaTest.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.qna.model.service.QnaService;
import koalaTest.qna.model.vo.QnaPageData;

/**
 * Servlet implementation class QnaSearchServlet
 */
@WebServlet(name = "QnaSearch", urlPatterns = { "/qnaSearch.do" })
public class QnaSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 2. 값추출
		String keyword = request.getParameter("qnaSearchKeyword");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		// 3. 비즈니스로직
		QnaService service = new QnaService();
		QnaPageData qpd = service.searchQnaList(reqPage,keyword);
		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/qna/qnaSearchResult.jsp");
		request.setAttribute("qnas", qpd.getList());
		request.setAttribute("pageNavi", qpd.getPageNavi());
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
