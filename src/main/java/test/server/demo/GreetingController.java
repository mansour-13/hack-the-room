package test.server.demo;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public GreetingResponseDTO getGreeting(){
        return new GreetingResponseDTO("Hi from backend","frontend");
    }
}