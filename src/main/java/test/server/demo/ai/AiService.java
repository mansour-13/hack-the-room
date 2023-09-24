package test.server.demo.ai;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
        // String url = "http://localhost:8090/gpt";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                // Do not share this on the internet please 🤞
                .queryParam("secret", "useaiforthebetter")

                // How long should be the overall result
                .queryParam("maxTokens", "450")

                // Remove cached result for the current prompt
                // .queryParam("invalidateCache", "true")

                // The prompt you want to send to ChatGPT
                .queryParam("prompt", content);

        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // handle error
            return "An error occurred";
        }
    }
}
