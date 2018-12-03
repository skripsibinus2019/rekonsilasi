package com.rekonsiliasi.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
 
  
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
        // Default..
    }
 
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
 
}