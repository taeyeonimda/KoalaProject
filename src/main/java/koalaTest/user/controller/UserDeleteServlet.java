package koalaTest.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koalaTest.user.model.service.UserService;
import koalaTest.user.model.vo.User;

/**
 * Servlet implementation class UserRegServlet
 */
@WebServlet(name = "UserDelete", urlPatterns = { "/userDelete.do" })
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDeleteServlet() {
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
		request.setCharacterEncoding("utf-8");
		// 2. 값추출
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
				// 3. 비즈니스로직
		User u = new User();
		u.setUserId(userId);
		u.setUserPw(userPw);
	
		UserService service = new UserService();
		int result = service.deleteUser(u);
		// 4. 결과처리
		PrintWriter out = response.getWriter();
		if(result==1){
			out.print(1);
		}else{
			out.print(0);
		}
		// 4-1. 결과처리할 페이지를 지정
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
