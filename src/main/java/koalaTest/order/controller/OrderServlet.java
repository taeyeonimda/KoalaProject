package koalaTest.order.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.cart.model.service.CartService;
import koalaTest.cart.model.vo.Cart;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(name = "Order", urlPatterns = { "/order.do" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		// 3. 비즈니스 로직
		CartService service = new CartService();
		ArrayList<Cart> cart = service.selectCart(userNo);
		// 4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/order.jsp");
		request.setAttribute("cart", cart);
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
