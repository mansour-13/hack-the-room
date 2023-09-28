package test.server.demo.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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

    // Working to include this PostMapping
    @PostMapping(value = "/produceAHint", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> produceAHint(@RequestBody CodeComparisonRequest request) {
        String promptToEvaluate = "Here is a coding challenge:\n\n" +
                request.getCodeChallenge() +
                "\n\nHere is a proposed solution:\n\n" +
                request.getCode() +
                "Give a hint to the user on what his proposed solution is missing to solve the challenge. The hint should not be longer than 3 sentences.";

        String result = this.aiService.prompt(promptToEvaluate);
        return ResponseEntity.ok(Collections.singletonMap("result", result));
    }


    // Working to include this PostMapping
    @PostMapping(value = "/getBinaryAnswerToCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBinaryAnswerToCode(@RequestBody CodeComparisonRequest request) {
        String promptToEvaluate =
                "Your task is to work like a Java compiler. Here's a coding challenge:\n\n" +
                        request.getCodeChallenge() +
                        "\n\nHere's a proposed solution:\n\n" +
                        request.getCode() +
                        "\n\n1. Answer with a single word: true if correct of false if incorrect. Is the proposed solution returning a valid answer to the coding challenge? Remember, you are a compiler, so syntax is important to consider.\n" +
                        "2. Please provide a kind and constructive explanation for your answer, but not longer than 3 sentences. It optimally should address the most critical part of the code.";

        System.out.println("Coding challenge: " + request.getCodeChallenge());
        System.out.println("Provided code: " + request.getCode());

        String result = this.aiService.prompt(promptToEvaluate);
        System.out.println("AI result: " + result);
        Map<String, Object> resultMap = new HashMap<>();
        Boolean responseBoolean = null;

        if (result.toLowerCase().contains("true")) {
            responseBoolean = true;
        } else if (result.toLowerCase().contains("false")) {
            responseBoolean = false;
        }

        if (responseBoolean != null) {
            int index = result.indexOf(responseBoolean.toString()) + responseBoolean.toString().length();
            String explanation = result.substring(index).trim().replaceFirst("[\\d]\\. ", ""); // remove any single-digit number followed by period and space

            // If the result is false, trim the first 4 characters from the explanation
            if (!responseBoolean) {
                explanation = explanation.substring(5);
            }

            resultMap.put("result", responseBoolean);
            resultMap.put("explanation", explanation);
        } else {
            // Handle cases where the response does not contain a recognizable boolean.
            resultMap.put("result", false);
            resultMap.put("explanation", "Unexpected AI response.");
        }

        System.out.println("Sending response: " + resultMap.toString());
        return ResponseEntity.ok(resultMap);
    }

    // Working to include this PostMapping
    @PostMapping(value = "/getSolutionToChallenge", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSolutionToChallenge(@RequestBody String codeChallenge) {
        String promptToEvaluate = "For this codeChallenge: " + codeChallenge + "\n. Give" +
                " the solution to the challenge without any comments. Indicate a new line with \\n";

        String result = this.aiService.prompt(promptToEvaluate);
        System.out.println("ChatGPT solution: " + result);
        return ResponseEntity.ok(Collections.singletonMap("result", result));
    }



    //        String[] parts = result.split("\n");
//        if (parts.length >= 2) {
//            String booleanResult = parts[0].split("\\.")[1].trim();
//            String explanation = parts[1].split("\\.")[1].trim();
//
//            Map<String, Object> resultMap = new HashMap<>();
//            resultMap.put("result", Boolean.parseBoolean(booleanResult));
//            resultMap.put("explanation", explanation);
//
//            return ResponseEntity.ok(resultMap);
//        }
//        return ResponseEntity.ok(Collections.singletonMap("result", result));
//    }

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

// .replaceAll(Pattern.quote("+"), "%2B")
}
