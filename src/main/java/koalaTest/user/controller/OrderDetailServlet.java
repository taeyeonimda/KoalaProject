package koalaTest.user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.order.model.vo.Order;
import koalaTest.order.model.vo.OrderDetail;
import koalaTest.order.model.vo.OrderDetailData;
import koalaTest.user.model.service.UserService;

/**
 * Servlet implementation class OrderDetailServlet
 */
@WebServlet(name = "OrderDetail", urlPatterns = { "/orderDetail.do" })
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailServlet() {
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
		OrderDetailData odd = service.selectOrderDetail(orderNo);
		// 4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/user/orderDetail.jsp");
		request.setAttribute("o", odd.getO());
		request.setAttribute("orderDetail", odd.getOrderDetail());
		request.setAttribute("reqPage", reqPage);
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
