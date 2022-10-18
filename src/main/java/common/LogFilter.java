package common;

import java.io.IOException;

import javax.annotation.ManagedBean;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import koalaTest.user.model.vo.User;
@WebFilter(urlPatterns= {"/adminPage.do",
		"/adminPage/*",
		"/admin/*"})
public class LogFilter implements Filter {	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("log filet init");
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		try {
				User user = (User)httpRequest.getSession().getAttribute("u");
				if(user==null) {
					httpResponse.sendRedirect("/");
					return;
				
			}
			chain.doFilter(httpRequest, httpResponse);
		} catch (Exception e) {
			throw e;
		}finally {
			
		}
		
	}


	@Override
	public void destroy() {
		System.out.println("log filet destroy");
	}

}
