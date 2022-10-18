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
@WebServlet(name = "AdminPage", urlPatterns = { "/adminPage.do" })
public class AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPageServlet() {
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
				HttpSession session = request.getSession();
				User u = (User)session.getAttribute("u");
				//3. 비즈니스로직
				int reqPage = Integer.parseInt(request.getParameter("reqPage"));
				UserService service = new UserService();
				UserPageData upd = service.selectUserList(reqPage);
				
	
				//4. 결과처리
				if(u.getUserLevel()==2) {
					RequestDispatcher view = 
							request.getRequestDispatcher("/WEB-INF/views/admin/user/adminPage.jsp");
					//4-1. 결과처리할 페이지를 지정
					request.setAttribute("list",upd.getList());
					request.setAttribute("pageNavi",upd.getPageNavi());
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
