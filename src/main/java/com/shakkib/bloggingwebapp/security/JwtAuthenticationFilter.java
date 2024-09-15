package com.shakkib.bloggingwebapp.security;

import java.io.IOException;
import java.util.Enumeration;

import com.shakkib.bloggingwebapp.config.SecurityConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	Logger logger = LogManager.getLogger(JwtAuthenticationFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		//		1. get token

		String requestToken = request.getHeader("Authorization");
		Enumeration<String> headerNames = request.getHeaderNames();

		while(headerNames.hasMoreElements())
		{
			System.out.println(headerNames.nextElement());
		}
		// Bearer 2352523sdgsg

		logger.log(Level.INFO,requestToken);

		String username = null;

		String token = null;

		if (requestToken != null && requestToken.startsWith("Bearer")) {

			token = requestToken.substring(7);

			try {
				username = this.jwtTokenHelper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				logger.log(Level.WARN,"Unable to get Jwt token");
			} catch (ExpiredJwtException e) {
				logger.log(Level.WARN,"Jwt token has expired");
			} catch (MalformedJwtException e) {
				logger.log(Level.WARN,"invalid jwt");

			}

		} else {
			logger.log(Level.WARN,"Jwt token does not begin with Bearer");
		}

		// once we get the token , now validate

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (this.jwtTokenHelper.validateToken(token, userDetails)) {
				// shi chal rha hai
				// authentication karna hai

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {
				logger.log(Level.WARN,"Invalid jwt token");
			}

		} else {
			String[] allowedUrls = SecurityConfig.PUBLIC_URLS;
			if(isEndpointAllowed(request)){
				logger.log(Level.WARN,"Endpoint is allowed");
			}else {
				logger.log(Level.WARN,"username is null or context is not null");
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean isEndpointAllowed(HttpServletRequest request) {
		String[] allowedUrls = SecurityConfig.PUBLIC_URLS;
		boolean flag = false;
		for (String s:
				allowedUrls) {
			if(s.contains(request.getRequestURI()) || request.getMethod().equals("GET")){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
