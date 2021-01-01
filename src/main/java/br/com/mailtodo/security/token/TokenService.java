package br.com.mailtodo.security.token;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.mailtodo.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${app.jwt.secret}")
	private String secretKey;
	
	@Value("${app.jwt.keep-alive}")
	private Long keepAlive;

	public String buildToken(Authentication authenticate) {
		User user = (User) authenticate.getPrincipal();
		
		Date now = new Date();
		Date expiration = new Date(now.getTime() + keepAlive);
		
		return Jwts.builder()
			.setIssuer("MailToDo")
			.setSubject(user.getId().toString())
			.setIssuedAt(new Date())
			.setExpiration(expiration)
			.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	
}
