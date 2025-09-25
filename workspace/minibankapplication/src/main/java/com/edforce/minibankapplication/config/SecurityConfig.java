package com.edforce.minibankapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	private static final String SECRET_KEY = "MyJwtSecretKey1234567890MyJwtSecretKey";
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
         .csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(auth -> auth
             .requestMatchers("/auth/**").permitAll()
             .anyRequest().authenticated()
         )
         .oauth2ResourceServer(oauth2 -> 
             oauth2.jwt(jwt -> jwt.decoder(jwtDecoder()))
         );
     return http.build();
    }
	
	@Bean
    JwtDecoder jwtDecoder() {
        // Use symmetric key for HS256
        return NimbusJwtDecoder.withSecretKey(
                new javax.crypto.spec.SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256")
        ).build();
    }
}
