package koalaTest.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.order.model.service.OrderService;
import koalaTest.order.model.vo.Order;
import koalaTest.order.model.vo.OrderPageData;

/**
 * Servlet implementation class OrderPageServlet
 */
@WebServlet(name = "OrderPage", urlPatterns = { "/orderPage.do" })
public class OrderPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPageServlet() {
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
		// 3. 비즈니스 로직
		OrderService service = new OrderService();
		OrderPageData opd = service.selectOrderList(reqPage);
		// 4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/order/orderPage.jsp");
		request.setAttribute("order", opd.getList());
		request.setAttribute("pageNavi", opd.getPageNavi());
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
