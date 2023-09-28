package test.server.demo.ai;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class AiService {
    private final RestTemplate restTemplate;

    public AiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String prompt(String content) {
        String url = "https://ai.bulbt.com/gpt";
//         String url = "http://localhost:8090/gpt";
        // Do not share this on the internet please 🤞
        final String secret = "useaiforthebetter";
        // How long should be the overall result
        final Integer maxTokens = 450;
        // Remove cached result for the current prompt
        final Boolean invalidateCache = true;
        // The prompt you want to send to ChatGPT
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/json;charset=UTF-8")); // Ensure it's using UTF-8 charset

        PromptRequest request = new PromptRequest(content, secret, invalidateCache, maxTokens);
        System.out.println("Prompt request: " + request);

        HttpEntity<PromptRequest> entity = new HttpEntity<>(new PromptRequest(content, secret, invalidateCache, maxTokens), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // handle error
            return "An error occurred";
        }
    }
}
