package com.mujun.mng.commons.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mujun.mng.commons.model.RestResult;
import com.mujun.mng.model.User;
import com.mujun.mng.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 配置类
 * 设置用户单台登录，单一认证等
 * auth: cj
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    /**
     * 配置忽略的请求
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode","/getVersion","/getConstant");
    }


    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    User hr = (User) authentication.getPrincipal();
                    hr.setPassword(null);
                    RestResult restResult = new RestResult();
                    restResult.setMeta(HttpStatus.OK.value(),"登录成功！");
                    restResult.setInfo(hr);
                    String s = new ObjectMapper().writeValueAsString(restResult);
                    out.write(s);
                    out.flush();
                    out.close();
                }
        );
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    RestResult restResult = new RestResult();
                    restResult.setMeta(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage());
                    if (exception instanceof LockedException) {
                        restResult.setMeta(HttpStatus.INTERNAL_SERVER_ERROR.value(),"账户被锁定，请联系管理员!");
                    } else if (exception instanceof CredentialsExpiredException) {
                        restResult.setMeta(HttpStatus.INTERNAL_SERVER_ERROR.value(),"密码过期，请联系管理员!");
                    } else if (exception instanceof AccountExpiredException) {
                        restResult.setMeta(HttpStatus.INTERNAL_SERVER_ERROR.value(),"账户过期，请联系管理员!");
                    } else if (exception instanceof DisabledException) {
                        restResult.setMeta(HttpStatus.INTERNAL_SERVER_ERROR.value(),"账户被禁用，请联系管理员!");
                    } else if (exception instanceof BadCredentialsException) {
                        restResult.setMeta(HttpStatus.INTERNAL_SERVER_ERROR.value(),"用户名或者密码输入错误，请重新输入!");
                    }
                    out.write(new ObjectMapper().writeValueAsString(restResult));
                    out.flush();
                    out.close();
                }
        );
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/doLogin");
        ConcurrentSessionControlAuthenticationStrategy sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        sessionStrategy.setMaximumSessions(1);
        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
        return loginFilter;
    }

    @Bean
    SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })
                .and()
                .logout()
                .logoutSuccessHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            RestResult restResult = new RestResult();
                            restResult.setMeta(HttpStatus.OK.value(),"注销成功！");
                            out.write(new ObjectMapper().writeValueAsString(restResult));
                            out.flush();
                            out.close();
                        }
                )
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                //没有认证时，在这里处理结果，不要重定向
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            resp.setStatus(401);
                            PrintWriter out = resp.getWriter();
                            RestResult restResult = new RestResult();
//                            RespBean respBean = RespBean.error("访问失败!");
                            restResult.setMeta(HttpStatus.UNAUTHORIZED.value(),"没有权限，访问失败！");
                            if (authException instanceof InsufficientAuthenticationException) {

                                restResult.setMeta(HttpStatus.BAD_REQUEST.value(),"请求失败，请联系管理员!");
                            }
                            out.write(new ObjectMapper().writeValueAsString(restResult));
                            out.flush();
                            out.close();
                        }
                );
        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
            HttpServletResponse resp = event.getResponse();
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(401);
            RestResult restResult = new RestResult();
            restResult.setMeta(HttpStatus.UNAUTHORIZED.value(),"您已在另一台设备登录，本次登录已下线!");
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(restResult));
            out.flush();
            out.close();
        }), ConcurrentSessionFilter.class);
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
