package io.spring2go.jwtauthserver.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class OrchidPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword == null || encodedPassword.length() == 0) {
			return false;
		}
		
		return rawPassword.equals(encodedPassword);
	}

}
