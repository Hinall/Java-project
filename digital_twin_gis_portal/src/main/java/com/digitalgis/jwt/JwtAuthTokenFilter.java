package com.digitalgis.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.digitalgis.security.service.UserDetailsServiceImpl;
import com.digitalgis.service.UserService;
import com.digitalgis.utils.LoggerUtil;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtAuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtProvider tokenProvider;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private UserService userService;

	@SuppressWarnings("serial")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, ExpiredJwtException {
		try {

			String jwt = getJwt(request);
			if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
				// VERIFY TOKEN - IF REQUEST HAS AN OLDER TOKEN AFTER USER LOGOUT THEN THROW
				// EXPIRATION ERROR
				String rokenResult = userService.verifyUserToken(jwt);
				JSONObject obj = new JSONObject(rokenResult);
				if (obj.has("data") && obj.getBoolean("data") == false) {
					JwtAuthEntryPoint entryPoint = new JwtAuthEntryPoint();
					entryPoint.commence(request, response, new AuthenticationException("Token is expired.") {

					});
				}

				String userName = tokenProvider.getUserNameFromJwtToken(jwt);
				System.out.println("USER NAME is " + userName);
				UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			LoggerUtil.setError(this.getClass(), "User authentication can't be set -> Message: {}" + e);
		}

		filterChain.doFilter(request, response);

	}

	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}

		return null;
	}
}
