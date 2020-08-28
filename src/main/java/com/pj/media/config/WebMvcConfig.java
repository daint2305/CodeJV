/*

package com.pj.media.config;


import java.nio.charset.Charset;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
@EnableWebMvc
@ComponentScan("com.pj.media.*")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("/WEB-INF/bootstrap/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/dist/**").addResourceLocations("/WEB-INF/dist/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/plugins/**").addResourceLocations("/WEB-INF/plugins/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/views/**").addResourceLocations("/WEB-INF/views/").setCachePeriod(Integer.MAX_VALUE);
    }
}

*/
