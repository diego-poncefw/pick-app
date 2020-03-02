package com.pickapp.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.pickapp.util.TokenUtil;

public class CredentialFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String token = req.getHeader("Authorization");
		if (token != null) {
			try {
				TokenUtil.decodeJWT(token);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("sphare", "sphare",
						Collections.emptyList());

				SecurityContextHolder.getContext().setAuthentication(auth);
			} catch (Exception e) {
				SecurityContextHolder.getContext().setAuthentication(null);
			}

		}
		chain.doFilter(request, response);

	}

}
