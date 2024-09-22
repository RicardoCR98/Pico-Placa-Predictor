package org.stackbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);  // No estamos usando credenciales
        config.addAllowedOrigin("*");  // Permitir solicitudes desde http://localhost
        config.addAllowedHeader("*");  // Permitir todos los encabezados
        config.addAllowedMethod("*");  // Permitir todos los métodos (GET, POST, etc.)
        config.addAllowedMethod("OPTIONS");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);  // Aplicar CORS a todas las rutas
        return new CorsFilter(source);
    }
}