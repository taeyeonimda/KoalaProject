package koalaTest.cart.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.cart.model.service.CartService;

/**
 * Servlet implementation class DeleteCartServlet
 */
@WebServlet(name = "DeleteCart", urlPatterns = { "/deleteCart.do" })
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCartServlet() {
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
		String delCartArr = request.getParameter("delCartArr");
		// 3. 비즈니스 로직
		CartService service = new CartService();
		boolean result = service.deleteCart(delCartArr);
		// 4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result) {
			request.setAttribute("title", "상품 삭제 성공");
			request.setAttribute("msg", "장바구니에서 상품이 삭제되었습니다.");
			request.setAttribute("icon", "success");
		} else {
			request.setAttribute("title", "상품 삭제 실패");
			request.setAttribute("msg", "요청이 처리되지 않았습니다.");
			request.setAttribute("icon", "error");			
		}
		request.setAttribute("loc", "/cart.do?userNo="+userNo);
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
