package test.server.demo;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {
    @GetMapping("/")
    public GreetingResponseDTO getHome(){
        return new GreetingResponseDTO("Root route of backend","frontend:not-logged-in");
    }

    @GetMapping("/greeting")
    public GreetingResponseDTO getGreeting(){
        return new GreetingResponseDTO("Hi from backend","frontend:not-logged-in");
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