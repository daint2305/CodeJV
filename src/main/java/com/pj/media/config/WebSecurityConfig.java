/*
/
package com.pj.media.config;

import com.learning.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

       
        
        // Các User trong Database
        //auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance()); 
         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());  
 
       

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout","/chart").permitAll();

        // Trang /userInfo yêu cầu phải login với vai trò USER hoặc ADMIN.
        // Nếu chưa login, nó sẽ redirect tới trang /login.
        //http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        // For ADMIN only.
        // Trang chỉ dành cho ADMIN
        //http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
        
        http.authorizeRequests().antMatchers("/person/list").access("hasRole('ROLE_PERSON')");
        http.authorizeRequests().antMatchers("/person/").access("hasRole('ROLE_PERSON')");
        http.authorizeRequests().antMatchers("/person/detail").access("hasRole('ROLE_PERSON')");
        http.authorizeRequests().antMatchers("/person/save").access("hasRole('ROLE_PERSON')");
        
        http.authorizeRequests().antMatchers("/autocomplete/").access("hasRole('ROLE_AUTOCOMPLETE')");
        http.authorizeRequests().antMatchers("/autocomplete/getTags").access("hasRole('ROLE_AUTOCOMPLETE')");
        http.authorizeRequests().antMatchers("/autocomplete/postData").access("hasRole('ROLE_AUTOCOMPLETE')");
        
        

        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//

                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

    }

}

*/
