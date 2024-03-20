package com.digitalgis.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.digitalgis.security.service.UserPrinciple;
import com.digitalgis.utils.LoggerUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	private String jwtSecret = "@mN3x$2s(Lmob_API";

//	private int jwtExpiration = 86400;

	public String generateJwtToken(Authentication authentication) {

		UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
		return Jwts.builder().setSubject((userPrincipal.getEmailId())).setIssuedAt(new Date())
				// .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			LoggerUtil.setError(this.getClass(), "Invalid JWT token -> Message: {}" + e);
		} catch (ExpiredJwtException e) {
			LoggerUtil.setError(this.getClass(), "Expired JWT token -> Message: {}" + e);
		} catch (UnsupportedJwtException e) {
			LoggerUtil.setError(this.getClass(), "Unsupported JWT token -> Message: {}" + e);
		} catch (IllegalArgumentException e) {
			LoggerUtil.setError(this.getClass(), "JWT claims string is empty -> Message: {}" + e);
		}

		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public void expireJxtToken(String token) {
		long currentTime = System.currentTimeMillis() + 10000L;
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().setExpiration(new Date(currentTime));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
