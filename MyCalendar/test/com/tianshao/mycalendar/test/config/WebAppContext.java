package com.tianshao.mycalendar.test.config;


	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.ComponentScan;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.web.servlet.ViewResolver;
	import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
	import org.springframework.web.servlet.config.annotation.EnableWebMvc;
	import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
	import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
	import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
	import org.springframework.web.servlet.view.InternalResourceViewResolver;
	import org.springframework.web.servlet.view.JstlView;

import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.repository.DAO;
import com.tianshao.mycalendar.services.EventService;
import com.tianshao.mycalendar.servicesImpl.EventServiceImpl;
import com.tianshao.mycalendar.test.mocks.MockDAO;
import com.tianshao.mycalendar.ws.EventController;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

	@Configuration
	@EnableWebMvc
	@ComponentScan(basePackages = {"com.tianshao.mycalendar"})
	public class WebAppContext extends WebMvcConfigurerAdapter {

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	    }

	    @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }

	    @Bean
	    public SimpleMappingExceptionResolver exceptionResolver() {
	        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

	        Properties exceptionMappings = new Properties();

	        exceptionMappings.put("net.petrikainulainen.spring.testmvc.todo.exception.TodoNotFoundException", "error/404");
	        exceptionMappings.put("java.lang.Exception", "error/error");
	        exceptionMappings.put("java.lang.RuntimeException", "error/error");

	        exceptionResolver.setExceptionMappings(exceptionMappings);

	        Properties statusCodes = new Properties();

	        statusCodes.put("error/404", "404");
	        statusCodes.put("error/error", "500");

	        exceptionResolver.setStatusCodes(statusCodes);

	        return exceptionResolver;
	    }

	    @Bean
	    public ViewResolver viewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/WEB-INF/jsp/");
	        viewResolver.setSuffix(".jsp");

	        return viewResolver;
	    }
	    
	    
	    @Bean
	    public EventController eventController(){
	    	return new EventController();
	    }
	    @Bean
	    public EventService eventService(){
	    	
	    	EventService ret=new EventServiceImpl();
	    	return ret;
	    }
	    @Bean
	    public DAO dao() throws IllegalArgumentException, IllegalAccessException{
	    	MockDAO dao=new MockDAO();
	    	dao.setEvents();
	    	return dao;
	    }

	}