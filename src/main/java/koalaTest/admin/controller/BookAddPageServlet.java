package koalaTest.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import koalaTest.user.model.service.UserService;
import koalaTest.user.model.vo.User;
import koalaTest.user.model.vo.UserPageData;

/**
 * Servlet implementation class UserRegServlet
 */
@WebServlet(name = "BookAdd", urlPatterns = { "/admin/bookAdd.do" })
public class BookAddPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookAddPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 인코딩
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 값추출

		// 3. 비즈니스로직

		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/book/bookAdd.jsp");
		// 4-1. 결과처리할 페이지를 지정
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
