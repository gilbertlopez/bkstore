package com.gillo.bookstore.configs;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Gilbert Lopez
 *
 * Created on Jan 19, 2021
 */

public class BookConfig implements WebMvcConfigurer {

	/*
	 * @Override public void addFormatters(FormatterRegistry registry) {
	 * //WebMvcConfigurer.super.addFormatters(registry);
	 * registry.addFormatter(dateFormatter()); }
	 * 
	 * @Bean public DateFormatter dateFormatter() { return new
	 * DateFormatter("yyyy-MM-dd"); }
	 */
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

}
