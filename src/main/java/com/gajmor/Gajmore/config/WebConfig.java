package com.gajmor.Gajmore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${project.upload.dir}")
	private String dir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // uploads (correct)
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + dir + "/");

        // FIX: serve static files at ROOT
        registry.addResourceHandler(
                "/css/**",
                "/js/**",
                "/images/**",
                "/fonts/**"
        ).addResourceLocations(
                "classpath:/static/css/",
                "classpath:/static/js/",
                "classpath:/static/images/",
                "classpath:/static/fonts/"
        );
    }

}