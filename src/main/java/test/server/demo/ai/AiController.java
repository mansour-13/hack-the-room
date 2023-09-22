package test.server.demo.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
