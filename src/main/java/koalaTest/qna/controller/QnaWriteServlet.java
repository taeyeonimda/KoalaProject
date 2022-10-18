package koalaTest.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import koalaTest.qna.model.service.QnaService;
import koalaTest.qna.model.vo.Qna;

/**
 * Servlet implementation class QnaWriteServlet
 */
@WebServlet(name = "QnaWrite", urlPatterns = { "/qnaWrite.do" })
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/qna";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,maxSize,"UTF-8", new DefaultFileRenamePolicy());
		
		String qnaTitle = mRequest.getParameter("qnaTitle");
		String qnaWriter = mRequest.getParameter("qnaWriter");
		String qnaContent = mRequest.getParameter("qnaContent");
		
		Qna q = new Qna();
		q.setQnaTitle(qnaTitle);
		q.setQnaWriter(qnaWriter);
		q.setQnaContent(qnaContent);
		
		QnaService service = new QnaService();
		int result = service.insertQna(q);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "문의사항이 등록되었습니다.");
			request.setAttribute("icon", "success");	
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "문의사항 등록중 문제가 발생했습니다.");
			request.setAttribute("icon", "error");	
		}
		request.setAttribute("loc", "/qnaList.do?reqPage=1");
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
