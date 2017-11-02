package com.empty_stack.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
	
	@Configuration
	public static class ApplicationConfiguration extends WebMvcConfigurerAdapter
	{
		@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry)
		{
			super.addResourceHandlers(registry);
			
	        if (!registry.hasMappingForPattern("/webjars/**"))
	        {
	            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	        }
		}
	}
}
