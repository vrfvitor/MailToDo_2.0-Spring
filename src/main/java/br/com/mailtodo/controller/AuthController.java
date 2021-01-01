package br.com.mailtodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mailtodo.dto.LoginForm;
import br.com.mailtodo.security.token.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginForm form) {
		UsernamePasswordAuthenticationToken userCredentials = form.converter();

		try {
			Authentication authenticate = authManager.authenticate(userCredentials);

			String jwt = tokenService.buildToken(authenticate);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Access-Control-Expose-Headers", "x-access-token"); // Exposes the following header
			responseHeaders.add("x-access-token", jwt); // Contains the Token built

			return ResponseEntity.ok().headers(responseHeaders).build();
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
