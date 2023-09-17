package test.server.demo.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AiController {

    final
    AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }


    @GetMapping("/fortuneCookie")
    public String getResult(@RequestParam(defaultValue = "Xena") String name){
        return this.aiService.prompt(String.format("Create a furtune cookie quote which %s should read. Make it funny and short (max 20 words).", name));
    }

    // Working to include this PostMapping
    @PostMapping(value = "/evaluateCode", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> evaluateCode(@RequestBody String code) {
        String promptToEvaluate = "The user is playing an escape room game. the following code is part of a challenge." +
                "Evaluate the given code for correctness: " + code + ". Give the answer in a style fitting the theme of the game.";

        String result = this.aiService.prompt(promptToEvaluate);
        return ResponseEntity.ok(Collections.singletonMap("result", result));
    }
}
