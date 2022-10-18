package koalaTest.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.order.model.service.OrderService;

/**
 * Servlet implementation class CheckedChangeStatusServlet
 */
@WebServlet(name = "CheckedChangeStatus", urlPatterns = { "/checkedChangeStatus.do" })
public class CheckedChangeStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckedChangeStatusServlet() {
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
		String orderNoArr = request.getParameter("orderNoArr");
		String orderStatusArr = request.getParameter("orderStatusArr");
		// 3. 비즈니스 로직
		OrderService service = new OrderService();
		boolean result = service.checkedChangeStatus(orderNoArr, orderStatusArr);
		// 4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result) {
			request.setAttribute("title", "상태 변경 성공");
			request.setAttribute("msg", "주문 상태가 변경되었습니다.");
			request.setAttribute("icon", "success");
		} else {
			request.setAttribute("title", "상태 변경 실패");
			request.setAttribute("msg", "요청이 처리되지 않았습니다.");
			request.setAttribute("icon", "error");		
		}
		request.setAttribute("loc", "/orderPage.do?reqPage="+reqPage);
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
