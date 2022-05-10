package com.teampjt.StepUP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.teampjt.StepUP.util.interceptor.UserAuthHandler;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
	
	// webMvcConfigurer의 메서드를 오버라이딩
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 로그인 프리핸들러
		registry.addInterceptor(userAuthHandler())
		.addPathPatterns("/user/**") // user페이지들에 적용
		.addPathPatterns("/group/**") // group페이지들에 적용
		.addPathPatterns("/board/**") // board페이지들에 적용
		.excludePathPatterns("/user/login") // 패스에서 제외
		.excludePathPatterns("/main") // 패스에서 제외
		.excludePathPatterns("/user/userJoin") // 가입요청 패스에서 제외
		.excludePathPatterns("/user/joinForm")
		
		.excludePathPatterns("/user/logout") // 패스에서 제외
		.excludePathPatterns("/board/freeboard_main"); // 패스에서 제외
		

		
	}
	
}
