package br.com.mailtodo.security.token;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.mailtodo.model.User;
import io.jsonwebtoken.Claims;
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
			.setIssuer("MailToDo++")				// Who generated the token
			.setSubject(user.getId().toString())	// Unique identifier of the user/subject
			.setIssuedAt(new Date())				// Date of generation
			.setExpiration(expiration)				// Date of expiration
			.signWith(SignatureAlgorithm.HS256, secretKey).compact();	// Encrypt using key and build the token
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Integer getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
		return Integer.parseInt(claims.getSubject());
	}

}
