package br.com.mailtodo.security.token;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.mailtodo.model.User;
import br.com.mailtodo.repository.UserRepository;

public class AuthViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository userRepository;

	/**
	 * As this filter is instantiated in the SecurityConfig class, DI is not
	 * available in the its context, then we provide the dependencies through
	 * constructor
	 * 
	 * @param tokenService
	 * @param userRepository
	 */
	public AuthViaTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = extractToken(request);

		if (tokenService.isTokenValid(token)) {
			forceAuthentication(token);
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * Once the token is valid, the user has already been authenticated, so the idea
	 * is to notify SpringSecurity telling it who the user is
	 * 
	 * @param token
	 */
	private void forceAuthentication(String token) {
		User tokenOwner = userRepository.findById(tokenService.getUserId(token)).get();
		UsernamePasswordAuthenticationToken tokenOwnerUser = new UsernamePasswordAuthenticationToken(tokenOwner, null,
				tokenOwner.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(tokenOwnerUser);
	}

	private String extractToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer "))
			return null;

		return authHeader.substring(7, authHeader.length());
	}

}
