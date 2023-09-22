package test.server.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    private final MyUserDetailsService userDetailsService;

    public SecurityConfiguration(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        // Enable all origins or specify your allowed origins
        configuration.setAllowedOriginPatterns(Arrays.asList("https://*.bulbt.com", "http://localhost:63342", "http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "Credentials"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource()) // Add this line
                .and()
                .csrf().disable()
                .headers(headers -> headers.frameOptions().sameOrigin())
                .formLogin(withDefaults())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/level/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/images/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/score").permitAll()
                        .requestMatchers(HttpMethod.POST, "/score/*/*/*").permitAll()
                        .requestMatchers("/", "/greeting", "/logout").permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers("/fortuneCookie").permitAll()
                        .requestMatchers(HttpMethod.POST, "/evaluateCode").permitAll()
                        .requestMatchers(HttpMethod.POST, "/produceAHint").permitAll()
                        .requestMatchers(HttpMethod.POST, "/getBinaryAnswerToCode").permitAll()
                        .requestMatchers(HttpMethod.POST, "/getSolutionToChallenge").permitAll()
                        .requestMatchers("/greeting-user").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/images/*").hasAnyRole("USER", "ADMIN")

                        .requestMatchers("/greeting-admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService);

        // Do not redirect but return success status
        http
                .formLogin()
                .successHandler((req, res, auth) -> res.setStatus(HttpStatus.NO_CONTENT.value()))
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                // Same for logout
                .and()
                .logout()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT))
                // Generic error response
                // 401-UNAUTHORIZED when anonymous user tries to access protected URLs
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        return http.build();
    }
}
