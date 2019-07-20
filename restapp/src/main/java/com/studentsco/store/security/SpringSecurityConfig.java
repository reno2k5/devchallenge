package com.studentsco.store.security;
//
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableAutoConfiguration
public class SpringSecurityConfig /* extends WebSecurityConfigurerAdapter */{
//
//    @Autowired
//    DataSource datasource;
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(datasource)
//                .usersByUsernameQuery("select username, password, enabled from app_user where username=?")
//                .authoritiesByUsernameQuery("select usr.username, ur.app_role from app_user usr inner join user_roles ur on usr.id=ur.user_id where username=?");
//    }
//    
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                //.antMatchers(HttpMethod.GET,"/product/**").hasRole("USER")
//                //.antMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
//                //.antMatchers(HttpMethod.PUT,"/product/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/buy/**").hasAnyRole("USER","ADMIN")
//                .antMatchers(HttpMethod.GET,"/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/**").permitAll()
//                ;
//    }
//    
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        
//        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN","USER")
//                .and().withUser("user").password("password").roles("USER");
//    }
//    
}