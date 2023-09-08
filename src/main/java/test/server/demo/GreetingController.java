package test.server.demo;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}