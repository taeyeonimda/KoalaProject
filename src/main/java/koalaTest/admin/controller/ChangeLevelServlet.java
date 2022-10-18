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

/**
 * Servlet implementation class UserRegServlet
 */
@WebServlet(name = "ChangeLevel", urlPatterns = { "/changeLevel.do" })
public class ChangeLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeLevelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
				request.setCharacterEncoding("utf-8");
				//2. 값추출
				int userNo = Integer.parseInt(request.getParameter("userNo"));
				int userLevel = Integer.parseInt(request.getParameter("userLevel"));
				//3. 비즈니스로직
				UserService service = new UserService();
				int result = service.changeLevel(userNo,userLevel);
				//4. 결과처리
				if(result>0) {
					response.sendRedirect("/adminPage.do?reqPage=1");
				}else {
					//변경 실패->alert띄우고 관리자페이지로
					RequestDispatcher view
					= request.getRequestDispatcher("/WEB-INF/views/common.msg.jsp");
					request.setAttribute("title","정보변경실패");
					request.setAttribute("msg", "정보 변경에 실패하였습니다");
					request.setAttribute("loc", "/adminPage.do?reqPage=1");
					view.forward(request, response);
				}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
