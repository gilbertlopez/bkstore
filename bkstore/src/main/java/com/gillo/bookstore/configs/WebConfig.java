package com.gillo.bookstore.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Gilbert Lopez
 *
 * Created on Jan 19, 2021
 */
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		//WebMvcConfigurer.super.addFormatters(registry);
		registry.addFormatter(dateFormatter());
	}
	
    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter("yyyy-MM-dd");
    }

}
