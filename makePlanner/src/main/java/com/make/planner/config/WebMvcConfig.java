package com.make.planner.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.make.planner.library.FrontInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
//	@Bean
//	public WebContentInterceptor webContentInterceptor() {
//		WebContentInterceptor webInterceptor = new WebContentInterceptor();
//		webInterceptor.setCacheSeconds(0);
//		webInterceptor.setUseExpiresHeader(true); //Expires 헤더 사용 여부 (기본값: true)
//		webInterceptor.setUseCacheControlHeader(true); //Cache-Control 헤더 사용 여부 (기본값: true)
//		webInterceptor.setUseCacheControlNoStore(true); //Cache 방지 시(cacheSeconds=0)에 Cache-Control 헤더를 사용할 경우 no-store를 함께 설정할지 여부 (기본값: true)
//		webInterceptor.addCacheMapping(CacheControl.noStore().mustRevalidate().proxyRevalidate().cachePrivate(), "/api/**");
//		webInterceptor.addCacheMapping(CacheControl.noCache(), "/");
//		webInterceptor.addCacheMapping(CacheControl.noStore(), "/");
//		
//		return webInterceptor;
//		
//	}
	
//	@Bean
//	public WebContentInterceptor webContentInterceptor() {
//		WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
//		webContentInterceptor.addCacheMapping(CacheControl.noStore().mustRevalidate().proxyRevalidate().cachePrivate(), "/api/**");
//		webContentInterceptor.addCacheMapping(CacheControl.noCache(), "/hello");
//		webContentInterceptor.addCacheMapping(CacheControl.noStore(), "/hello");
//		return webContentInterceptor;
//	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		List<String> ignoreList = new ArrayList<>();
		
		ignoreList.add("/css/**");
		ignoreList.add("/js/**");
		ignoreList.add("/img/**");
		ignoreList.add("/lib/**");	
		ignoreList.add("/bootstrap/**");
		ignoreList.add("/fullcalendar/**");
		
//		registry.addInterceptor(webContentInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new FrontInterceptor())
//        		.excludePathPatterns(ignoreList.stream().map(Ignore::getUrl).collect(Collectors.toList())
        		.excludePathPatterns(ignoreList.stream().collect(Collectors.toList()))
                .addPathPatterns("/","/hello/**");
        
    }
	
	// 캐시 : https://vaert.tistory.com/179

}
