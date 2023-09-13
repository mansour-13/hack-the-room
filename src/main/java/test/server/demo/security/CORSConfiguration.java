package test.server.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Deactivates "Same Origin Policy"
        // Not very safe - ok for not complicating things in final project;)
        registry.addMapping("/**").allowedMethods("*");
    }
}
