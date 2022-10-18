package koalaTest.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.MailSender;
import koalaTest.user.model.service.UserService;
import koalaTest.user.model.vo.User;

/**
 * Servlet implementation class UserRegServlet
 */
@WebServlet(name = "FindPw", urlPatterns = { "/findPw.do" })
public class FindPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindPwServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 2. 값추출
		String userIdVal = request.getParameter("userId");
		String userEmail = request.getParameter("userEmail");
		// 3. 비즈니스로직
		UserService service = new UserService();
	
		User user = service.findPw(userIdVal,userEmail);

		// 4. 결과처리
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		if(user!=null) {
			MailSender sender = new MailSender();
			String userPw = user.getUserPw();
			String randomCode = sender.sendMail2(userEmail);
			User u = new User("",userPw,randomCode);
			gson.toJson(u,out);
		}else {
			out.print("null");
		}
		
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
