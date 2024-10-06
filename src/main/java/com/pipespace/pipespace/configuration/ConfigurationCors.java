package com.pipespace.pipespace.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ConfigurationCors {

	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true); // Permitir credenciais (cookies, cabeçalhos de autorização)
        config.addAllowedOrigin("*"); // Permitir solicitações de todos os domínios
        config.addAllowedHeader("*"); // Permitir todos os cabeçalhos
        config.addAllowedMethod("*"); // Permitir todos os métodos (GET, POST, PUT, DELETE, etc)
        source.registerCorsConfiguration("/api/send-pdf", config); // Aplicar a configuração a todos os endpoints de socket.io
        return new CorsFilter(source);
    }
}
