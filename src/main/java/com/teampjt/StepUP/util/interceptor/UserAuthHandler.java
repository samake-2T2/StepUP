package com.teampjt.StepUP.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.teampjt.StepUP.command.UserVO;

public class UserAuthHandler implements HandlerInterceptor{

		
	
	
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {

			HttpSession session = request.getSession();
			
			UserVO userVO = (UserVO)session.getAttribute("userVO");
			
			if(userVO == null) { // 로그인이 안된 시점
				
				response.sendRedirect(request.getContextPath() + "/user/login"); // 로그인페이지로 이동
				return false;
				
			} else { // 로그인이 된 시점
				// 포워드방식에 request객체에 값을 저장한다.
				// 컨트롤러에서 사용하는 Model과 동일
				request.setAttribute("menu", request.getRequestURI());
				return true;
			}

		}
		/*
		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			
			
			HttpSession session = request.getSession();
			
			UserVO userVO = (UserVO)session.getAttribute("userVO");
			
			if(userVO == null) { // 로그인이 안된 시점
				
				response.sendRedirect(request.getContextPath() + "/user/login"); // 로그인페이지로 이동
				
				
			} else { // 로그인이 된 시점
				// 포워드방식에 request객체에 값을 저장한다.
				// 컨트롤러에서 사용하는 Model과 동일
				request.setAttribute("menu", request.getRequestURI());
			}

		}
		*/
}
