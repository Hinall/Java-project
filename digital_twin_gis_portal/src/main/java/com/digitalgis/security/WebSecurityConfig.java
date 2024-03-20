package com.digitalgis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.digitalgis.jwt.JwtAuthEntryPoint;
import com.digitalgis.jwt.JwtAuthTokenFilter;
import com.digitalgis.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;

	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		return new JwtAuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.authorizeRequests()
//			.antMatchers("/signup").permitAll()
				.antMatchers("/login","/registeruser", "/getImage/**","/getBuildingImage/**", "/getUserSignatureImage/**" ,"/weblogin/**","/generateAnnexure/**","/storeTemporaryPassword/**","/updateWebUserPassword/**","/send_OTP/**","/verify_otp/**","/reset_pasword/**","/get_user_id_by_mail/**","/insertVisitorCount/**","/getcaptcha/**","/verify_captcha/**","/download_file/**","/register_web_user/**").permitAll()
//			.antMatchers("/forgotpassword").permitAll()
//			.antMatchers("/resetPasswordForForgot").permitAll()
//			.antMatchers("/file/**").permitAll()
			.antMatchers("/v2/api-docs/**").permitAll()				//SWAGGER UI RESOURCE
			.antMatchers("/webjars/**").permitAll()					//SWAGGER UI RESOURCE
			.antMatchers("/swagger-resources/**").permitAll()		//SWAGGER UI RESOURCE
			.antMatchers("/configuration/**").permitAll()			//SWAGGER UI RESOURCE
			.antMatchers("/*.html").permitAll()						//SWAGGER UI RESOURCE
			.anyRequest().authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	}

}

