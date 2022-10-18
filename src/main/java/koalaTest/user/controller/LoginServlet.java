package koalaTest.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import koalaTest.user.model.service.UserService;
import koalaTest.user.model.vo.User;

/**
 * Servlet implementation class UserRegServlet
 */
@WebServlet(name = "Login", urlPatterns = { "/login.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 값추출
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		User user = new User();
		user.setUserId(userId);
		user.setUserPw(userPw);
		// 3. 비즈니스로직
		UserService service = new UserService();
		User u = service.selectOneUser(user);
		// 4. 결과처리
		PrintWriter out = response.getWriter();
		if(u==null) {
			out.print(1);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("u", u);
			out.print(0);
		}
		// 4-1. 결과처리할 페이지를 지정
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
