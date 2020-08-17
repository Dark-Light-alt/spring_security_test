package com.securiry;

import com.util.MD5;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserConfig userConfig;

    @Resource
    private LoginSuccessHandel loginSuccessHandel; // 登录成功处理器

    @Resource
    private LoginErrorHandel loginErrorHandel; //登录失败处理器

    @Resource
    private AuthenticationErrorHandel authenticationErrorHandel; // 验证失败处理器

    @Resource
    private AccessErrorHandle accessErrorHandle; // 访问失败处理器

    @Resource
    private QuitSuccessHandler quitSuccessHandler; // 注销成功处理器

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 创建身份证验证者
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // 定义 password 编码器
        daoAuthenticationProvider.setPasswordEncoder(new PasswordEncoder() {
            /**
             * 编码
             * @param charSequence
             * @return
             */
            public String encode(CharSequence charSequence) {
                return MD5.encode(charSequence.toString());
            }

            /**
             * 密码匹配
             * @param charSequence 表单提交的密码
             * @param s 正确的密码
             * @return
             */
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(MD5.encode(charSequence.toString()));
            }
        });

        // 设置 user 详细信息实现
        daoAuthenticationProvider.setUserDetailsService(userConfig);

        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest)
                .permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandel)
                .accessDeniedHandler(accessErrorHandle)
                .and()
                .formLogin()
                .failureHandler(loginErrorHandel)
                .successHandler(loginSuccessHandel)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(quitSuccessHandler)
                .permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable();
    }
}
