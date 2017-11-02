package com.github.kuntian.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.github.kuntian.config.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	/**
	 * 设置用户密码的加密方式为BCrypt加密
	 * 
	 * @return
	 */
	// @Bean
	// public PasswordEncoder passwordEncoder() {
	// return new BCryptPasswordEncoder();
	// }

	/**
	 * 设置用户密码的加密方式为MD5加密
	 * 
	 * @return
	 */
	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();

	}

	// @Bean
	// public JwtAuthenticationTokenFilter authenticationTokenFilterBean()
	// throws Exception {
	// return new JwtAuthenticationTokenFilter();
	// }

	/**
	 * 自定义UserDetailsService，从数据库中读取用户信息
	 * 
	 * @return
	 */
	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 提供in-memory的用户名/密码
		// auth.inMemoryAuthentication().withUser("tiankun971").password("test").roles("USER");

		// 提供自定义的用户获取服务
		auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 允许所有用户访问的路径
				.antMatchers("/css/**", "/images/**", "/js/**", "/json/**", "/layui/**", "/getKaptchaSessionKey",
						"/info")
				.permitAll()
				// 对于获取token的rest api要允许匿名访问
				.antMatchers("/auth/**").permitAll()
				// 对含有特定字符串的URL进行角色验证
				.antMatchers("/admin/**").hasRole("admin")
				// 其他地址的访问均需验证权限
				.anyRequest().authenticated().and().formLogin()
				// 指定登录页是"/login"
				.loginPage("/login").permitAll().failureUrl("/login?error").usernameParameter("username")
				.passwordParameter("password")
				// 登录成功后默认跳转到"/index"
				.defaultSuccessUrl("/index")
				// 退出登录后的默认url是"/login"
				.and().logout().logoutSuccessUrl("/login?logout").permitAll().invalidateHttpSession(true)
				// 登录后记住用户，下次自动登录
				.and().rememberMe().tokenValiditySeconds(3600)
				// 公用access deny page
				.and().exceptionHandling().accessDeniedPage("/common/403.html");
		http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
		// 由于使用的是JWT，我们这里不需要csrf
		http.csrf().disable();
		// 添加JWT filter
		// http.addFilterBefore(authenticationTokenFilterBean(),
		// UsernamePasswordAuthenticationFilter.class);
		// 基于token，所以不需要session(网页版 还是需要的)
		// http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}