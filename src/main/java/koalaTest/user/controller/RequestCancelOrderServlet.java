package koalaTest.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.user.model.service.UserService;

/**
 * Servlet implementation class RequestCancelOrderServlet
 */
@WebServlet(name = "RequestCancelOrder", urlPatterns = { "/requestCancelOrder.do" })
public class RequestCancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestCancelOrderServlet() {
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
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		// 3. 비즈니스 로직
		UserService service = new UserService();
		int result = service.requestCancelOrder(orderNo);
		// 4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("title", "취소요청 성공");
			request.setAttribute("msg", "취소가 요청되었습니다.");
			request.setAttribute("icon", "success");
		} else {
			request.setAttribute("title", "취소요청 실패");
			request.setAttribute("msg", "취소 요청 중 문제가 발생했습니다.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/orderDetail.do?orderNo="+orderNo+"&reqPage="+reqPage);
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
