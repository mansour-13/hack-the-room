package test.server.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.server.demo.security.MyUserDetailsService;
import test.server.demo.user.User;

@RestController
public class RegistrationController {

    private final MyUserDetailsService userDetailsService;

    public RegistrationController(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User newUser) {
        User user = userDetailsService.saveUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
