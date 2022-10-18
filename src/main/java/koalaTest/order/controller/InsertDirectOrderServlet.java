package koalaTest.order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.order.model.service.OrderService;
import koalaTest.order.model.vo.Order;

/**
 * Servlet implementation class InsertDirectOrderServlet
 */
@WebServlet(name = "InsertDirectOrder", urlPatterns = { "/insertDirectOrder.do" })
public class InsertDirectOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDirectOrderServlet() {
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
		Order o = new Order();
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int orderQuan = Integer.parseInt(request.getParameter("orderQuan"));
		int orderPrice = Integer.parseInt(request.getParameter("orderPrice"));
		String shippingName = request.getParameter("shippingName");
		String shippingPhone = request.getParameter("shippingPhone");
		String shippingAddr = request.getParameter("shippingAddr1") + " " + request.getParameter("shippingAddr2");
		String impUid = request.getParameter("impUid");
		o.setUserNo(userNo);
		o.setOrderQuan(orderQuan);
		o.setOrderPrice(orderPrice);
		o.setShippingName(shippingName);
		o.setShippingPhone(shippingPhone);
		o.setShippingAddr(shippingAddr);
		o.setImpUid(impUid);
		// 3. 비즈니스 로직
		OrderService service = new OrderService();
		int result = service.insertDirectOrder(o, bookNo);
		// 4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("title", "주문 성공");
			request.setAttribute("msg", "주문이 완료되었습니다.");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/orderComplete.do");
		} else {
			request.setAttribute("title", "주문 실패");
			request.setAttribute("msg", "요청이 처리되지 않았습니다.");
			request.setAttribute("icon", "error");	
			request.setAttribute("loc", "/");
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
