package test.server.demo.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        String promptToEvaluate = "Evaluate the following code for correctness: " + code + ". Answer with true or false.";

        String result = this.aiService.prompt(promptToEvaluate);
        return ResponseEntity.ok(Collections.singletonMap("result", result));
    }

//    @PostMapping(value = "/getBinaryAnswerToCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getBinaryAnswerToCode(@RequestBody CodeComparisonRequest request) {
//        String promptToEvaluate =
//                "Here's a coding challenge:\n\n" +
//                        request.getCodeChallenge() +
//                        "\n\nHere's a proposed solution:\n\n" +
//                        request.getCode() +
//                        "\n\n----DELIMITER BETWEEN POINTS!----\n" +
//                        "1. Reformat the code without changing the logic.\n" +
//                        "----DELIMITER BETWEEN POINTS!----\n" +
//                        "2. Is the reformatted code an appropriate answer to the solution? Only answer with 'true' or 'false'.";
//
//        System.out.println(request.getCodeChallenge());
//        System.out.println(request.getCode());
//
//        String result = this.aiService.prompt(promptToEvaluate);
//        System.out.println(result);
//        String[] sections = result.split("----DELIMITER BETWEEN POINTS!----");
//
//        // Assuming the AI respects the structure and returns two sections
//        if (sections.length >= 2) {
//            String reformattedCode = sections[0].trim();
//            String validity = sections[1].trim().split("\\.")[1].trim();
//
//            Map<String, Object> resultMap = new HashMap<>();
//            resultMap.put("reformattedCode", reformattedCode);
//            resultMap.put("result", Boolean.parseBoolean(validity));
//
//            return ResponseEntity.ok(resultMap);
//        }
//        return ResponseEntity.badRequest().body("Unexpected response format");
//    }


    // Working to include this PostMapping
    @PostMapping(value = "/getBinaryAnswerToCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBinaryAnswerToCode(@RequestBody CodeComparisonRequest request) {
        String promptToEvaluate =
                "Here's a coding challenge:\n\n" +
                        request.getCodeChallenge() +
                        "\n\nHere's a proposed solution:\n\n" +
                        request.getCode() +
                        "\n\n1. Is the proposed solution correct? Answer with 'true' or 'false'.\n" +
                        "2. Please provide an explanation for your answer. If the solution is incorrect, please provide a hint on how to fix it.";

        System.out.println(request.getCodeChallenge());
        System.out.println(request.getCode());

        String result = this.aiService.prompt(promptToEvaluate);
        System.out.println(result);
        String[] parts = result.split("\n");
        if (parts.length >= 2) {
            String booleanResult = parts[0].split("\\.")[1].trim();
            String explanation = parts[1].split("\\.")[1].trim();

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", Boolean.parseBoolean(booleanResult));
            resultMap.put("explanation", explanation);

            return ResponseEntity.ok(resultMap);
        }
        return ResponseEntity.ok(Collections.singletonMap("result", result));
    }

    // Working to include this PostMapping
    @PostMapping(value = "/getSolutionToChallenge", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSolutionToChallenge(@RequestBody String codeChallenge) {
        String promptToEvaluate = "For this codeChallenge: " + codeChallenge + "\n. Give" +
                " the solution to the challenge without any comments. Indicate a new line with \\n";

        String result = this.aiService.prompt(promptToEvaluate);
        System.out.println(result);
        return ResponseEntity.ok(Collections.singletonMap("result", result));
    }

    //Old version, just to save it
    // Working to include this PostMapping
//    @PostMapping(value = "/evaluateCode", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> evaluateCode(@RequestBody String code) {
//        String promptToEvaluate = "The user is playing an escape room game. the following code is part of a challenge." +
//                "Evaluate the given code for correctness: " + code + ". Give the answer in a style fitting the theme of the game.";
//
//        String result = this.aiService.prompt(promptToEvaluate);
//        return ResponseEntity.ok(Collections.singletonMap("result", result));
//    }

    // Working to include this PostMapping
    @PostMapping(value = "/produceAHint", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> produceAHint(@RequestBody String code) {
        String promptToEvaluate = "For the given code, give the user some intermediate hints on how to solve the riddle: " + code;

        String result = this.aiService.prompt(promptToEvaluate);
        return ResponseEntity.ok(Collections.singletonMap("result", result));
    }
}
