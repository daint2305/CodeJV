/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.config;

import com.learning.bean.TestBean;
import com.learning.service.LanguageService;
import com.learning.service.PersonService;
import com.learning.serviceImpl.EnglishLanguageImpl;
import com.learning.serviceImpl.PersonServiceImpl;
import java.nio.charset.Charset;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
/**
 *
 * @author vnpt2
 */
@Configuration
@EnableWebMvc
@ComponentScan({"com.pj.*","com.learning.*"})
public class ApplicationContextConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/bootstrap/**").addResourceLocations("/WEB-INF/bootstrap/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/dist/**").addResourceLocations("/WEB-INF/dist/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/plugins/**").addResourceLocations("/WEB-INF/plugins/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/views/**").addResourceLocations("/WEB-INF/views/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/jQuery/**").addResourceLocations("/WEB-INF/jQuery/")
                .setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/jQuery-ui/**").addResourceLocations("/WEB-INF/jquery-ui-1.12.1.custom/")
                .setCachePeriod(Integer.MAX_VALUE);

    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /*
    * STEP 2 - Create SpringTemplateEngine
    * */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);

        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.addDialect(new LayoutDialect(new GroupingStrategy()));
        return templateEngine;
    }

    /*
    * STEP 3 - Register ThymeleafViewResolver
    * */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(resolver);
    }

    //Cấu hình upload file
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }
    
    /*
    //Cấu hình SessionManager
    @Bean
    SessionManager getSessionManager() {
         return new SessionManager();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSessionManager())
        .addPathPatterns("/**")
        .excludePathPatterns("/resources/**", "/login","/plugins/**","/bootstrap/**","/dist/**" );
     // assuming you put your serve your static files with /resources/ mapping
     // and the pre login page is served with /login mapping
    }

    */
    
    

    @Bean
    @Scope("prototype")
    public TestBean getTestBean() {
        
        System.out.println("Create Test Bean");
        TestBean s = new TestBean();
        return s;
    }

}
