package test.server.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.server.demo.security.User;
import test.server.demo.security.UserRepository;

import java.util.Optional;

@RestController
public class GreetingController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public GreetingResponseDTO getHome(){
        return new GreetingResponseDTO("Hi from backend","frontend");
    }

    @GetMapping("/greeting")
    public GreetingResponseDTO getGreeting(){
        Optional<User> test = userRepository.findByUserName("newuser");
        if(test.isPresent()){
            System.out.println("Print out roles");
            System.out.println(test.get().getUserName());
            System.out.println("test.get().getRoles() = " + test.get().getRoles());
        }else {
            System.err.println("User with name not found:" + "newuser");
        }

        return new GreetingResponseDTO("Hi from backend","frontend");
    }

    @GetMapping("/greeting-user")
    public GreetingResponseDTO getGreetingUser(){
        return new GreetingResponseDTO("You've found me ...","frontend::user");
    }

    @GetMapping("/greeting-admin")
    public GreetingResponseDTO getGreetingAdmin(){
        return new GreetingResponseDTO("You've found me ...","frontend::admin");
    }
}