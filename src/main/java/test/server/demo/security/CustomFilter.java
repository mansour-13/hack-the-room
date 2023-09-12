package test.server.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Map;

public class CustomFilter extends AbstractAuthenticationProcessingFilter {

    protected CustomFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username, password;
        System.err.println("Catching POST request");

        try {
            Map<String, String> requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            username = requestMap.get("userName");
            password = requestMap.get("password");
            System.out.println("username = " + username);
            System.out.println("password = " + password);
        } catch (IOException e) {
            System.err.println("Exception:" + e.getMessage());
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);
        System.out.println("authRequest = " + authRequest);
        Authentication authenticate = this.getAuthenticationManager().authenticate(authRequest);
        System.out.println("authenticate = " + authenticate);
        System.out.println("authenticate.getCredentials().toString() = " + authenticate.getCredentials().toString());
        return authenticate;
    }

}
