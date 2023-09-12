package test.server.demo;

public class GreetingResponseDTO {
    private final String message;
    private final String audience;

    public GreetingResponseDTO(String message, String audience) {
        this.message = message;
        this.audience = audience;
    }

    public String getMessage() {
        return message;
    }

    public String getAudience() {
        return audience;
    }
}
